package com.sy.word.segmentation.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 从左向右取待切分语句的m个字符作为匹配字段（m是字典中最长的词的长度）
 * 如果成功匹配，则将这个匹配字段做为一个词切分出来。
 * 否则，移除这个匹配字段的最后一个字符，再继续匹配。重复以上过程，直到切分出所有词为止。
 * 正向最大匹配算法
 * @author cck
 */
@Component
public class MaximumMatching extends AbstractSegmentation {

    @Override
    public List<String> segImpl(String text) {
        List<String> result = new ArrayList<>();
        
        // 文本长度
        final int textLen = text.length();
        // 每一次截取的长度，这里采用字段中最长的词的长度。
        int len = DIC.getMaxLength();
        // 文本索引
        int start = 0;
        // 只要有词未分完就一直继续
        while (start < textLen) {
        	if (len > textLen - start) {
        		// 剩下的文本长度小于要截取的长度
        		len = textLen - start;
        	}
        	
        	// 用长度为len的字符串查词
        	while (!DIC.contains(text, start ,len)) {
        		
        		// 没匹配到，则将一个字符作为一个词
        		if (len == 1) {
        			break;
        		}
        		// 长度自减
        		len--;
        	}
        	
        	addWord(result, text, start, len);
        	start += len;
        	len = DIC.getMaxLength();
        }
        
        return result;
    }
    
    public static void main(String[] args) {
    	
    	String text = "哈哈哈，今天也是非常开心的一天呐";
    	MaximumMatching m = new MaximumMatching();
    	System.out.println(m.seg(text).toString());
	}
    
}
