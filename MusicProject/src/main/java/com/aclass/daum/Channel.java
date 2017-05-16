package com.aclass.daum;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;
@XmlRootElement
public class Channel {
	private List<Item> item= new ArrayList<Item>();

	public List<Item> getItem() {
		return item;
	}
	@XmlElement
	public void setItem(List<Item> item) {
		this.item = item;
	}
}
