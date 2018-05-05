package com.cck;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 话题
 * @author cck
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Topic implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer userId;
    private String title;
    private String tag;       // 标签
    private String content;
    private Date writeTime;
    private Integer replyNum; // 评论数
}
