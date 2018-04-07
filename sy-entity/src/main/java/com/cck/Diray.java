package com.cck;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author cck
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Diray implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer userId;
    private String content;
    private Date writeTime;
    
    private Double emotionVal;
    private Double poEmotionVal;
    private Double neEmotionVal;
    private Integer poCount;
    private Integer neCount;
    private String poSentences;
    private String neSentences;
    private String poSentenceDetails;
    private String neSentenceDetails;
}
