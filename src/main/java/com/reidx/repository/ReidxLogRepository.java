package com.reidx.repository;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.reidx.entity.ReidxLog;

@Repository
public class ReidxLogRepository {

	@Inject
	private MongoTemplate mongoTemplate;

	public void save(ReidxLog reidxLog) {
		this.mongoTemplate.insert(reidxLog);
	}
}
