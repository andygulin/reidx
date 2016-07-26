package com.reidx;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.reidx.service.ReidxService;
import com.reidx.vo.ReidxParam;

@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:applicationContext-mongodb.xml" })
public class ReidxTest extends AbstractJUnit4SpringContextTests {

	private static final Logger LOGGER = Logger.getLogger(ReidxTest.class);

	@Autowired
	private ReidxService reidxService;

	@Test
	public void reidx() {
		for (int i = 1; i <= 10; i++) {
			String next_id = reidxService.readNextIdFile();
			ReidxParam param = new ReidxParam();
			if (StringUtils.isBlank(next_id) || StringUtils.isEmpty(next_id)) {
				next_id = reidxService.getInitNextId();
			}
			param.setReidxId(next_id);
			param.setNum("50");
			param.setType("");
			reidxService.reidx(param);
			LOGGER.info(i + " reidx -> " + param);
		}
	}

}
