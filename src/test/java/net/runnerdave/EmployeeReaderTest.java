package net.runnerdave;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Sets of tests for the EmployeeReader.
 *
 * Created by runnerdave on 3/06/17.
 */
public class EmployeeReaderTest {
    @Test
    public void testReadCSV() {
        List<Employee> sample = Arrays.asList(new Employee("Juan", 1, 0, true),
                new Employee("Pedro", 2, 1, false));
        String pathToCsv = "testDataSimple.csv";
        Map<Integer, Employee> readEmployees = EmployeeReader.getEmployeesFromCSV(pathToCsv);
        assertArrayEquals(sample.toArray(), readEmployees.values().toArray());
    }

    @Test
    public void testProcessEmployee() {
        //valid employee
        assertEquals(new Employee("Pedro", 1, 6, false), EmployeeReader.processEmployee("Pedro", "1", " 6 ").get());
        //invalid manager id
        assertEquals(false, EmployeeReader.processEmployee("Pedro", "1", " zz ").isPresent());
        //valid boss
        assertEquals(new Employee("Juan", 1, 0, true), EmployeeReader.processEmployee("Juan", "1 ", "").get());
        //invalid id
        assertEquals(false, EmployeeReader.processEmployee("Juan", "dd ", "").isPresent());
    }


}
