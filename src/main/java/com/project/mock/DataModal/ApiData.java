package com.project.mock.DataModal;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 
 * @author Pranav
 *
 */
@Document(indexName = "mock-data", type = "ApiData")
public class ApiData {

    @Id
    private String id;
    private String urlPath;
    
    private String method;
    private String request;
    private String response;

    public ApiData() {
		super();
	}

	public ApiData(String urlPath, String method, String request, String response) {
		super();
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
