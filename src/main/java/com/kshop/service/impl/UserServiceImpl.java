package com.kshop.service.impl;

import com.kshop.common.Const;
import com.kshop.common.ServerResponse;
import com.kshop.dao.UserMapper;
import com.kshop.pojo.User;
import com.kshop.service.IUserService;
import com.kshop.util.MD5Util;
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
        User user = userMapper.selectUser(username, MD5Util.MD5EncodeUtf8(password));
        if (user == null) {
            return ServerResponse.createError("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);

        return ServerResponse.createSuccess(user);
    }

    @Override
    public ServerResponse<User> register(User user) {
        int resutlCout = userMapper.checkUsername(user.getUsername());
        if (resutlCout > 0) {
            return ServerResponse.createError("用户已存在");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        user.setRole(Const.Role.ROLE_ADMIN);
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createError("注册失败");
        }

        return ServerResponse.createSuccess("注册成功");
    }
}
