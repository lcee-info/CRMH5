package com.sail.simonli.server.service;
 
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sail.simonli.server.dao.UserInfoMapper;
import com.sail.simonli.server.gary.Utils;
import com.sail.simonli.server.model.UserInfo;

 
@Service
@Transactional
public class UserService {
	
	private Logger logger=Logger.getLogger(UserService.class);
    @Autowired
   UserInfoMapper mapper;
 
    public UserInfo loadUserByMobile(String s) {
    	
    	UserInfo user = mapper.loadUserByMobile(s);
    	
    	if(user!=null) {
    		
    		Utils.query(user);
    		
    	}
         
        return user;
    }
    
    public UserInfo loadUserByOpenid(String openid) {
    	
    	UserInfo user = mapper.loadUserByOpenid(openid);
    	
    	if(user!=null) {

    		Utils.query(user);
    		
    	}
         
        return user;
    }
    
    
    public UserInfo selectByPrimaryKey(Integer id) {
    	
    	UserInfo user = mapper.selectByPrimaryKey(id);
         
        return user;
    }
    
   public int save(HttpServletRequest request,UserInfo user) {
	  
	 return mapper.updateByPrimaryKeySelective(user);
         
    }
   
   @Async
   public void saveSf(HttpServletRequest request,UserInfo user) {
	   try {
			 
		   Utils.save(request,user);
		   
	   }catch(Exception e) {
		   
		   e.printStackTrace();
		   
		   logger.error(e.getMessage());
		   
	   }
	   
   }
  
}
