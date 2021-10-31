package com.lyh.task.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParseData {
	private String number;
	private String upperCaseAlphabet;
	private String lowerCaseAlphabet;
	private String alphabet;
	private boolean isData;
	
	public ParseData() {
		this("","","");
	}
	
	public ParseData(String number, String upperCaseAlphabet, String lowerCaseAlphabet) {
		this.number = number;
		this.upperCaseAlphabet = upperCaseAlphabet;
		this.lowerCaseAlphabet = lowerCaseAlphabet;
		this.isData = true;
	}
}
