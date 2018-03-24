package com.itgd.parser;
import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class JsonRec 
{
	/*private String title;
	private String link;
	private String desc;*/
	private List<ItemRec> items;
	/*@XmlElement
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@XmlElement
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@XmlElement
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}*/
	@XmlElement
	public List<ItemRec> getItems() {
		return items;
	}
	public void setItems(List<ItemRec> items) {
		this.items = items;
	}
	
}
