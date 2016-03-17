package database.operations;

import java.util.HashMap;

public class DatabaseOperations {
	HashMap<String, Integer> variables;
	HashMap<Integer, Integer> valueCounts;

	public void begin() {
		variables = new HashMap<String, Integer>();
		valueCounts = new HashMap<Integer, Integer>();
	}

}
