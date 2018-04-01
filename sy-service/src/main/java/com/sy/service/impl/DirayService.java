package com.sy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.cck.Diray;
import com.object.req.AddDirayReq;
import com.sy.mapper.DirayMapper;
import com.sy.mapper.UserMapper;
import com.sy.service.IDirayService;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author cck
 */
@Slf4j
@Service
@Component
public class DirayService implements IDirayService {

    @Autowired
    private DirayMapper dirayMapper;

    @Autowired
    private UserMapper userMapper;

    public final static ThreadLocal<SimpleDateFormat> FORMATTER
        = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    @Override
    public void add(AddDirayReq req) {

        dirayMapper.save(req.getUserId(), req.getContent());
        userMapper.updateLastDirayDate(req.getUserId(),
                FORMATTER.get().format(new Date()));
        log.info("save diray success");
    }

    @Override
    public List<Diray> getByUserId(Integer userId) {

        return dirayMapper.getByUserId(userId);
    }

    @Override
    public List<Diray> getByWriteTime(Integer userId, String dateTime) {

        return dirayMapper.getByWriteTime(userId, dateTime);
    }

}
