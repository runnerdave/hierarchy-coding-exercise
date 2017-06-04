package net.runnerdave;

import java.util.Map;

/**
 * Process the input of csv Employees to print a hierarchical
 * representation of the organization structure as an output to the console.
 */
public class App {
    public static void main(String[] args) {

        System.out.println("Welcome to the hierarchy generator!");

        Map<Integer, Employee> readEmployees = EmployeeReader.getEmployeesFromCSV("employees.csv");
        Company company = null;
        try {
            company = new Company(readEmployees);
            //warn that there are errors in the input data, however let it run
            company.isValidOrganizationStructure(readEmployees);
        } catch (TooManyBossesException | NoBossException e) {
            System.out.println("Invalid input data, check the logs for errors.");
        }
        if (company != null) {
            System.out.println(EmployeePrinter.treePrinter(company.getHierarchy(), company.getCeo()));
        } else {
            System.out.println("Error in initialization of company, check the logs for errors.");
        }


    }
}
