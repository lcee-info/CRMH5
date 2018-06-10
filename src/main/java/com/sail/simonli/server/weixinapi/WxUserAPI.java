package com.sail.simonli.server.weixinapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sail.simonli.server.config.CustomProperty;
import com.sail.simonli.server.weixinapi.api.Const;
import com.sail.simonli.server.weixinapi.entity.Response;
import com.sail.simonli.server.weixinapi.utils.httpclient.HttpClientUtils;
import com.sail.simonli.server.weixinapi.utils.httpclient.HttpResult;

 
public class WxUserAPI {

    
    public static String getCodeUrl(String REDIRECT_URI,String state,boolean getuserinfo) {
    	String scope="snsapi_base";
    	if(getuserinfo) {
    		scope="snsapi_userinfo";
    	}
    	CustomProperty property= new CustomProperty();
    	return Const.CODEURL.replace("APPID",property.getAppid()).replaceAll("REDIRECT_URI", REDIRECT_URI)
    			.replaceAll("SCOPE", scope).replaceAll("STATE", state);
    }
    

    public static Response getUserinfo(String code) {
    	Response response = null;
    	CustomProperty property= new CustomProperty();
    	String url = Const.TOKENURL.replace("APPID",property.getAppid())
    			.replace("SECRET", property.getSecret()).replace("CODE",code);
    	HttpResult httpResult = HttpClientUtils.doGet(url);
        if (httpResult.getStatus() == 200) {
            JSONObject jsonObject = JSON.parseObject(httpResult.getData());
            response = JSON.toJavaObject(jsonObject,Response.class);
        }
        return response;
    }

}
