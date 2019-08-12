package com.qzy.mab.service.impl;

import com.qzy.mab.entity.User;
import com.qzy.mab.mapper.UserMapper;
import com.qzy.mab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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
}
