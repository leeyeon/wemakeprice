package com.lyh.task.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyh.task.dto.ParserRequestDto;
import com.lyh.task.dto.ParserResponseDto;
import com.lyh.task.service.PageParserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class PageParserController {
	
	private final PageParserService pageParserService;

	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@PostMapping("/parser")
	@ResponseBody
	public ParserResponseDto pageParse(@Valid @RequestBody ParserRequestDto parserRequestDto) {
		return pageParserService.parser(parserRequestDto);
	}
}