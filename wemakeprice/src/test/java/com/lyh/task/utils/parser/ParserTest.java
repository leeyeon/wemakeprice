package com.lyh.task.utils.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.lyh.task.vo.ParseResult;

@SpringBootTest
@AutoConfigureMockMvc
public class ParserTest {

    @ParameterizedTest
    @MethodSource("simpleTestCase")
    public void SimpleHtmlParserTest(String data, int unitNum, String quotient, String remainder) {
        // given
        Parser parser = new SimpleHtmlParser("http://localhost:8080/", unitNum);
        parser.setHtml(data);
        
        // when
        ParseResult result = parser.parse();

        // then
        assertThat(result.getQuotient()).isEqualTo(quotient);
        assertThat(result.getRemainder()).isEqualTo(remainder);
    }
    
    @ParameterizedTest
    @MethodSource("excludeTagTestCase")
    public void ExcludeTagHtmlParserTest(String data, int unitNum, String quotient, String remainder) {
        // given
        Parser parser = new ExcludeTagHtmlParser("http://localhost:8080/", unitNum);
        parser.setHtml(data);
        
        // when
        ParseResult result = parser.parse();

        // then
        assertThat(result.getQuotient()).isEqualTo(quotient);
        assertThat(result.getRemainder()).isEqualTo(remainder);
    }

    private static Stream<Arguments> simpleTestCase() {
        return Stream.of(
        		Arguments.of("timeout: 10000", 100 , "", "e0i0m0o0t1tu"),
        		Arguments.of("<aaa>timeout: 10000</aaa>", 100 , "", "a0a0a0a0a1aeimottu"),
        		Arguments.of("<aaa>timeout: 10000</aaa>", 10 , "a0a0a0a0a1", "aeimottu")
        );
    }
    
    private static Stream<Arguments> excludeTagTestCase() {
        return Stream.of(
        		Arguments.of("timeout: 10000", 100 , "", "e0i0m0o0t1tu"),
        		Arguments.of("<aaa>timeout: 10000</aaa>", 100 , "", "e0i0m0o0t1tu"),
        		Arguments.of("<aaa>timeout: 10000</aaa>", 10 , "e0i0m0o0t1", "tu")
        );
    }
}
