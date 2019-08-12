package com.qzy.mab.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qzy.mab.entity.BaseEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "username")
    private String username; //用户名

    @Column(name = "password")
    private String password; //密码

    @Column(name = "head")
    private String head; //密码
}
