package com.sail.simonli.server.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sail.simonli.server.config.CustomProperty;
import com.sail.simonli.server.model.UserInfo;
import com.sail.simonli.server.service.UserService;
import com.sail.simonli.server.weixinapi.WxUserAPI;
import com.sail.simonli.server.weixinapi.entity.Response;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/oauth")
public class OAuthController {
	
	private Logger logger=Logger.getLogger(OAuthController.class);
	
	@Autowired
	private UserService service;
	@Autowired
	private CustomProperty customProperty;
	
	
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void index(HttpServletRequest req, HttpServletResponse resp) {
    	try {
    		
    		HttpSession session = req.getSession();
    		
    		UserInfo user = (UserInfo) session.getAttribute("user");
    		
    		if(user != null) {
				
				req.getSession().setAttribute("user", user);
	    			
				resp.sendRedirect("index.html");
				
				return ;
    				
			} 
    		
    		String code = getPara(req,"code");
    		
    		if (StringUtils.isNotBlank(code)) {
    			
    			code(req,resp);
    			
    			return;
    			
    		}
    		
    		String callback = req.getParameter("callback") == null?"":req.getParameter("callback").toString();
    		 
    		//String redirect_uri=URLEncoder.encode(getReqPath(req)+"/oauth/code", "utf-8");
    		String redirect_uri=URLEncoder.encode(customProperty.getDomain()+"/oauth/code", "utf-8");
    		logger.debug("redirect_uri:"+redirect_uri);
    		
			String codeUrl = WxUserAPI.getCodeUrl(redirect_uri, callback,true);
			
			resp.sendRedirect(codeUrl);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
    }
    
    private String getPara(HttpServletRequest req,String param) {
    	return req.getParameter(param);
    }
    
    @RequestMapping("/code")
    public void code(HttpServletRequest req, HttpServletResponse resp){
    	
    	//如果cookie中都没有信息，要重新获取OPENID，
    	
		String openid=null;
		
		String state = null;
		
		String code = getPara(req,"code");
		
		//String redirect_uri = getPara(req,"redirect_uri");
		String redirect_uri = null;
		try {
		
			if (StringUtils.isNotBlank(code)) {
				
				logger.info("code:"+code);
				
				redirect_uri = getPara(req,"state");
				
				logger.info(" state:"+redirect_uri);
				
				Response response = WxUserAPI.getAccessToken(code);
				
				logger.info("response:"+response);
				
				if(response.getErrcode()!=null) {
					
					resp.sendRedirect("500.html?errcode="+response.getErrcode());
					
				}else {
					
					openid = response.getOpenid();
					
					Response userInfo = WxUserAPI.getUserinfo(response);

					userInfo.setOpenid(openid);
					
					UserInfo user = service.loadUserByOpenid(openid);//如果COOKIE没有，重新获取OPENID
					
					if(user != null) {//表示本地己有，要从SF中更新一下本地。
						
						service.save(req, user);// 从SF中获取最新的用户信息，并更新到－－本地DB中。
						req.getSession().setAttribute("user", user);
			    			
					} else {
						
						req.getSession().setAttribute("response", userInfo);
						
					}
					
					resp.sendRedirect(redirect_uri);
    				
    				return ;
	    				
				}
					
			}
		}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("code："+e.getMessage());
		}
		catch (Exception e) {
				e.printStackTrace();
				logger.error("code："+e.getMessage());
		}
			
	}

    
    private String getReqPath(HttpServletRequest request) {
    	
    	String requestUrl = request.getScheme() //当前链接使用的协议
    	        +"://" + request.getServerName()//服务器地址  
     	        + ":" + request.getServerPort() //端口号  
    	        + request.getContextPath();
    	
    	return requestUrl;
    }
    
}
