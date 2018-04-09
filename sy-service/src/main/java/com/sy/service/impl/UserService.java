package com.sy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.cck.Article;
import com.cck.OpenDirayUser;
import com.cck.User;
import com.object.code.ErrorCode;
import com.object.exception.ErrorCodeException;
import com.sy.mapper.ArticleMapper;
import com.sy.mapper.UserMapper;
import com.sy.service.IUserService;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author cck
 */
@Slf4j
@Service
@Component
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private ArticleMapper articleMapper;
    
    public final static ThreadLocal<SimpleDateFormat> FORMATTER
        = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    
    @Override
    public User login(String email, String pwd)
            throws ErrorCodeException {

        User user = userMapper.getByEmailAndPwd(email, pwd);
        if (user == null) {
            throw new ErrorCodeException(ErrorCode.USER_NO_EXITS, "账户或密码错误");
        }
        
        // 设置用户当前情感值
        // 每天情感值与平衡值 0 之间的距离缩小0.5，直至最终回归0
        String[] date = user.getLastLoginDate().split("-");
        
        Calendar cal = Calendar.getInstance();
        Integer year  = Integer.valueOf(date[0]);
        Integer month = Integer.valueOf(date[1]);
        Integer day   = Integer.valueOf(date[2]);
        month = month == 0 ? 0 : month - 1;
        cal.set(year, month, day);
        
        long passDay = System.currentTimeMillis();
        passDay = passDay - cal.getTimeInMillis();
        // 24 * 60 * 60 * 1000
        passDay = passDay / 86400000;
        
        Double reduceEmotion = passDay * 0.5;
        Double nowEmotion = 0D;
        
        if (Math.abs(user.getNowEmotion()) > reduceEmotion) {
            
            if (user.getNowEmotion() < 0) {
                nowEmotion = user.getNowEmotion() + reduceEmotion; 
            } else {
                nowEmotion = user.getNowEmotion() - reduceEmotion;
            }
        }
        
        userMapper.updateLastDirayDateAndEmotion(user.getId(), 
                user.getLastDirayDate(), nowEmotion, 
                user.getLastDirayEmotion(), 
                FORMATTER.get().format(new Date()));
        
        log.info("{}", user);
        return user;
    }

    @Override
    public void register(String email, String pwd, String nickname)
            throws ErrorCodeException {

        log.info("req:register emial:{} pwd:{}");

        User user = User.builder()
                .email(email)
                .password(pwd)
                .nickname(nickname)
                .build();
        int save = userMapper.save(user);

        if(save == 0) {
            throw new ErrorCodeException(ErrorCode.EMAIL_HAS_REGISTER, "邮箱已被注册");
        }
    }

    @Override
    public void update(User user) {

        userMapper.update(user);
    }

    @Override
    public User getById(Integer userId) {

        return userMapper.getById(userId);
    }

    @Override
    public List<String> getRandomPic() {

        List<String> pics = userMapper.getRandomPic();

        for (int i = pics.size(); i < UserMapper.INDEX_NEED_PIC_NUM; i++) {
            pics.add("http://localhost:8080/pic/" + i + ".jpg");
        }
        return pics;
    }

    @Override
    public List<OpenDirayUser> getOpenDirayUser(Integer userId) {

        User user = userMapper.getById(userId);
        return userMapper.getOpenDirayUser(user.getNowEmotion());
    }

    @Override
    public Article getArticle(Integer userId) {
        
        User user = userMapper.getById(userId);
        return articleMapper.getArticle(user.getNowEmotion());
    }

}
