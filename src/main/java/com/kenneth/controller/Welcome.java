package com.kenneth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.PageTemp;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.kenneth.domain.UserPo;
import com.kenneth.mapper.UserMapper;
import com.kenneth.service.UserService;

/**
 * @author qinkai
 * @date 2018年8月2日
 */
@RestController
public class Welcome {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    
    @GetMapping("/index")
    public String index(){
        return "hello world";
    }
    @GetMapping("/selectAll")
    public String selectAll(PageTemp page){
        PageInfo<UserPo> userList = userService.selectAll(page);
        return ResUtils.okRes(userList);
    }
    @GetMapping("/selectOne")
    public String selectOne(Integer userId){
        UserPo selectOne = userService.selectOne(userId);
        return ResUtils.okRes(selectOne);
    }
    
    @PostMapping("/insert")
    public String insert(UserPo user){
        userService.insertOne(user);
        return ResUtils.okRes();
    }
}
