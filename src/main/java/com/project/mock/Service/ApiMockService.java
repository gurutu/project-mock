package com.project.mock.Service;

import java.util.List;
import java.util.Optional;

import com.project.mock.DataModal.ApiData;
import com.project.mock.DataModal.ApiMongoTemplate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface ApiMockService {
    
	List<ApiMongoTemplate> findEmployeeByName(String url);

	List<ApiMongoTemplate> findByUrlPathAndMethodAndRequest(String urlPath, String method, String request);

	List<ApiMongoTemplate> findByUrlPathAndMethod(String urlPath, String method);
}
