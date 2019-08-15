package com.qzy.mab.mapper;

import com.qzy.mab.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    @Results({
        @Result(property = "id",column = "id"),
        @Result(property = "username",column = "username"),
        @Result(property = "password",column = "password"),
    })
    List<User> findAll();

    @Select("SELECT * FROM user where username = #{username}")
    @Results({
        @Result(property = "id",column = "id"),
        @Result(property = "username",column = "username"),
        @Result(property = "password",column = "password"),
    })
    User getByUsername(@Param("username") String username);

    @Select("SELECT * FROM user where username = #{username} and password = #{password}")
    @Results({
        @Result(property = "id",column = "id"),
        @Result(property = "username",column = "username"),
        @Result(property = "password",column = "password"),
    })
    User getByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

}
