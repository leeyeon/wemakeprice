package com.lyh.task.utils.parser;

public class SimpleHtmlParser extends Parser {

	public SimpleHtmlParser(String url, int unitNum) {
		super(url, unitNum);
	}

	@Override
	protected String getContent(String html) {
		return html;
	}
}
