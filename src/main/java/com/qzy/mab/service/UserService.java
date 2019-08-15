package com.qzy.mab.service;

import com.qzy.mab.entity.user.User;
import com.qzy.mab.exception.MabRemoteBusinessException;

public interface UserService extends BaseService<User> {
    void login(String username,String password) throws MabRemoteBusinessException;
}
