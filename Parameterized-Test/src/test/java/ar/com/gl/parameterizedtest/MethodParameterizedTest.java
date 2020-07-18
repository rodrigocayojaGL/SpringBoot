package ar.com.gl.parameterizedtest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MethodParameterizedTest {

	@ParameterizedTest
	@MethodSource("provideStringsForIsBlank")
	void isForNullOrBlankStringsTest(String input, boolean expected) {
		System.out.println("--------ISFORNULLORBLANKSTRINGS------------");
		System.out.println("input: " + input);
		System.out.println("expected: " + expected);

		assertEquals(expected, Strings.isBlank(input));
	}

	private static Stream<Arguments> provideStringsForIsBlank() {
		return Stream.of(Arguments.of(null, true), Arguments.of("", true), Arguments.of("  ", true),
				Arguments.of("not blank", false));
	}

	@ParameterizedTest
	@MethodSource("users")
	void userDniTest(UserDto user) {
		System.out.println("--------USERDNI------------");
		System.out.println("DNI:  " + user.getDni());
		assertNotNull(user);
	}

	private static Stream<UserDto> users() {
		final UserDto userA = new UserDto();
		userA.setDni("1");
		final UserDto userB = new UserDto();
		userB.setDni("2");
		return Stream.of(userA, userB);
	}
}
