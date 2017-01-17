package com.reidx.repository;

import com.reidx.entity.Resource;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResourceRepository extends PagingAndSortingRepository<Resource, String> {

}