package com.sail.simonli.server.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sail.simonli.server.util.HttpRequestUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class LogHandlerInterceptor implements HandlerInterceptor{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogHandlerInterceptor.class);
	private static final String LOGGER_SEND_TIME="logger_send_time";
	private static final String LOGGER_ENTITY="logger_entity";
	public static final String LOGGER_RETURN = "_logger_return";
	
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
//		LOGGER.info("preHandle:"+handler.toString());
//		SysAccessLogWithBLOBs log=new SysAccessLogWithBLOBs();	
//		String sessionid=request.getRequestedSessionId();
//		//请求路径
//		String url=request.getRequestURI();
//		/**
//		String token=request.getHeader("accessToken");
//		String storeId=request.getHeader("storeId");
//		if(!token.equals(storeId)) {
//			LOGGER.error(HttpRequestUtils.getIp(request)+request.getParameterMap()+"无效的请求");
//			return false;
//		}
//		*/
//		//请求参数信息
//		String paramData=JSON.toJSONString(request.getParameterMap(),SerializerFeature.DisableCircularReferenceDetect,
//				SerializerFeature.WriteMapNullValue);
//		//设置客户端ip
//		log.setLogClientIp(HttpRequestUtils.getIp(request));
//		log.setLogMethod(request.getMethod());
//		log.setLogType(HttpRequestUtils.getRequestType(request));
//		log.setLogParamData(paramData);
//		log.setLogUri(url);
//		log.setLogSessionId(sessionid);
//		log.setLogId(UUID.randomUUID().toString());
//		request.setAttribute(LOGGER_SEND_TIME, System.currentTimeMillis());
//		request.setAttribute(LOGGER_ENTITY, log);
		return true;
	}
 



    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
//        LOGGER.info("afterCompletion:" + handler.toString());
//        int status = response.getStatus();
//        long currentTime = System.currentTimeMillis();
//        long time = Long.valueOf(request.getAttribute(LOGGER_SEND_TIME).toString());
//        SysAccessLogWithBLOBs log = (SysAccessLogWithBLOBs) request.getAttribute(LOGGER_ENTITY);
//        log.setLogTimeConsuming(Integer.valueOf((currentTime - time) + ""));
//        log.setLogReturnTime(currentTime + "");
//        log.setLogHttpStatusCode(status + "");
//        //设置返回值
//        log.setLogReturnData(JSON.toJSONString(response.toString(),
//                SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.WriteMapNullValue));

        //SysAssessLogService service=getBean(SysAssessLogService.class,request);
        //service.insert(log);
    }

    private <T> T getBean(Class<T> clazz, HttpServletRequest request) {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(clazz);
    }




    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    		// TODO Auto-generated method stub
    		LOGGER.info("postHandle:" + handler.toString());
    	}

}
