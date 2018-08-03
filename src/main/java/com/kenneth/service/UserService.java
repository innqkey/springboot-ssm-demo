package com.kenneth.service;

import com.kenneth.domain.User;

/**
 * @author qinkai
 * @date 2018年8月2日
 */
public interface UserService {

    public User selectByUserId(Integer userId);
}
