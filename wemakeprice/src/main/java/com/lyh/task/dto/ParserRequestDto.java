package com.lyh.task.dto;

import com.lyh.task.common.ParserType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParserRequestDto {

	private String url;
	private ParserType type;
	private int unitNum;
	
}
