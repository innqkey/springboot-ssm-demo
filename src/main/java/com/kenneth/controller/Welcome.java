package com.kenneth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.kenneth.service.UserService;

/**
 * @author qinkai
 * @date 2018年8月2日
 */
@RestController
public class Welcome {

    @Autowired
    private UserService userService;
    
    @GetMapping("/index")
    public String index(){
        return "hello world";
    }
    @GetMapping("/selectOne")
    public String selectOne(Integer userId){
        return ResUtils.okRes(userService.selectByUserId(userId));
    }
}
