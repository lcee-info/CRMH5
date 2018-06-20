package com.gary.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Properties;
import java.util.ResourceBundle;




/**
 * 
  * @ClassName: PropertyUtil
  * @Description: Proper文件处理公共类
  * @author Comsys-Gary Hu
  * @date 2016年1月29日 下午3:25:20
  *
 */
public class PropertyUtil {
	
	/**
	 * 获取properties文件的值
	 * @param key 键
	 * @return
	 */
	private static final ResourceBundle resource = ResourceBundle.getBundle("appconfig"); //获根元素的名称
	public static String getResourceByKey(String key){
		//System.out.println(PropertyUtil.class.getResource("/appconfig.properties").toString()+"-----file path------");
		if(key==null||"".equals(key)){
			return null;
		}
		try {
			String value = resource.getString(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 修改配置文件
	 * @param filePath    文件名称
	 * @param parameterName	键名称
	 * @param parameterValue 值
	 */
	public static void updateProperties(String filePath,String parameterName,String parameterValue){
	     Properties prop = new Properties();
	     try {
	    	filePath = PropertyUtil.class.getResource("/" + filePath).toString(); 
	    	filePath =URLDecoder.decode(filePath,"utf-8");
	    	System.out.println(filePath);
	    	filePath = filePath.substring(6);   
			InputStream fis=new FileInputStream(new File(filePath));
			prop.load(fis);
			fis.close();   
			prop.setProperty(parameterName, parameterValue);
			System.out.println("parameterValue::::"+parameterValue);
			OutputStream fos=new FileOutputStream(new File(filePath));
			prop.store(fos, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ResourceBundle resource = ResourceBundle.getBundle("I:/breoconf/timecheck");//test为属性文件名，放在包com.mmq下，如果是放在src下，直接用test即可    
		String key = resource.getString("acctimeponit");  
		System.out.println(key+"--------");
	}
}
