package com.sy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cck.Article;
import com.cck.Diray;
import com.cck.OpenDirayUser;
import com.cck.User;
import com.google.common.collect.Lists;
import com.object.resp.BaseResp;
import com.object.resp.LoginResp;
import com.object.resp.UserMsgAndDiray;
import com.sy.jwt.JwtUtil;
import com.sy.service.IDirayService;
import com.sy.service.IUserService;
import com.sy.util.ImageUtil;
import com.sy.util.RespUtil;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author cck
 */
@Slf4j
@RestController
public class UserController extends BaseController {

    @Reference
    private IUserService userService;

    @Reference
    private IDirayService dirayService;

    @Autowired
    private RespUtil util;

    @Autowired
    private ImageUtil imageUtil;

    @RequestMapping(value = "/register")
    public BaseResp register(String email, String pwd, String nickname) {

        userService.register(email, pwd, nickname);
        return success();
    }

    @RequestMapping(value = "/login")
    public BaseResp login(String email, String pwd) {

        User user = userService.login(email, pwd);
        String token = JwtUtil.createToken(user.getId());

        LoginResp resp = LoginResp.builder()
            .token(token)
            .userId(user.getId())
            .build();

        log.info("user[{}] login success", user.getId());
        return success(resp);
    }

    @RequestMapping(value = "/myIndex")
    public BaseResp userIndex(Integer writerId) {

        User user = userService.getById(writerId);
        List<Diray> dirays =
                dirayService.getByWriteTime(writerId, user.getLastDirayDate());

        UserMsgAndDiray resp = util.getUserIndexResp(user, dirays);
        return success(resp);
    }

    @RequestMapping(value = "/update")
    public BaseResp update(String nickname, Integer userId,
            String pwd, Integer isOpen, String signature,
            String avator) {

        User user = User.builder()
            .id(userId)
            .nickname(nickname)
            .signature(signature)
            .password(pwd)
            .avator(avator)
            .isOpen(isOpen)
            .build();
        userService.update(user);
        return success();
    }

    @RequestMapping(value = "/userMsg")
    public BaseResp userMsg(Integer userId) {

        User user = userService.getById(userId);
        return success(user);
    }

    @RequestMapping(value = "/uploadPic")
    public BaseResp uploadPic(MultipartFile file) {

        String path = imageUtil.save(file);
        return success(path);
    }

    @RequestMapping(value = "/randomPic")
    public BaseResp getRandomPic() {

        return success(userService.getRandomPic());
    }

    @RequestMapping(value = "/getOpenDirayUser")
    public BaseResp getOpenDirayUser(Integer userId) {

        List<OpenDirayUser> openDirayUsers
            = userService.getOpenDirayUser(userId);
        return success(Lists.partition(openDirayUsers, 5));
    }
    
    @RequestMapping(value = "/getArticle")
    public BaseResp getArticle(Integer userId) {
        
        Article article = userService.getArticle(userId);
        return success(article);
    }

}
