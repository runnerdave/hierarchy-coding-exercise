# Representing hierarchical trees with nodes

Coding exercise to represent a hierarchical manager - employee
structure and print it out.

# Problem statement
![problem statement](https://github.com/runnerdave/hierarchy-coding-exercise/blob/master/problem-for-momenton.png)

# Requirements
* Java 8
* Maven

## Build
``$mvn clean package``

## Run
``java -jar target/hierarchy-1.0-SNAPSHOT-jar-with-dependencies.jar``

The default run is the _happy path_, to test with data with problems run the following commands:

``java -jar target/hierarchy-1.0-SNAPSHOT-jar-with-dependencies.jar employees_no_managers.csv``

``java -jar target/hierarchy-1.0-SNAPSHOT-jar-with-dependencies.jar employees_two_bosses.csv``

``java -jar target/hierarchy-1.0-SNAPSHOT-jar-with-dependencies.jar employees_invalid_managers.csv``

...and check the **application.log** file for errors

## Test
``$mvn clean test``

The test cases for the Printer use the following structure:
![test hierarchies](https://github.com/runnerdave/hierarchy-coding-exercise/blob/master/testHierarchies.png)

## Approaches

### 1st Approach
* use a map with managers as keys and values with teams (list of employees)
* use recursion to print each branch
    
#### critique
* Data structures are simple (Maps and Lists)
* Recursion can cause a stack overflow with big hierarchies
* Printing algorithm is relatively easy to understand.
* Data structure population algorithm is not as simple.

### 2nd Approach
(not implemented)
* use a tree structure such as the one described here:
https://stackoverflow.com/questions/30502723/display-tree-hierarchy-with-their-values-in-java
* much more natural model to a hierarchy
* harder to implement and understand (Tree vs Maps)

## Review
_Performed by: I.A. on Jun 5th_

### file: /src/main/java/net/runnerdave/App.java

1. recommendation: usage of Logger for exception handling instead of console.

response: taken into account and implemented

### file: /src/main/java/net/runnerdave/Company.java

1. recommendation: avoid double handling of the data structure by processing the information once.

response: valid point, still need to think about how to implement it better TODO

2. recommendation: remove instance variable: ceo and add that property to the Employee class.
 
 response: valid point, it is a cleaner code that way. TODO

### file: /src/main/java/net/runnerdave/EmployeePrinter.java

1. recommendation: do not remove objects from the map in every pass while printing the tree branch.

response: I think I did that on purpose, need to remove it and see if the tests still pass. TODO

2. I.A.: In this class StringBuilder is used for obvious reasons.
   If I challenge you that you could have used String and + operator instead to keep things simple and light
   and it wouldn't have made any difference, would you agree?
   How would you prove me wrong or right showing something concrete?
   
response: TODO

### file: /src/test/java/net/runnerdave/EmployeePrinterTest.java

1. recommendation: usage of @Before to do the setup of the test case

response: valid point TODO

2. recommendation: present the expected string in a more readable way.

response: valid point TODO

