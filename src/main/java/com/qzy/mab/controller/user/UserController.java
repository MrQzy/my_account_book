package com.qzy.mab.controller.user;

import com.qzy.mab.entity.User;
import com.qzy.mab.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("用户管理")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("查询用户")
    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }
}
