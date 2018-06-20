package com.gary.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * 
 * @ClassName: SFDCLoginUtil
 * @Description: REST Login
 * @author Comsys-Gary Hu
 * @date 2017年3月26日 下午9:46:18
 *
 */
public class SFDCLoginUtil {

	static final String USERNAME = PropertyUtil.getResourceByKey("username");
	static final String PASSWORD = PropertyUtil.getResourceByKey("password");
	static final String LOGINURL = PropertyUtil.getResourceByKey("loginurl");
	static final String GRANTSERVICE = PropertyUtil.getResourceByKey("grantservice");
	static final String CLIENTID = PropertyUtil.getResourceByKey("clientid");
	static final String CLIENTSECRET = PropertyUtil.getResourceByKey("clientsecret");
	static final String  INSTANCE_URL= PropertyUtil.getResourceByKey("instance_url");
	
	
	
	public static Map<String, String> getOauth() {
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		Map<String, String> oauthMap = new HashMap<String, String>();
		String loginURL = LOGINURL + GRANTSERVICE + "&client_id=" + CLIENTID + "&client_secret=" + CLIENTSECRET
				+ "&username=" + USERNAME + "&password=" + PASSWORD;
		
		loginURL="https://test.salesforce.com/services/oauth2/token?grant_type=password&client_id="
				+ "3MVG99S6MzYiT5k9H2ZS9jfMygW.e8a4ONaI1A6.OEmGBs2b0NlBqpPZw90Sh2LROUuD5MeutZUFVv88OoLBX&client_secret=5867733029077662500&username=crm@trayton.com.develop01&password=demo1234";
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
			System.out.println("Error authenticating to Force.com:" + statusCode);
		}
		String getResult = null;
		try {
			getResult = EntityUtils.toString(response.getEntity());
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		JSONObject jsonObject = null;
		String loginAccessToken = null;
		String loginInstanceUrl = null;
		try {
			jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
			loginAccessToken = jsonObject.getString("access_token");
			loginInstanceUrl = jsonObject.getString("instance_url");
			oauthMap.put("access_token", loginAccessToken);
			if(INSTANCE_URL !=null && !"".equals(INSTANCE_URL)){
				oauthMap.put("instance_url", INSTANCE_URL);
			}else{
				oauthMap.put("instance_url", loginInstanceUrl);
			}

		} catch (JSONException jsonException) {
			jsonException.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		if(oauthMap.size() >0 && oauthMap!=null){
				return oauthMap;
		}else{
			return null;
		}
	}
	
}
