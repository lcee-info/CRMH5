package com.sail.simonli.server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.sail.simonli.server.service.UserService;

public class SmsUtil {
	  
	  private static final String SNAME="dlshxm00";
	  private static final String SPWD="Simonli2018";
	  private static final String SPRDID="1012818";
	  private static final String smsUrl="http://cf.51welink.com/submitdata/Service.asmx/g_Submit";
	  
	  private static Logger logger=Logger.getLogger(SmsUtil.class);	
	
	  public static String sendSms(String mobile,String content) throws Exception {
		  
		  String PostData = "sname="+SNAME+"&spwd="+SPWD+"&scorpid=&sprdid="+SPRDID+"&sdst="+mobile+"&smsg="+java.net.URLEncoder.encode(content,"utf-8");
		
		  return SMS(PostData,smsUrl);
	  }
	
	  public static String SMS(String postData, String postUrl) throws Exception{
	        
	            //发送POST请求
	            URL url = new URL(postUrl);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	            conn.setRequestProperty("Connection", "Keep-Alive");
	            conn.setUseCaches(false);
	            conn.setDoOutput(true);

	            conn.setRequestProperty("Content-Length", "" + postData.length());
	            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
	            out.write(postData);
	            out.flush();
	            out.close();

	            //获取响应状态
	            logger.error("send sms ResponseCode:" +  conn.getResponseCode());
	            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	                System.out.println("connect failed!");
	                logger.error("send sms error: connect failed " );
	                return "";
	            }
	            //获取响应内容体
	            String line, result = "";
	            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
	            while ((line = in.readLine()) != null) {
	                result += line + "\n";
	            }
	            in.close();
	            return result;
//	        } catch (Exception e) {
//	            e.printStackTrace(System.out);
//	            logger.error("send sms error:", e.fillInStackTrace());
//	        }
	        
	    }

}
