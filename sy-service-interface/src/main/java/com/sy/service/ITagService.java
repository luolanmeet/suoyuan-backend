package com.sy.service;

import java.util.List;

/**
 *
 * @author cck
 */
public interface ITagService {

    void add(Integer createrId, String name);
    List<String> get();
}
