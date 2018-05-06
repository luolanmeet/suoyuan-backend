package com.object.resp.community;

import java.util.List;

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
public class TopicIndexResp {
    
    List<TopicResp> topics;
    List<String> tags;
}
