package com.itgd.parser;

import javax.xml.bind.annotation.XmlElement;

public class RelatedFeedPhoto {
	private String kickerImage;
	private String articleId;
	private String relatedArticleId;
	private String relatedTitle;
	private String relatedType;
	private String featured;
	private String contentUrl;
	private String selfUrl;
	//private String title;
	
	/*@XmlElement
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}*/
	@XmlElement
	public String getKickerImage() {
		return kickerImage;
	}
	public void setKickerImage(String kickerImage) {
		this.kickerImage = kickerImage;
	}
	@XmlElement
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	@XmlElement
	public String getRelatedArticleId() {
		return relatedArticleId;
	}
	public void setRelatedArticleId(String relatedArticleId) {
		this.relatedArticleId = relatedArticleId;
	}
	@XmlElement
	public String getRelatedTitle() {
		return relatedTitle;
	}
	public void setRelatedTitle(String relatedTitle) {
		this.relatedTitle = relatedTitle;
	}
	@XmlElement
	public String getRelatedType() {
		return relatedType;
	}
	public void setRelatedType(String relatedType) {
		this.relatedType = relatedType;
	}
	@XmlElement
	public String getFeatured() {
		return featured;
	}
	public void setFeatured(String featured) {
		this.featured = featured;
	}
	@XmlElement
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	@XmlElement
	public String getSelfUrl() {
		return selfUrl;
	}
	public void setSelfUrl(String selfUrl) {
		this.selfUrl = selfUrl;
	}
}
