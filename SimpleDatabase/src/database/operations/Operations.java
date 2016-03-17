package database.operations;

import java.util.HashMap;

public class Operations {
	private HashMap<String, Integer> variables;
	private HashMap<Integer, Integer> valueCounts;

	public Operations() {
		variables = new HashMap<String, Integer>();
		valueCounts = new HashMap<Integer, Integer>();
	}

	public Operations(Operations operations) {
		this.variables = new HashMap<String, Integer>(operations.getVariables());
		this.valueCounts = new HashMap<Integer, Integer>(operations.getValueCounts());
	}

	public void get(String variable) {
		if (variables != null) {
			if (variables.containsKey(variable))
				System.out.println(variables.get(variable));
			else
				System.out.println("NULL");
		}
	}

	public void set(String variable, int value) {
		if (variables != null && valueCounts != null) {
			if (variables.containsKey(variable)) {
				int updatedValue = variables.get(variable);
				variables.put(variable, value);
				valueCounts.put(updatedValue, valueCounts.get(updatedValue) - 1);
			} else {
				variables.put(variable, value);
			}
			if (valueCounts.containsKey(value))
				valueCounts.put(value, valueCounts.get(value) + 1);
			else
				valueCounts.put(value, 1);
		}
	}

	public void unset(String variable) {
		if (variables != null && valueCounts != null) {
			if (variables.containsKey(variable)) {
				int value = variables.get(variable);
				valueCounts.put(value, valueCounts.get(value) - 1);
				variables.remove(variable);
			} else
				System.err.println("Variable does not exist in memory.");
		}
	}

	public void numEqualTo(int value) {
		if (valueCounts != null) {
			if (valueCounts.containsKey(value))
				System.out.println(valueCounts.get(value));
			else
				System.out.println("0");
		}
	}

	public boolean equals(Operations operations) {
		if (this.variables.keySet().size() == operations.getVariables().keySet().size()) {
			for (String variable : this.variables.keySet()) {
				if (operations.getVariables().containsKey(variable)
						&& operations.getVariables().get(variable) == this.variables.get(variable))
					continue;
				else
					return false;
			}
		} else
			return false;
		return true;
	}

	public HashMap<String, Integer> getVariables() {
		return variables;
	}

	public void setVariables(HashMap<String, Integer> variables) {
		this.variables = variables;
	}

	public HashMap<Integer, Integer> getValueCounts() {
		return valueCounts;
	}

	public void setValueCounts(HashMap<Integer, Integer> valueCounts) {
		this.valueCounts = valueCounts;
	}

	public String toString() {
		return variables.toString();
	}
}
