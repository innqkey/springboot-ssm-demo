package com.kenneth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.PageTemp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kenneth.domain.UserPo;
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
    
    public UserPo selectByUserId(Integer userId){
        UserPo user = new UserPo();
        user.setId(userId);
        UserPo selectPo = userMapper.selectOne(user);
        return selectPo;
    }

    @Override
    public void insertOne(UserPo user) {
        userMapper.insert(user);
    }

    @Override
    public PageInfo<UserPo> selectAll(PageTemp page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize()); 
        List<UserPo> selectAll = userMapper.selectAll();
        return new PageInfo<UserPo>(selectAll);
    }

    @Override
    public UserPo selectOne(Integer userId) {
        UserPo user = new UserPo();
        user.setId(userId);
        return userMapper.selectOne(user);
    }
}
