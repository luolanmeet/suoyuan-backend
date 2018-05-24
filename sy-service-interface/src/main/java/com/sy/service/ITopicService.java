package com.sy.service;

import java.util.List;

import com.cck.Notify;
import com.cck.Reply;
import com.cck.Topic;
import com.object.resp.community.ReplyResp;
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
    List<Topic> getByTag(String tag);
    List<ReplyResp> getLookReplys(Integer replyId);
    Integer getNoReadNum(Integer userId);
    List<Notify> getNoReadMsg(Integer userId);
    void updateRead(Integer notifyId);
}
