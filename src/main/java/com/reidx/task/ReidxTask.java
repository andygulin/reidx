package com.reidx.task;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.reidx.service.ReidxService;
import com.reidx.vo.ReidxParam;

@Component
public class ReidxTask {

	private static final Logger LOGGER = Logger.getLogger(ReidxTask.class);

	@Inject
	private ReidxService reidxService;

	public void execute() {
		String next_id = reidxService.readNextIdFile();
		ReidxParam param = new ReidxParam();
		if (StringUtils.isBlank(next_id) || StringUtils.isEmpty(next_id)) {
			next_id = reidxService.getInitNextId();
		}
		param.setReidxId(next_id);
		param.setNum("50");
		param.setType("");
		this.reidxService.reidx(param);

		LOGGER.info("reidx -> " + param);
	}
}
