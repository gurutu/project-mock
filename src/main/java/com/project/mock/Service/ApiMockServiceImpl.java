package com.project.mock.Service;

import  org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		String response="";
		String id="";
		//boolean flag=true;
		List<ApiMongoTemplate> ls=new ArrayList<>();
		List<ApiMongoTemplate> findByUrlPathAndMethod = this.findByUrlPathAndMethod(urlPath, method);
		for (ApiMongoTemplate apiMongoTemplate : findByUrlPathAndMethod) {
			if(jsonCompare(request,apiMongoTemplate.getRequest())) {
				
				ls.add(new ApiMongoTemplate(apiMongoTemplate.getId(),urlPath, method, request,apiMongoTemplate.getResponse()));
				break;
			}
		}
		
		
		return ls;
	}

	@Override
	public List<ApiMongoTemplate> findByUrlPathAndMethod(String urlPath, String method) {
		// TODO Auto-generated method stub
		return apiDataMongoRepository.findByUrlPathAndMethod(urlPath, method);
	}
	private static boolean jsonCompare(String s1,String s2)  {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode tree1;
		JsonNode tree2 ;
		try {
			tree1 = mapper.readTree(s1);
			tree2=mapper.readTree(s2);
			boolean areTheyEqual = tree1.equals(tree2);
			return areTheyEqual;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}

	@Override
	public ApiMongoTemplate findById(String id) {
		// TODO Auto-generated method stub
		return apiDataMongoRepository.findById(id).get();
	}

	@Override
	public boolean saveData(ApiMongoTemplate data) {
		boolean flag=true;
		List<ApiMongoTemplate> findByUrlPathAndMethod = this.findByUrlPathAndMethod(data.getUrlPath(),data.getMethod());
		for (ApiMongoTemplate apiMongoTemplate : findByUrlPathAndMethod) {
			if(jsonCompare(data.getRequest(),apiMongoTemplate.getRequest())) {
				flag=false;
				break;
			}
		}
		if(flag) {
			apiDataMongoRepository.save(data);
			}
		return flag;
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		try {
			apiDataMongoRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteByUrlAndMethod(String url, String method) {
		List<ApiMongoTemplate> findByUrlPathAndMethod = this.findByUrlPathAndMethod(url, method);
		for (ApiMongoTemplate apiMongoTemplate : findByUrlPathAndMethod) {
			this.deleteById(apiMongoTemplate.getId());
		}
		return true;
	}

     
	
}
