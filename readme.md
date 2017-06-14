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

response: **valid** point, taken into account and implemented

### file: /src/main/java/net/runnerdave/Company.java

1. recommendation: avoid double handling of the data structure by processing the information once.

response: **valid** point, implemented by removing the unnecessary method and logging warning messages when 
employees had invalid managers. This has made the class much simpler.

2. recommendation: remove instance variable: ceo and add that property to the Employee class.
 
 response: **debatable** point, the Employee class has now been added a method isCeo however the company 
 still needs the instance variable for ceo as this is the root that is required when printing.
 Also the use of this property in the employee class did not reduce the code significantly.
 
3. recommendation: better handling of exception: TooManyBosses

response: **valid** point, the exception was unnecessarily caught in the constructor, this has been amended.

### file: /src/main/java/net/runnerdave/EmployeePrinter.java

1. recommendation: do not remove objects from the map in every pass while printing the tree branch.

response: **valid** point, the removal of the value was unnecessary, probably was a left over from an initial idea.

2. I.A.: In this class StringBuilder is used for obvious reasons.
   If I challenge you that you could have used String and + operator instead to keep things simple and light
   and it wouldn't have made any difference, would you agree?
   How would you prove me wrong or right showing something concrete?
   
response: The creation of new String objects is an expensive process (due to their immutability) that is noticeable only when used in much bigger instances
than this example, so _I would agree_ that it would not have made any difference in this example.

As for the concrete example, I have modified and ran in my computer at home (Ubuntu 16.04 8GB ram, open jdk 1.8.0_131):
the following set of tests: [tests for String concatenation vs StringBuilder append()](https://github.com/runnerdave/katas/blob/master/src/main/java/net/runnerdave/TestStrings.java)

In there it is evident that only after 10000 iterations there seem to appear noticeable differences. So for readibility and simplicity the + operator is preferable.

### file: /src/test/java/net/runnerdave/EmployeePrinterTest.java

1. recommendation: usage of @Before to do the setup of the test case

response: **valid** point, implemented.

2. recommendation: present the expected string in a more readable way.

response: **valid** point, implemented.

