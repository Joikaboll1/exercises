package com.pluralsight.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExactMatchDto {

	private String exactMatch;

	public String getExactMatch() {
		return exactMatch;
	}

	public void setExactMatch(String exactMatch) {
		this.exactMatch = exactMatch;
	}
}
