package com.itgd.dto;

import java.io.Serializable;

public class SocialShairingDTO
  implements Serializable
{
  private Long articleId = 0L;
  private String website = null;
  private String contentType = null;
  private String contentTitle = null;
  private String contentImage = null;
  private String contentUrl = null;
  private String trendTotalCount = null;
  private String contentId = null;
  private String contentImageAlttext = null;
  private Long stumbleupon = 0L;
  private Long googleplusone = 0L;
  private Long twitter = 0L;
  private Long pLongerest = 0L;
  private Long linkedin = 0L;
  private Long fb_commentsbox_count = 0L;
  private Long fb_click_count = 0L;
  private Long fb_total_count = 0L;
  private Long fb_comment_count = 0L;
  private Long fb_like_count = 0L;
  private Long fb_share_count = 0L;
  private Long totalCount = 0L;
  

  public Long getStumbleupon() { return this.stumbleupon; }

  public void setStumbleupon(Long stumbleupon) {
    this.stumbleupon = stumbleupon;
  }
  public Long getGoogleplusone() {
    return this.googleplusone;
  }
  public void setGoogleplusone(Long googleplusone) {
    this.googleplusone = googleplusone;
  }
  public Long getTwitter() {
    return this.twitter;
  }
  public void setTwitter(Long twitter) {
    this.twitter = twitter;
  }
  public Long getPLongerest() {
    return this.pLongerest;
  }
  public void setPLongerest(Long pLongerest) {
    this.pLongerest = pLongerest;
  }
  public Long getLinkedin() {
    return this.linkedin;
  }
  public void setLinkedin(Long linkedin) {
    this.linkedin = linkedin;
  }
  public Long getFb_commentsbox_count() {
    return this.fb_commentsbox_count;
  }
  public void setFb_commentsbox_count(Long fbCommentsboxCount) {
    this.fb_commentsbox_count = fbCommentsboxCount;
  }
  public Long getFb_click_count() {
    return this.fb_click_count;
  }
  public void setFb_click_count(Long fbClickCount) {
    this.fb_click_count = fbClickCount;
  }
  public Long getFb_total_count() {
    return this.fb_total_count;
  }
  public void setFb_total_count(Long fbTotalCount) {
    this.fb_total_count = fbTotalCount;
  }
  public Long getFb_comment_count() {
    return this.fb_comment_count;
  }
  public void setFb_comment_count(Long fbCommentCount) {
    this.fb_comment_count = fbCommentCount;
  }
  public Long getFb_like_count() {
    return this.fb_like_count;
  }
  public void setFb_like_count(Long fbLikeCount) {
    this.fb_like_count = fbLikeCount;
  }
  public Long getFb_share_count() {
    return this.fb_share_count;
  }
  public void setFb_share_count(Long fbShareCount) {
    this.fb_share_count = fbShareCount;
  }
  public Long getArticleId() {
    return this.articleId;
  }
  public void setArticleId(Long articleId) {
    this.articleId = articleId;
  }
  public String getWebsite() {
    return this.website;
  }
  public void setWebsite(String website) {
    this.website = website;
  }
  public String getContentType() {
    return this.contentType;
  }
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

public String getContentImage() {
	return contentImage;
}

public void setContentImage(String contentImage) {
	this.contentImage = contentImage;
}

public String getContentUrl() {
	return contentUrl;
}

public void setContentUrl(String contentUrl) {
	this.contentUrl = contentUrl;
}

public Long getTotalCount() {
	return totalCount;
}

public void setTotalCount(Long totalCount) {
	this.totalCount = totalCount;
}

public String getTrendTotalCount() {
	return trendTotalCount;
}

public void setTrendTotalCount(String trentTotalCount) {
	this.trendTotalCount = trentTotalCount;
}

public Long getpLongerest() {
	return pLongerest;
}

public void setpLongerest(Long pLongerest) {
	this.pLongerest = pLongerest;
}
public String getContentId() {
	return contentId;
}

public void setContentId(String contentId) {
	this.contentId = contentId;
}

public String getContentImageAlttext() {
	return contentImageAlttext;
}

public void setContentImageAlttext(String contentImageAlttext) {
	this.contentImageAlttext = contentImageAlttext;
}

public String getContentTitle() {
	return contentTitle;
}

public void setContentTitle(String contentTitle) {
	this.contentTitle = contentTitle;
}
}