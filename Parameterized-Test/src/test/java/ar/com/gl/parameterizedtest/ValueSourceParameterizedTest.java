package ar.com.gl.parameterizedtest;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ValueSourceParameterizedTest {

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3, 4, 5 })
	void numberTest(Integer input) {
		System.out.println("---------ISTYPESOFBLANKS------------");
		System.out.println("Input: " + input);
		assertTrue(input < 3);

	}

	@ParameterizedTest
	@ValueSource(strings = { "true", "false" })
	void booleanTest(Boolean input) {
		System.out.println("---------BOOLEAN------------");
		System.out.println("Input: " + input);
		assertTrue(input);
	}

	@ParameterizedTest
	@ValueSource(strings = { "", "  " })
	void isBlankTest(String input) {
		System.out.println("---------ISBLANK------------");
		System.out.println("Input: " + input);
		assertTrue(Strings.isBlank(input));
	}
}