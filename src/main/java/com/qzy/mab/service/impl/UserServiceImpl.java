package com.qzy.mab.service.impl;

import java.util.List;

import com.qzy.mab.config.AuthenticFactory;
import com.qzy.mab.support.WebContext;
import com.qzy.mab.util.CookiesUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.qzy.mab.entity.user.User;
import com.qzy.mab.exception.MabRemoteBusinessException;
import com.qzy.mab.mapper.UserMapper;
import com.qzy.mab.service.UserService;
import com.qzy.mab.util.RedisUtil;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    private static final String SOLT = "mab666";

    @Override public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override public List<User> findById(Long id) {
        return null;
    }

    @Override public void create(User user) {

    }

    @Override public void delete(Long... ids) {

    }

    @Override public void update(User user) {

    }

    @Override
    public void login(String username, String password) throws MabRemoteBusinessException {
        Preconditions.checkArgument(StringUtils.isNotBlank(username),"用户名不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(password),"密码不能为空");
        User user = userMapper.getByUsername(username);
        if(null == user){
            throw new MabRemoteBusinessException(String.format("登陆失败：用户[%s]不存在"));
        }
        password = DigestUtils.md5Hex(password);
        User userWd = userMapper.getByUsernameAndPassword(username, password);
        if (userWd != null) {
            /**
             * 登陆成功了
             */
            log.info(String.format("用户[%s]登陆成功",username));
        }else{
            /**
             * 登陆失败了(密码不对)
             */
            throw new MabRemoteBusinessException(String.format("用户名或密码不正确"));
        }

    }
}
