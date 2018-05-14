package com.sy.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.cck.Reply;
import com.cck.Topic;
import com.cck.User;
import com.object.resp.community.ReplyResp;
import com.object.resp.community.TopicDetailResp;
import com.sy.mapper.ReplyMapper;
import com.sy.mapper.TopicMapper;
import com.sy.mapper.UserMapper;
import com.sy.service.ITopicService;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author cck
 */
@Slf4j
@Service
@Component
public class TopicService implements ITopicService {

    @Autowired
    private ReplyMapper replyMapper;
    
    @Autowired
    private TopicMapper topicMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    public final static ThreadLocal<SimpleDateFormat> FORMATTER
        = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    @Override
    public void add(Topic topic) {
        topicMapper.save(topic);
        log.info("add topic success");
    }

    @Override
    public List<Topic> get() {
        return topicMapper.get();
    }

    @Override
    public TopicDetailResp getTopic(Integer topicId) {
        
        Topic topic = topicMapper.getById(topicId);
        Integer userId = topic.getUserId();
        User user = userMapper.getById(userId);
        
        List<Reply> replys = replyMapper.getByTopicId(topicId);
        List<ReplyResp> replyResps = new ArrayList<>(replys.size());
        
        int i = 1;
        for (Reply reply : replys) {
            
            ReplyResp replyResp = ReplyResp.builder()
            .replyId(reply.getId())
            .userId(reply.getFromUserId())
            .content(reply.getContent())
            .avator(reply.getAvator())
            .time(formatTime(reply.getWriteTime()))
            .nickname(reply.getNickname())
            .toUserId(reply.getToUserId())
            .toNickname(reply.getToNickname())
            .no(i++)
            .build();
            
            replyResps.add(replyResp);
        }
        
        return TopicDetailResp.builder()
                .topicId(topicId)
                .userId(userId)
                .avator(user.getAvator())
                .nickname(user.getNickname())
                .title(topic.getTitle())
                .tag(topic.getTag())
                .content(topic.getContent())
                .replys(replyResps)
                .build();
    }
    
    /**
     * 计算过去了多少时间
     * 超过一天就显示多少天前
     * 没超过一天就显示多少小时之前
     * 没超过一小时就显示多少分钟之前
     * @param writeTime
     * @return
     */
    private String formatTime(Date writeTime) {
        
        long nowTime = System.currentTimeMillis();
        long passTime = nowTime - writeTime.getTime(); 
        
        // 60 * 1000
        passTime = passTime / 60_000;
        
        if (passTime / 60 > 0) {
            // 小时
            passTime /= 60; 

            if (passTime > 24) {
                return (passTime / 24) + "天前";
            } else {
                return passTime + "小时前";
            }
        } else {
            return passTime + "分钟前";
        }
        
    }

    @Override
    public void reply(Reply reply) {
        
        replyMapper.save(reply);
        
        if (reply.getToReplyId() != -1) {
            
            String path = replyMapper.getPath(reply.getToReplyId());
            replyMapper.updatePath(reply.getId(), path + reply.getId() + "/");        
        } else {
            replyMapper.updatePath(reply.getId(), reply.getId() + "/");
        }
    }

    @Override
    public List<Topic> getByTag(String tag) {
        
        return topicMapper.getByTag(tag);
    }
}
