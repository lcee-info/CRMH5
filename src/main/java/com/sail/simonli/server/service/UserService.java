package com.sail.simonli.server.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sail.simonli.server.dao.UserInfoMapper;
import com.sail.simonli.server.model.UserInfo;

 
@Service
@Transactional
public class UserService {
	
    @Autowired
   UserInfoMapper mapper;
 
    public UserInfo loadUserByMobile(String s) {
    	
    	UserInfo user = mapper.loadUserByMobile(s);
         
        return user;
    }
    
    public UserInfo loadUserByOpenid(String openid) {
    	
    	UserInfo user = mapper.loadUserByOpenid(openid);
         
        return user;
    }
    
    
    public UserInfo selectByPrimaryKey(Integer id) {
    	
    	UserInfo user = mapper.selectByPrimaryKey(id);
         
        return user;
    }
    
   public int save(UserInfo user) {
    	
	 return mapper.updateByPrimaryKeySelective(user);
         
    }
  
}
