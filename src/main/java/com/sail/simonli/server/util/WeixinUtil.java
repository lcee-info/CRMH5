package com.sail.simonli.server.util;

import javax.servlet.http.HttpServletRequest;

public class WeixinUtil {
	
	public static int isWeChat(HttpServletRequest request){
		String agent = request.getHeader("User-agent");
//		System.out.println(agent);
		if(agent.toLowerCase().indexOf("micromessenger")>0){
			return 1;
		}
		return 0;
	}

}
