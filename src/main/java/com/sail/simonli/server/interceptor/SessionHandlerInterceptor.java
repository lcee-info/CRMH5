package com.sail.simonli.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.sail.simonli.server.model.UserInfo;
import com.sail.simonli.server.weixinapi.entity.Response;

public class SessionHandlerInterceptor implements HandlerInterceptor{
	
	private Logger logger=Logger.getLogger(SessionHandlerInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		UserInfo user = (UserInfo) session.getAttribute("user");

		if (user != null) {
			
			return true;
			
		}else {
			
			String callback = request.getRequestURL().toString();
			
			logger.info("callback:"+callback+";-----request.getContextPath():"+request.getContextPath());
			
			if(callback.indexOf("/user/sendSms")!=-1||callback.indexOf("/register")!=-1||callback.indexOf("/500.html")!=-1||callback.indexOf("/oauth")!=-1) {
			
				return true;
				
			}else {
				
				Response userinfo = (Response) session.getAttribute("response");
				
				if(userinfo!=null) {
					
					return true;
					
				}else {
					logger.info(request.getContextPath() + "/oauth/index?callback=" + callback);
					response.sendRedirect(
							request.getContextPath() + "/oauth/index?callback=" + callback);
					return false;
				}
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
