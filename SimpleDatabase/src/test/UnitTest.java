package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import database.Database;

public class UnitTest {
	final ByteArrayOutputStream myOut = new ByteArrayOutputStream();

	@Test
	public void test1() {
		String output = "10NULL";
		System.setOut(new PrintStream(myOut));
		Database.main(new String[] { "test1.txt" });
		String thisOut = myOut.toString();
		thisOut = thisOut.replaceAll("\\r|\\n", "");
		assertEquals(output, thisOut);
	}

	@Test
	public void test2() {
		String output = "201";
		System.setOut(new PrintStream(myOut));
		Database.main(new String[] { "test2.txt" });
		String thisOut = myOut.toString();
		thisOut = thisOut.replaceAll("\\r|\\n", "");
		assertEquals(output, thisOut);
	}

	@Test
	public void test3() {
		String output = "102010NULL";
		System.setOut(new PrintStream(myOut));
		Database.main(new String[] { "test3.txt" });
		String thisOut = myOut.toString();
		thisOut = thisOut.replaceAll("\\r|\\n", "");
		assertEquals(output, thisOut);
	}

	@Test
	public void test4() {
		String output = "40NO TRANSACTION";
		System.setOut(new PrintStream(myOut));
		Database.main(new String[] { "test4.txt" });
		String thisOut = myOut.toString();
		thisOut = thisOut.replaceAll("\\r|\\n", "");
		assertEquals(output, thisOut);
	}

	@Test
	public void test5() {
		String output = "50NULL6060";
		System.setOut(new PrintStream(myOut));
		Database.main(new String[] { "test5.txt" });
		String thisOut = myOut.toString();
		thisOut = thisOut.replaceAll("\\r|\\n", "");
		assertEquals(output, thisOut);
	}

	@Test
	public void test6() {
		String output = "101";
		System.setOut(new PrintStream(myOut));
		Database.main(new String[] { "test6.txt" });
		String thisOut = myOut.toString();
		thisOut = thisOut.replaceAll("\\r|\\n", "");
		assertEquals(output, thisOut);
	}

}
