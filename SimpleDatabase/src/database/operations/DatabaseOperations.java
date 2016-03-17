package database.operations;

import java.util.HashMap;

public class DatabaseOperations {
	HashMap<String, Integer> variables;
	HashMap<Integer, Integer> valueCounts;

	public void begin() {
		variables = new HashMap<String, Integer>();
		valueCounts = new HashMap<Integer, Integer>();
	}

	private void printErrorMessage() {
		System.err.println("Variables have not been initialized. Please use BEGIN command "
				+ "before doing any other operation.\nIgnoring this line.");
	}

	public void get(String variable) {
		if (variables != null) {
			if (variables.containsKey(variable))
				System.out.println(variable);
			else
				System.out.println("NULL");
		} else
			printErrorMessage();

	}

	public void set(String variable, int value) {
		if (variables != null && valueCounts != null) {
			variables.put(variable, value);
			if (valueCounts.containsKey(valueCounts))
				valueCounts.put(value, valueCounts.get(value) + 1);
			else
				valueCounts.put(value, 1);
		} else
			printErrorMessage();
	}

	public void unset(String variable) {
		if (variables != null && valueCounts != null) {
			if (variables.containsKey(variable)) {
				int value = variables.get(variable);
				valueCounts.put(value, valueCounts.get(value) - 1);
				variables.remove(variable);
			}
		} else
			printErrorMessage();
	}

	public void numEqualTo(int value) {
		if (valueCounts != null) {
			if (valueCounts.containsKey(value))
				System.out.println(valueCounts.get(value));
			else
				System.out.println("0");
		} else
			printErrorMessage();
	}
}
