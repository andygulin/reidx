package com.reidx.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ResourceCount {
	private Integer sourceType;
	private Integer count;

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}