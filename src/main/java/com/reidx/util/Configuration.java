package com.reidx.util;

import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Configuration {

	private String theme;
	private String appKey;
	private String redixGetUrl;
	private String wNextIdFile;
	private String lNextIdFile;
	private String charset;
	private Map<Integer, String> sourceTypes;

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getRedixGetUrl() {
		return redixGetUrl;
	}

	public void setRedixGetUrl(String redixGetUrl) {
		this.redixGetUrl = redixGetUrl;
	}

	public String getwNextIdFile() {
		return wNextIdFile;
	}

	public void setwNextIdFile(String wNextIdFile) {
		this.wNextIdFile = wNextIdFile;
	}

	public String getlNextIdFile() {
		return lNextIdFile;
	}

	public void setlNextIdFile(String lNextIdFile) {
		this.lNextIdFile = lNextIdFile;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public Map<Integer, String> getSourceTypes() {
		return sourceTypes;
	}

	public void setSourceTypes(Map<Integer, String> sourceTypes) {
		this.sourceTypes = sourceTypes;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
