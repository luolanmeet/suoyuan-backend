package com.sy.service;

import java.util.List;

import com.cck.Tag;

/**
 *
 * @author cck
 */
public interface ITagService {

    void add(Integer createrId, String name);
    List<Tag> get();
}
