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
 * Created by runnerdave on 3/06/17.
 */
public class EmployeeReader {

    private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("bundle");
    private static Logger logger = Logger.getLogger(EmployeeReader.class);

    /**
     * Reads Employees from CSV file.
     *
     * @param pathToCsv the path to the csv file.
     * @return List with valid employees.
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
            logger.error("File not found");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return employeeMap;
    }

    /**
     * Process employee and discards invalid ones.
     *
     * @param name     string with the name.
     * @param idStr    string with the id.
     * @param mgrIdStr string with the manager id.
     * @return optional of Employee
     */
    public static Optional<Employee> processEmployee(String name, String idStr, String mgrIdStr) {
        Optional<Employee> emp = Optional.empty();
        Integer id = new Integer(0);
        Integer mgrId = new Integer(0);
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
            logger.error(errorMessage);
        } else {
            Employee employee = new Employee(name, id, mgrId);
            emp = Optional.of(employee);
        }

        return emp;
    }

    /**
     * List of employees for the company.
     *
     * @param employees list of valid employees.
     * @return
     */
    public static Map<Employee, List<Employee>> populateHierarchy(Map<Integer, Employee> employees) {
        Map<Employee, List<Employee>> hierarchy = new HashMap<>();
        employees.forEach((k, v) -> {
            List<Employee> team = hierarchy.get(v.getManagerId());
            if (team == null) {
                List<Employee> newTeam = new ArrayList<>();
                //if root manager id is 0
                if(v.getManagerId() == 0) {
                    hierarchy.put(v, newTeam);
                } else {
                    newTeam.add(v);
                    hierarchy.put(employees.get(v.getManagerId()), newTeam);
                }
            } else {
                team.add(v);
            }
        });
        return hierarchy;
    }

    /**
     * Validates if hierarchy is valid.
     * Reasons for invalidation:
     * * Empty map.
     * * More than one root.
     * * Employees with no valid manager.
     * Writes invalid reasons/entries to the logger.
     *
     * @param hierarchy the hierarchy to evaluate.
     * @return true if the hierarchy is valid, false if not
     */
    public boolean validateHierarchy(Map<Employee, List<Employee>> hierarchy) {
        boolean valid = false;

        return valid;
    }
}
