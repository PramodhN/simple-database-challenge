package database;

import database.operations.DatabaseOperations;

public class Database {
	private static DatabaseOperations db;

	private static void fileInput(String string) {

	}

	private static void commandLineInput() {

	}

	public static void main(String[] args) {
		db = new DatabaseOperations();
		if (args.length == 0)
			commandLineInput();
		else if (args.length == 1)
			fileInput(args[0]);
		else
			System.out.println("Invalid number of arguments.");
	}
}
