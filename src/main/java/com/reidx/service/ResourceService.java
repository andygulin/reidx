package com.reidx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reidx.entity.Resource;
import com.reidx.repository.ResourceRepository;
import com.reidx.vo.ResourceCount;
import com.reidx.vo.SearchParam;

@Service
public class ResourceService {

	@Autowired
	private ResourceRepository resourceRepository;

	public List<Resource> findByPage(int pageNumber, int pageSize, SearchParam param) {
		return this.resourceRepository.findByPage(pageNumber, pageSize, param);
	}

	public long getCount(SearchParam param) {
		return this.resourceRepository.getCount(param);
	}

	public Resource findById(String id) {
		return this.resourceRepository.findById(id);
	}

	public List<ResourceCount> getResourceCount() {
		return this.resourceRepository.getResourceCount();
	}
}
