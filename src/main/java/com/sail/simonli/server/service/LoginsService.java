package com.sail.simonli.server.service;
 
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sail.simonli.server.dao.LoginsMapper;
import com.sail.simonli.server.dao.UserInfoMapper;
import com.sail.simonli.server.entity.Logins;
import com.sail.simonli.server.model.UserInfo;
import com.sail.simonli.server.util.CookieConstantTable;
import com.sail.simonli.server.util.CookieUtils;
import com.sail.simonli.server.util.EncryptionUtil;

 
@Service
@Transactional
public class LoginsService {
	
    @Autowired
   LoginsMapper mapper;
    
   @Autowired
   UserInfoMapper userMapper;
 
    
    public Logins selectByUsernameAndSeries(String username,String series) {
    	
    	Logins user = mapper.selectByUsernameAndSeries(username,series);
         
        return user;
    }
    
    
    public UserInfo register(UserInfo result, HttpServletResponse response) {
    	
		        // 有效期
		        Calendar calendar = Calendar.getInstance();
		        
		        calendar.add(Calendar.MONTH, 1); // 一个月
		        
		        Date validTime = calendar.getTime();
		        
		        // 精确到分的时间字符串
		        String timeString = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-"
		                + calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.HOUR_OF_DAY) + "-"
		                        + calendar.get(Calendar.MINUTE);
		 
		                // sha256加密用户信息
		        String token = EncryptionUtil.sha256Hex(result.getMobile()+"_"+result.getOpenid()+"_"+ timeString + "_" + CookieConstantTable.salt);
		        
		        result.setToken(token);
		        
		        userMapper.insert(result);
		        // UUID值
		        String uuidString = UUID.randomUUID().toString();
		        
		        // Cookie值
		        String cookieValue = EncryptionUtil.base64Encode(result.getMobile() + ":" + uuidString);
		 
		        // 在数据库中保存自动登录记录（如果已有该用户的记录则更新记录）
               Logins pLogin = mapper.selectByUsername(result.getMobile());
               
                if (pLogin == null) {
                    pLogin = new Logins();
                    pLogin.setUsername(result.getMobile());
                    pLogin.setSeries(uuidString);
                    pLogin.setToken(token);
                    pLogin.setValidtime(validTime);
                    mapper.insertSelective(pLogin);
                }else{
                    pLogin.setSeries(uuidString);
                    pLogin.setToken(token);
                    pLogin.setValidtime(validTime);
                    mapper.updateByPrimaryKeySelective(pLogin);
                }
                
                // 保存cookie
                CookieUtils.addCookie(response, CookieConstantTable.RememberMe, cookieValue, null);
 
                return result;
    }

  
    public void updateByPrimaryKeySelective(Logins logins) {
    	mapper.updateByPrimaryKeySelective(logins);
    }
    
    public  int deleteByPrimaryKey(Integer loginId) {
    	return mapper.deleteByPrimaryKey(loginId);
    }
}
