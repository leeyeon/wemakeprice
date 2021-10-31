package com.lyh.task.utils.parser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.lyh.task.exception.NotFoundException;
import com.lyh.task.vo.ParseData;
import com.lyh.task.vo.ParseResult;

import lombok.NoArgsConstructor;

/*
 * HTML �Ľ� ���� ��ƿ
 */
@NoArgsConstructor
public abstract class Parser {
	private String url;
	private int unitNum;
	private String html;
	
	public Parser(String url, int unitNum) {
		this.url = url;
		this.unitNum = unitNum;
		setHtml();
	}
	
	public ParseResult parse() {
		ParseResult result = null;
		
		// Url ��û �� Html ���� ��������
		String html = getHtml();
		
		// Ÿ�Ժ��� ���� �������� ����,���ڸ� ����
		String content = removeExceptNumAndAlphabet(getContent(html));
		
		// ��ҹ��� ����, ���� �и�
		ParseData data = splitNumAndAlphabet(content);
		
		// content ���� ���� �� ���� ����
		if(data.isData()) {
			// 1. �������� ����
			sortParseData(data);
			
			// 2. ����, ���� ������ �����ϱ�
			String crossData = crossNumAndAlphabet(data);
			
			// 3. ��, ������ ���ϱ�
			result = devideData(crossData);
		} else {
			result = new ParseResult("","");
		}
		
		return result;
	}

	public String getHtml() {
		return html;
	}
	
	public void setHtml(String html) {
		this.html = html;
	}
	
	public void setHtml() {
		try {
			Document doc = Jsoup.connect(url).get();
			html = doc.toString();
		} catch(IOException ex) {
			throw new NotFoundException(ex.getMessage()); //URL��ȿ����������
		}
	}
	
	private String removeExceptNumAndAlphabet(String content) {
		return content.replaceAll("[^a-zA-Z0-9]", "");
	}
	
	private ParseData splitNumAndAlphabet(String content) {
		if(content.equals("")) {  // ���� ������
			return new ParseData();
		}
		
		StringBuilder number = new StringBuilder();
        StringBuilder upperCaseAlphabet = new StringBuilder();
        StringBuilder lowerCaseAlphabet = new StringBuilder();
        
        char[] chArr = content.toCharArray();
        
        for (char ch : chArr) {
			if(ch >= '0' && ch <= '9') { // ����
				number.append(ch);
			} else if(ch >= 'A' && ch <= 'Z') { // �빮��
				upperCaseAlphabet.append(ch);
			} else if(ch >= 'a' && ch <= 'z') { // �ҹ���
				lowerCaseAlphabet.append(ch);
			}
		}
        
		return new ParseData(number.toString(), upperCaseAlphabet.toString(), lowerCaseAlphabet.toString());
	}
	
	private void sortParseData(ParseData parseData) {
		char[] numberChArr = parseData.getNumber().toCharArray();
		char[] upperCaseAlphabetChArr = parseData.getUpperCaseAlphabet().toCharArray();
		char[] lowerCaseAlphabetChArr = parseData.getLowerCaseAlphabet().toCharArray();
		
		Arrays.sort(numberChArr);
		
		StringBuilder number = new StringBuilder();
		for (char ch : numberChArr) {
			number.append(ch);
		}
		
		List<StringBuilder> sbList = Stream.generate(StringBuilder::new)
											.limit(26)
											.collect(Collectors.toList());
		
		for(char ch : upperCaseAlphabetChArr) {
			sbList.get(ch-65).append(ch);
		}
		for(char ch : lowerCaseAlphabetChArr) {
			sbList.get(ch-97).append(ch);
		}
		
		StringBuilder alphabet = new StringBuilder();
		for(StringBuilder stringBuilder : sbList) {
			alphabet.append(stringBuilder);
		}
		
		parseData.setNumber(number.toString());
		parseData.setAlphabet(alphabet.toString());
		
	}
	
	private String crossNumAndAlphabet(ParseData parseData) {
		StringBuilder result = new StringBuilder();
		String number = parseData.getNumber();
		String alphabet = parseData.getAlphabet();
		
		int commonLength = (alphabet.length() > number.length()) ? number.length() : alphabet.length();
		
		// ����, �������� ���� ���� ����
		for (int i = 0; i < commonLength; i++) {
			result.append(alphabet.charAt(i));
			result.append(number.charAt(i));
		}
		
		// ���� ���� ���� �� ���� ���� ������
		if(alphabet.length() > number.length()) {
			result.append(alphabet.substring(commonLength));
		} else {
			result.append(number.substring(commonLength));
		}
		
		return result.toString();
	}
	
	private ParseResult devideData(String data) {
		int quotientLength = (data.length() / unitNum) * unitNum;
		
		return new ParseResult(data.substring(0, quotientLength), data.substring(quotientLength));
	}
	
	protected abstract String getContent(String html);

}
