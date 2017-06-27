package com.kshop.service;

import com.kshop.common.ServerResponse;
import com.kshop.pojo.User;

/**
 * [Des]      :     TODO
 * [Author]   :     KingJA
 * [Date]     :     2017/6/25
 * [email]    :     kingjavip@gmail.com
 */
public interface IUserService {
    ServerResponse<User> login(String username, String password);

    ServerResponse<User> register(User user);

    ServerResponse<String> getForgetQuestion(String username);

    ServerResponse<String> checkForgetAnswer(String username, String quesionn, String answer);

    ServerResponse<String> resetPassword(String username, String newPassword, String token);
}
