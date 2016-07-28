package com.reidx.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.reidx.vo.ReidxParam;

@Document(collection = "reidx_log")
public class ReidxLog implements Serializable {

	private static final long serialVersionUID = 1867603957157782020L;

	@Id
	private String id;
	@Indexed
	private Date logDate;
	private ReidxParam reidxParam;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public ReidxParam getReidxParam() {
		return reidxParam;
	}

	public void setReidxParam(ReidxParam reidxParam) {
		this.reidxParam = reidxParam;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}