package com.project.mock.Service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.project.mock.DataModal.ApiData;
import com.project.mock.DataModal.ApiMongoTemplate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface ApiMockService {
    
	List<ApiMongoTemplate> findEmployeeByName(String url);

	List<ApiMongoTemplate> findByUrlPathAndMethodAndRequest(String urlPath, String method, String request,HttpServletRequest httpRequest);

	List<ApiMongoTemplate> findByUrlPathAndMethod(String urlPath, String method,HttpServletRequest request);
	
	List<ApiMongoTemplate> findByUrlPathAndMethodForUI(String urlPath, String method);
	
	
	ApiMongoTemplate findById(String id);
	
	boolean saveData(ApiMongoTemplate data);
	
	boolean deleteById(String id);
	
	boolean deleteByUrlAndMethod(String url,String method);
}
