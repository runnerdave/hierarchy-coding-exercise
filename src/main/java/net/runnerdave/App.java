package net.runnerdave;

import java.util.Map;

/**
 * Process the input of csv Employees to print a hierarchical
 * representation of the organization structure as an output to the console.
 */
public class App {

    /**
     * Main method for the application, can accept different files as input in the args array.
     * @param args if first value in the array is present can override default file.
     */
    public static void main(String[] args) {

        String inputFile = "employees.csv";
        if (args.length == 1) {
            inputFile = args[0];
        }

        System.out.println("Welcome to the hierarchy generator!");

        Map<Integer, Employee> readEmployees = EmployeeReader.getEmployeesFromCSV(inputFile);
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
