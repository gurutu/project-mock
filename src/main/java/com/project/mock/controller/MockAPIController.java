package com.project.mock.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.mock.DataModal.ApiDataPojo;
import com.project.mock.DataModal.ApiMongoTemplate;
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

	/**
	 * @implNote PostAPI CAll for User
	 * @param map
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/**", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject postDataToDatabase(@RequestBody Map<String, String> map, HttpServletRequest request)
			throws ParseException {
		JSONObject jsonObject = new JSONObject(map);
		JSONParser parser = new JSONParser();
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethodAndRequest(request.getRequestURI() + uri, "POST", jsonObject.toString()); 

		String response = findByUrlPathAAndMethodAndRequest.size() > 0
				? findByUrlPathAAndMethodAndRequest.get(0).getResponse()
				: "{\"status\":\"No Result Found\"}";
		return (JSONObject) parser.parse(response);
	}

	@RequestMapping(value = "/**", method = RequestMethod.PUT, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject putDataToDatabase(@RequestBody Map<String, String> map, HttpServletRequest request)
			throws ParseException {
		JSONObject jsonObject = new JSONObject(map);
		JSONParser parser = new JSONParser();
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethodAndRequest(request.getRequestURI() + uri, "PUT", jsonObject.toString());

		String response = findByUrlPathAAndMethodAndRequest.size() > 0
				? findByUrlPathAAndMethodAndRequest.get(0).getResponse()
				: "{\"status\":\"No Result Found\"}";
		return (JSONObject) parser.parse(response);
	}

	@RequestMapping(value = "/**", method = RequestMethod.PATCH, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject patchDataToDatabase(@RequestBody Map<String, String> map, HttpServletRequest request)
			throws ParseException {
		JSONObject jsonObject = new JSONObject(map);
		JSONParser parser = new JSONParser();
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethodAndRequest(request.getRequestURI() + uri, "PATCH", jsonObject.toString());

		String response = findByUrlPathAAndMethodAndRequest.size() > 0
				? findByUrlPathAAndMethodAndRequest.get(0).getResponse()
				: "{\"status\":\"No Result Found\"}";
		return (JSONObject) parser.parse(response);
	}

	@RequestMapping(value = "/**", method = RequestMethod.GET, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject getDataToData(HttpServletRequest request) throws ParseException {
		JSONParser parser = new JSONParser();
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethod(request.getRequestURI() + uri, "GET"); 

		String response = findByUrlPathAAndMethodAndRequest.size() > 0
				? findByUrlPathAAndMethodAndRequest.get(0).getResponse()
				: "{\"status\":\"No Result Found\"}";
		return (JSONObject) parser.parse(response);

	}

	@RequestMapping(value = "/**", method = RequestMethod.DELETE, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject deleteDataToData(HttpServletRequest request) throws ParseException {
		JSONParser parser = new JSONParser();
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethod(request.getRequestURI() + uri, "DELETE");

		String response = findByUrlPathAAndMethodAndRequest.size() > 0
				? findByUrlPathAAndMethodAndRequest.get(0).getResponse()
				: "{\"status\":\"No Result Found\"}";
		return (JSONObject) parser.parse(response);
	}

	@RequestMapping(value = "/**", method = RequestMethod.HEAD, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject headApi(HttpServletRequest request) throws ParseException {
		JSONParser parser = new JSONParser();
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethod(request.getRequestURI() + uri, "HEAD");

		String response = findByUrlPathAAndMethodAndRequest.size() > 0
				? findByUrlPathAAndMethodAndRequest.get(0).getResponse()
				: "{\"status\":\"No Result Found\"}";
		return (JSONObject) parser.parse(response);
	}

	@RequestMapping(value = "/**", method = RequestMethod.TRACE, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject traceApi(HttpServletRequest request) throws ParseException {
		JSONParser parser = new JSONParser();
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethod(request.getRequestURI() + uri, "TRACE");

		String response = findByUrlPathAAndMethodAndRequest.size() > 0
				? findByUrlPathAAndMethodAndRequest.get(0).getResponse()
				: "{\"status\":\"No Result Found\"}";
		return (JSONObject) parser.parse(response);
	}

	@RequestMapping(value = "/**", method = RequestMethod.OPTIONS, headers = "Accept=*/*", produces = {
			"application/json" })
	public JSONObject optionApi(HttpServletRequest request) throws ParseException {
		JSONParser parser = new JSONParser();
		String uri = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest = apiDataService
				.findByUrlPathAndMethod(request.getRequestURI() + uri, "OPTIONS");

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
			apiDataMongoRepository
					.save(new ApiMongoTemplate(map.getUrlPath(), map.getMethod(), map.getRequest(), map.getResponse()));
		//});
		return new HashMap<>();
	}

	@RequestMapping(value = "/project-mock/search", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			"application/json" })
	public List<ApiMongoTemplate> getData(@RequestBody Map<String, Object> payload, HttpServletRequest request) {
		return apiDataMongoRepository.findEmployeeByName(payload.get("searchQuery").toString());
	}
	
	@RequestMapping(value = "/project-mock/searchbyUriandmethod", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
		"application/json" })
	public List<ApiMongoTemplate> searchByUriAndMethod(@RequestBody Map<String, Object> payload, HttpServletRequest request) {
	return apiDataMongoRepository.findByUrlPathAndMethod( payload.get("display").toString(), payload.get("value").toString());
	}
	
	@RequestMapping(value = "/project-mock/searchbyid", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
	"application/json" })
public ApiMongoTemplate searchById(@RequestBody Map<String, String> payload, HttpServletRequest request) {
return apiDataMongoRepository.findById(payload.get("Id")).get();
}
	

	@RequestMapping(value = "/js/**", method = RequestMethod.GET)
	public byte[] getJSFile(HttpServletRequest request) {
		Path path = Paths.get(".\\src\\main\\resources\\static\\" + request.getRequestURI());
		byte[] bytes = null;
		try {
			bytes = Files.readAllBytes(path);
		} catch (Exception e) {
		}
		return bytes;
	}

	@RequestMapping(value = "/css/**", method = RequestMethod.GET)
	public byte[] getCSSFile(HttpServletRequest request) {
		Path path = Paths.get(".\\src\\main\\resources\\static\\" + request.getRequestURI());
		byte[] bytes = null;
		try {
			bytes = Files.readAllBytes(path);
		} catch (Exception e) {
		}
		return bytes;
	}

	@RequestMapping(value = "/img/**", method = RequestMethod.GET)
	public byte[] getImageFile(HttpServletRequest request) {
		Path path = Paths.get(".\\src\\main\\resources\\static\\" + request.getRequestURI());
		byte[] bytes = null;
		try {
			bytes = Files.readAllBytes(path);
		} catch (Exception e) {
		}
		return bytes;
	}

}
