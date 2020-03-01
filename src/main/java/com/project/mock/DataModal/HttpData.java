package com.project.mock.DataModal;

import java.util.Map;

import javax.servlet.http.Cookie;

public class HttpData {

	private String requestData;
	private String method;
	//private String responseData;
	private String queryData;
	private Map<String, String> headerData;
	private String Url;
	private Cookie[] cookies;
	public String getRequestData() {
		return requestData;
	}
	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getQueryData() {
		return queryData;
	}
	public void setQueryData(String queryData) {
		this.queryData = queryData;
	}
	public Map<String, String> getHeaderData() {
		return headerData;
	}
	public void setHeaderData(Map<String, String> headers) {
		this.headerData = headers;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public Cookie[] getCookies() {
		return cookies;
	}
	public void setCookies(Cookie[] cookies) {
		this.cookies = cookies;
	}
	
	
   
}
