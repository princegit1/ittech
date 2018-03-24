package com.itgd.dto;

import java.io.Serializable;

public class ArchiveBean
  implements Serializable
{
  private String coverImage = null;
  private int issueId;
  private String issueDate = null;
  private int issueCount;
  private int archivearticleid;
  private int archivesectionid;
  private String archivesectionname = null;
  private String archivesectionbyline = null;
  private String archivetitle = null;
  private String archivekicker = null;
  private String archivecover = null;
  private String archivedate = null;
  private String coverbyline = null;
  private String coverKicker = null;
  private String covertitle = null;
  private int coverartid;
  private int SuppArtid;
  private int SuppId;
  private String Suppname = null;
  private String SuppTitle = null;
  private String SuppKicker = null;
  private String SuppByline = null;
  private String SuppImage = null;
  private String sefUrl = null;
  private String kickerImage = null;
  private String kickerImageAltText = null;
  private String largeKickerImage = null;
  private String smallImage = null;
  private String largeKickerImageCaption = null;
  private int contentHighlighted;
  private int contentLocked;
  private String extraLargeKickerImage;
  private String contentUrl;
  private String syndicationDate = null;
  private String createdDate = null;
  private String updatedDate = null;
  private String createdTime = null;
  private String updatedTime = null;
  private String bylineModifiedBy = null;
  private String city = null;
  private String courtesy = null;
  private String extraLargeImage="";
   
  
  public void setExtraLargeImage(String extraLargeImage) {
	this.extraLargeImage = extraLargeImage;
}public String getExtraLargeImage() {
	return extraLargeImage;
}

  public String getSuppImage()
  {
    return this.SuppImage;
  }

  public void setSuppImage(String suppImage)
  {
    this.SuppImage = suppImage;
  }

  public int getSuppArtid()
  {
    return this.SuppArtid;
  }

  public void setSuppArtid(int suppArtid)
  {
    this.SuppArtid = suppArtid;
  }

  public int getSuppId()
  {
    return this.SuppId;
  }

  public void setSuppId(int suppId)
  {
    this.SuppId = suppId;
  }

  public String getSuppname()
  {
    return this.Suppname;
  }

  public void setSuppname(String suppname)
  {
    this.Suppname = suppname;
  }

  public String getSuppTitle()
  {
    return this.SuppTitle;
  }

  public void setSuppTitle(String suppTitle)
  {
    this.SuppTitle = suppTitle;
  }

  public String getSuppKicker()
  {
    return this.SuppKicker;
  }

  public void setSuppKicker(String suppKicker)
  {
    this.SuppKicker = suppKicker;
  }

  public String getSuppByline()
  {
    return this.SuppByline;
  }

  public void setSuppByline(String suppByline)
  {
    this.SuppByline = suppByline;
  }

  public String getArchivecover()
  {
    return this.archivecover;
  }

  public void setArchivecover(String archivecover)
  {
    this.archivecover = archivecover;
  }

  public String getArchivedate()
  {
    return this.archivedate;
  }

  public void setArchivedate(String archivedate)
  {
    this.archivedate = archivedate;
  }

  public String getCoverbyline()
  {
    return this.coverbyline;
  }

  public void setCoverbyline(String coverbyline)
  {
    this.coverbyline = coverbyline;
  }

  public String getCoverKicker()
  {
    return this.coverKicker;
  }

  public void setCoverKicker(String coverKicker)
  {
    this.coverKicker = coverKicker;
  }

  public String getCovertitle()
  {
    return this.covertitle;
  }

  public void setCovertitle(String covertitle)
  {
    this.covertitle = covertitle;
  }

  public int getCoverartid()
  {
    return this.coverartid;
  }

  public void setCoverartid(int coverartid)
  {
    this.coverartid = coverartid;
  }

  public int getArchivearticleid()
  {
    return this.archivearticleid;
  }

  public void setArchivearticleid(int archivearticleid)
  {
    this.archivearticleid = archivearticleid;
  }

  public int getArchivesectionid()
  {
    return this.archivesectionid;
  }

  public void setArchivesectionid(int archivesectionid)
  {
    this.archivesectionid = archivesectionid;
  }

  public String getArchivesectionname()
  {
    return this.archivesectionname;
  }

  public void setArchivesectionname(String archivesectionname)
  {
    this.archivesectionname = archivesectionname;
  }

  public String getArchivesectionbyline()
  {
    return this.archivesectionbyline;
  }

  public void setArchivesectionbyline(String archivesectionbyline)
  {
    this.archivesectionbyline = archivesectionbyline;
  }

  public String getArchivetitle()
  {
    return this.archivetitle;
  }

  public void setArchivetitle(String archivetitle)
  {
    this.archivetitle = archivetitle;
  }

  public String getArchivekicker()
  {
    return this.archivekicker;
  }

  public void setArchivekicker(String archivekicker)
  {
    this.archivekicker = archivekicker;
  }

  public String getCoverImage()
  {
    return this.coverImage;
  }

  public void setCoverImage(String coverImage)
  {
    this.coverImage = coverImage;
  }

  public int getIssueId()
  {
    return this.issueId;
  }

  public void setIssueId(int issueId)
  {
    this.issueId = issueId;
  }

  public String getIssueDate()
  {
    return this.issueDate;
  }

  public void setIssueDate(String issueDate)
  {
    this.issueDate = issueDate;
  }

  public int getIssueCount()
  {
    return this.issueCount;
  }

  public void setIssueCount(int issueCount)
  {
    this.issueCount = issueCount;
  }

  public String getSefUrl()
  {
    return this.sefUrl;
  }

  public void setSefUrl(String sefUrl)
  {
    this.sefUrl = sefUrl;
  }

  public String getKickerImage()
  {
    return this.kickerImage;
  }

  public void setKickerImage(String kickerImage)
  {
    this.kickerImage = kickerImage;
  }

  public String getKickerImageAltText()
  {
    return this.kickerImageAltText;
  }

  public void setKickerImageAltText(String kickerImageAltText)
  {
    this.kickerImageAltText = kickerImageAltText;
  }

  public String getLargeKickerImage()
  {
    return this.largeKickerImage;
  }

  public void setLargeKickerImage(String largeKickerImage)
  {
    this.largeKickerImage = largeKickerImage;
  }

  public String getSmallImage()
  {
    return this.smallImage;
  }

  public void setSmallImage(String smallImage)
  {
    this.smallImage = smallImage;
  }

  public String getLargeKickerImageCaption()
  {
    return this.largeKickerImageCaption;
  }

  public void setLargeKickerImageCaption(String largeKickerImageCaption)
  {
    this.largeKickerImageCaption = largeKickerImageCaption;
  }

  public int getContentHighlighted()
  {
    return this.contentHighlighted;
  }

  public void setContentHighlighted(int contentHighlighted)
  {
    this.contentHighlighted = contentHighlighted;
  }

  public int getContentLocked()
  {
    return this.contentLocked;
  }

  public void setContentLocked(int contentLocked)
  {
    this.contentLocked = contentLocked;
  }

  public String getExtraLargeKickerImage()
  {
    return this.extraLargeKickerImage;
  }

  public void setExtraLargeKickerImage(String extraLargeKickerImage)
  {
    this.extraLargeKickerImage = extraLargeKickerImage;
  }

  public void setContentUrl(String contentUrl) {
    this.contentUrl = contentUrl;
  }

  public String getContentUrl() {
    return this.contentUrl;
  }

  public String getSyndicationDate() {
    return this.syndicationDate;
  }

  public void setSyndicationDate(String syndicationDate) {
    this.syndicationDate = syndicationDate;
  }

  public String getCreatedDate() {
    return this.createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public String getUpdatedDate() {
    return this.updatedDate;
  }

  public void setUpdatedDate(String updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getCreatedTime() {
    return this.createdTime;
  }

  public void setCreatedTime(String createdTime) {
    this.createdTime = createdTime;
  }

  public String getUpdatedTime() {
    return this.updatedTime;
  }

  public void setUpdatedTime(String updatedTime) {
    this.updatedTime = updatedTime;
  }

  public String getBylineModifiedBy() {
    return this.bylineModifiedBy;
  }

  public void setBylineModifiedBy(String bylineModifiedBy) {
    this.bylineModifiedBy = bylineModifiedBy;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCourtesy() {
    return this.courtesy;
  }

  public void setCourtesy(String courtesy) {
    this.courtesy = courtesy;
  }
}