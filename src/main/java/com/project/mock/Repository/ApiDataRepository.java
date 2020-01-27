//package com.project.mock.Repository;
//
//import com.project.mock.DataModal.ApiData;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.annotations.Query;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import java.util.List;
//
//
//public interface ApiDataRepository extends ElasticsearchRepository<ApiData,String> {
////    Page<ApiData> findByUri(String name, Pageable pageable);
////
////    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
////    Page<ApiData> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);
//
//
//    List<ApiData> findByUrlPath(String name);
//
//  List<ApiData> findByUrlPathAndMethod(String urlPath,String method);
//  
//  List<ApiData> findByUrlPathAndMethodAndRequest(String urlPath,String method,String request);
//
//   // Page<ApiData> findByUrlPathAndMethod(String name, Pageable pageable);
////    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
////    Page<ApiData> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);
////
//   // @Query("{\"bool\": {\"must\": {\"match_all\": {}}, \"filter\": {\"term\": {\"urlPath\": \"?0\" },\"term\": {\"request\": \"\"?1\"\" }}}}")
//	//@Query("{ \"dis_max\": {\"queries\": [{ \"match\": { \"urlPath\": \"?0\" }},{ \"match\": { \"request\":  \"{\"Hello1\":\"Pranav1\"}\" }}],\"tie_breaker\" : 1.0}}")
//	@Query("{\r\n" + 
//			"    \"dis_max\": {\r\n" + 
//			"        \"queries\": [\r\n" + 
//			"            { \"match\": { \"urlPath\": \"?0\" }},\r\n" + 
//			"            { \"match\": { \"request\":  \"?1\" }}\r\n" + 
//			"        ],\r\n" + 
//			"        \"tie_breaker\" : 1.0\r\n" + 
//			"    }\r\n" + 
//			"  }")
//  List<ApiData> findByFilteredTagQuery(String urlPath, String request);
////
////    @Query("{\"bool\": {\"must\": {\"match\": {\"authors.name\": \"?0\"}}, \"filter\": {\"term\": {\"tags\": \"?1\" }}}}")
////    Page<ApiData> findByAuthorsNameAndFilteredTagQuery(String name, String tag, Pageable pageable);
//}
