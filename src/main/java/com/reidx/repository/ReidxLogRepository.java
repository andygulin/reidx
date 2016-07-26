package com.reidx.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.reidx.entity.ReidxLog;

@Repository
public class ReidxLogRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(ReidxLog reidxLog) {
		this.mongoTemplate.insert(reidxLog);
	}
}
