package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import database.operations.Operations;
import database.operations.Transactions;

public class Database {
	private static Operations operations;
	private static Transactions transactions;

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
		String variable;
		int value;
		boolean isProblem = false;
		switch (operation.toUpperCase()) {
		case BEGIN:
			operations = transactions.begin(operations);
			break;
		case GET:
			if (parameters.length == 2) {
				variable = parameters[1];
				operations.get(variable);
			} else
				isProblem = true;
			break;
		case SET:
			if (parameters.length == 3) {
				variable = parameters[1];
				try {
					value = Integer.parseInt(parameters[2]);
					operations.set(variable, value);
				} catch (NumberFormatException e) {
					isProblem = true;
				}
			} else
				isProblem = true;
			break;
		case UNSET:
			if (parameters.length == 2) {
				variable = parameters[1];
				operations.unset(variable);
			} else
				isProblem = true;
			break;
		case NUMEQUALTO:
			if (parameters.length == 2) {
				try {
					value = Integer.parseInt(parameters[1]);
					operations.numEqualTo(value);
				} catch (NumberFormatException e) {
					isProblem = true;
				}
			} else
				isProblem = true;
			break;
		case ROLLBACK:
			operations = transactions.rollback(operations);
			break;
		case COMMIT:
			operations = transactions.commit(operations);
			break;
		default:
			System.err.println("Invalid operation: " + operation + "\nIgnoring this line.");
			break;
		}
		if (isProblem)
			System.err.println("Invalid parameters for " + operation + " operation. Ignoring this line.");
	}

	private static void fileInput(String inputFileName) {
		try {
			File file = new File(inputFileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.equals(END))
					break;
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
		operations = new Operations();
		transactions = new Transactions();
		if (args.length == 0)
			commandLineInput();
		else if (args.length == 1)
			fileInput(args[0]);
		else
			System.out.println("Invalid number of arguments.");
	}
}
