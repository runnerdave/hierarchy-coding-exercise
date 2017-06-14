package net.runnerdave;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Process the input of csv Employees to print a hierarchical
 * representation of the organization structure as an output to the console.
 */
public class App {

    private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("bundle");
    private final static Logger LOGGER = Logger.getLogger(EmployeeReader.class);

    /**
     * Main method for the application, can accept different files as input in the args array.
     *
     * @param args if first value in the array is present can override default file.
     */
    public static void main(String[] args) {

        String inputFile = BUNDLE.getString("input.file.default");
        if (args.length == 1) {
            inputFile = args[0];
        }

        System.out.println(BUNDLE.getString("message.welcome"));

        Map<Integer, Employee> readEmployees = EmployeeReader.getEmployeesFromCSV(inputFile);
        Company company = null;
        try {
            company = new Company(readEmployees);
        } catch (TooManyBossesException | NoBossException e) {
            LOGGER.error(BUNDLE.getString("message.error.invalid.input.data"));
        }
        if (company != null) {
            System.out.println(EmployeePrinter.treePrinter(company.getHierarchy(), company.getCeo()));
        } else {
            LOGGER.error(BUNDLE.getString("message.error.initialization"));
        }


    }
}
