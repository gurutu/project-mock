package com.project.mock.Service;

import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import  org.junit.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
		List<ApiMongoTemplate> list=new ArrayList<>();
		List<ApiMongoTemplate> findEmployeeByName = apiDataMongoRepository.findEmployeeByName(url);
		for (ApiMongoTemplate apiMongoTemplate : findEmployeeByName) {
			if(!list.parallelStream().anyMatch(a -> a.getUrlPath().equals(apiMongoTemplate.getUrlPath())&&a.getMethod().equals(apiMongoTemplate.getMethod()))) {
				list.add(apiMongoTemplate);
			}
		}
		return  list;
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
		if(s1==null&&s2==null) {
			return true;
		}
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
		String id="";
		List<ApiMongoTemplate> findByUrlPathAndMethod = this.findByUrlPathAndMethod(data.getUrlPath(),data.getMethod());
		for (ApiMongoTemplate apiMongoTemplate : findByUrlPathAndMethod) {
			if(jsonCompare(data.getRequest(),apiMongoTemplate.getRequest())) {
				flag=false;
				id=apiMongoTemplate.getId();
				break;
			}
		}
		if(flag) {
			apiDataMongoRepository.save(data);
			}else {
				data.setId(id);
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

	
	/**
	 * {"Hello":"hello","Helo":[{"h1":"h2"}]}
		PATH
		PARAM
		BODY
		B{[h1
	 * @param args
	 */
	private static String find(String value) {
		if(value.matches("[0-9]+")) {
			return "D";
		}else if(value.matches("[a-zA-Z]+")) {
			return "W";
		}
		return "X";
	}
	private static void checked(int j,String format,String key, JsonElement jsonElement) {
		for (int i = j; i < format.length(); i++) {
			if(format.charAt(i)=='{') {
				
				if (jsonElement.isJsonObject()) {
				   Set<Map.Entry<String, JsonElement>> entrySet = jsonElement
		                    .getAsJsonObject().entrySet();
		            for (Map.Entry<String, JsonElement> entry : entrySet) {
		                String key1 = entry.getKey();
		                if (key1.equals(key)) {
		                    list.add(entry.getValue().toString());
		                }
		                checked(i,format,key, entry.getValue());
		             }
				}
			}else if(format.charAt(i)=='[') {
				if (jsonElement.isJsonArray()) {
				JsonElement jsonElement2 = jsonElement.getAsJsonArray().get(Integer.parseInt(String.valueOf(format.charAt(i+1))));
								checked(i,format,key, jsonElement2);
				}
				}
			}
		}

		
	
	private static String getValue(String json,String format) throws JsonMappingException, JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		JsonParser  parser = new JsonParser();
		checked(0,format,"h4",parser.parse(json));

		return "";
	}
	private static List<String> findvalueInResponse(String res) {
		 Pattern pattern = Pattern.compile("\\$\\{([^\\}]+)\\}", Pattern.MULTILINE);
		 Matcher matcher = pattern.matcher(res);
         List<String> list=new ArrayList<>();
		while (matcher.find()) {
		    System.out.println("Full match: " + matcher.group(0));
		    list.add(matcher.group(0));
		    for (int i = 1; i <= matcher.groupCount(); i++) {
		        System.out.println("Group " + i + ": " + matcher.group(i));
		    }
		}
		return list;
	}

	
	static List<String> list = new ArrayList<String>();
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException, ParseException {
		
		getValue("[{\"Hello\":\"hello\",\"Helo\":[{\"h1\":\"h2\",\"h4\":\"h7\"},{\"h1\":\"h4\"}]}]","[0{[0{");
		list.forEach(a->{System.out.println(a);});
	}
     
	
}
