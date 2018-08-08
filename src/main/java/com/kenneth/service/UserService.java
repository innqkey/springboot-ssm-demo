package com.kenneth.service;

import com.common.PageTemp;
import com.github.pagehelper.PageInfo;
import com.kenneth.domain.User;

/**
 * @author qinkai
 * @date 2018年8月2日
 */
public interface UserService {

    public void insertOne(User user);

    public PageInfo<User> selectAll(PageTemp page);

    public User selectOne(Integer userId);

    public void updateById(Integer userId, String name);
}
