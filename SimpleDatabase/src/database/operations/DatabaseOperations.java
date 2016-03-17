package database.operations;

import java.util.HashMap;

public class DatabaseOperations {
	private HashMap<String, Integer> committedVariables;
	private HashMap<Integer, Integer> committedValueCounts;
	private HashMap<String, Integer> variables;
	private HashMap<Integer, Integer> valueCounts;

	private boolean isCommitted = false;
	private int transactionCount = 0;

	public void begin() {
		variables = new HashMap<String, Integer>();
		valueCounts = new HashMap<Integer, Integer>();
		committedVariables = new HashMap<String, Integer>();
		committedValueCounts = new HashMap<Integer, Integer>();
	}

	private void printErrorMessage() {
		System.err.println("Variables have not been initialized. Please use BEGIN command "
				+ "before doing any other operation.\nIgnoring this line.");
	}

	public void get(String variable) {
		if (variables != null) {
			if (variables.containsKey(variable))
				System.out.println(variables.get(variable));
			else
				System.out.println("NULL");
		} else
			printErrorMessage();

	}

	public void set(String variable, int value) {
		if (variables != null && valueCounts != null) {
			variables.put(variable, value);
			if (valueCounts.containsKey(value))
				valueCounts.put(value, valueCounts.get(value) + 1);
			else
				valueCounts.put(value, 1);
			transactionCount++;
		} else
			printErrorMessage();
	}

	public void unset(String variable) {
		if (variables != null && valueCounts != null) {
			if (variables.containsKey(variable)) {
				int value = variables.get(variable);
				valueCounts.put(value, valueCounts.get(value) - 1);
				variables.remove(variable);
				transactionCount++;
			} else
				System.err.println("Variable does not exist in memory.");
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

	public void rollback() {
		if (variables != null && committedVariables != null && valueCounts != null && committedValueCounts != null) {
			if (transactionCount == 0) {
				System.out.println("NO TRANSACTION");
				return;
			}
			variables.clear();
			valueCounts.clear();
			for (String variable : committedVariables.keySet())
				variables.put(variable, committedVariables.get(variable));
			for (int value : committedValueCounts.keySet())
				valueCounts.put(value, committedValueCounts.get(value));
		} else
			printErrorMessage();
	}

	public void commit() {
		if (variables != null && committedVariables != null && valueCounts != null && committedValueCounts != null) {
			for (String variable : variables.keySet())
				committedVariables.put(variable, variables.get(variable));
			for (int value : valueCounts.keySet())
				committedValueCounts.put(value, valueCounts.get(value));
			isCommitted = true;
			transactionCount = 0;
		} else
			printErrorMessage();
	}
}
