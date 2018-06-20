package com.sail.simonli.server.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.sail.simonli.server.model.UserInfo;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
	
	private Logger logger=Logger.getLogger(IndexController.class);
	
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void index(HttpServletRequest req, HttpServletResponse resp) {
    	try {
    		
    		logger.info("访问个人信息:"+req.getParameterMap());
    		
    		HttpSession session = req.getSession();
    		
    		UserInfo user = (UserInfo) session.getAttribute("user");
    		
    		if(user != null) {
				
				if("1".equals(user.getFinish())) {
					
					resp.sendRedirect("grxx.html");
					
				}else {
					
					resp.sendRedirect("xxwh.html");
					
				}
				
				return ;
    				
			}
			
			resp.sendRedirect("register.html");
			
			return ;
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
    }
    
    
}
