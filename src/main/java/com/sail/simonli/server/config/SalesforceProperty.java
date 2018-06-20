package com.sail.simonli.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "salesforce")
public class SalesforceProperty {
	
	private String username="wang_nick@allergan.com.ap7.test";
	
	private String password="Allergan123";
	
	private String loginurl = "https://test.salesforce.com";
	
	private String grantservice="/services/oauth2/token?grant_type=password";
	
	private String clientid="3MVG9e2mBbZnmM6kt2.w5N9yFTtU1ow7ssunn2WH8w9AYSbGElkyeReteO015Llx6vsVS70GBKXOuWA1vwkYG";
	
	private String clientsecret="1296866769507064557";
	
	private String rest_endpoint="/services/data/v36.0/";
	
	private String instance_url="";
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginurl() {
		return loginurl;
	}

	public void setLoginurl(String loginurl) {
		this.loginurl = loginurl;
	}

	public String getGrantservice() {
		return grantservice;
	}

	public void setGrantservice(String grantservice) {
		this.grantservice = grantservice;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getClientsecret() {
		return clientsecret;
	}

	public void setClientsecret(String clientsecret) {
		this.clientsecret = clientsecret;
	}

	public String getRest_endpoint() {
		return rest_endpoint;
	}

	public void setRest_endpoint(String rest_endpoint) {
		this.rest_endpoint = rest_endpoint;
	}

	public String getInstance_url() {
		return instance_url;
	}

	public void setInstance_url(String instance_url) {
		this.instance_url = instance_url;
	}

	
	 
}
