package com.object.resp.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 话题首页
 * @author cck
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicResp {
    
    private Integer topicId;
    private String avator;
    private String title;
    private String tag;
    private Integer replyNum;
}
