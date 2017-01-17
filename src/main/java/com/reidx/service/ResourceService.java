package com.reidx.service;

import com.reidx.entity.Resource;
import com.reidx.vo.ResourceCount;
import com.reidx.vo.SearchParam;

import java.util.List;

public interface ResourceService {

    List<Resource> findByPage(int pageNumber, int pageSize, SearchParam param);

    long getCount(SearchParam param);

    List<ResourceCount> getResourceCount();
}
