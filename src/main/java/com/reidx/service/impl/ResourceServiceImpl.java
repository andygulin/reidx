package com.reidx.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.reidx.entity.Resource;
import com.reidx.service.ResourceService;
import com.reidx.vo.ResourceCount;
import com.reidx.vo.SearchParam;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Resource> findByPage(int pageNumber, int pageSize, SearchParam param) {
		Query query = new Query();
		query.skip(pageNumber).limit(pageSize);
		query.with(new Sort(Direction.DESC, "releaseDate", "addTime"));
		query.fields().include("id").include("reidxId").include("title").include("mediaName").include("releaseDate")
				.include("sourceType");
		if (param.getRdStart() != null && param.getRdEnd() != null) {
			query.addCriteria(Criteria.where("releaseDate").gte(param.getRdStart()).lte(param.getRdEnd()));
		}
		if (StringUtils.isNotEmpty(param.getReidxId())) {
			query.addCriteria(Criteria.where("reidxId").is(param.getReidxId()));
		}
		if (StringUtils.isNotEmpty(param.getTitle())) {
			query.addCriteria(Criteria.where("title")
					.regex(Pattern.compile("^.*" + param.getTitle() + ".*$", Pattern.CASE_INSENSITIVE)));
		}
		if (param.getSourceType() != null && param.getSourceType() != -1) {
			query.addCriteria(Criteria.where("sourceType").is(param.getSourceType()));
		} else {
			query.addCriteria(Criteria.where("sourceType").gte(0).lte(6));
		}
		return this.mongoTemplate.find(query, Resource.class);
	}

	@Override
	public long getCount(SearchParam param) {
		Query query = new Query();
		if (param.getRdStart() != null && param.getRdEnd() != null) {
			query.addCriteria(Criteria.where("releaseDate").gte(param.getRdStart()).lte(param.getRdEnd()));
		}
		if (StringUtils.isNotEmpty(param.getReidxId())) {
			query.addCriteria(Criteria.where("reidxId").is(param.getReidxId()));
		}
		if (StringUtils.isNotEmpty(param.getTitle())) {
			query.addCriteria(Criteria.where("title")
					.regex(Pattern.compile("^.*" + param.getTitle() + ".*$", Pattern.CASE_INSENSITIVE)));
		}
		if (param.getSourceType() != null && param.getSourceType() != -1) {
			query.addCriteria(Criteria.where("sourceType").is(param.getSourceType()));
		} else {
			query.addCriteria(Criteria.where("sourceType").gte(0).lte(6));
		}
		return this.mongoTemplate.count(query, Resource.class);
	}

	@Override
	public List<ResourceCount> getResourceCount() {
		List<ResourceCount> resourceCounts = new ArrayList<>();
		GroupByResults<ResourceCount> groupByResults = this.mongoTemplate.group(
				Criteria.where("sourceType").gte(0).lte(6), "resource", GroupBy.key("sourceType")
						.initialDocument("{ count : 0 }").reduceFunction("function(doc, prev) { prev.count += 1 }"),
				ResourceCount.class);
		Iterator<ResourceCount> iterator = groupByResults.iterator();
		while (iterator.hasNext()) {
			resourceCounts.add(iterator.next());
		}
		return resourceCounts;
	}
}