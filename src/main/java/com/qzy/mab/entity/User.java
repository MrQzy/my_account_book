package com.qzy.mab.entity;

import lombok.Data;

@Data
public class User {
    private Long id; //编号
    private String username; //用户名
    private String password; //密码
}
