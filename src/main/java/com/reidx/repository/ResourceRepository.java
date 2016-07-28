package com.reidx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.reidx.entity.Resource;

public interface ResourceRepository extends PagingAndSortingRepository<Resource, String> {

}