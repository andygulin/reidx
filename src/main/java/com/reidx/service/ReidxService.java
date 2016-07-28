package com.reidx.service;

import java.util.List;

import com.reidx.entity.Resource;
import com.reidx.vo.ReidxParam;

public interface ReidxService {

	List<Resource> reidx(ReidxParam param);

	void writeNextIdFile(String next_id);

	String readNextIdFile();

	String getInitNextId();
}