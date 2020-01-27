package com.project.mock.Service;

import java.util.List;
import java.util.Optional;

import com.project.mock.DataModal.ApiData;
import com.project.mock.DataModal.ApiMongoTemplate;
import com.project.mock.Repository.ApiDataMongoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Pranav
 *
 */
@Service
public class ApiMockServiceImpl implements ApiMockService {

	   @Autowired ApiDataMongoRepository apiDataMongoRepository;
	
	@Override
	public List<ApiMongoTemplate> findEmployeeByName(String url) {
		// TODO Auto-generated method stub
		return apiDataMongoRepository.findEmployeeByName(url);
	}

	@Override
	public List<ApiMongoTemplate> findByUrlPathAndMethodAndRequest(String urlPath, String method, String request) {
		// TODO Auto-generated method stub
		return apiDataMongoRepository.findByUrlPathAndMethodAndRequest(urlPath, method, request);
	}

	@Override
	public List<ApiMongoTemplate> findByUrlPathAndMethod(String urlPath, String method) {
		// TODO Auto-generated method stub
		return apiDataMongoRepository.findByUrlPathAndMethod(urlPath, method);
	}
     
	
}
