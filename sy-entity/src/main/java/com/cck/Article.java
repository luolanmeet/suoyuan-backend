package com.cck;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 每日一文 
 * @author cck
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String url;
    private String title;
    private String author;
    private String content;
    private Double emotionVal;
}
