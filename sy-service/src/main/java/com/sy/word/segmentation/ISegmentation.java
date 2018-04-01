package com.sy.word.segmentation;

import java.util.List;

/**
 * 分词接口
 * @author cck
 */
public interface ISegmentation {

    List<Word> seg(String text);

}
