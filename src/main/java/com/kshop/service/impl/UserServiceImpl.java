package com.kshop.service.impl;

import com.kshop.common.ServerResponse;
import com.kshop.dao.UserMapper;
import com.kshop.pojo.User;
import com.kshop.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * [Des]      :     TODO
 * [Author]   :     KingJA
 * [Date]     :     2017/6/25
 * [email]    :     kingjavip@gmail.com
 */
@Service("iUserServiceImpl")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        int resutlCout = userMapper.checkUsername(username);
        if (resutlCout == 0) {
            return ServerResponse.createError("用户不存在");
        }
        User user = userMapper.selectUser(username, password);
        if (user == null) {
            return ServerResponse.createError("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);

        return ServerResponse.createSuccess(user);
    }
}
