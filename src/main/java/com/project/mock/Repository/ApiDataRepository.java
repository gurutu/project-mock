package com.project.mock.Repository;

import com.project.mock.DataModal.ApiData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ApiDataRepository extends ElasticsearchRepository<ApiData,String> {
//    Page<ApiData> findByUri(String name, Pageable pageable);
//
//    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
//    Page<ApiData> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);


    Page<ApiData> findByUrlPath(String name, Pageable pageable);

    Page<ApiData> findByUrlPathAAndMethodAndRequest(String name, Pageable pageable);

    Page<ApiData> findByUrlPathAndMethod(String name, Pageable pageable);
//    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
//    Page<ApiData> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);
//
//    @Query("{\"bool\": {\"must\": {\"match_all\": {}}, \"filter\": {\"term\": {\"tags\": \"?0\" }}}}")
//    Page<ApiData> findByFilteredTagQuery(String tag, Pageable pageable);
//
//    @Query("{\"bool\": {\"must\": {\"match\": {\"authors.name\": \"?0\"}}, \"filter\": {\"term\": {\"tags\": \"?1\" }}}}")
//    Page<ApiData> findByAuthorsNameAndFilteredTagQuery(String name, String tag, Pageable pageable);
}
