package com.reidx.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class RelatedInfo implements Serializable {

	private static final long serialVersionUID = -6356409643874838906L;
	private String tag;
	private String clue;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getClue() {
		return clue;
	}

	public void setClue(String clue) {
		this.clue = clue;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
