package com.sy.service;

import java.util.List;

import com.cck.Topic;

/**
 *
 * @author cck
 */
public interface ITopicService {

    void add(Topic topic);
    List<Topic> get();
}
