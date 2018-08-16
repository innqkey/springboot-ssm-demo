package com.kenneth.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.PageTemp;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.kenneth.domain.User;
import com.kenneth.mapper.UserMapper;
import com.kenneth.service.UserService;

/**
 * @author qinkai
 * @date 2018年8月2日
 */
@RestController
public class WelcomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/index")
    public String index(){
        return "hello world";
    }
    @GetMapping("/selectAll")
    public String selectAll(PageTemp page){
        PageInfo<User> userList = userService.selectAll(page);
        return ResUtils.okRes(userList);
    }
    @GetMapping("/selectOne")
    public String selectOne(Integer userId){
        User selectOne = userService.selectOne(userId);
        return ResUtils.okRes(selectOne);
    }
    
    @PostMapping("/update")
    public String update(Integer userId,String name){
        userService.updateById(userId,name);
        return ResUtils.okRes();
    }
    
    @PostMapping("/insert")
    public String insert(User user){
        userService.insertOne(user);
        return ResUtils.okRes();
    }
    
    @GetMapping("/restTemplate")
    public String restTemplate() throws IOException{
        //根据cookie请求接口 
//        URL url = new URL("http://localhost:8080/api/user/info/getUserBaseInfo?userId=1");
//        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//        connection.setRequestProperty("Cookie", "JSESSIONID=8A4E429516376FFE1C3C7F6E0B5E1CD0; rememberMe=gKLt7N6HoyKmwhIL4uCSIxmqdKLvTM9q5M6ERzodu42uKCXTZai61egsvAi+gTvoTzTdgnX73LLhxiKxAOJ8H4Sqoa3VyyLpK4fy16VtSJTMoecGMmoae0lVe/uAoy/luLU8caD3Ih1Q7JXISEC5gAZBdjfECswmWkJfllr0yCNxJau9t8vUdPiSq1OVZiatVj4x7PRkZHZtronEOVe1/GYedpKnZ2l1V6IypBhJY0ppa7fUs2sXHTUj7kr7gNlDjkWjOCXqlX/WhAQN9d8i2sfecwta2q42jTzK2zi2E1iBdDwhJHrPObN/QcysUOAh1rrO6NjPVH6+uqcUTHkxdsavcLaJAbs2yP52XrMZiYhcyxYj2jezTLuyxiQHK6hF7Q6Ocpn7UeBXkf2/sKwC14gknvWO5TvvSeZEOSXvTdZUbxEGbRgTYero7TLq7X4BCvRojU3Q8g0EP2lodpulYPnbQn82B7FKFzwVbcvbfQrvg6SgxhJ/hjaAWfN3s9sCaPOguyWPcRSkKBCQVg1BJCOl09Apkv9dzokoymEuMQU=");
//        connection.connect();
//        BufferedReader bReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        String str = bReader.readLine().toString();
//        JSONObject parseObject = JSON.parseObject(str);
        
        //url要使用占位符
        Map<String, Object>vars = new HashMap<>();
        vars.put("userId", 1);
        String result = restTemplate.getForObject("http://localhost:80/selectOne?userId={userId}",String.class, vars);
        
        return ResUtils.okRes(result);
    }
}
