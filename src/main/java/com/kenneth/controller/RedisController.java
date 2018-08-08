package com.kenneth.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.ResUtils;
import com.kenneth.config.RedisUtil;
import com.kenneth.domain.User;
import com.kenneth.service.UserService;

/**
 * @author qinkai
 * @date 2018年8月6日
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private RedisUtil redis;
    
    /**
     * string set
     * @return
     */
    @GetMapping("add")
    public String add(){
        User user = userService.selectOne(1);
        redis.set("test", JSONObject.toJSONString(user));
        return ResUtils.okRes();
    }
    
    /**
     * string get
     * @return
     */
    @GetMapping("get")
    public String get(){
        User parse = JSON.parseObject(redis.get("test"), User.class);
        System.err.println("phone: " + parse.getPhone());
        return ResUtils.okRes(JSONObject.parse(redis.get("test")));
    }
    
    @GetMapping("delete")
    public String delete(){
        redis.delete("test");
        return ResUtils.okRes();
    }
    
    /**
     * list set
     * @return
     */
    @GetMapping("addList")
    public String addList(){
        User user = userService.selectOne(1);
        redis.lLeftPush("name:list", JSONObject.toJSONString(user));
        return ResUtils.okRes();
    }
    
    /**
     * list get
     * @return
     */
    @GetMapping("getList")
    public String getList(){
        List<String> lRange = redis.lRange("name:list", 0, -1);
//        return ResUtils.okRes(JSON.parse(lRange.get(0)));  //返回json对象
//        User user = (User)(JSONObject.parse(lRange.get(0))); //error
        User user = JSON.parseObject(lRange.get(0), User.class); 
        return ResUtils.okRes(JSONObject.parse(lRange.get(0)));
    }
    
    /**
     * hash set
     * @return
     */
    @GetMapping("addHash")
    public String addHash(){
        User user = userService.selectOne(1);
        Map<String, Object>map = new HashMap<>();
        map.put("id", user.getId());
        map.put("cardNo", user.getCardNo());
        map.put("name", user.getName());
        map.put("password", user.getPassword());
        map.put("phone", user.getPhone());
        map.put("userHight", user.getUserHight());
        redis.hPutAll2("name:hash00",map);
//        redis.hPut("name:hash", "hash00", JSONObject.toJSONString(user));
        return ResUtils.okRes();
    }
    
    /**
     * hash get
     * @return
     */
    @GetMapping("acruireHash")
    public Object acruireHash(){
        Object hGet = redis.hGet("name:hash", "hash00");
        return ResUtils.okRes(hGet);
    }
    /**
     * hash get
     * @return
     */
    @GetMapping("getHash")
    public Object getHash(){
        Map<Object, Object> hGetAll = redis.hGetAll("name:hash00");
        for (Map.Entry<Object, Object> entry : hGetAll.entrySet()) {
            System.out.println("key = " + entry.getKey() + " and value = " + entry.getValue());
        }
        return ResUtils.okRes(hGetAll);
    }
}
