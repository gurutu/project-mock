package com.project.mock.Service;

import java.util.Optional;

import com.project.mock.DataModal.ApiData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface ApiDataService {
    ApiData save(ApiData ApiData);

    Optional<ApiData> findOne(String id);

    Iterable<ApiData> findAll();

    Page<ApiData> findByUrlPath(String name, Pageable pageable);



//    Page<ApiData> findByAuthorNameUsingCustomQuery(String name, Pageable pageable);
//
//    Page<ApiData> findByFilteredTagQuery(String tag, Pageable pageable);
//
//    Page<ApiData> findByAuthorsNameAndFilteredTagQuery(String name, String tag, Pageable pageable);

    long count();

    void delete(ApiData ApiData);
}
