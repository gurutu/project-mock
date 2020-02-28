package com.project.mock.util;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.project.mock.DataModal.ApiMongoTemplate;

/**
 * 
 * @author Pranav
 * 
 *
 */
public class MockUtil {

	/**
	 * 
	 * @param s1 == [{\"Hello\":\"hello\",\"Helo\":[{\"h1\":\"h2\",\"h4\":\"h7\"},{\"h1\":\"h4\"}]}]
	 * @param s2 == [{\"Hello\":\"hello\",\"Helo\":[{\"h1\":\"h2\",\"h4\":\"h7\"},{\"h1\":\"h4\"}]}]
	 * @return
	 */
	public  static boolean jsonDataCompare(String s1,String s2)  {
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
	
	/**
	 * Finding the Value from  JSON   
	 * @param j
	 * @param format define the Location
	 * @param key     properties of JSON
	 * @param jsonElement JSON Object
	 * @param list      Find The value
	 */
	public static void checked(int j,String format,String key, JsonElement jsonElement,List<String> list) {
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
	
	/**
	 * 
	 * @param json     == [{\"Hello\":\"hello\",\"Helo\":[{\"h1\":\"h2\",\"h4\":\"h7\"},{\"h1\":\"h4\"}]}]
	 * @param format   == [0{[0{h1
	 * @return List<String>  == [h2]
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @throws ParseException
	 */
	public static List<String> getValue(String json,String format) throws JsonMappingException, JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		JsonParser  parser = new JsonParser();
		List<String> list = new ArrayList<String>();
		int properString = getProperString(format);
		checked(0,format.substring(0, properString),format.substring(properString, format.length()),parser.parse(json),list);

		return list;
	}
	
	/**
	 * 
	 * @param st    ==  [0{[0{h1
	 * @return  Int index ==  6
	 */
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
	
	/**
	 * 
	 * @param findByUrlPathAndMethod
	 * @param mainUrl
	 * @param queryValue
	 * @return
	 */
	public  static Map<String,Object> getCorrectUrl(List<ApiMongoTemplate> findByUrlPathAndMethod,String mainUrl,String queryValue) {
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
	
	
	
	public static List<String> getValueInQuery(String queryParam,String format) {
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
	public static List<String> findvalueInResponse(String res) {
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
	
}
