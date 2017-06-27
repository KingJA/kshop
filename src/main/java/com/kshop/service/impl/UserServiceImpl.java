package com.kshop.service.impl;

import com.kshop.common.Const;
import com.kshop.common.ServerResponse;
import com.kshop.common.TokenCache;
import com.kshop.dao.UserMapper;
import com.kshop.pojo.User;
import com.kshop.service.IUserService;
import com.kshop.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

        return ServerResponse.createSuccessMsg("注册成功");
    }

    @Override
    public ServerResponse<String> getForgetQuestion(String username) {
        int resultCout = userMapper.checkUsername(username);
        if (resultCout == 0) {
            return ServerResponse.createError("用户不存在");
        }
        String question = userMapper.selectForgetQuestion(username);
        if (StringUtils.isNotBlank(question)) {
            return ServerResponse.createSuccess(question);
        }
        return ServerResponse.createError("没有忘记问题");
    }

    @Override
    public ServerResponse<String> checkForgetAnswer(String username, String quesionn, String answer) {
        int resultCout = userMapper.checkForgetAnswer(username, quesionn, answer);
        if (resultCout > 0) {
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey("token_" + username, forgetToken);
            return ServerResponse.createSuccess(forgetToken);

        }
        return ServerResponse.createSuccessMsg("忘记密码答案错误");
    }

    @Override
    public ServerResponse<String> resetPassword(String username, String newPassword, String token) {
        if (StringUtils.isBlank(token)) {
            return ServerResponse.createError("参数错误，需要传递token");
        }
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0) {
            return ServerResponse.createError("用户不存在");
        }
        String forgetToken = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if (StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createError("token无效或者过期");
        }

        if (StringUtils.equals(token, forgetToken)) {
            String password = MD5Util.MD5EncodeUtf8(newPassword);
            int updateCount = userMapper.updatePassword(username, password);
            if (updateCount > 0) {
                return ServerResponse.createSuccess("密码重置成功");
            }
        } else {
            return ServerResponse.createError("token错误，请重新获取");
        }
        return ServerResponse.createError("重置密码错误");
    }
}
