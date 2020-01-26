package com.project.mock.Service;

import java.util.List;
import java.util.Optional;

import com.project.mock.DataModal.ApiData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface ApiDataService {
    ApiData save(ApiData ApiData);

    Optional<ApiData> findOne(String id);

    Iterable<ApiData> findAll();

   List<ApiData> findByUrlPath(String name);
    List<ApiData> findByUrlPathAAndMethodAndRequest(String uri,String method,String request);

    
    
//    Page<ApiData> findByAuthorNameUsingCustomQuery(String name, Pageable pageable);
//
    List<ApiData> findByFilteredTagQuery(String tag, String responce);
//
//    Page<ApiData> findByAuthorsNameAndFilteredTagQuery(String name, String tag, Pageable pageable);

    long count();

    void delete(ApiData ApiData);
}
