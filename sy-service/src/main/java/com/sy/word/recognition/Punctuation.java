package com.sy.word.recognition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sy.word.util.ResourceLoader;

import lombok.extern.slf4j.Slf4j;

/**
 * 通过标点符号分割
 * @author cck
 */
@Slf4j
public class Punctuation {

    final static String PATH = "classpath:punctuation";

    private static char[] chars = null;

    static {
        init();
    }

    /**
     * 加在标点符号资源
     */
    public static void init() {
        
        List<String> lines = ResourceLoader.load(PATH);

        Set<Character> set = new HashSet<>();
        for (String line : lines) {
            
            if (line.length() == 1) {

                set.add(line.charAt(0));
            } else {

                log.warn("长度不为一的标点符号：{}", line);
            }
        }
        //增加空白字符
        set.add(' ');
        set.add('　');
        set.add('\t');
        set.add('\n');
        set.add('\r');
        List<Character> list = new ArrayList<>();
        list.addAll(set);
        Collections.sort(list);
        int len = list.size();
        chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = list.get(i);
        }
        set.clear();
        list.clear();
    }

    /**
     * 将文本根据标点符号分割
     * @param text
     * @return
     */
    public static List<String> seg(String text) {
        
        List<String> list = new ArrayList<>();
        int start = 0;
        char[] array = text.toCharArray();
        int len = array.length;

        for (int i = 0; i < len; i++) {
            
            char c = array[i];
            if (is(c)) {

                if (i > start) {
                    
                    list.add(text.substring(start, i));
                    // 下一句的开始
                    start = i + 1;
                } else {
                    
                    // 跳过标点符号
                    start++;
                }
            }
        }

        // 最后一句可能没有标点
        if (len - start > 0) {
            list.add(text.substring(start, len));
        }
        return list;
    }

    private static boolean is(char _char) {
        
        // 二分查找
        int index = Arrays.binarySearch(chars, _char);
        return index >= 0;
    }

    public static void main(String[] args) {
        
        log.info("{}", Punctuation.chars);
        String text = "今天的天气，哈哈 哈哈。";
        log.info("{}", Punctuation.seg(text));
    }

}
