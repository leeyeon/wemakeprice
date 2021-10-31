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

	@URL(message = "URL�� ���Ŀ� �°� �Է����ּ���.")
	private String url;
	
	@NotBlank(message = "Ÿ���� �Է����ּ���.")
	private ParserType type;
	
	@NotBlank(message = "���ڸ� �Է����ּ���.")
	private int unitNum;
	
}
