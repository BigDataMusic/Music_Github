package com.aclass.daum;

import javax.xml.bind.annotation.XmlElement;

public class Item {
	private String description;

	public String getDescription() {
		return description;
	}
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
}
