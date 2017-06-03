package net.runnerdave;

import java.util.List;
import java.util.Map;

/**
 * Process the input of csv Employees to print a hierarchical
 * representation of the organization structure as an output to the console.
 */
public class App {
    public static void main(String[] args) {

        System.out.println("Welcome to the hierarchy generator!");

        Map<Integer, Employee> readEmployees = EmployeeReader.getEmployeesFromCSV("employees.csv");
        Map<Employee, List<Employee>> hierarchy = EmployeeReader.populateHierarchy(readEmployees);
        System.out.println(Printer.treePrinter(hierarchy, readEmployees.get(150)));
    }
}
