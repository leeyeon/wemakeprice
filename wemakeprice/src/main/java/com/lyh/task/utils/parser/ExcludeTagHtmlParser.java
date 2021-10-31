package com.lyh.task.utils.parser;

public class ExcludeTagHtmlParser extends Parser {
	
	public ExcludeTagHtmlParser(String url, int unitNum) {
		super(url, unitNum);
	}

	@Override
	protected String getContent(String html) {
		String EXCLUDE_TAG_PATTERN = "<[^>]*>";
		return html.replaceAll(EXCLUDE_TAG_PATTERN, "");
	}
}