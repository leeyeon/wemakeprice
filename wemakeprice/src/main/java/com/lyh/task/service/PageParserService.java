package com.lyh.task.service;

import org.springframework.stereotype.Service;

import com.lyh.task.common.ParserType;
import com.lyh.task.dto.ParserRequestDto;
import com.lyh.task.dto.ParserResponseDto;
import com.lyh.task.exception.NotFoundException;
import com.lyh.task.utils.parser.ExcludeTagHtmlParser;
import com.lyh.task.utils.parser.Parser;
import com.lyh.task.utils.parser.SimpleHtmlParser;
import com.lyh.task.vo.ParseResult;

@Service
public class PageParserService {
	
	public ParserResponseDto parser(ParserRequestDto requestDto) {
		Parser parser = null;
		if(requestDto.getType() == ParserType.ALL) {
			parser = new SimpleHtmlParser(requestDto.getUrl(), requestDto.getUnitNum());
		} else if(requestDto.getType() == ParserType.EXCLUDE_TAG) {
			parser = new ExcludeTagHtmlParser(requestDto.getUrl(), requestDto.getUnitNum());
		} else {
			throw new NotFoundException("타입을 찾을 수 없습니다.");
		}
		ParseResult result = parser.parse();
		ParserResponseDto responseDto = new ParserResponseDto();
		responseDto.setQuotient(result.getQuotient());
		responseDto.setRemainder(result.getRemainder());
		return responseDto;
	}
}
