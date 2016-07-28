package com.reidx.repository;

import org.springframework.data.repository.CrudRepository;

import com.reidx.entity.ReidxLog;

public interface ReidxLogRepository extends CrudRepository<ReidxLog, String> {

}