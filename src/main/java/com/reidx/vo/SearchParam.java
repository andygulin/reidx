package com.reidx.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.format.annotation.DateTimeFormat;

public class SearchParam {

	private String reidxId;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date rdStart;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date rdEnd;

	private String title;
	private Integer sourceType;

	public String getReidxId() {
		return reidxId;
	}

	public void setReidxId(String reidxId) {
		this.reidxId = reidxId;
	}

	public Date getRdStart() {
		return rdStart;
	}

	public void setRdStart(Date rdStart) {
		this.rdStart = rdStart;
	}

	public Date getRdEnd() {
		return rdEnd;
	}

	public void setRdEnd(Date rdEnd) {
		this.rdEnd = rdEnd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String buildUrl() {
		List<String> url = new ArrayList<>();
		if (this.reidxId != null) {
			url.add("reidxId=" + this.reidxId);
		}
		if (this.rdStart != null) {
			url.add("rdStart=" + DateFormatUtils.format(this.rdStart, "yyyy/MM/dd HH:mm:ss"));
		}
		if (this.rdEnd != null) {
			url.add("rdEnd=" + DateFormatUtils.format(this.rdEnd, "yyyy/MM/dd HH:mm:ss"));
		}
		if (this.title != null) {
			url.add("title=" + this.title);
		}
		if (this.sourceType != null) {
			url.add("sourceType=" + this.sourceType);
		}
		return "?" + StringUtils.join(url, "&");
	}
}