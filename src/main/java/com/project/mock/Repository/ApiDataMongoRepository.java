package com.project.mock.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.project.mock.DataModal.ApiData;
import com.project.mock.DataModal.ApiMongoTemplate;

public interface ApiDataMongoRepository extends  MongoRepository<ApiMongoTemplate, String>{

	List<ApiMongoTemplate> findByUrlPath(String replace);
	
	@Query("{'urlPath': {$regex: '.*?0.*'} }")
	//@Query("{'urlPath': {$regex: '*?0*'} }")
	List<ApiMongoTemplate> findEmployeeByName(String url);

	//List<> findEmployeeByName(String url);
	  List<ApiMongoTemplate> findByUrlPathAndMethodAndRequest(String urlPath,String method,String request);
	  
	  List<ApiMongoTemplate> findByUrlPathAndMethod(String urlPath,String method);
}
