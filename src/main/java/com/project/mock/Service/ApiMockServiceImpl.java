package com.project.mock.Service;

import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import  org.junit.*;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

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
	public List<ApiMongoTemplate> findByUrlPathAndMethodAndRequest(String urlPath, String method, String request,HttpServletRequest httpRequest) {
		
		List<ApiMongoTemplate> ls=new ArrayList<>();
		Map<String,String> mp=new HashMap<>();
		List<ApiMongoTemplate> findByUrlPathAndMethod = this.findByUrlPathAndMethod(urlPath, method,httpRequest);
		try {
		
		for (ApiMongoTemplate apiMongoTemplate : findByUrlPathAndMethod) {
			List<String> findvalueInResponse = findvalueInResponse(apiMongoTemplate.getRequest());
			String req=apiMongoTemplate.getRequest();
			List<String> value =new ArrayList<>();
			for (String string : findvalueInResponse) {
				
				try {
					if(string.substring(0,1).equals("B")){
						value = getValue(request, string.substring(1));
					}else if(string.subSequence(0, 2).equals("Q&")) {
						value=getValueInQuery(httpRequest.getQueryString(), string.substring(2));
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				mp.put("${"+string+"}", value.get(0).replace("\"", ""));
				req=req.replace("${"+string+"}", value.get(0).replace("\"", ""));
			}
			if(jsonCompare(request,req)) {
				ls.add(new ApiMongoTemplate(apiMongoTemplate.getId(),urlPath, method, request,apiMongoTemplate.getResponse()));
				break;
			}else {
				mp.clear();
			}
		}
		if(ls.size()!=0) {
		String responseValue=ls.get(0).getResponse();
		for (Entry<String, String> m : mp.entrySet()) {
			responseValue=responseValue.replace(m.getKey(),m.getValue());
		}
		 ls.get(0).setResponse(responseValue);;
		}
		}catch(Exception e) {
			
		}
		return ls;
	}

	@Override
	public List<ApiMongoTemplate> findByUrlPathAndMethodForUI(String urlPath, String method) {
		// TODO Auto-generated method stub
		return apiDataMongoRepository.findByUrlPathAndMethod(urlPath, method);
	}
	
	private static Map<String,Object> getCorrectUrl(List<ApiMongoTemplate> findByUrlPathAndMethod,String mainUrl,String queryValue) {
		List<ApiMongoTemplate> list=new ArrayList<>();
		boolean flag=false;
		Map<String,Object> map=new HashMap<>();
		Map<String,String> mapval=new HashMap<>();
		try {
			mainUrl=URLDecoder.decode( mainUrl, "UTF-8" );
		for (ApiMongoTemplate apiMongoTemplate : findByUrlPathAndMethod) {// template url
			String tempUrl=apiMongoTemplate.getUrlPath();
			//if(flag)break;
			List<String> findvalueInResponse = findvalueInResponse(tempUrl);//find value match //${Q&h1} -> Q&h1
			List<String> value =new ArrayList<>();
			for (String str : findvalueInResponse) {
				if(str.subSequence(0, 2).equals("Q&")) {
					value=getValueInQuery(URLDecoder.decode( queryValue, "UTF-8" ), str.substring(2));
				}
				tempUrl=tempUrl.replace("${"+str+"}", value.get(0).replace("\"", ""));
				mapval.put(str, value.get(0).replace("\"", ""));
			}
			if("GET,HEAD".contains(apiMongoTemplate.getMethod())&&tempUrl.equals(mainUrl)) {
				list.clear();
				list.add(apiMongoTemplate);
				flag=true;
				break;
				
			}else if(!"GET,HEAD".contains(apiMongoTemplate.getMethod())&&tempUrl.equals(mainUrl)){
				//if(jsonCompare(request,req)) {
					list.add(apiMongoTemplate);
					//break;
				//}
			}else {
				mapval.clear();
			}
			
			
		}
		}catch(Exception e) {
			
		}
		map.put("ApiMongoTemplate", list);
		map.put("MapValue", mapval);
		return map;
	}
	
	@Override
	public List<ApiMongoTemplate> findByUrlPathAndMethod(String urlPath, String method, HttpServletRequest request) {
		// TODO Auto-generated method stub
		//System.out.println(request.getQueryString());
		List<ApiMongoTemplate> findByUrlPathAndMethod2 = apiDataMongoRepository.findByUrlAndMethodReg(request.getRequestURI(), method);
		int index=0;
		List<ApiMongoTemplate> apiMongoTemplatedata=new ArrayList<>();
		Map<String,Object> mapData=getCorrectUrl(findByUrlPathAndMethod2,urlPath,request.getQueryString());
		List<ApiMongoTemplate> findByUrlPathAndMethod = (List<ApiMongoTemplate>) mapData.get("ApiMongoTemplate");
		Map<String,String> mapkeyvalue=(Map<String, String>) mapData.get("MapValue");
		for (ApiMongoTemplate apiMongoTemplate : findByUrlPathAndMethod) {
			String response=apiMongoTemplate.getResponse();
			for (Entry<String, String> m : mapkeyvalue.entrySet()) {
				response=response.replace("${"+m.getKey()+"}", m.getValue().replace("\"", ""));
				}
			apiMongoTemplate.setResponse(response);
			apiMongoTemplatedata.add(apiMongoTemplate);
		}
		
		return findByUrlPathAndMethod;
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
		List<ApiMongoTemplate> findByUrlPathAndMethod = apiDataMongoRepository.findByUrlPathAndMethod(data.getUrlPath(),data.getMethod());
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
		List<ApiMongoTemplate> findByUrlPathAndMethod = apiDataMongoRepository.findByUrlPathAndMethod(url, method);
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
	private static void checked(int j,String format,String key, JsonElement jsonElement,List<String> list) {
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
		                checked(i,format,key, entry.getValue(),list);
		             }
				}
			}else if(format.charAt(i)=='[') {
				if (jsonElement.isJsonArray()) {
				JsonElement jsonElement2 = jsonElement.getAsJsonArray().get(Integer.parseInt(String.valueOf(format.charAt(i+1))));
								checked(i,format,key, jsonElement2,list);
				}
				}
			}
		}

		
	
	private static List<String> getValue(String json,String format) throws JsonMappingException, JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		JsonParser  parser = new JsonParser();
		List<String> list = new ArrayList<String>();
		int properString = getProperString(format);
		checked(0,format.substring(0, properString),format.substring(properString, format.length()),parser.parse(json),list);

		return list;
	}
	private static List<String> getValueInQuery(String queryParam,String format) {
		List<String> list=new ArrayList<>();
		String[] split = queryParam.split("&");
		for (String string : split) {
			String querySplit[]=string.split("=");
			if(format.equals(querySplit[0])) {
				list.add(querySplit[1]);
				break;
			}
		}
		return list;
	}
	private static List<String> findvalueInResponse(String res) {
		 Pattern pattern = Pattern.compile("\\$\\{([^\\}]+)\\}", Pattern.MULTILINE);
		 Matcher matcher = pattern.matcher(res);
         List<String> list=new ArrayList<>();
		while (matcher.find()) {
		  //  System.out.println("Full match: " + matcher.group(0));
		    //list.add(matcher.group(0));
		    for (int i = 1; i <= matcher.groupCount(); i++) {
		   //     System.out.println("Group " + i + ": " + matcher.group(i));
		        list.add(matcher.group(i));
		    }
		}
		return list;
	}
private static int getProperString(String st) {
 int index=0;
	for (int i = 0; i < st.length(); i++) {
		if(String.valueOf(st.charAt(i)).matches("[a-zA-Z]+")) {
			index=i;
			break;
		}
		index=i;
	}
	return index;
}
	
	static List<String> list = new ArrayList<String>();
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException, ParseException {
		//String properString = getProperString("{{h1");
		//System.out.println(properString);
		List<String> value = getValue("[{\"Hello\":\"hello\",\"Helo\":[{\"h1\":\"h2\",\"h4\":\"h7\"},{\"h1\":\"h4\"}]}]","[0{[0{h1");
		value.forEach(a->{System.out.println(a);});
	}

	
     
	
}
