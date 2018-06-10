package com.sail.simonli.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "weixin")
public class CustomProperty {
	
	public static final String DOMIAN = "http://www.ct918.com";
	
	private String appid="wxd86e2b0f2f4be055";
	
	private String secret="ba2bf3b8e2815737e27df65b2cf66801";
	
	private String scope = "snsapi_base";

	
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	

}
