package com.reidx.util;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Map;

public class Configuration {

    private String theme;
    private String appKey;
    private String redixGetUrl;
    private String nextIdFile;
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

    public String getNextIdFile() {
        return nextIdFile;
    }

    public void setNextIdFile(String nextIdFile) {
        this.nextIdFile = nextIdFile;
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
