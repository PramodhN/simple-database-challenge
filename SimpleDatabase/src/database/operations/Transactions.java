package database.operations;

import java.util.Stack;

/**
 * This class handles BEGIN, ROLLBACK and COMMIT
 * 
 * @author Pramodh
 * 
 */
public class Transactions {
	/**
	 * Transaction stack with push for each begin, pop for each rollback and clear all but top elements for commit.
	 */
	Stack<Operations> transactions = new Stack<Operations>();
	private boolean isBegin = false;

	/**
	 * Perform begin operation. Push current operation to stack and return a copy of it.
	 * 
	 * @param operations
	 */
	public Operations begin(Operations operations) {
		transactions.push(operations);
		isBegin = true;
		return new Operations(operations);
	}

	/**
	 * Perform rollback operation. Pop from stack and check if there are any changes. If there are no changes, push back
	 * the popped object. Finally return the last committed object.
	 * 
	 * @param operations
	 */
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

	/**
	 * Perform commit operation. Clear the stack and push just the existing Operations object into it.
	 * 
	 * @param operations
	 */
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
