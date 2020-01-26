package com.project.mock.controller;

import com.project.mock.DataModal.ApiData;
import com.project.mock.DataModal.ApiDataPojo;
import com.project.mock.DataModal.ApiMongoTemplate;
import com.project.mock.Repository.ApiDataMongoRepository;
import com.project.mock.Service.ApiDataService;
import com.project.mock.Service.ApiDataServiceImpl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.index.TextIndexDefinition.TextIndexDefinitionBuilder;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.*;

/**
 * @author Pranav
 * @apiNote Comman Controller
 */
@RestController
public class MockController {

    @Autowired
    private ApiDataServiceImpl apiDataService;
    
    @Autowired ApiDataMongoRepository apiDataMongoRepository;
    
    @Autowired
    private MongoOperations mongoOperations;

    /**
     * @implNote PostAPI CAll for User
     * @param map
     * @param request
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value = "/**", method = RequestMethod.POST, headers="Accept=*/*", produces = { "application/json" })
    public JSONObject postDataToDatabase(@RequestBody Map<String,String> map, HttpServletRequest request) throws ParseException{
     JSONObject jsonObject = new JSONObject(map);
       JSONParser parser = new JSONParser();
      String uri=request.getQueryString()==null?"":"?"+request.getQueryString();
      List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest =apiDataMongoRepository.findByUrlPathAndMethodAndRequest(request.getRequestURI()+uri, "POST", jsonObject.toString());  // apiDataService.findByFilteredTagQuery(request.getRequestURI()+uri, jsonObject.toString().replace("\"", "\\\"").replace("\"", "\\\""));
        
      String response=findByUrlPathAAndMethodAndRequest.size()>0?findByUrlPathAAndMethodAndRequest.get(0).getResponse():"{\"status\":\"No Result Found\"}";   
      return (JSONObject)parser.parse(response);
    }

    @RequestMapping(value = "/**", method = RequestMethod.PUT, headers="Accept=*/*", produces = { "application/json" })
    public JSONObject putDataToDatabase(@RequestBody Map<String,String> map, HttpServletRequest request) throws ParseException{
    	 JSONObject jsonObject = new JSONObject(map);
         JSONParser parser = new JSONParser();
        String uri=request.getQueryString()==null?"":"?"+request.getQueryString();
        List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest =apiDataMongoRepository.findByUrlPathAndMethodAndRequest(request.getRequestURI()+uri, "PUT", jsonObject.toString());  // apiDataService.findByFilteredTagQuery(request.getRequestURI()+uri, jsonObject.toString().replace("\"", "\\\"").replace("\"", "\\\""));
          
        String response=findByUrlPathAAndMethodAndRequest.size()>0?findByUrlPathAAndMethodAndRequest.get(0).getResponse():"{\"status\":\"No Result Found\"}";   
        return (JSONObject)parser.parse(response);
    }
    @RequestMapping(value = "/**", method = RequestMethod.PATCH, headers="Accept=*/*", produces = { "application/json" })
    public JSONObject patchDataToDatabase(@RequestBody Map<String,String> map, HttpServletRequest request) throws ParseException{
    	 JSONObject jsonObject = new JSONObject(map);
         JSONParser parser = new JSONParser();
        String uri=request.getQueryString()==null?"":"?"+request.getQueryString();
        List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest =apiDataMongoRepository.findByUrlPathAndMethodAndRequest(request.getRequestURI()+uri, "PATCH", jsonObject.toString());  // apiDataService.findByFilteredTagQuery(request.getRequestURI()+uri, jsonObject.toString().replace("\"", "\\\"").replace("\"", "\\\""));
          
        String response=findByUrlPathAAndMethodAndRequest.size()>0?findByUrlPathAAndMethodAndRequest.get(0).getResponse():"{\"status\":\"No Result Found\"}";   
        return (JSONObject)parser.parse(response);
    }

    @RequestMapping(value = "/**", method = RequestMethod.GET, headers="Accept=*/*", produces = { "application/json" })
    public JSONObject getDataToData(HttpServletRequest request) throws ParseException{
    	JSONParser parser = new JSONParser();
        String uri=request.getQueryString()==null?"":"?"+request.getQueryString();
        List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest =apiDataMongoRepository.findByUrlPathAndMethod(request.getRequestURI()+uri, "GET");  // apiDataService.findByFilteredTagQuery(request.getRequestURI()+uri, jsonObject.toString().replace("\"", "\\\"").replace("\"", "\\\""));
        
        String response=findByUrlPathAAndMethodAndRequest.size()>0?findByUrlPathAAndMethodAndRequest.get(0).getResponse():"{\"status\":\"No Result Found\"}";   
        return (JSONObject)parser.parse(response);
        
    }

    @RequestMapping(value = "/**", method = RequestMethod.DELETE, headers="Accept=*/*", produces = { "application/json" })
    public JSONObject deleteDataToData(HttpServletRequest request) throws ParseException{
    	JSONParser parser = new JSONParser();
    	 String uri=request.getQueryString()==null?"":"?"+request.getQueryString();
         List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest =apiDataMongoRepository.findByUrlPathAndMethod(request.getRequestURI()+uri, "DELETE");  // apiDataService.findByFilteredTagQuery(request.getRequestURI()+uri, jsonObject.toString().replace("\"", "\\\"").replace("\"", "\\\""));
         
         String response=findByUrlPathAAndMethodAndRequest.size()>0?findByUrlPathAAndMethodAndRequest.get(0).getResponse():"{\"status\":\"No Result Found\"}";   
         return (JSONObject)parser.parse(response);
    }

    @RequestMapping(value = "/**", method = RequestMethod.HEAD, headers="Accept=*/*", produces = { "application/json" })
    public JSONObject headApi(HttpServletRequest request) throws ParseException{
    	JSONParser parser = new JSONParser();
   	 String uri=request.getQueryString()==null?"":"?"+request.getQueryString();
        List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest =apiDataMongoRepository.findByUrlPathAndMethod(request.getRequestURI()+uri, "HEAD");  // apiDataService.findByFilteredTagQuery(request.getRequestURI()+uri, jsonObject.toString().replace("\"", "\\\"").replace("\"", "\\\""));
        
        String response=findByUrlPathAAndMethodAndRequest.size()>0?findByUrlPathAAndMethodAndRequest.get(0).getResponse():"{\"status\":\"No Result Found\"}";   
        return (JSONObject)parser.parse(response);
    }

    @RequestMapping(value = "/**", method = RequestMethod.TRACE, headers="Accept=*/*", produces = { "application/json" })
    public JSONObject traceApi(HttpServletRequest request) throws ParseException{
    	JSONParser parser = new JSONParser();
      	 String uri=request.getQueryString()==null?"":"?"+request.getQueryString();
           List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest =apiDataMongoRepository.findByUrlPathAndMethod(request.getRequestURI()+uri, "TRACE");  // apiDataService.findByFilteredTagQuery(request.getRequestURI()+uri, jsonObject.toString().replace("\"", "\\\"").replace("\"", "\\\""));
           
           String response=findByUrlPathAAndMethodAndRequest.size()>0?findByUrlPathAAndMethodAndRequest.get(0).getResponse():"{\"status\":\"No Result Found\"}";   
           return (JSONObject)parser.parse(response);
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS, headers="Accept=*/*", produces = { "application/json" })
    public JSONObject optionApi(HttpServletRequest request) throws ParseException{
    	JSONParser parser = new JSONParser();
      	 String uri=request.getQueryString()==null?"":"?"+request.getQueryString();
           List<ApiMongoTemplate> findByUrlPathAAndMethodAndRequest =apiDataMongoRepository.findByUrlPathAndMethod(request.getRequestURI()+uri, "OPTIONS");  // apiDataService.findByFilteredTagQuery(request.getRequestURI()+uri, jsonObject.toString().replace("\"", "\\\"").replace("\"", "\\\""));
           
           String response=findByUrlPathAAndMethodAndRequest.size()>0?findByUrlPathAAndMethodAndRequest.get(0).getResponse():"{\"status\":\"No Result Found\"}";   
           return (JSONObject)parser.parse(response);
    }


    /**
     * @implNote For UI Application Api
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/project-mock/save-data", method = RequestMethod.POST, headers="Accept=*/*", produces = { "application/json" })
    public Map<String,String> sendDataToDatabase(@RequestBody List<ApiDataPojo> map, HttpServletRequest request){
//        List<ApiData> list = map.entrySet().stream().sorted(Comparator.comparing(e -> e.getKey()))
//                .map(e -> new ApiData(e.getKey(), e.getValue())).collect(Collectors.toList());
//        for(Map.Entry<String, String> entry:map.entrySet()){
//                 entry.getKey()
//        }
System.out.println();//apiDataMongoRepository  //apiDataService.save(new ApiData(e.getUrlPath().replace("?","#"),e.getMethod(),e.getRequest(),e.getResponse()));
        map.forEach(e->{
        	apiDataMongoRepository.save(new ApiMongoTemplate(e.getUrlPath(), e.getMethod(), e.getRequest(),e.getResponse()));
        });
        // List<?> list=map.entrySet().stream().map(e -> new ArrayList<ApiData>(e.getKey(), e.getValue());
                        //.collect(Collectors.)
    //forEach((e1)->{System.out.println(e1.getKey()+e1.getValue());});


        return new HashMap<>();
    }
    @RequestMapping(value = "/project-mock/search", method = RequestMethod.POST, headers="Accept=*/*", produces = { "application/json" })
    public List<ApiMongoTemplate> getData(@RequestBody Map<String, Object> payload,HttpServletRequest request){
       String val="hello3";
      
          System.out.println(payload.get("searchQuery"));
        String res="\"\"{\"Hello6\":\"Pranav\"}\"\"";
     // Create TextCriteria
//        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny("some key word"); 
//        TextIndexDefinitionBuilder textIndex = new TextIndexDefinitionBuilder().onField(fieldname);
//        		  
//        // Build Query
//        Query query = TextQuery.queryText(criteria.o);
//        // Add the additional criteria to query
//       // query.addCriteria(Criteria.where("field").is("fieldValue"));
//        query.addCriteria(Criteria.where("anIntegerProperty").is(1));   
//
//        List<ApiMongoTemplate> textExamples = apiDataMongoRepository.findAll(query, ApiMongoTemplate.class);
        //mongoOperations.indexOps(ApiMongoTemplate.class).
        //ensureIndex(new Index().on("urlPath", Direction.ASC));
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingPhrase("urlPath")
                .matchingPhrase(payload.get("searchQuery").toString());
        Query query = TextQuery.queryText(criteria).sortByScore();

        List<ApiMongoTemplate> orderDetails =mongoOperations.find(query, ApiMongoTemplate.class); 
        //return orderDetails;
        return apiDataMongoRepository.findEmployeeByName(payload.get("searchQuery").toString());
        
       // return apiDataMongoRepository.findByUrlPath(payload.get("searchQuery").toString());
        //return apiDataService.findByUrlPathAAndMethodAndRequest(val,"POST",res);
    }





    @RequestMapping(value = "/js/**", method = RequestMethod.GET)
    public byte[] getJSFile(HttpServletRequest request)  {
        Path path = Paths.get(".\\src\\main\\resources\\static\\"+request.getRequestURI());
        byte[] bytes=null;
        try{bytes = Files.readAllBytes(path);
        }catch (Exception e){}
        return bytes;
    }

    @RequestMapping(value = "/css/**", method = RequestMethod.GET)
    public byte[] getCSSFile(HttpServletRequest request){
        Path path = Paths.get(".\\src\\main\\resources\\static\\"+request.getRequestURI());
        byte[] bytes=null;
        try{bytes = Files.readAllBytes(path);
        }catch (Exception e){}
        return bytes;
    }
    @RequestMapping(value = "/img/**", method = RequestMethod.GET)
    public byte[] getImageFile(HttpServletRequest request) {
        Path path = Paths.get(".\\src\\main\\resources\\static\\"+request.getRequestURI());
        byte[] bytes=null;
        try{bytes = Files.readAllBytes(path);
        }catch (Exception e){}
        return bytes;
    }
}
