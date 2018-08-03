package com.kenneth.service;

import com.common.PageTemp;
import com.github.pagehelper.PageInfo;
import com.kenneth.domain.UserPo;

/**
 * @author qinkai
 * @date 2018年8月2日
 */
public interface UserService {

    public void insertOne(UserPo user);

    public PageInfo<UserPo> selectAll(PageTemp page);

    public UserPo selectOne(Integer userId);
}
