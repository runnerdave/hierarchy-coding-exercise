package net.runnerdave;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Set of tests for the Company class.
 *
 * Created by runnerdave on 4/06/17.
 */
public class CompanyTest {
    @Test
    public void testPopulateHierarchy() throws TooManyBossesException, NoBossException {
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
    public void testPopulateWithTwoBosses() throws NoBossException {
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

    @Test
    public void testEmployeesWithInvalidManagers() throws TooManyBossesException, NoBossException {
        Map<Integer, Employee> sample = new HashMap<>();
        Employee boss = new Employee("Juan", 1, 0);
        Employee worker = new Employee("Pedro", 2, 1);
        Employee randomDude = new Employee("Random", 3, 45);
        sample.put(1, boss);
        sample.put(2, worker);
        sample.put(3, randomDude);

        Company company = new Company(sample);

        assertFalse(company.isValidOrganizationStructure(sample));
    }

    @Test
    public void testEmployeesWithValidManagers() throws TooManyBossesException, NoBossException {
        Map<Integer, Employee> sample = new HashMap<>();
        Employee boss = new Employee("Juan", 1, 0);
        Employee worker = new Employee("Pedro", 2, 1);
        Employee worker2 = new Employee("Maria", 3, 1);
        sample.put(1, boss);
        sample.put(2, worker);
        sample.put(3, worker2);

        Company company = new Company(sample);

        assertTrue(company.isValidOrganizationStructure(sample));
    }
}
