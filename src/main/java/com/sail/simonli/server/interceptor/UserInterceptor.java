package com.sail.simonli.server.interceptor;


import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sail.simonli.server.entity.Logins;
import com.sail.simonli.server.model.UserInfo;
import com.sail.simonli.server.service.LoginsService;
import com.sail.simonli.server.service.UserService;
import com.sail.simonli.server.util.CookieConstantTable;
import com.sail.simonli.server.util.CookieUtils;
import com.sail.simonli.server.util.EncryptionUtil;

public class UserInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginsService loginsService;
	
	/**
	 * 用于处理自动登录
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		UserInfo user = (UserInfo) session.getAttribute("user");

		// 已登录
		if (user != null) {
			return true;
		} else {
			String callback = request.getRequestURL().toString();
			
			if(callback.indexOf("/register")!=-1||callback.indexOf("/500.html")!=-1||callback.indexOf("/oauth")!=-1) {
				
				return true;
			}
			// 从cookie中取值
			Cookie rememberme = CookieUtils.getCookie(request, CookieConstantTable.RememberMe);
			if (rememberme != null) {
				String cookieValue = EncryptionUtil.base64Decode(rememberme.getValue());

				String[] cValues = cookieValue.split(":");
				if (cValues.length == 2) {
					String usernameByCookie = cValues[0]; // 获取用户名
					String uuidByCookie = cValues[1]; // 获取UUID值
					// 到数据库中查询自动登录记录
					if (loginsService == null) {//解决service为null无法注入问题 
				         loginsService=getBean(LoginsService.class,request);
				     } 
					Logins pLogins = loginsService.selectByUsernameAndSeries(usernameByCookie,
							uuidByCookie);
					if (pLogins != null) {
						String savedToken = pLogins.getToken(); // 数据库中保存的密文

						// 获取有效时间
						Date savedValidtime = pLogins.getValidtime();
						Date currentTime = new Date();

						// 如果还在cookie有效期之内，继续判断是否可以自动登录
						if (currentTime.before(savedValidtime)) {
							 if (userService == null) {//解决service为null无法注入问题 
							 	userService=getBean(UserService.class,request);
						     } 
							
							UserInfo u = userService.loadUserByMobile(usernameByCookie); //如果cookie有，也要从SF中获取。
							
							if (u != null) {
								
								 userService.saveSf(request, u);
								
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(pLogins.getValidtime());
								// 精确到分的时间字符串
								String timeString = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH)
										+ "-" + calendar.get(Calendar.DAY_OF_MONTH) + "-"
										+ calendar.get(Calendar.HOUR_OF_DAY) + "-" + calendar.get(Calendar.MINUTE);
								// 为了校验而生成的密文
								 String newToken = EncryptionUtil.sha256Hex(u.getMobile()+"_"+u.getOpenid()+"_"+ timeString + "_" + CookieConstantTable.salt);

								// 校验sha256加密的值，如果不一样则表示用户部分信息已被修改，需要重新登录
								if (savedToken.equals(newToken)) {
									/**
									 * 为了提高安全性，每次登录之后都更新自动登录的cookie值
									 */
									// 更新cookie值
									String uuidNewString = UUID.randomUUID().toString();
									String newCookieValue = EncryptionUtil
											.base64Encode(u.getMobile() + ":" + uuidNewString);
									CookieUtils.editCookie(request, response, CookieConstantTable.RememberMe,
											newCookieValue, null);

									// 更新数据库
									pLogins.setSeries(uuidNewString);
									loginsService.updateByPrimaryKeySelective(pLogins);

									/**
									 * 将用户加到session中，不退出浏览器时就只需判断session即可
									 */
									session.setAttribute("user", u);

									return true;  //校验成功，此次拦截操作完成
								} else { // 用户部分信息被修改，删除cookie并清空数据库中的记录
									CookieUtils.delCookie(response, rememberme);
									loginsService.deleteByPrimaryKey(pLogins.getLoginId());
								}
							}
						} else { // 超过保存的有效期，删除cookie并清空数据库中的记录
							CookieUtils.delCookie(response, rememberme);
							loginsService.deleteByPrimaryKey(pLogins.getLoginId());
						}
					}else {
						CookieUtils.delCookie(response, rememberme);
					}
				}
			}
			return true;
		}
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		super.afterCompletion(request, response, handler, ex);
	}

    private <T> T getBean(Class<T> clazz, HttpServletRequest request) {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(clazz);
    }

}
