package com.sy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.sy.mapper.TagMapper;
import com.sy.service.ITagService;

/**
 *
 * @author cck
 */
@Service
@Component
public class TagService implements ITagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public void add(Integer createrId, String name) {
        
        tagMapper.save(createrId, name);
    }

    @Override
    public List<String> get() {
        
        return tagMapper.get();
    }
    
}
