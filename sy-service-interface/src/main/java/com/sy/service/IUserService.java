package com.sy.service;

import java.util.List;

import com.cck.Article;
import com.cck.OpenDirayUser;
import com.cck.User;
import com.object.exception.ErrorCodeException;

/**
 *
 * @author cck
 */
public interface IUserService {

    User login(String email, String pwd) throws ErrorCodeException;
    void register(String email, String pwd, String nickname) throws ErrorCodeException;
    void update(User user);
    User getById(Integer userId);
    List<String> getRandomPic();
    List<OpenDirayUser> getOpenDirayUser(Integer userId);
    Article getArticle(Integer userId);
    List<String> getAvator(List<Integer> userIds);
}
