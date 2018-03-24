package com.itgd.parser;
import javax.xml.bind.annotation.XmlElement;

public class Bitrate {
private String bitrate;
private String sourceFileName;
private String filePath;
private String status;
private String fileDuration;
private String website;
private String hlsDomain;
private String s3Domain;
private String rtmpDomain;
private String videoType;
private String multipartVideo;
private String ordering;
@XmlElement
public String getBitrate() {
	return bitrate;
}
public void setBitrate(String bitrate) {
	this.bitrate = bitrate;
}
@XmlElement
public String getSourceFileName() {
	return sourceFileName;
}
public void setSourceFileName(String sourceFileName) {
	this.sourceFileName = sourceFileName;
}
@XmlElement
public String getFilePath() {
	return filePath;
}
public void setFilePath(String filePath) {
	this.filePath = filePath;
}
@XmlElement
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
@XmlElement
public String getFileDuration() {
	return fileDuration;
}
public void setFileDuration(String fileDuration) {
	this.fileDuration = fileDuration;
}
@XmlElement
public String getWebsite() {
	return website;
}
public void setWebsite(String website) {
	this.website = website;
}
@XmlElement
public String getHlsDomain() {
	return hlsDomain;
}
public void setHlsDomain(String hlsDomain) {
	this.hlsDomain = hlsDomain;
}
@XmlElement
public String getS3Domain() {
	return s3Domain;
}
public void setS3Domain(String s3Domain) {
	this.s3Domain = s3Domain;
}
@XmlElement
public String getRtmpDomain() {
	return rtmpDomain;
}
public void setRtmpDomain(String rtmpDomain) {
	this.rtmpDomain = rtmpDomain;
}
@XmlElement
public String getVideoType() {
	return videoType;
}
public void setVideoType(String videoType) {
	this.videoType = videoType;
}
@XmlElement
public String getMultipartVideo() {
	return multipartVideo;
}
public void setMultipartVideo(String multipartVideo) {
	this.multipartVideo = multipartVideo;
}
@XmlElement
public String getOrdering() {
	return ordering;
}
public void setOrdering(String ordering) {
	this.ordering = ordering;
}

}
