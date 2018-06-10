package com.sail.simonli.server.util;


/**
 * 2018-05-28
 * @author admin
 *
 */
public class Result {
	public String status; // 执行结果：1成功、0失败
	public String message; // 执行消息
	public String url; // 执行消息
	public Object data; // 返回结果对象
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}


}
