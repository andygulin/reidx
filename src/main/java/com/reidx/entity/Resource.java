package com.reidx.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "resource")
public class Resource implements Serializable {

	private static final long serialVersionUID = -5873900706056981325L;

	@Id
	private String id;
	@Indexed
	private String reidxId;
	private String url;
	private String referUrl;
	@Indexed
	private String title;
	private String formatContent;
	private String author;
	private String contentMediaName;
	private Integer words;
	@Indexed
	private Date releaseDate;
	@Indexed
	private Date addTime;
	private String navigation;
	private String mediaId;
	@Indexed
	private String mediaName;
	private String mediaUrl;
	@Indexed
	private Integer sourceType;
	private String keywords;
	private String keywordsCode;
	private List<String> pictureList;
	private Integer ver;
	private String _abstract;
	private Integer relativity;
	private Integer relavancy;
	private Integer relatedFields;
	private List<RelatedInfo> relatedInfos;
	private Integer repeatCount;
	private Integer commentCount;
	private Integer clickCount;
	private Integer quoteCount;
	private String titleCrc;
	private String urlCrc;
	private String contentCrc;
	private List<Repeat> repeats;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFormatContent() {
		return formatContent;
	}

	public void setFormatContent(String formatContent) {
		this.formatContent = formatContent;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContentMediaName() {
		return contentMediaName;
	}

	public void setContentMediaName(String contentMediaName) {
		this.contentMediaName = contentMediaName;
	}

	public Integer getWords() {
		return words;
	}

	public void setWords(Integer words) {
		this.words = words;
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

	public String getNavigation() {
		return navigation;
	}

	public void setNavigation(String navigation) {
		this.navigation = navigation;
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

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getKeywordsCode() {
		return keywordsCode;
	}

	public void setKeywordsCode(String keywordsCode) {
		this.keywordsCode = keywordsCode;
	}

	public List<String> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<String> pictureList) {
		this.pictureList = pictureList;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

	public String get_abstract() {
		return _abstract;
	}

	public void set_abstract(String _abstract) {
		this._abstract = _abstract;
	}

	public Integer getRelativity() {
		return relativity;
	}

	public void setRelativity(Integer relativity) {
		this.relativity = relativity;
	}

	public Integer getRelavancy() {
		return relavancy;
	}

	public void setRelavancy(Integer relavancy) {
		this.relavancy = relavancy;
	}

	public Integer getRelatedFields() {
		return relatedFields;
	}

	public void setRelatedFields(Integer relatedFields) {
		this.relatedFields = relatedFields;
	}

	public Integer getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}

	public List<RelatedInfo> getRelatedInfos() {
		return relatedInfos;
	}

	public void setRelatedInfos(List<RelatedInfo> relatedInfos) {
		this.relatedInfos = relatedInfos;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public Integer getQuoteCount() {
		return quoteCount;
	}

	public void setQuoteCount(Integer quoteCount) {
		this.quoteCount = quoteCount;
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

	public List<Repeat> getRepeats() {
		return repeats;
	}

	public void setRepeats(List<Repeat> repeats) {
		this.repeats = repeats;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
