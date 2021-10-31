package com.lyh.task.vo;

import lombok.Getter;

@Getter
public class ParseResult {
	private final String quotient; //몫
	private final String remainder; //나머지
	
	public ParseResult(String quotient) { //몫만 있는 경우
		this(quotient, "");
	}
	
	public ParseResult(String quotient, String remainder) {
		this.quotient = quotient;
		this.remainder = remainder;
	}
}
