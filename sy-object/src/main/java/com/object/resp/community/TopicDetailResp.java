package com.object.resp.community;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 点进去某个话题之后的响应
 * @author cck
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicDetailResp implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer topicId;
    private Integer userId;
    private String nickname;
    private String avator;
    private String title;
    private String tag;
    private String content;
    
    private List<ReplyResp> replys;
}
