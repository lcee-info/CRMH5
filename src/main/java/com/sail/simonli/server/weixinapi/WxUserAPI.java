package com.sail.simonli.server.weixinapi;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sail.simonli.server.config.CustomProperty;
import com.sail.simonli.server.config.SpringUtil;
import com.sail.simonli.server.controller.OAuthController;
import com.sail.simonli.server.weixinapi.api.Const;
import com.sail.simonli.server.weixinapi.entity.Response;
import com.sail.simonli.server.weixinapi.utils.httpclient.HttpClientUtils;
import com.sail.simonli.server.weixinapi.utils.httpclient.HttpResult;

 
public class WxUserAPI {
	
	private static Logger logger=Logger.getLogger(WxUserAPI.class);
    
    public static String getCodeUrl(String REDIRECT_URI,String state,boolean getuserinfo) {
    	String scope="snsapi_base";
    	if(getuserinfo) {
    		scope="snsapi_userinfo";
    	}
    	CustomProperty property= SpringUtil.getBean(CustomProperty.class);
    	logger.debug("appid:"+property.getAppid());
    	return Const.CODEURL.replace("APPID",property.getAppid()).replaceAll("REDIRECT_URI", REDIRECT_URI)
    			.replaceAll("SCOPE", scope).replaceAll("STATE", state);
    }
    

    public static Response getAccessToken(String code) {
    	Response response = null;
    	CustomProperty property= SpringUtil.getBean(CustomProperty.class);
    	logger.debug("appid:"+property.getAppid()+"&SECRET"+property.getSecret());
    	String url = Const.TOKENURL.replace("APPID",property.getAppid())
    			.replace("SECRET", property.getSecret()).replace("CODE",code);
    	HttpResult httpResult = HttpClientUtils.doGet(url);
        if (httpResult.getStatus() == 200) {
            JSONObject jsonObject = JSON.parseObject(httpResult.getData());
            response = JSON.toJavaObject(jsonObject,Response.class);
        }
        	return response;
    }
    
    public static Response getUserinfo(Response response) {
		Response user = null;
        if(response!=null&&response.getAccess_token()!=null&&response.getOpenid()!=null) {
        	String userurl = Const.USERURL.replace("ACCESS_TOKEN",response.getAccess_token())
        			.replace("OPENID", response.getOpenid());
        	HttpResult result = HttpClientUtils.doGet(userurl);
        	if (result.getStatus() == 200) {
                JSONObject object = JSON.parseObject(result.getData());
                user = JSON.toJavaObject(object,Response.class);
        	}
        }
    	return user;
        
    }

}
