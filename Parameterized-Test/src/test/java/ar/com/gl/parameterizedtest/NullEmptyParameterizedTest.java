package ar.com.gl.parameterizedtest;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

public class NullEmptyParameterizedTest {

	@ParameterizedTest
	@NullSource
	void isNullTest(String input) {
		System.out.println("---------ISNULL------------");
		System.out.println("Input: " + input);
		assertTrue(Strings.isBlank(input));
	}

	@ParameterizedTest
	@EmptySource
	void isEmptyTest(String input) {
		System.out.println("---------ISEMPTY------------");
		System.out.println("Input: " + input);
		assertTrue(Strings.isBlank(input));
	}

	@ParameterizedTest
	@NullAndEmptySource
	void isNullAndEmptyTest(String input) {
		System.out.println("---------ISNULLANDEMPTY------------");
		System.out.println("Input: " + input);
		assertTrue(Strings.isBlank(input));
	}

}
