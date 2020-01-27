package com.project.mock.DataModal;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.BasicDBObject;

import java.util.Date;

import org.springframework.data.annotation.Id;

@Document
public class SearchDataTemplate {

	@Id
	private String Id;
	private String method;
	private String urlPath;
	private String ApiMongoId;
	private BasicDBObject currentDate;
	
	public SearchDataTemplate() {
		
	}
	
	
	public SearchDataTemplate( String method, String urlPath, String apiMongoId) {
		super();
		this.method = method;
		this.urlPath = urlPath;
		this.ApiMongoId = apiMongoId;
		this.currentDate= new BasicDBObject("date", new Date());
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUrlPath() {
		return urlPath;
	}
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	public String getApiMongoId() {
		return ApiMongoId;
	}
	public void setApiMongoId(String apiMongoId) {
		ApiMongoId = apiMongoId;
	}


	public BasicDBObject getCurrentDate() {
		return currentDate;
	}


	public void setCurrentDate(BasicDBObject currentDate) {
		this.currentDate = currentDate;
	}
	
	
	
}
