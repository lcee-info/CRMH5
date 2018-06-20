package com.sail.simonli.server.entity;

import java.io.Serializable;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.sail.simonli.server.util.RetryUtils.ResultCheck;

 
public class AccessToken implements ResultCheck,  Serializable{

    private String access_token;    // 正确获取到 access_token 时有值
    private Integer expires_in;        // 正确获取到 access_token 时有值
    private Integer errcode;        // 出错时有值
    private String errmsg;            // 出错时有值
    private Long expiredTime;        // 正确获取到 access_token 时有值，存放过期时间
    private String json;
    private String instance_url;

    public AccessToken(String jsonStr) {
        this.json = jsonStr;
        try {
            Map<String, Object> temp = JSONObject.parseObject(jsonStr, Map.class);
            access_token = (String) temp.get("access_token");
            //expires_in = (Integer) temp.get("expires_in");
            expires_in = 7200;
            errcode = (Integer) temp.get("errcode");
            instance_url= (String) temp.get("instance_url");
            if (errcode !=null && errcode == 0) {
            	errcode = null;
			}
            errmsg = (String) temp.get("errmsg");

            if (expires_in != null)
                expiredTime = System.currentTimeMillis() + ((expires_in -5) * 1000);
            // 用户缓存时还原
            if (temp.containsKey("expiredTime")) {
                 expiredTime = (Long) temp.get("expiredTime");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getJson() {
        return json;
    }

    public String getCacheJson() {
        Map<String, Object> temp = JSONObject.parseObject(json, Map.class);
        temp.put("expiredTime", expiredTime);
        temp.remove("expires_in");
        return JSONObject.toJSONString(temp);
    }

    public boolean isAvailable() {
        if (expiredTime == null)
            return false;
        if (errcode != null)
            return false;
        if (expiredTime < System.currentTimeMillis())
            return false;
        return access_token != null;
    }

    public String getAccessToken() {
        return access_token;
    }

    public Integer getExpiresIn() {
        return expires_in;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public Integer getErrorCode() {
        return errcode;
    }

	public String getInstance_url() {
		return instance_url;
	}

	public void setInstance_url(String instance_url) {
		this.instance_url = instance_url;
	}

	public boolean matching() {
		// TODO Auto-generated method stub
		return isAvailable();
	}


}
