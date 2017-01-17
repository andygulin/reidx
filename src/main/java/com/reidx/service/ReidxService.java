package com.reidx.service;

import com.reidx.entity.Resource;
import com.reidx.vo.ReidxParam;

import java.util.List;

public interface ReidxService {

    List<Resource> reidx(ReidxParam param);

    void writeNextIdFile(String next_id);

    String readNextIdFile();

    String getInitNextId();
}