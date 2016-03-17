package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import database.operations.DatabaseOperations;

public class Database {
	private static DatabaseOperations db;

	private final static String BEGIN = "BEGIN";
	private final static String SET = "SET";
	private final static String GET = "GET";
	private final static String UNSET = "UNSET";
	private final static String NUMEQUALTO = "NUMEQUALTO";
	private final static String ROLLBACK = "ROLLBACK";
	private final static String COMMIT = "COMMIT";
	private final static String END = "END";

	private static void handleInputLine(String line) {
		String[] parameters = line.split("\\s");
		String operation = parameters[0];
		switch (operation) {
		case BEGIN:
			db.begin();
			break;
		case GET:
			break;
		case SET:
			break;
		case UNSET:
			break;
		case NUMEQUALTO:
			break;
		case ROLLBACK:
			break;
		case COMMIT:
			break;
		default:
			System.err.println("Invalid operation: " + operation + "\nIgnoring this line.");
			break;
		}
	}

	private static void fileInput(String inputFileName) {
		try {
			File file = new File(inputFileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String line;
			while ((line = reader.readLine()) != null) {
				handleInputLine(line);
			}
			fileReader.close();
		} catch (IOException e) {
			System.err.println("Issue with reading the file.");
			e.printStackTrace();
		}
	}

	private static void commandLineInput() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String line = reader.readLine();
			while (!line.equals(END)) {
				handleInputLine(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			System.err.print("Issue with command line input");
			e.printStackTrace();
		}
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
