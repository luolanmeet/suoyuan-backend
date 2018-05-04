package com.sy.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.cck.Topic;
import com.sy.mapper.TopicMapper;
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
    private TopicMapper topicMapper;
    
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

}
