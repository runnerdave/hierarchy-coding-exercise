package net.runnerdave;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        List<Employee> readEmployees = EmployeeReader.getEmployeesFromCSV(pathToCsv);
        assertArrayEquals(sample.toArray(), readEmployees.toArray());
    }

    @Test
    public void testProcessEmployee() {
        assertEquals(new Employee("Pedro", 1, 6), EmployeeReader.processEmployee("Pedro", "1", " 6 ").get());
        assertEquals(false, EmployeeReader.processEmployee("Pedro", "1", " zz ").isPresent());
    }
}
