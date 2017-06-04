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
* Printing algortithm is relatively easy to understand.
* Data structure population algorithm is not as simple.

### 2nd Approach
(not implemented)
* use a tree structure such as the one described here:
https://stackoverflow.com/questions/30502723/display-tree-hierarchy-with-their-values-in-java
* much more natural model to a hierarchy
* harder to implement and understand (Tree vs Maps)
