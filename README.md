# Simple Database Challenge

Java based solution for [Thumbtack Simple Database Challenge](https://www.thumbtack.com/challenges/simple-database). The different classes used to implement this solution are described below.

Database
--------
- This is the main class for reading inputs and calling corresponding methods. The *main* method checks for command line arguments (for input file). If there is no command line argument, it initializes the console for user input.
- Based on the input provided, each line is processed using *handleInputLine* method. The switch-case condition calls the appropriate methods to perform the given operation.

Operations
----------
- This class stores the temporary information of the variables created and handles operations such as *GET, SET, UNSET and NUMEQUALTO*.
- It also has an equals method to check if 2 operations are the same. This is used to decide if *ROLLBACK* needs to be done in case of some new transaction.
- It uses 2 maps. One map stores the value for each variable. The other map stores the count of each value assigned to the variables.
 
Transactions
------------
- This class is used to perform operations such as *BEGIN, ROLLBACK and COMMIT*
- It uses an *Operation* stack to store operations after every new BEGIN statement. A ROLLBACK tries to pop the top element from the stack. A commit clears every element in the stack except for the top element.
 
# Running the code

- I used Eclipse to set up this project. So it can be imported onto Eclipse to execute it.
- However, there are no external depedencies as such and this code can be run on Terminal or CMD too. Just navigate to *src* folder inside *SimpleDatabase* and execute the below commands to start the code.
```
javac database/Database.java
java database/Database   // for command line input
java database/Database locationoffile.txt     // for file input
```

# Performance

- Since I have used a HashMap, the operations GET, SET, UNSET and NUMEQUALTO have a runtime of O(1).
- Same holds for BEGIN and ROLLBACK too, which just performs push and pop on the stack.
- COMMIT operations takes time to clear the stack and then keep only one committed copy.
