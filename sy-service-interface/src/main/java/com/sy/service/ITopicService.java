package com.sy.service;

import java.util.List;

import com.cck.Reply;
import com.cck.Topic;
import com.object.resp.community.TopicDetailResp;

/**
 *
 * @author cck
 */
public interface ITopicService {

    void add(Topic topic);
    List<Topic> get();
    TopicDetailResp getTopic(Integer topicId);
    void reply(Reply reply);
}
