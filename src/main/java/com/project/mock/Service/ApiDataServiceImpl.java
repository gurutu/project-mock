package com.project.mock.Service;

import java.util.Optional;

import com.project.mock.DataModal.ApiData;
import com.project.mock.Repository.ApiDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ApiDataServiceImpl implements ApiDataService {

    private final ApiDataRepository ApiDataRepository;
    
    @Autowired
    public ApiDataServiceImpl(ApiDataRepository ApiDataRepository) {
        this.ApiDataRepository = ApiDataRepository;
    }

    @Override
    public ApiData save(ApiData ApiData) {
        return ApiDataRepository.save(ApiData);
    }

    @Override
    public Optional<ApiData> findOne(String id) {
        return ApiDataRepository.findById(id);
    }

    @Override
    public Iterable<ApiData> findAll() {
        return ApiDataRepository.findAll();
    }

    @Override
    public Page<ApiData> findByUri(String name, Pageable pageable) {
        return ApiDataRepository.findByUri(name, pageable);
    }

//    @Override
//    public Page<ApiData> findByAuthorNameUsingCustomQuery(String name, Pageable pageable) {
//        return ApiDataRepository.findByAuthorsNameUsingCustomQuery(name, pageable);
//    }
//
//    @Override
//    public Page<ApiData> findByFilteredTagQuery(String tag, Pageable pageable) {
//        return ApiDataRepository.findByFilteredTagQuery(tag, pageable);
//    }
//
//    @Override
//    public Page<ApiData> findByAuthorsNameAndFilteredTagQuery(String name, String tag, Pageable pageable) {
//        return ApiDataRepository.findByAuthorsNameAndFilteredTagQuery(name, tag, pageable);
//    }

    @Override
    public long count() {
        return ApiDataRepository.count();
    }

    @Override
    public void delete(ApiData ApiData) {
        ApiDataRepository.delete(ApiData);
    }
}
