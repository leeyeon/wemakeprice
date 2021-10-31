package com.lyh.task.vo;

import lombok.Getter;

@Getter
public class ParseResult {
	private final String quotient; //��
	private final String remainder; //������
	
	public ParseResult(String quotient) { //�� �ִ� ���
		this(quotient, "");
	}
	
	public ParseResult(String quotient, String remainder) {
		this.quotient = quotient;
		this.remainder = remainder;
	}
}
