package com.sail.simonli.server.gary;

import com.sail.simonli.server.config.SalesforceProperty;
import com.sail.simonli.server.config.SpringUtil;
import com.sail.simonli.server.entity.AccessToken;
import com.sail.simonli.server.util.CommUtil;
import com.sail.simonli.server.util.RetryUtils;
import com.sail.simonli.server.weixinapi.cache.IAccessTokenCache;
import com.sail.simonli.server.weixinapi.exception.RCodeException;

import java.io.IOException;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AccessTokenAPI {

    private static Logger logger = LoggerFactory.getLogger(AccessTokenAPI.class);

    public static AccessToken httpAccessToken(HttpServletRequest request) throws RCodeException{
    	AccessToken accessToken=null;
    	SalesforceProperty salesforce = CommUtil.getBean(SalesforceProperty.class,request);
    	HttpClient httpClient = HttpClientBuilder.create().build();
		String loginURL = salesforce.getLoginurl() + salesforce.getGrantservice() + "&client_id=" + salesforce.getClientid() + "&client_secret=" + salesforce.getClientsecret()
				+ "&username=" + salesforce.getUsername() + "&password=" + salesforce.getPassword();
		HttpPost httpPost = new HttpPost(loginURL);
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
		} catch (ClientProtocolException cpException) {
			cpException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		final int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			logger.error("请求响应错误"+statusCode);
		}
		String getResult = null;
		try {
			getResult = EntityUtils.toString(response.getEntity());
			//JSONObject jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
			accessToken = new AccessToken(getResult);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
			
     return accessToken;
 }
    
    
    public static AccessToken getAccessToken() {
        AccessToken result = getAvailableAccessToken();
        if (result != null) {
            return result;
        }

        return refreshAccessTokenIfNecessary();
    }

    private static AccessToken getAvailableAccessToken() {
        // 利用 appId 与 accessToken 建立关联，支持多账户
        IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();
        SalesforceProperty salesforce = SpringUtil.getBean(SalesforceProperty.class);
        String accessTokenJson = accessTokenCache.get(salesforce.getUsername());
        if (StringUtils.isNoneBlank(accessTokenJson)) {
            AccessToken result = new AccessToken(accessTokenJson);
            if (result != null && result.isAvailable()) {
                return result;
            }
        }
        return null;
    }

    /**
     * 直接获取 accessToken 字符串，方便使用
     * @return String accessToken
     */
    public static String getAccessTokenStr() {
        return getAccessToken().getAccessToken();
    }

    /**
     * synchronized 配合再次获取 token 并检测可用性，防止多线程重复刷新 token 值
     */
    private static synchronized AccessToken refreshAccessTokenIfNecessary() {
        AccessToken result = getAvailableAccessToken();
        if (result != null) {
            return result;
        }
        return refreshAccessToken();
    }


    /**
     * 无条件强制更新 access token 值，不再对 cache 中的 token 进行判断
     * @param ac ApiConfig
     * @return AccessToken
     */
    public static AccessToken refreshAccessToken() {
        SalesforceProperty salesforce = SpringUtil.getBean(SalesforceProperty.class);
        // 最多三次请求
        AccessToken result = RetryUtils.retryOnException(3, new Callable<AccessToken>() {

            public AccessToken call() throws Exception {
            	AccessToken accessToken=null;
            	SalesforceProperty salesforce = SpringUtil.getBean(SalesforceProperty.class);
            	HttpClient httpClient = HttpClientBuilder.create().build();
        		String loginURL = salesforce.getLoginurl() + salesforce.getGrantservice() + "&client_id=" + salesforce.getClientid() + "&client_secret=" + salesforce.getClientsecret()
        				+ "&username=" + salesforce.getUsername() + "&password=" + salesforce.getPassword();
        		HttpPost httpPost = new HttpPost(loginURL);
        		HttpResponse response = null;
        		try {
        			response = httpClient.execute(httpPost);
        		} catch (ClientProtocolException cpException) {
        			cpException.printStackTrace();
        		} catch (IOException ioException) {
        			ioException.printStackTrace();
        		}
        		final int statusCode = response.getStatusLine().getStatusCode();
        		if (statusCode != HttpStatus.SC_OK) {
        			logger.error("请求响应错误"+statusCode);
        		}else {
	        		String getResult = null;
	        		try {
	        			getResult = EntityUtils.toString(response.getEntity());
	        			//JSONObject jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
	        			accessToken = new AccessToken(getResult);
	        		} catch (IOException ioException) {
	        			ioException.printStackTrace();
	        		} catch (Exception e){
	        			e.printStackTrace();
	        			logger.error(e.getMessage());
	        		}
        		}
        		return accessToken;
            }
        });

        // 三次请求如果仍然返回了不可用的 access token 仍然 put 进去，便于上层通过 AccessToken 中的属性判断底层的情况
        if (null != result) {
            // 利用 appId 与 accessToken 建立关联，支持多账户
            IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();
            accessTokenCache.set(salesforce.getUsername(), result.getCacheJson());
        }
        return result;
    }
}
