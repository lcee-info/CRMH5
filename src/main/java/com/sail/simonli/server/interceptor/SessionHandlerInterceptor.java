package com.sail.simonli.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sail.simonli.server.model.UserInfo;

public class SessionHandlerInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		UserInfo user = (UserInfo) session.getAttribute("user");

		if (user != null) {
			
			return true;
			
		}else {
			
			String callback = request.getRequestURL().toString();
			
			if(callback.indexOf("/register")!=-1||callback.indexOf("/500.html")!=-1||callback.indexOf("/oauth")!=-1) {
			
				return true;
				
			}else {
				
				response.sendRedirect(
						request.getContextPath() + "/oauth/index?callback=" + callback);
				
				return false;
			}
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
