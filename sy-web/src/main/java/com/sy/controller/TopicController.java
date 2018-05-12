package com.sy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cck.Reply;
import com.cck.Topic;
import com.object.resp.BaseResp;
import com.object.resp.community.TopicIndexResp;
import com.object.resp.community.TopicResp;
import com.sy.service.ITagService;
import com.sy.service.ITopicService;
import com.sy.service.IUserService;

/**
 *
 * @author cck
 */
@RestController
public class TopicController extends BaseController {

    @Reference
    private ITopicService topicService;

    @Reference
    private ITagService tagService;
    
    @Reference
    private IUserService userService;    
    
    @RequestMapping(value = "/createTopic")
    public BaseResp add(
            Integer userId, String title, 
            String content, String tag) {
        
        Topic topic = Topic.builder()
             .userId(userId)
             .title(title)
             .content(content)
             .tag(tag)
             .build();
        topicService.add(topic);
        
        if (tag != null && !"".equals(tag)) {
            tagService.add(userId, tag);
        }
        return success();
    }

    @RequestMapping(value = "/topic") 
    public BaseResp topic() {
        
        List<Topic> topic = topicService.get();
        
        List<Integer> userIds = topic.stream()
                .map(Topic::getUserId)
                .collect(Collectors.toList());
        List<String> avators = userService.getAvator(userIds);
        
        List<TopicResp> topics = new ArrayList<>();
        for (int i = 0; i < topic.size(); i++) {
            
            Topic temp = topic.get(i);
            TopicResp topicResp = TopicResp.builder()
                .topicId(temp.getId())
                .title(temp.getTitle())
                .tag(temp.getTag())
                .replyNum(temp.getReplyNum())
                .avator(avators.get(i))
                .build();
            topics.add(topicResp);
        }
        
        List<String> tags = tagService.get();
        
        return success(new TopicIndexResp(topics, tags));
    }
    
    @RequestMapping(value = "/getTopic") 
    public BaseResp getTopic(Integer topicId) {
        
        return success(topicService.getTopic(topicId));
    }
    
    @RequestMapping(value = "/reply") 
    public BaseResp reply(Reply reply) {
        topicService.reply(reply);
        return success();
    }
    
}
