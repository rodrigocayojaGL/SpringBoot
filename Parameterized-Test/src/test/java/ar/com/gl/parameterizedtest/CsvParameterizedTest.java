package ar.com.gl.parameterizedtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class CsvParameterizedTest {

	@ParameterizedTest
	@CsvSource({ "test,TEST", "tEst,TEST", "Java,JAVA" })
	void toExpectedUppercaseValueTest(String input, String expected) {
		String actualValue = input.toUpperCase();
		System.out.println("---------TOEXPECTEDUPPERCASEVALUE------------");
		System.out.println("Input: " + input);
		System.out.println("Expected: " + expected);

		assertEquals(expected, actualValue);
	}

	@ParameterizedTest
	@CsvSource(value = { "test:test", "tEst:test", "Java:java" }, delimiter = ':')
	void toExpectedLowercaseValueTest(String input, String expected) {
		System.out.println("---------TOEXPECTEDLOWERCASEVALUE------------");
		String actualValue = input.toLowerCase();
		System.out.println("Input: " + input);
		System.out.println("Expected: " + expected);
		assertEquals(expected, actualValue);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
	void toCSVFileTest(String input, String expected) {
		System.out.println("---------TOCSVFILE------------");
		System.out.println("Input: " + input);
		System.out.println("Expected: " + expected);
		String actualValue = input.toUpperCase();
		assertEquals(expected, actualValue);
	}
}