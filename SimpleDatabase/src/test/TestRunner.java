package test;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(UnitTest.class);
		for (Failure failure : result.getFailures()) {
			Logger.getLogger("failure").log(new LogRecord(Level.SEVERE, failure.toString()));
		}
		Logger.getLogger("failure").log(new LogRecord(Level.INFO, "Done testing"));
	}
}