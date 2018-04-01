package com.sy.service.util;

import java.util.List;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;

/**
 *
 * @author cck
 */
public class WordCutUtil {

    static List<Word> cutDiray(String diray) {
        List<Word> list = WordSegmenter.segWithStopWords(diray, SegmentationAlgorithm.MaximumMatching);
        return list;
    }

}
