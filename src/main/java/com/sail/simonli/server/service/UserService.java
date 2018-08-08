package com.sail.simonli.server.service;
 
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sail.simonli.server.dao.NameIndexMapper;
import com.sail.simonli.server.dao.UserInfoMapper;
import com.sail.simonli.server.entity.NameIndex;
import com.sail.simonli.server.gary.Utils;
import com.sail.simonli.server.model.UserInfo;
import com.sail.simonli.server.util.StringUtil;

 
@Service
@Transactional
public class UserService {
	
       private Logger logger=Logger.getLogger(UserService.class);
   
       @Autowired
       UserInfoMapper mapper;
     
       @Autowired
 	   private NameIndexMapper nameIndexMapper;
   
       
	   public UserInfo loadUserByMobileFromDB(String s) {//从本地数据库拉用户。	 
	   	
	   	   UserInfo user = mapper.loadUserByMobile(s); //从本地数据库拉用户。	   		      
	       return user;
	   }
	   
	   public UserInfo loadUserByMobileFromSF(String s) {//从SF中获取用户
	    	
		        UserInfo user = new  UserInfo();
		        user.setMobile(s);	    	    		
	    		Utils.query(user);

  	            return user;
	    }
 
       public UserInfo loadUserByMobile(String s) {
    	
    	   UserInfo user = mapper.loadUserByMobile(s); //从本地数据库拉用户。
    	
      	  if(user!=null) {//如果本地有，从SF中拉取最新的。    		
    		 Utils.query(user);    		
    	  }
         
          return user;
      }
    
      public UserInfo loadUserByOpenid(String openid) {
    	
    	 UserInfo user = mapper.loadUserByOpenid(openid); //通过OPENID从本地获取用户。
    	
    	 if(user!=null) {//如果有，再从SF中加载

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
			 
		   if(user.getName() == null || "".equals(user.getName())){
			   
			    NameIndex nameIndex =  nameIndexMapper.selectNameIndex();
			    Integer index = nameIndex.getIndex();
				if(index == null || index == 0){
		    		 index = 1;
		    	}else{
		    		 index = 1 + index;
		    	}
				nameIndex.setIndex(index);
				String result = StringUtil.padLeftStr(index);
				user.setName("匿名" + result);
				nameIndexMapper.updateByPrimaryKeySelective(nameIndex);
		   }
		   		   
		   Utils.save(request,user);
		   
	   }catch(Exception e) {
		   
		   e.printStackTrace();
		   
		   logger.error(e.getMessage());
		   
	   }
	   
   }
  
}
