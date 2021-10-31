package com.lyh.task.common;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ParserType {
	EXCLUDE_TAG,
	ALL;
	
	@JsonCreator
    public static ParserType decode(String s) {
        return ParserType.valueOf(s.toUpperCase());
    }
}
