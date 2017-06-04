package net.runnerdave;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Set of tests for the Company class.
 *
 * Created by runnerdave on 4/06/17.
 */
public class CompanyTest {
    @Test
    public void testPopulateHierarchy() throws TooManyBossesException {
        Map<Integer, Employee> sample = new HashMap<>();
        Employee boss = new Employee("Juan", 1, 0);
        Employee worker = new Employee("Pedro", 2, 1);
        sample.put(1, boss);
        sample.put(2, worker);
        Company company = new Company(sample);
        Map<Employee, List<Employee>> hierarchy = company.getHierarchy();
        System.out.println(hierarchy);
        assertEquals(hierarchy.get(boss).get(0), worker);
    }

    @Test
    public void testPopulateWithTwoBosses() {
        Map<Integer, Employee> sample = new HashMap<>();
        Employee boss = new Employee("Juan", 1, 0);
        Employee worker = new Employee("Pedro", 2, 0);
        sample.put(1, boss);
        sample.put(2, worker);
        try {
            Company company = new Company(sample);
        } catch (TooManyBossesException e) {
            assertTrue(true);
        }
    }
}
