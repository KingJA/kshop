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
}
