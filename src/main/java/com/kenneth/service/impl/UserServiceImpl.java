package com.kenneth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenneth.domain.User;
import com.kenneth.mapper.UserMapper;
import com.kenneth.service.UserService;

/**
 * @author qinkai
 * @date 2018年8月2日
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    
    public User selectByUserId(Integer userId){
        User user = new User();
        user.setId(userId);
        User selectPo = userMapper.selectOne(user);
        return selectPo;
    }
}
