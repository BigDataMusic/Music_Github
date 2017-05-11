package com.aclass.review.naver;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Channel {
	private List<Item> item=new ArrayList<Item>();

	public List<Item> getItem() {
		return item;
	}
    @XmlElement
	public void setItem(List<Item> item) {
		this.item = item;
	}
	
}