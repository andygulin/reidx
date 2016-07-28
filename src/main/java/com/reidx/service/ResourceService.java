package com.reidx.service;

import java.util.List;

import com.reidx.entity.Resource;
import com.reidx.vo.ResourceCount;
import com.reidx.vo.SearchParam;

public interface ResourceService {

	List<Resource> findByPage(int pageNumber, int pageSize, SearchParam param);

	long getCount(SearchParam param);

	List<ResourceCount> getResourceCount();
}
