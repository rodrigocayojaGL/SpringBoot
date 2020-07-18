package ar.com.gl.parameterizedtest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Month;
import java.util.EnumSet;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class EnumParameterizedTest {

	@ParameterizedTest
	@EnumSource(value = Month.class, names = { "APRIL", "JUNE", "SEPTEMBER", "NOVEMBER" })
	void someMonthsTest(Month month) {
		final boolean isALeapYear = false;
		System.out.println("---------SOMEMONTHS------------");
		System.out.println("Month: " + month);
		assertEquals(30, month.length(isALeapYear));
	}

	@ParameterizedTest
	@EnumSource(Month.class) // passing all 12 months
	void getValueForaMonthTest(Month month) {
		System.out.println("---------GETVALUEFORAMONTH------------");
		System.out.println("Month: " + month);
		int monthNumber = month.getValue();
		assertTrue(monthNumber >= 1 && monthNumber <= 12);
	}

	@ParameterizedTest
	@EnumSource(value = Month.class, names = ".+BER", mode = EnumSource.Mode.MATCH_ANY)
	void fourMonthsAreEndingWithBerTest(Month month) {
		System.out.println("---------FOURMONTHSAREENDINGWITHBER------------");
		System.out.println("Month: " + month);
		EnumSet<Month> months = EnumSet.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
		assertTrue(months.contains(month));
	}
}
