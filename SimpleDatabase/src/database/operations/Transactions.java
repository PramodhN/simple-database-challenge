package database.operations;

import java.util.Stack;

public class Transactions {
	Stack<Operations> transactions = new Stack<Operations>();
	private boolean isBegin = false;

	public Operations begin(Operations operations) {
		transactions.push(operations);
		isBegin = true;
		return new Operations(operations);
	}

	public Operations rollback(Operations operations) {
		if (!isBegin) {
			System.err.println("Cannot ROLLBACK without BEGIN");
			return operations;
		}
		if (!transactions.isEmpty()) {
			Operations lastCommitted = transactions.pop();
			if (lastCommitted.equals(operations)) {
				System.out.println("NO TRANSACTION");
				transactions.push(lastCommitted);
			}
			return lastCommitted;
		} else {
			System.out.println("NO TRANSACTION");
			return operations;
		}
	}

	public Operations commit(Operations operations) {
		if (!isBegin) {
			System.err.println("Cannot COMMIT without BEGIN");
			return operations;
		}
		transactions.clear();
		transactions.push(operations);
		return new Operations(operations);
	}
}
