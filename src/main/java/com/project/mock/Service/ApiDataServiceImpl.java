package com.project.mock.Service;

import java.util.List;
import java.util.Optional;

import com.project.mock.DataModal.ApiData;
import com.project.mock.Repository.ApiDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ApiDataServiceImpl implements ApiDataService {


    private final ApiDataRepository apiDataRepository;
    
    @Autowired
    public ApiDataServiceImpl(ApiDataRepository ApiDataRepository) {
        this.apiDataRepository = ApiDataRepository;
    }

    @Override
    public ApiData save(ApiData ApiData) {
        return apiDataRepository.save(ApiData);
    }

    @Override
    public Optional<ApiData> findOne(String id) {
        return apiDataRepository.findById(id);
    }

    @Override
    public Iterable<ApiData> findAll() {
        return apiDataRepository.findAll();
    }

    @Override
    public List<ApiData> findByUrlPath(String name) {
        return apiDataRepository.findByUrlPath(name);
    }

    @Override
    public List<ApiData> findByUrlPathAAndMethodAndRequest(String uri, String method, String request) {
        return apiDataRepository.findByUrlPathAndMethodAndRequest(uri, method, request);
    }

//    @Override
//    public Page<ApiData> findByAuthorNameUsingCustomQuery(String name, Pageable pageable) {
//        return ApiDataRepository.findByAuthorsNameUsingCustomQuery(name, pageable);
//    }
//
    @Override
    public List<ApiData> findByFilteredTagQuery(String tag, String pageable) {
        return apiDataRepository.findByFilteredTagQuery(tag, pageable);
    }
//
//    @Override
//    public Page<ApiData> findByAuthorsNameAndFilteredTagQuery(String name, String tag, Pageable pageable) {
//        return ApiDataRepository.findByAuthorsNameAndFilteredTagQuery(name, tag, pageable);
//    }

    @Override
    public long count() {
        return apiDataRepository.count();
    }

    @Override
    public void delete(ApiData ApiData) {
        apiDataRepository.delete(ApiData);
    }
}
