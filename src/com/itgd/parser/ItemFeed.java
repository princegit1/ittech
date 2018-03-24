package com.itgd.parser;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
public class ItemFeed {
	private String id;
	private String title;
	private String sef_url;
	private String byline;
	private String city;
	private String introtext;
	private String extralarge_image;
	private String mobile_image;
	private String large_kicker_image;
	private String kicker_image;
	private String large_kicker_image_alt_text;
	private String is_external;
	private String createddate;
	private String createddateYYMMDD;
	private String content_url;
	private String	primary_category;
	private String	medium_kicker_image;
	private String short_introtext;
	private String sectitle;
	private String 	secid;
	private String secsefurl;
	private String seccontenturl;
	private String cattitle;
	private String catid;
	private String catsefurl;
	private String catcontenturl;
	private String curcattitle;
	private String curcatid;
	private String curcatsefurl;
	private String curcatcontenturl;
	private String metatitle;
	private String metakey;
	private String metadesc;
	private String topnav_path;
	private String rightnav_path;
	private String bottomnav_path;
	private String leftnav_path;
	private String header_image;
	private List<RelatedFeedVideo> relatedFeedsVideo;
	private List<RelatedFeedPhoto> relatedFeedsPhoto;
	private List<AuthorList> authorList;
	private String photo_small_name;
	private String gallery_name;
	private String strap_headline;
	private String gallery_caption;
	private String gallery_sefurl;
	private String photo_category;
	private String gallerykeywords;
	private String gallerydesc;
	private String photoid;
	private String photoname;
	private String imagestrap;
	private String photoCaption;
	private String photo_title;
	private String photo_width;
	private String photo_height;
	private String image_alt_text;
	private String image_metakey;
	private String image_title;
	private String catname;
	private String fullText;
	private String story_highlights_flag;
	private String story_highlights;
	private String comment_question;
	private String reporter_twitter_handle;
	private String isMedium;
	private String issueId;
	private String titlealias;
	private String titleMagazine;
	private String courtesey;
	private String imagePath;
	private String metatags;
	private String story_syndication;
	private String crt;
	private String mdate;
	private String snd;
	private String article_icon;
	private String article_icon_img;
	private String ctime;
	private String mtime;
	private String quotes;
	private String blurbs;
	private String tables;
	private String large_kicker_image_width;
	private String kicker_image_caption;
	private String indiatoday_expert;
	private String google_standout;	
	private String commentboxflag;
	private String byLineModifiedBy;
	private String externalUrl;
	private String modified;
	private String cat_name;
	private String cat_id;
	private String multibitrate_flag;
	private String video_duration;
	private String created;
	private String mp4_flag;
	private String VideoGallery_foldername;
	private String VideoGallery_filename;
	private List<Bitrate> bitrate;
	private String cat_template_path;
	private String cat_leftnave_path;
	private String cat_bottomnav_path;
	private String cat_rightnav_path;
	private String cat_topnav_path;
	private String date_time;
	private String comments;
	
	@XmlElement
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@XmlElement
	public String getSef_url() {
		return sef_url;
	}
	public void setSef_url(String sef_url) {
		this.sef_url = sef_url;
	}
	@XmlElement
	public String getByline() {
		return byline;
	}
	public void setByline(String byline) {
		this.byline = byline;
	}
	@XmlElement
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@XmlElement
	public String getIntrotext() {
		return introtext;
	}
	public void setIntrotext(String introtext) {
		this.introtext = introtext;
	}
	@XmlElement
	public String getExtralarge_image() {
		return extralarge_image;
	}
	public void setExtralarge_image(String extralarge_image) {
		this.extralarge_image = extralarge_image;
	}
	@XmlElement
	public String getKicker_image() {
		return kicker_image;
	}
	public void setKicker_image(String kicker_image) {
		this.kicker_image = kicker_image;
	}
	@XmlElement
	public String getMobile_image() {
		return mobile_image;
	}
	public void setMobile_image(String mobile_image) {
		this.mobile_image = mobile_image;
	}
	@XmlElement
	public String getLarge_kicker_image() {
		return large_kicker_image;
	}
	public void setLarge_kicker_image(String large_kicker_image) {
		this.large_kicker_image = large_kicker_image;
	}
	@XmlElement
	public String getLarge_kicker_image_alt_text() {
		return large_kicker_image_alt_text;
	}
	public void setLarge_kicker_image_alt_text(String large_kicker_image_alt_text) {
		this.large_kicker_image_alt_text = large_kicker_image_alt_text;
	}
	@XmlElement
	public String getIs_external() {
		return is_external;
	}
	public void setIs_external(String is_external) {
		this.is_external = is_external;
	}
	@XmlElement
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	@XmlElement
	public String getCreateddateYYMMDD() {
		return createddateYYMMDD;
	}
	public void setCreateddateYYMMDD(String createddateYYMMDD) {
		this.createddateYYMMDD = createddateYYMMDD;
	}
	@XmlElement
	public String getContent_url() {
		return content_url;
	}
	public void setContent_url(String content_url) {
		this.content_url = content_url;
	}
	@XmlElement
	public String getPrimary_category() {
		return primary_category;
	}
	public void setPrimary_category(String primary_category) {
		this.primary_category = primary_category;
	}
	@XmlElement
	public String getMedium_kicker_image() {
		return medium_kicker_image;
	}
	public void setMedium_kicker_image(String medium_kicker_image) {
		this.medium_kicker_image = medium_kicker_image;
	}
	@XmlElement
	public String getShort_introtext() {
		return short_introtext;
	}
	public void setShort_introtext(String short_introtext) {
		this.short_introtext = short_introtext;
	}
	@XmlElement
	public String getSectitle() {
		return sectitle;
	}
	public void setSectitle(String sectitle) {
		this.sectitle = sectitle;
	}
	@XmlElement
	public String getSecid() {
		return secid;
	}
	public void setSecid(String secid) {
		this.secid = secid;
	}
	@XmlElement
	public String getSecsefurl() {
		return secsefurl;
	}
	public void setSecsefurl(String secsefurl) {
		this.secsefurl = secsefurl;
	}
	@XmlElement
	public String getSeccontenturl() {
		return seccontenturl;
	}
	public void setSeccontenturl(String seccontenturl) {
		this.seccontenturl = seccontenturl;
	}
	@XmlElement
	public String getCattitle() {
		return cattitle;
	}
	public void setCattitle(String cattitle) {
		this.cattitle = cattitle;
	}
	@XmlElement
	public String getCatid() {
		return catid;
	}
	public void setCatid(String catid) {
		this.catid = catid;
	}
	@XmlElement
	public String getCatsefurl() {
		return catsefurl;
	}
	public void setCatsefurl(String catsefurl) {
		this.catsefurl = catsefurl;
	}
	@XmlElement
	public String getCatcontenturl() {
		return catcontenturl;
	}
	public void setCatcontenturl(String catcontenturl) {
		this.catcontenturl = catcontenturl;
	}
	@XmlElement
	public String getCurcattitle() {
		return curcattitle;
	}
	public void setCurcattitle(String curcattitle) {
		this.curcattitle = curcattitle;
	}
	@XmlElement
	public String getCurcatid() {
		return curcatid;
	}
	public void setCurcatid(String curcatid) {
		this.curcatid = curcatid;
	}
	@XmlElement
	public String getCurcatsefurl() {
		return curcatsefurl;
	}
	public void setCurcatsefurl(String curcatsefurl) {
		this.curcatsefurl = curcatsefurl;
	}
	@XmlElement
	public String getCurcatcontenturl() {
		return curcatcontenturl;
	}
	public void setCurcatcontenturl(String curcatcontenturl) {
		this.curcatcontenturl = curcatcontenturl;
	}
	@XmlElement
	public String getMetatitle() {
		return metatitle;
	}
	public void setMetatitle(String metatitle) {
		this.metatitle = metatitle;
	}
	@XmlElement
	public String getMetakey() {
		return metakey;
	}
	public void setMetakey(String metakey) {
		this.metakey = metakey;
	}
	@XmlElement
	public String getMetadesc() {
		return metadesc;
	}
	public void setMetadesc(String metadesc) {
		this.metadesc = metadesc;
	}
	@XmlElement
	public String getTopnav_path() {
		return topnav_path;
	}
	public void setTopnav_path(String topnav_path) {
		this.topnav_path = topnav_path;
	}
	@XmlElement
	public String getRightnav_path() {
		return rightnav_path;
	}
	public void setRightnav_path(String rightnav_path) {
		this.rightnav_path = rightnav_path;
	}
	@XmlElement
	public String getBottomnav_path() {
		return bottomnav_path;
	}
	public void setBottomnav_path(String bottomnav_path) {
		this.bottomnav_path = bottomnav_path;
	}
	@XmlElement
	public String getLeftnav_path() {
		return leftnav_path;
	}
	public void setLeftnav_path(String leftnav_path) {
		this.leftnav_path = leftnav_path;
	}
	@XmlElement
	public String getHeader_image() {
		return header_image;
	}
	public void setHeader_image(String header_image) {
		this.header_image = header_image;
	}
	@XmlElement
	public List<RelatedFeedVideo> getRelatedFeedsVideo() {
		return relatedFeedsVideo;
	}
	public void setRelatedFeedsVideo(List<RelatedFeedVideo> relatedFeedsVideo) {
		this.relatedFeedsVideo = relatedFeedsVideo;
	}
	@XmlElement
	public List<RelatedFeedPhoto> getRelatedFeedsPhoto() {
		return relatedFeedsPhoto;
	}
	public void setRelatedFeedsPhoto(List<RelatedFeedPhoto> relatedFeedsPhoto) {
		this.relatedFeedsPhoto = relatedFeedsPhoto;
	}
	@XmlElement
	public String getPhoto_small_name() {
		return photo_small_name;
	}
	public void setPhoto_small_name(String photo_small_name) {
		this.photo_small_name = photo_small_name;
	}
	@XmlElement
	public String getGallery_name() {
		return gallery_name;
	}
	public void setGallery_name(String gallery_name) {
		this.gallery_name = gallery_name;
	}
	@XmlElement
	public String getStrap_headline() {
		return strap_headline;
	}
	public void setStrap_headline(String strap_headline) {
		this.strap_headline = strap_headline;
	}
	@XmlElement
	public String getGallery_caption() {
		return gallery_caption;
	}
	public void setGallery_caption(String gallery_caption) {
		this.gallery_caption = gallery_caption;
	}
	@XmlElement
	public String getGallery_sefurl() {
		return gallery_sefurl;
	}
	public void setGallery_sefurl(String gallery_sefurl) {
		this.gallery_sefurl = gallery_sefurl;
	}
	@XmlElement
	public String getPhoto_category() {
		return photo_category;
	}
	public void setPhoto_category(String photo_category) {
		this.photo_category = photo_category;
	}
	@XmlElement
	public String getGallerykeywords() {
		return gallerykeywords;
	}
	public void setGallerykeywords(String gallerykeywords) {
		this.gallerykeywords = gallerykeywords;
	}
	@XmlElement
	public String getGallerydesc() {
		return gallerydesc;
	}
	public void setGallerydesc(String gallerydesc) {
		this.gallerydesc = gallerydesc;
	}
	@XmlElement
	public String getPhotoid() {
		return photoid;
	}
	public void setPhotoid(String photoid) {
		this.photoid = photoid;
	}
	@XmlElement
	public String getPhotoname() {
		return photoname;
	}
	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}
	@XmlElement
	public String getImagestrap() {
		return imagestrap;
	}
	public void setImagestrap(String imagestrap) {
		this.imagestrap = imagestrap;
	}
	@XmlElement
	public String getPhotoCaption() {
		return photoCaption;
	}
	public void setPhotoCaption(String photoCaption) {
		this.photoCaption = photoCaption;
	}
	@XmlElement
	public String getPhoto_title() {
		return photo_title;
	}
	public void setPhoto_title(String photo_title) {
		this.photo_title = photo_title;
	}
	@XmlElement
	public String getPhoto_width() {
		return photo_width;
	}
	public void setPhoto_width(String photo_width) {
		this.photo_width = photo_width;
	}
	@XmlElement
	public String getPhoto_height() {
		return photo_height;
	}
	public void setPhoto_height(String photo_height) {
		this.photo_height = photo_height;
	}
	@XmlElement
	public String getImage_alt_text() {
		return image_alt_text;
	}
	public void setImage_alt_text(String image_alt_text) {
		this.image_alt_text = image_alt_text;
	}
	@XmlElement
	public String getImage_metakey() {
		return image_metakey;
	}
	public void setImage_metakey(String image_metakey) {
		this.image_metakey = image_metakey;
	}
	@XmlElement
	public String getImage_title() {
		return image_title;
	}
	public void setImage_title(String image_title) {
		this.image_title = image_title;
	}
	@XmlElement
	public String getCatname() {
		return catname;
	}
	public void setCatname(String catname) {
		this.catname = catname;
	}
	@XmlElement
	public String getFullText() {
		return fullText;
	}
	public void setFullText(String fullText) {
		this.fullText = fullText;
	}
	@XmlElement
	public String getStory_highlights_flag() {
		return story_highlights_flag;
	}
	public void setStory_highlights_flag(String story_highlights_flag) {
		this.story_highlights_flag = story_highlights_flag;
	}
	@XmlElement
	public String getStory_highlights() {
		return story_highlights;
	}
	public void setStory_highlights(String story_highlights) {
		this.story_highlights = story_highlights;
	}
	@XmlElement
	public String getComment_question() {
		return comment_question;
	}
	public void setComment_question(String comment_question) {
		this.comment_question = comment_question;
	}
	@XmlElement
	public String getReporter_twitter_handle() {
		return reporter_twitter_handle;
	}
	public void setReporter_twitter_handle(String reporter_twitter_handle) {
		this.reporter_twitter_handle = reporter_twitter_handle;
	}
	@XmlElement
	public String getIsMedium() {
		return isMedium;
	}
	public void setIsMedium(String isMedium) {
		this.isMedium = isMedium;
	}
	@XmlElement
	public String getIssueId() {
		return issueId;
	}
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}
	@XmlElement
	public String getTitlealias() {
		return titlealias;
	}
	public void setTitlealias(String titlealias) {
		this.titlealias = titlealias;
	}
	@XmlElement
	public String getTitleMagazine() {
		return titleMagazine;
	}
	public void setTitleMagazine(String titleMagazine) {
		this.titleMagazine = titleMagazine;
	}
	@XmlElement
	public String getCourtesey() {
		return courtesey;
	}
	public void setCourtesey(String courtesey) {
		this.courtesey = courtesey;
	}
	@XmlElement
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@XmlElement
	public String getMetatags() {
		return metatags;
	}
	public void setMetatags(String metatags) {
		this.metatags = metatags;
	}
	@XmlElement
	public String getStory_syndication() {
		return story_syndication;
	}
	public void setStory_syndication(String story_syndication) {
		this.story_syndication = story_syndication;
	}
	@XmlElement
	public String getCrt() {
		return crt;
	}
	public void setCrt(String crt) {
		this.crt = crt;
	}
	@XmlElement
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	@XmlElement
	public String getSnd() {
		return snd;
	}
	public void setSnd(String snd) {
		this.snd = snd;
	}
	@XmlElement
	public String getArticle_icon() {
		return article_icon;
	}
	public void setArticle_icon(String article_icon) {
		this.article_icon = article_icon;
	}
	@XmlElement
	public String getArticle_icon_img() {
		return article_icon_img;
	}
	public void setArticle_icon_img(String article_icon_img) {
		this.article_icon_img = article_icon_img;
	}
	@XmlElement
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	@XmlElement
	public String getMtime() {
		return mtime;
	}
	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
	@XmlElement
	public String getQuotes() {
		return quotes;
	}
	public void setQuotes(String quotes) {
		this.quotes = quotes;
	}
	@XmlElement
	public String getBlurbs() {
		return blurbs;
	}
	public void setBlurbs(String blurbs) {
		this.blurbs = blurbs;
	}
	@XmlElement
	public String getTables() {
		return tables;
	}
	public void setTables(String tables) {
		this.tables = tables;
	}
	@XmlElement
	public String getLarge_kicker_image_width() {
		return large_kicker_image_width;
	}
	public void setLarge_kicker_image_width(String large_kicker_image_width) {
		this.large_kicker_image_width = large_kicker_image_width;
	}
	@XmlElement
	public String getKicker_image_caption() {
		return kicker_image_caption;
	}
	public void setKicker_image_caption(String kicker_image_caption) {
		this.kicker_image_caption = kicker_image_caption;
	}
	@XmlElement
	public String getIndiatoday_expert() {
		return indiatoday_expert;
	}
	public void setIndiatoday_expert(String indiatoday_expert) {
		this.indiatoday_expert = indiatoday_expert;
	}
	@XmlElement
	public String getGoogle_standout() {
		return google_standout;
	}
	public void setGoogle_standout(String google_standout) {
		this.google_standout = google_standout;
	}
	@XmlElement
	public String getCommentboxflag() {
		return commentboxflag;
	}
	public void setCommentboxflag(String commentboxflag) {
		this.commentboxflag = commentboxflag;
	}
	@XmlElement
	public String getByLineModifiedBy() {
		return byLineModifiedBy;
	}
	public void setByLineModifiedBy(String byLineModifiedBy) {
		this.byLineModifiedBy = byLineModifiedBy;
	}
	@XmlElement
	public String getExternalUrl() {
		return externalUrl;
	}
	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}
	@XmlElement
	public List<AuthorList> getAuthorList() {
		return authorList;
	}
	public void setAuthorList(List<AuthorList> authorList) {
		this.authorList = authorList;
	}
	@XmlElement
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	@XmlElement
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	@XmlElement
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	@XmlElement
	public List<Bitrate> getBitrate() {
		return bitrate;
	}
	public void setBitrate(List<Bitrate> bitrate) {
		this.bitrate = bitrate;
	}
	@XmlElement
	public String getMultibitrate_flag() {
		return multibitrate_flag;
	}
	public void setMultibitrate_flag(String multibitrate_flag) {
		this.multibitrate_flag = multibitrate_flag;
	}
	@XmlElement
	public String getVideo_duration() {
		return video_duration;
	}
	public void setVideo_duration(String video_duration) {
		this.video_duration = video_duration;
	}
	@XmlElement
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	@XmlElement
	public String getMp4_flag() {
		return mp4_flag;
	}
	public void setMp4_flag(String mp4_flag) {
		this.mp4_flag = mp4_flag;
	}
	@XmlElement
	public String getVideoGallery_foldername() {
		return VideoGallery_foldername;
	}
	public void setVideoGallery_foldername(String videoGallery_foldername) {
		VideoGallery_foldername = videoGallery_foldername;
	}
	@XmlElement
	public String getVideoGallery_filename() {
		return VideoGallery_filename;
	}
	public void setVideoGallery_filename(String videoGallery_filename) {
		VideoGallery_filename = videoGallery_filename;
	}
	@XmlElement
	public String getCat_template_path() {
		return cat_template_path;
	}
	public void setCat_template_path(String cat_template_path) {
		this.cat_template_path = cat_template_path;
	}
	@XmlElement
	public String getCat_leftnave_path() {
		return cat_leftnave_path;
	}
	public void setCat_leftnave_path(String cat_leftnave_path) {
		this.cat_leftnave_path = cat_leftnave_path;
	}
	@XmlElement
	public String getCat_bottomnav_path() {
		return cat_bottomnav_path;
	}
	public void setCat_bottomnav_path(String cat_bottomnav_path) {
		this.cat_bottomnav_path = cat_bottomnav_path;
	}
	@XmlElement
	public String getCat_rightnav_path() {
		return cat_rightnav_path;
	}
	public void setCat_rightnav_path(String cat_rightnav_path) {
		this.cat_rightnav_path = cat_rightnav_path;
	}
	@XmlElement
	public String getCat_topnav_path() {
		return cat_topnav_path;
	}
	public void setCat_topnav_path(String cat_topnav_path) {
		this.cat_topnav_path = cat_topnav_path;
	}
	@XmlElement
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	@XmlElement
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
}
