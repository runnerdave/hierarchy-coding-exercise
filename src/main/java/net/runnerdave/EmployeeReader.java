package net.runnerdave;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.*;

/**
 * Reads employee information from a csv input.
 *
 * Created by runnerdave on 3/06/17.
 */
public class EmployeeReader {

    private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("bundle");
    private final static Logger LOGGER = Logger.getLogger(EmployeeReader.class);

    /**
     * Reads Employees from CSV file.
     *
     * @param pathToCsv the path to the csv file.
     * @return Map with valid employees.
     */
    public static Map<Integer, Employee> getEmployeesFromCSV(String pathToCsv) {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        try (Reader in = new FileReader(pathToCsv)) {
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                String name = record.get(0);
                String id = record.get(1).trim();
                String managerId = record.get(2).trim();
                Optional<Employee> employee = processEmployee(name, id, managerId);
                if (employee.isPresent()) {
                    Employee emp = employee.get();
                    employeeMap.put(emp.getId(), emp);
                }

            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return employeeMap;
    }

    /**
     * Process employee by validating the valid ones and logging and discards invalid ones.
     *
     * @param name     string with the name.
     * @param idStr    string with the id.
     * @param mgrIdStr string with the manager id.
     * @return optional of Employee
     */
    public static Optional<Employee> processEmployee(String name, String idStr, String mgrIdStr) {
        Optional<Employee> emp = Optional.empty();
        Integer id = 0;
        Integer mgrId = 0;
        String errorMessage = MessageFormat.format(BUNDLE.getString("message.error.processing"), name, idStr, mgrIdStr);
        boolean invalid = false;
        try {
            id = Integer.parseInt(idStr.trim());
        } catch (NumberFormatException e) {
            invalid = true;
            errorMessage += " has invalid id";
        }
        try {
            //if empty leave default 0
            if (!mgrIdStr.trim().isEmpty())
                mgrId = Integer.parseInt(mgrIdStr.trim());
        } catch (NumberFormatException e) {
            invalid = true;
            errorMessage += " has invalid manager id";
        }

        if (invalid) {
            LOGGER.error(errorMessage);
        } else {
            Employee employee = new Employee(name, id, mgrId);
            emp = Optional.of(employee);
        }

        return emp;
    }
}
