package com.project.mock.DataModal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ApiMongoTemplate {

	    @Id
	    private String id;
	    
	   
	    private String urlPath; 
	    
	    private String method;
	    private String request;
	    private String response;
	    
	    public ApiMongoTemplate() {
			// TODO Auto-generated constructor stub
		}
	    
		public ApiMongoTemplate(String urlPath, String method, String request, String response) {
			super();
			this.urlPath = urlPath;
			this.method = method;
			this.request = request;
			this.response = response;
		}
		
		public ApiMongoTemplate(String id,String urlPath, String method, String request, String response) {
			super();
			this.id=id;
			this.urlPath = urlPath;
			this.method = method;
			this.request = request;
			this.response = response;
		}
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUrlPath() {
			return urlPath;
		}
		public void setUrlPath(String urlPath) {
			this.urlPath = urlPath;
		}
		public String getMethod() {
			return method;
		}
		public void setMethod(String method) {
			this.method = method;
		}
		public String getRequest() {
			return request;
		}
		public void setRequest(String request) {
			this.request = request;
		}
		public String getResponse() {
			return response;
		}
		public void setResponse(String response) {
			this.response = response;
		}
	
}
