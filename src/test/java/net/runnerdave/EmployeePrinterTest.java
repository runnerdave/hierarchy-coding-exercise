package net.runnerdave;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

/**
 * Set of tests for the EmployeePrinter class.
 * <p>
 * Created by davidajimenez on 31/05/2017.
 */
public class EmployeePrinterTest {

    private Map<Employee, List<Employee>> hierarchySimple;
    private Map<Employee, List<Employee>> hierarchyDouble;
    private Map<Employee, List<Employee>> hierarchyTriple;
    private List<Employee> sarahTeam;
    private List<Employee> juanaTeam;
    private List<Employee> marionTeam;
    private List<Employee> bertaTeam;
    private Employee emp1;
    private Employee emp2;
    private Employee emp3;
    private Employee emp4;
    private Employee emp5;
    private Employee emp6;
    private Employee emp7;
    private Employee emp8;
    private Employee emp9;

    @Before
    public void setup() {
        hierarchySimple = new HashMap<>();
        hierarchyDouble = new HashMap<>();
        hierarchyTriple = new HashMap<>();

        emp1 = new Employee("Dan", 1, 2, false);
        emp2 = new Employee("Sarah", 2, 7, false);
        emp3 = new Employee("Mary", 3, 2, false);
        emp4 = new Employee("Pedro", 4, 5, false);
        emp5 = new Employee("Juana", 5, 7, false);
        emp6 = new Employee("Berta", 6, 5, false);
        emp7 = new Employee("Marion", 7, 0, true);
        emp8 = new Employee("Juan", 8, 6, false);
        emp9 = new Employee("Ricardo", 9, 6, false);

        sarahTeam = Arrays.asList(emp1, emp3);
        juanaTeam = Arrays.asList(emp4, emp6);
        marionTeam = Arrays.asList(emp2, emp5);
        bertaTeam = Arrays.asList(emp8, emp9);

        hierarchySimple.put(emp2, sarahTeam);

        hierarchyDouble.put(emp5, juanaTeam);
        hierarchyDouble.put(emp2, sarahTeam);
        hierarchyDouble.put(emp7, marionTeam);

        hierarchyTriple.put(emp7, marionTeam);
        hierarchyTriple.put(emp5, juanaTeam);
        hierarchyTriple.put(emp2, sarahTeam);
        hierarchyTriple.put(emp6, bertaTeam);


    }

    @Test
    public void testBranchPrinterSimple() {
        String expected = "Dan\r\n" +
                    "Mary\r\n";
        StringBuilder branch = EmployeePrinter.branchPrinter(hierarchySimple, emp2, new StringBuilder(), 0);
        assertTrue(expected.equalsIgnoreCase(branch.toString()));
    }

    @Test
    public void testBranchPrinterDouble() {
        String expected = "Sarah\r\n" +
                "     Dan\r\n" +
                "     Mary\r\n" +
                "Juana\r\n" +
                "     Pedro\r\n" +
                "     Berta\r\n";
        StringBuilder branch = EmployeePrinter.branchPrinter(hierarchyDouble, emp7, new StringBuilder(), 0);
        assertTrue(expected.equalsIgnoreCase(branch.toString()));
    }

    @Test
    public void testBranchPrinterTriple() {
        String expected = "Sarah\r\n" +
                "     Dan\r\n" +
                "     Mary\r\n" +
                "Juana\r\n" +
                "     Pedro\r\n" +
                "     Berta\r\n" +
                "          Juan\r\n" +
                "          Ricardo\r\n";
        StringBuilder branch = EmployeePrinter.branchPrinter(hierarchyTriple, emp7, new StringBuilder(), 0);
        assertTrue(expected.equalsIgnoreCase(branch.toString()));
    }


    @Test
    public void testTreePrinter() {
        String expected = "Marion\r\n" +
                    "     Sarah\r\n" +
                    "          Dan\r\n" +
                    "          Mary\r\n" +
                    "     Juana\r\n" +
                    "          Pedro\r\n" +
                    "          Berta\r\n" +
                    "               Juan\r\n" +
                    "               Ricardo\r\n";
        String tree = EmployeePrinter.treePrinter(hierarchyTriple, emp7);
        assertTrue(expected.trim().equalsIgnoreCase(tree.trim()));
    }
}
