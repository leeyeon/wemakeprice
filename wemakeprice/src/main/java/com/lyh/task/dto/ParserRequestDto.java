package com.lyh.task.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import com.lyh.task.common.ParserType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParserRequestDto {

	@URL(message = "URL을 형식에 맞게 입력해주세요.")
	private String url;
	
	@NotBlank(message = "타입을 입력해주세요.")
	private ParserType type;
	
	@NotBlank(message = "숫자를 입력해주세요.")
	private int unitNum;
	
}
