package com.sy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.cck.Diray;
import com.cck.User;
import com.object.req.AddDirayReq;
import com.sy.emotion.judge.impl.EmotionJudge;
import com.sy.emotion.object.Emotions;
import com.sy.mapper.DirayMapper;
import com.sy.mapper.UserMapper;
import com.sy.service.IDirayService;
import com.sy.word.segmentation.impl.MaximumMatching;

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
    
    @Autowired
    private MaximumMatching matching;
    
    @Autowired
    private EmotionJudge judge;
    
    public final static ThreadLocal<SimpleDateFormat> FORMATTER
        = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    @Override
    public void add(AddDirayReq req) {

        Emotions emotions = judge.judge(matching.seg(req.getContent()));
        
        String poSentenceDetails = emotions.getEmotionStr(emotions.getPoEmotions());
        String neSentenceDetails = emotions.getEmotionStr(emotions.getNeEmotions());
        
        Diray diray = Diray.builder()
                   .userId(req.getUserId())
                   .content(req.getContent())
                   .emotionVal(emotions.getEmotionVal())
                   .poEmotionVal(emotions.getPoEmotionVal())
                   .neEmotionVal(emotions.getNeEmotionVal())
                   .poCount(emotions.getPoCount())
                   .neCount(emotions.getNeCount())
                   .poSentenceDetails(poSentenceDetails)
                   .neSentenceDetails(neSentenceDetails)
                   .build();
        dirayMapper.save(diray);
        
        String date = FORMATTER.get().format(new Date());
        
        // 设置最后一次写日记的时间、情感值变化 
        User user = userMapper.getById(req.getUserId());
        Double lastDirayEmotion = 0D;
        Double nowEmotion = user.getNowEmotion() + emotions.getEmotionVal();
        
        // 如果是今天的第一篇日记，则重置之前日志的情感值。如果不是，则在原来的基础上修改
        if (user.getLastDirayDate().equals(date)) {
            lastDirayEmotion = user.getLastDirayEmotion() + emotions.getEmotionVal();
        } else {
            lastDirayEmotion = emotions.getEmotionVal();
        }
        
        userMapper.updateLastDirayDateAndEmotion(req.getUserId(), date, 
                nowEmotion, lastDirayEmotion, user.getLastLoginDate());
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
