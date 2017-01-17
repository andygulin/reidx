package com.reidx.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

public class Repeat implements Serializable {

    private static final long serialVersionUID = -4669374897994509006L;

    private String reidxId;
    private String url;
    private String referUrl;
    private String author;
    private Date releaseDate;
    private Date addTime;
    private String mediaId;
    private String mediaName;
    private String mediaUrl;
    private String titleCrc;
    private String urlCrc;
    private String contentCrc;

    public String getReidxId() {
        return reidxId;
    }

    public void setReidxId(String reidxId) {
        this.reidxId = reidxId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReferUrl() {
        return referUrl;
    }

    public void setReferUrl(String referUrl) {
        this.referUrl = referUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getTitleCrc() {
        return titleCrc;
    }

    public void setTitleCrc(String titleCrc) {
        this.titleCrc = titleCrc;
    }

    public String getUrlCrc() {
        return urlCrc;
    }

    public void setUrlCrc(String urlCrc) {
        this.urlCrc = urlCrc;
    }

    public String getContentCrc() {
        return contentCrc;
    }

    public void setContentCrc(String contentCrc) {
        this.contentCrc = contentCrc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}