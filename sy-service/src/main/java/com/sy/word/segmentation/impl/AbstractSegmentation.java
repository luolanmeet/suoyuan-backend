package com.sy.word.segmentation.impl;

import java.util.List;

import com.sy.word.segmentation.ISegmentation;
import com.sy.word.segmentation.Word;


/**
 * 分词算法抽象类
 * @author cck
 */
public class AbstractSegmentation implements ISegmentation {

    @Override
    public List<Word> seg(String text) {
        return null;
    }

}
