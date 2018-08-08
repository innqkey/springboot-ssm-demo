package com.kenneth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.common.PageTemp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public void insertOne(User user) {
        userMapper.insert(user);
    }

    @Override
    public PageInfo<User> selectAll(PageTemp page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize()); 
        List<User> selectAll = userMapper.selectAll();
        return new PageInfo<User>(selectAll);
    }

    @Override
    @Cacheable(value = "selectOne")
    public User selectOne(Integer userId) {
        User user = new User();
        user.setId(userId);
        return userMapper.selectOne(user);
    }

    @Override
    public void updateById(Integer userId, String name) {
        userMapper.updateByUserId(userId,name);
    }
}
