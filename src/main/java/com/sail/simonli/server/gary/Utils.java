/**
 * 
 */
package com.sail.simonli.server.gary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.sail.simonli.server.model.UserInfo;
import com.sail.simonli.server.util.CountryMap;
import com.sail.simonli.server.util.DateUtils;


/**
 * @author admin
 *
 */
public class Utils {

	private static Logger logger=Logger.getLogger(Utils.class);
	
		public static void save(HttpServletRequest request,UserInfo user) {
			
		    JSONObject json = new JSONObject();
		    
		    String country = user.getCountry();
		    if(country == null || "".equals(country)){
		    	 country = "中国";
		    }
		    
	  	    json.put("Country__c", country);
	  	    
	  	    String sex = user.getSex();
	  	    
	  	    if(sex != null && !"".equals(sex)){
		        json.put("Gender__c", sex.equals("1")?"男":"女"); //
	  	    }		    
		    
		    /**
		     * 省市需要处理？？？？？？？？？
		     */
		    String provinceCode="",cityCode="";
		    String  city = user.getCity();

		    if(city != null && !"".equals(city)){
				String[] province = city.split(",");

				provinceCode=province[0];

				cityCode=province[1];
		    
				json.put("Province__c", CountryMap.getProvinceMap(provinceCode)); //省
				    
				json.put("City__c",CountryMap.getCityMap(cityCode)); //市
		    }
		    
		   // json.put("CustomerName__c", user.getName());//姓名
		    
		    if(user.getName() != null && !"".equals(user.getName())){
		        json.put("Name", user.getName());//姓名
		    }else{
		    	json.put("Name", "匿名"+ DateUtils.datetoStr(new Date(), "yyyyMMddHHmmss"));//姓名
		    }
		   
		    if(user.getBirthday() != null ){
		         json.put("Birthday__c", DateUtils.datetoStr(user.getBirthday(), "yyyy-MM-dd"));//生日
		    }
		    
		    
		    if(user.getOpenid() != null && !"".equals(user.getOpenid())){
		         json.put("OpenId__c", user.getOpenid());//姓名
		    }
		    		    
		    
		    //json.put("PhoneNumber__c", user.getMobile());
		    String moblie =user.getMobile();
		    
		    Map<String,String> map = SFDCDataCtrlUtil.upsertToSFDCData("Account", json,"PhoneNumber__c" ,moblie);
		   
		    logger.info("ID:"+map.get("ID"));
			
		}
		
		public static UserInfo query(UserInfo user) {//查询
			
			String sql = "select+Id,name,Country__c,Province__c,City__c,CustomerName__c,Birthday__c,Gender__c+from+Account+where+PhoneNumber__c='"+user.getMobile()+"'";

			JSONArray jsonArray=SFDCDataCtrlUtil.getForSFDCData(sql);
			
			logger.info("jsonArray:"+jsonArray);
			
			if(jsonArray!=null&&jsonArray.length()>0) {
				
				JSONObject obj = jsonArray.getJSONObject(0);

				user.setRemark("1"); //表示SF中有这个用户
				
				String Birthday__c = null;
				if(!obj.isNull("Birthday__c")){
					Birthday__c = obj.getString("Birthday__c");
				}

				String Country__c = null;
				if(!obj.isNull("Country__c")){
					Country__c = obj.getString("Country__c");					
					user.setCountryName(Country__c);
					user.setCountry(Country__c);
				}
				
				String City__c = null;
				if(!obj.isNull("City__c")){
					City__c = obj.getString("City__c");
				}

				String Province__c = null;
				if(!obj.isNull("Province__c")){
					Province__c = obj.getString("Province__c");
				}
				
				if(City__c!=null&&Province__c!=null) {								
					
					String province_code=CountryMap.getProvinceCodeMap(Province__c);
					String city_code=CountryMap.getCityCodeMap(City__c);
					user.setCity(CountryMap.getProvinceCodeMap(Province__c)+","+CountryMap.getCityCodeMap(City__c));
					user.setCityName(CountryMap.getCityNameMap(province_code)+","+CountryMap.getCityNameMap(city_code));

				}

				if(!obj.isNull("Name")){
					user.setName(obj.getString("Name"));
				}

				String Gender__c = null;
				if(!obj.isNull("Gender__c")){
					Gender__c = obj.getString("Gender__c");
				}

				
				if(null != Gender__c && Gender__c.equals("男")) {
					
					user.setSex("1");
					
				}else if(null != Gender__c && Gender__c.equals("女")){
					
					user.setSex("2");
				}
				
				if(Birthday__c!=null) {
					
					SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd");
					
					user.setBirthday(DateUtils.str2Date(Birthday__c, sformat));
					
				}
				
			}
			return user;
			
		}
		
}
