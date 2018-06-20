package com.sail.simonli.server.gary;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.sail.simonli.server.config.SalesforceProperty;
import com.sail.simonli.server.config.SpringUtil;
import com.sail.simonli.server.entity.AccessToken;

/**
 * 
  * @ClassName: SFDCDataCtrlUtil
  * @Description: salesforce 
  * @author Comsys-Gary Hu
  * @date  
  *
 */
public class SFDCDataCtrlUtil {
	
	private static Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");
	
    private static <T> T getBean(Class<T> clazz, HttpServletRequest request) {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(clazz);
    }
	/**
	 * 
	* @Title: upsertToSFDCData 
	* @Description: upsert sfdc
	* @param @param objName
	* @param @param jsonObject
	* @param @param extNameAndextId
	* @param @return    
	* @return Map<String,String>    
	* @throws
	 */
	public static Map<String,String> upsertToSFDCData(final String objName,final JSONObject jsonObject,final String extNameAndextId,final String extId){
		final AccessToken accessToken = AccessTokenAPI.getAccessToken();
		final SalesforceProperty property =SpringUtil.getBean(SalesforceProperty.class);
		if(accessToken != null){
			try {
	            	Map<String,String> mapReturnValue =new HashMap<String, String>();
	            	Header oauthHeader = new BasicHeader("Authorization", "Bearer " + accessToken.getAccessToken());
					String uri = accessToken.getInstance_url() + property.getRest_endpoint()+"sobjects/"+objName+"/"+extNameAndextId +"/" + extId;
					HttpClient httpClient = HttpClientBuilder.create().build();
					HttpPatch httpPatch = new HttpPatch(uri);
					httpPatch.setHeader(oauthHeader);
					httpPatch.addHeader(prettyPrintHeader);
					HttpResponse response;
					StringEntity body = new StringEntity(jsonObject.toString(1),Charset.forName("UTF-8"));
					body.setContentType("application/json");
					httpPatch.setEntity(body);
					response = httpClient.execute(httpPatch);
					String response_string = null;
					int statusCode = response.getStatusLine().getStatusCode(); 
					
					if(statusCode==400){
						response_string = EntityUtils.toString(response.getEntity()); 
						
						return null;
					}
					if(statusCode == 201){
						response_string = EntityUtils.toString(response.getEntity()); 
						JSONObject jsonObj = new JSONObject(response_string);
						mapReturnValue.put("Id", jsonObj.getString("id"));
						return mapReturnValue;
					}
					if(statusCode == 204){
						mapReturnValue.put("Id", "is_update_data");
						return mapReturnValue;
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	 

	/**
	 * 
	* @Title: getForSFDCData 
	* @Description: query  
	* @param @param strSql
	* @param @return    
	* @return JSONArray  
	* @throws
	 */
	public static JSONArray getForSFDCData(String strSql){
		
		AccessToken accessToken = AccessTokenAPI.getAccessToken();
		SalesforceProperty property =SpringUtil.getBean(SalesforceProperty.class);
		if(accessToken != null){
			try {
				
				Header oauthHeader = new BasicHeader("Authorization", "Bearer " + accessToken.getAccessToken());
				String uri = accessToken.getInstance_url()+ property.getRest_endpoint() + "query/?q=" + strSql;
				HttpClient httpClient = HttpClientBuilder.create().build();
				HttpGet httpGet = new HttpGet(uri);
				httpGet.setHeader(oauthHeader);
				httpGet.addHeader(prettyPrintHeader);
				HttpResponse response;
				response = httpClient.execute(httpGet);
				int statusCode = response.getStatusLine().getStatusCode();
				
				if(statusCode == 400){
					String response_string = EntityUtils.toString(response.getEntity()); 
					return null;
				}
				if (statusCode == HttpStatus.SC_OK) {
					String response_string = EntityUtils.toString(response.getEntity());
					JSONObject jsonObject = new JSONObject(response_string);
					
					JSONArray jsonArray = jsonObject.getJSONArray("records");
					int totalSize = jsonObject.getInt("totalSize");
					if (totalSize > 0) {
						return jsonArray;
					} else {
						return null;
					}
				} else {
					return null;
				}
			} catch (ClientProtocolException ce) {
				ce.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	  
	
}
