package com.kenneth.mapper;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.kenneth.domain.User;

public interface UserMapper extends MyMapper<User> {

    void updateByUserId(@Param("userId")Integer userId, @Param("name")String name);
}