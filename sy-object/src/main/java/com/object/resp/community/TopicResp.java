package com.object.resp.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 话题首页
 * @author cck
 */
@Getter
@Setter
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
