package com.itgd.parser;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
public class ItemRec 
{
private String id;
/*private String articlebody;*/
private String name;
private String type;
private Integer start;
private Integer end;
private Integer total_records;
private List<ItemFeed> feed; 
private String blogTitle;
private String blogDesc;

/*
private String metakey;
private String title;
private String videoUrl;
private String desc;
private String date;
private String thumbUrl;*/
@XmlAttribute
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Integer getStart() {
	return start;
}
public void setStart(Integer start) {
	this.start = start;
}
public Integer getEnd() {
	return end;
}
public void setEnd(Integer end) {
	this.end = end;
}
public List<ItemFeed> getFeed() {
	return feed;
}
public void setFeed(List<ItemFeed> feed) {
	this.feed = feed;
}
public Integer getTotal_records() {
	return total_records;
}
public void setTotal_records(Integer total_records) {
	this.total_records = total_records;
}
public String getBlogTitle() {
	return blogTitle;
}
public void setBlogTitle(String blogTitle) {
	this.blogTitle = blogTitle;
}
public String getBlogDesc() {
	return blogDesc;
}
public void setBlogDesc(String blogDesc) {
	this.blogDesc = blogDesc;
}

/*
 * @XmlElement
public String getArticlebody() {
	return articlebody;
}
public void setArticlebody(String articlebody) {
	this.articlebody = articlebody;
}
@XmlElement
public String getMetakey() {
	return metakey;
}
public void setMetakey(String metakey) {
	this.metakey = metakey;
}
@XmlElement
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
@XmlElement
public String getVideoUrl() {
	return videoUrl;
}
public void setVideoUrl(String videoUrl) {
	this.videoUrl = videoUrl;
}
@XmlElement
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
@XmlElement
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
@XmlElement
public String getThumbUrl() {
	return thumbUrl;
}
public void setThumbUrl(String thumbUrl) {
	this.thumbUrl = thumbUrl;
}*/
}
