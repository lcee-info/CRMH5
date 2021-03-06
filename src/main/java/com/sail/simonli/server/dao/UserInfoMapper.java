package com.sail.simonli.server.dao;

import com.sail.simonli.server.model.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    UserInfo  loadUserByMobile(String mobile);
    
    UserInfo  loadUserByOpenid(String openid);
}