package com.itgd.parser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthorList {
	private String id;
	private String authorName;
	private String authorEmail;
	private String profieImage;
	private String designation;
	private String fullDescription;
	private String sefUrl;
	private String twitterId;
	private String facebookId;
	private String profileType;
	
	@XmlElement
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	@XmlElement
	public String getAuthorEmail() {
		return authorEmail;
	}
	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}
	@XmlElement
	public String getProfieImage() {
		return profieImage;
	}
	public void setProfieImage(String profieImage) {
		this.profieImage = profieImage;
	}
	@XmlElement
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@XmlElement
	public String getFullDescription() {
		return fullDescription;
	}
	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}
	@XmlElement
	public String getSefUrl() {
		return sefUrl;
	}
	public void setSefUrl(String sefUrl) {
		this.sefUrl = sefUrl;
	}
	@XmlElement
	public String getTwitterId() {
		return twitterId;
	}
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}
	@XmlElement
	public String getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	@XmlElement
	public String getProfileType() {
		return profileType;
	}
	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}
	

}
