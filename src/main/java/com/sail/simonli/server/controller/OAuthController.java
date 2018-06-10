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
    		
    		String redirect_uri=URLEncoder.encode(CustomProperty.DOMIAN+"/oauth/code", "utf-8");
    		
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
    	
		String openid=null;
		
		String state = null;
		
		String code = getPara(req,"code");
		
		String redirect_uri = getPara(req,"redirect_uri");
		try {
		
			if (StringUtils.isNotBlank(code)) {
				
				logger.info("code:"+code);
				
				state = getPara(req,"state");
				
				logger.info(" state:"+state);
				
				Response response = WxUserAPI.getUserinfo(code);
				
				logger.info("response:"+response);
				
				if(response.getErrcode()!=null) {
					
					resp.sendRedirect("500.html?errcode="+response.getErrcode());
					
				}else {
					
					openid = response.getOpenid();
					
					UserInfo user = service.loadUserByOpenid(openid);
					
					if(user != null) {
						
						req.getSession().setAttribute("user", user);
			    			
					} else {
						
						req.getSession().setAttribute("openid", openid);
						
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

    
}
