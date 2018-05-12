package com.sy.word.segmentation.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.sy.word.dictionary.DictionaryTrie;
import com.sy.word.recognition.Punctuation;
import com.sy.word.segmentation.ISegmentation;
import com.sy.word.util.ResourceLoader;

import lombok.extern.slf4j.Slf4j;


/**
 * 分词算法抽象类
 * @author cck
 */
@Slf4j
public abstract class AbstractSegmentation implements ISegmentation {

    // 词典
    protected static final DictionaryTrie DIC = new DictionaryTrie();
    // 字典路径
    private static final String PATH = "classpath:dic";
    // 线程池
    private static ExecutorService EXECUTOR;
    
    static {
        init();
    }
    
    // 初始化词典
    private static void init() {
        List<String> lines = ResourceLoader.load(PATH);
        for (String line : lines) {
            DIC.add(line);
        }
        
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        EXECUTOR = Executors.newFixedThreadPool(availableProcessors);
    }
    
    public abstract List<String> segImpl(String text);

    @Override
    public List<String> seg(String text) {

        List<String> sentences = Punctuation.seg(text);
        // 只有一个句子
        if (sentences.size() == 1) {
            return segSentence(sentences.get(0));
        }
        
        // 使用多线程进行分词
        List<Future<List<String>>> futures = new ArrayList<>(sentences.size());
        for (String sentence : sentences) {
            futures.add(submit(sentence));
        }
        sentences.clear();
        List<String> result = new ArrayList<>();
        
        for (Future<List<String>> future : futures) {
            try {
                List<String> String = future.get();
                
                if (String != null) {
                    result.addAll(String);
                }
            } catch (InterruptedException | ExecutionException e) {
                log.error("获取分词结果失败. {}", e);
            }
            
        }
        
        futures.clear();
        return result;
    }
    
    private Future<List<String>> submit(String sentence) {
        
        return EXECUTOR.submit(new Callable<List<String>>() {

            @Override
            public List<String> call() throws Exception {
                return segSentence(sentence);
            }
            
        });
    }

    // 分割句子
    private List<String> segSentence(String sentence) {

        List<String> list = segImpl(sentence);

        if (list == null) {
            log.info("[{}] 无分词结果");
        }
        return list;
    }
    
    /**
     * 将匹配到的词添加到结果中
     * @param result
     * @param text
     * @param start
     * @param len
     */
    protected void addWord(List<String> result, 
            String text, int start, int len) {
        
        // 不保留空白字符
        if (len > 1) {
            if (isWhiteSpace(text, start, len)) {
                return;
            }
        }
        String word = new String(text.substring(start,  start + len).toLowerCase());
        result.add(word);
    }
    
    private boolean isWhiteSpace(String text, int start, int len){
        
        char c = text.charAt(start);
        return c == ' ' || c == '　' || c == '\t' || c == '\n';
    }
    
}
