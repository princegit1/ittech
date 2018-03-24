package com.itgd.dto;

import java.io.Serializable;

public class AskQuestionDTO implements Serializable{
	private int id;
	private String title;
	private String longTitle;
	private String sefTitle;
	private String byline;
	private String shortDescription;
	private String longDescription;
	private String kickerImage;
	private String kickerImageAltText;
	private String createdDate;
	private String metaTitle;
	private String metaKeyword;
	private String metaDescription;
	private int sectionId;
	private String sectionTitle;
	private String sectionSefTitle;
	private int questionCount;
	private int commentCount;
	private String currentPage;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSefTitle() {
		return sefTitle;
	}
	public void setSefTitle(String sefTitle) {
		this.sefTitle = sefTitle;
	}
	public String getByline() {
		return byline;
	}
	public void setByline(String byline) {
		this.byline = byline;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	public String getKickerImage() {
		return kickerImage;
	}
	public void setKickerImage(String kickerImage) {
		this.kickerImage = kickerImage;
	}
	public String getKickerImageAltText() {
		return kickerImageAltText;
	}
	public void setKickerImageAltText(String kickerImageAltText) {
		this.kickerImageAltText = kickerImageAltText;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getMetaTitle() {
		return metaTitle;
	}
	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}
	public String getMetaKeyword() {
		return metaKeyword;
	}
	public void setMetaKeyword(String metaKeyword) {
		this.metaKeyword = metaKeyword;
	}
	public String getMetaDescription() {
		return metaDescription;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionTitle() {
		return sectionTitle;
	}
	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}
	public String getSectionSefTitle() {
		return sectionSefTitle;
	}
	public void setSectionSefTitle(String sectionSefTitle) {
		this.sectionSefTitle = sectionSefTitle;
	}
	public int getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getLongTitle() {
		return longTitle;
	}
	public void setLongTitle(String longTitle) {
		this.longTitle = longTitle;
	}
	
	
}
