package com.itgd.dto;

public class RelatedContentDTO {
	private int contentId;
	private int relatedContentId;
	private String relatedContentSefTitle;
	private String relatedTitle;
	private String createddate;
	

	private String relatedType;
	private int relatedOrder;
	private int featured;
	private String relatedThumbImage;
	
	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	
	public String getRelatedThumbImage() {
		return relatedThumbImage;
	}

	public void setRelatedThumbImage(String relatedThumbImage) {
		this.relatedThumbImage = relatedThumbImage;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getRelatedContentId() {
		return relatedContentId;
	}

	public void setRelatedContentId(int relatedContentId) {
		this.relatedContentId = relatedContentId;
	}

	public String getRelatedContentSefTitle() {
		return relatedContentSefTitle;
	}

	public void setRelatedContentSefTitle(String relatedContentSefTitle) {
		this.relatedContentSefTitle = relatedContentSefTitle;
	}

	public String getRelatedTitle() {
		return relatedTitle;
	}

	public void setRelatedTitle(String relatedTitle) {
		this.relatedTitle = relatedTitle;
	}

	public String getRelatedType() {
		return relatedType;
	}

	public void setRelatedType(String relatedType) {
		this.relatedType = relatedType;
	}

	public int getRelatedOrder() {
		return relatedOrder;
	}

	public void setRelatedOrder(int relatedOrder) {
		this.relatedOrder = relatedOrder;
	}

	public int getFeatured() {
		return featured;
	}

	public void setFeatured(int featured) {
		this.featured = featured;
	}  
}