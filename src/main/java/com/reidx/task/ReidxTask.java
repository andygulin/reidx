package com.reidx.task;

import com.reidx.service.ReidxService;
import com.reidx.vo.ReidxParam;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReidxTask {

    private static final Logger LOGGER = Logger.getLogger(ReidxTask.class);

    @Autowired
    private ReidxService reidxService;

    public void execute() {
        String next_id = reidxService.readNextIdFile();
        if (StringUtils.isBlank(next_id) || StringUtils.isEmpty(next_id)) {
            next_id = reidxService.getInitNextId();
        }
        ReidxParam param = new ReidxParam(next_id, "50", "");
        this.reidxService.reidx(param);
        LOGGER.info("reidx -> " + param);
    }
}