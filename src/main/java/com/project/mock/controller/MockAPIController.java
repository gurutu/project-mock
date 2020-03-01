package com.project.mock.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.mock.DataModal.ApiDataPojo;
import com.project.mock.DataModal.ApiMongoTemplate;
import com.project.mock.DataModal.HttpData;
import com.project.mock.Repository.ApiDataMongoRepository;
import com.project.mock.Service.ApiMockServiceImpl;


/**
 * 
 * @author Pranav
 *
 */
@RestController
public class MockAPIController {

	@Autowired
	private ApiMockServiceImpl apiDataService;

	@Autowired
	ApiDataMongoRepository apiDataMongoRepository;

	@Autowired
	private MongoOperations mongoOperations;
	
	@Autowired
	ResourceLoader resourceLoader;

	/**
	 * @implNote PostAPI CAll for User
	 * @param map
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/**", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject postDataToDatabase(@RequestHeader Map<String, String> headers, HttpServletRequest request)
			throws ParseException, IOException {
		String res=request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		HttpData data=new HttpData();
		data.setHeaderData(headers);
		data.setCookies(request.getCookies());
        JSONParser parser = new JSONParser();		
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethodAndRequest(request.getRequestURI() + uri, "POST",res,request,data); 

		String response = findByUrlPathAAndMethodAndRequest.size() > 0
				? findByUrlPathAAndMethodAndRequest.get(0).getResponse()
				: "{\"status\":\"No Result Found\"}";
		return (JSONObject) parser.parse(response);
	}

	@RequestMapping(value = "/**", method = RequestMethod.PUT, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject putDataToDatabase( HttpServletRequest request,@RequestHeader Map<String, String> headers)
			throws ParseException, IOException {
		String jsonObject=request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		HttpData data=new HttpData();
		data.setHeaderData(headers);
		data.setCookies(request.getCookies());
		JSONParser parser = new JSONParser();
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethodAndRequest(request.getRequestURI() + uri, "PUT", jsonObject,request,data);

		String response = findByUrlPathAAndMethodAndRequest.size() > 0
				? findByUrlPathAAndMethodAndRequest.get(0).getResponse()
				: "{\"status\":\"No Result Found\"}";
		return (JSONObject) parser.parse(response);
	}

	@RequestMapping(value = "/**", method = RequestMethod.PATCH, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject patchDataToDatabase( HttpServletRequest request,@RequestHeader Map<String, String> headers)
			throws ParseException, IOException {
		String jsonObject=request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		HttpData data=new HttpData();
		data.setHeaderData(headers);
		data.setCookies(request.getCookies());
		JSONParser parser = new JSONParser();
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethodAndRequest(request.getRequestURI() + uri, "PATCH", jsonObject,request,data);

		String response = findByUrlPathAAndMethodAndRequest.size() > 0
				? findByUrlPathAAndMethodAndRequest.get(0).getResponse()
				: "{\"status\":\"No Result Found\"}";
		return (JSONObject) parser.parse(response);
	}

	@RequestMapping(value = "/**", method = RequestMethod.GET, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject getDataToData(HttpServletRequest request,@RequestHeader Map<String, String> headers) throws ParseException {
		JSONParser parser = new JSONParser();
		HttpData data=new HttpData();
		data.setHeaderData(headers);
		data.setCookies(request.getCookies());
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethod(request.getRequestURI() + uri, "","GET",request,data); 

		String response = findByUrlPathAAndMethodAndRequest.size() > 0
				? findByUrlPathAAndMethodAndRequest.get(0).getResponse()
				: "{\"status\":\"No Result Found\"}";
		return (JSONObject) parser.parse(response);

	}

	@RequestMapping(value = "/**", method = RequestMethod.DELETE, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject deleteDataToDatabase(HttpServletRequest request,@RequestHeader Map<String, String> headers)
			throws ParseException, IOException {
		String jsonObject=request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		HttpData data=new HttpData();
		data.setHeaderData(headers);
		data.setCookies(request.getCookies());
		JSONParser parser = new JSONParser();
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethodAndRequest(request.getRequestURI() + uri, "DELETE", jsonObject,request,data);

		String response = findByUrlPathAAndMethodAndRequest.size() > 0
				? findByUrlPathAAndMethodAndRequest.get(0).getResponse()
				: "{\"status\":\"No Result Found\"}";
		return (JSONObject) parser.parse(response);
	}

	@RequestMapping(value = "/**", method = RequestMethod.HEAD, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject headApi(HttpServletRequest request,@RequestHeader Map<String, String> headers) throws ParseException {
		JSONParser parser = new JSONParser();
		HttpData data=new HttpData();
		data.setHeaderData(headers);
		data.setCookies(request.getCookies());
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethod(request.getRequestURI() + uri,"", "HEAD",request,data);

		String response = findByUrlPathAAndMethodAndRequest.size() > 0
				? findByUrlPathAAndMethodAndRequest.get(0).getResponse()
				: "{\"status\":\"No Result Found\"}";
		return (JSONObject) parser.parse(response);
	}

	@RequestMapping(value = "/**", method = RequestMethod.TRACE, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject traceDataToDatabase(HttpServletRequest request,@RequestHeader Map<String, String> headers)
			throws ParseException, IOException {
		String jsonObject=request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		HttpData data=new HttpData();
		data.setHeaderData(headers);
		data.setCookies(request.getCookies());
		JSONParser parser = new JSONParser();
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethodAndRequest(request.getRequestURI() + uri, "TRACE", jsonObject,request,data);

		String response = findByUrlPathAAndMethodAndRequest.size() > 0
				? findByUrlPathAAndMethodAndRequest.get(0).getResponse()
				: "{\"status\":\"No Result Found\"}";
		return (JSONObject) parser.parse(response);
	}

	@RequestMapping(value = "/**", method = RequestMethod.OPTIONS, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject optionDataToDatabase(HttpServletRequest request,@RequestHeader Map<String, String> headers)
			throws ParseException, IOException {
		String jsonObject=request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		HttpData data=new HttpData();
		data.setHeaderData(headers);
		data.setCookies(request.getCookies());
		JSONParser parser = new JSONParser();
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethodAndRequest(request.getRequestURI() + uri, "OPTIONS", jsonObject,request,data);

		String response = findByUrlPathAAndMethodAndRequest.size() > 0
				? findByUrlPathAAndMethodAndRequest.get(0).getResponse()
				: "{\"status\":\"No Result Found\"}";
		return (JSONObject) parser.parse(response);
	}

	/**
	 * @implNote For UI Application Api
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/project-mock/save-data", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			"application/json" })
	public Map<String, String> sendDataToDatabase(@RequestBody ApiDataPojo map, HttpServletRequest request) {
		//map.forEach(e -> {
		apiDataService
					.saveData(new ApiMongoTemplate(map.getUrlPath(), map.getMethod(), map.getRequest(), map.getResponse()));
		//});
		return new HashMap<>();
	}

	@RequestMapping(value = "/project-mock/search", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			"application/json" })
	public List<ApiMongoTemplate> getData(@RequestBody Map<String, Object> payload, HttpServletRequest request) {
		return apiDataService.findEmployeeByName(payload.get("searchQuery").toString());
	}
	
	@RequestMapping(value = "/project-mock/searchbyUriandmethod", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
		"application/json" })
	public List<ApiMongoTemplate> searchByUriAndMethod(@RequestBody Map<String, String> payload, HttpServletRequest request) {
	return apiDataService.findByUrlPathAndMethodForUI( payload.get("display").toString(), payload.get("value").toString());
	}

	@RequestMapping(value = "/project-mock/searchbyid", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			"application/json" })
	public ApiMongoTemplate searchById(@RequestBody Map<String, String> payload, HttpServletRequest request) {
		return apiDataService.findById(payload.get("Id"));
	}

	@RequestMapping(value = "/project-mock/deletebyid", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			"application/json" })
	public boolean deleteById(@RequestBody Map<String, String> payload, HttpServletRequest request) {
		return apiDataService.deleteById(payload.get("id"));
	}

	@RequestMapping(value = "/project-mock/deletebyUrlAndMethod", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			"application/json" })
	public boolean deleteByUrlAndMethod(@RequestBody Map<String, String> payload, HttpServletRequest request) {
		return apiDataService.deleteByUrlAndMethod(payload.get("url"), payload.get("method"));
	}
	

	@RequestMapping(value = "/js/**", method = RequestMethod.GET)
	public byte[] getJSFile(HttpServletRequest request)  {
		
		
		
		byte[] bytes = null;
		try {
			Resource resource = resourceLoader.getResource("classpath:static" + request.getRequestURI());
		    InputStream inputStream = resource.getInputStream();
			bytes =FileCopyUtils.copyToByteArray(inputStream);// Files.readAllBytes(path);
		} catch (Exception e) {
		}
		return bytes;
	}

	@RequestMapping(value = "/css/**", method = RequestMethod.GET)
	public byte[] getCSSFile(HttpServletRequest request)  {
		
		
		byte[] bytes = null;
		try {
			Resource resource = resourceLoader.getResource("classpath:static" + request.getRequestURI());
		    InputStream inputStream = resource.getInputStream();
			bytes =FileCopyUtils.copyToByteArray(inputStream);// Files.readAllBytes(path);
		} catch (Exception e) {
		}
		return bytes;
	}

	@RequestMapping(value = "/img/**", method = RequestMethod.GET)
	public byte[] getImageFile(HttpServletRequest request)  {
		
		
		byte[] bytes = null;
		try {
			Resource resource = resourceLoader.getResource("classpath:static" + request.getRequestURI());
		    InputStream inputStream = resource.getInputStream();
			bytes =FileCopyUtils.copyToByteArray(inputStream);// Files.readAllBytes(path);
		} catch (Exception e) {
		}
		return bytes;
	}

}
