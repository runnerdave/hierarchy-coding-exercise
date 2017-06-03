package net.runnerdave;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by runnerdave on 3/06/17.
 */
public class EmployeeReaderTest {
    @Test
    public void testReadCSV() {
        List<Employee> sample = Arrays.asList(new Employee("Juan", 1, 0),
                new Employee("Pedro", 2, 1));
        String pathToCsv = "testDataSimple.csv";
        Map<Integer, Employee> readEmployees = EmployeeReader.getEmployeesFromCSV(pathToCsv);
        assertArrayEquals(sample.toArray(), readEmployees.values().toArray());
    }

    @Test
    public void testProcessEmployee() {
        assertEquals(new Employee("Pedro", 1, 6), EmployeeReader.processEmployee("Pedro", "1", " 6 ").get());
        assertEquals(false, EmployeeReader.processEmployee("Pedro", "1", " zz ").isPresent());
        assertEquals(new Employee("Juan", 1, 0), EmployeeReader.processEmployee("Juan", "1 ", "").get());
        assertEquals(false, EmployeeReader.processEmployee("Juan", "dd ", "").isPresent());
    }

    @Test
    public void testPopulateHierarchy() {
        Map<Integer, Employee> sample = new HashMap<>();
        sample.put(1, new Employee("Juan", 1, 0));
        sample.put(2, new Employee("Pedro", 2, 1));
        Map<Employee, List<Employee>> hierarchy = EmployeeReader.populateHierarchy(sample);
        System.out.println(hierarchy);
    }
}
