package net.runnerdave;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by davidajimenez on 31/05/2017.
 */
public class PrinterTest {

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

    {
        hierarchySimple = new HashMap<>();
        hierarchyDouble = new HashMap<>();
        hierarchyTriple = new HashMap<>();

        emp1 = new Employee("Dan", 1, 2);
        emp2 = new Employee("Sarah", 2, 7);
        emp3 = new Employee("Mary", 3, 2);
        emp4 = new Employee("Pedro", 4, 5);
        emp5 = new Employee("Juana", 5, 7);
        emp6 = new Employee("Berta", 6, 5);
        emp7 = new Employee("Marion", 7, 0);
        emp8 = new Employee("Juan", 8, 6);
        emp9 = new Employee("Ricardo", 9, 6);

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
        StringBuilder sb = new StringBuilder()
                .append("Dan")
                .append(Printer.LINE_BREAK)
                .append("Mary")
                .append(Printer.LINE_BREAK);
        assertTrue(sb.equals(Printer.branchPrinter(hierarchySimple, emp2, sb, 0)));
    }

    @Test
    public void testBranchPrinterDouble() {
        StringBuilder sb = new StringBuilder()
                .append("Sarah")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 5))
                .append("Dan")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 5))
                .append("Mary")
                .append(Printer.LINE_BREAK)
                .append("Juana")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 5))
                .append("Pedro")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 5))
                .append("Berta")
                .append(Printer.LINE_BREAK);
        StringBuilder branch = Printer.branchPrinter(hierarchyDouble, emp7, new StringBuilder(), 0);
        assertTrue(sb.toString().equalsIgnoreCase(branch.toString()));
    }

    @Test
    public void testBranchPrinterTriple() {
        StringBuilder sb = new StringBuilder()
                .append("Sarah")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 5))
                .append("Dan")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 5))
                .append("Mary")
                .append(Printer.LINE_BREAK)
                .append("Juana")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 5))
                .append("Pedro")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 5))
                .append("Berta")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 10))
                .append("Juan")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 10))
                .append("Ricardo")
                .append(Printer.LINE_BREAK);
        StringBuilder branch = Printer.branchPrinter(hierarchyTriple, emp7, new StringBuilder(), 0);
        assertTrue(sb.toString().equalsIgnoreCase(branch.toString()));
    }

    @Test
    public void testTreePrinter() {
        StringBuilder sb = new StringBuilder()
                .append("Marion")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 5))
                .append("Sarah")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 10))
                .append("Dan")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 10))
                .append("Mary")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 5))
                .append("Juana")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 10))
                .append("Pedro")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 10))
                .append("Berta")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 15))
                .append("Juan")
                .append(Printer.LINE_BREAK)
                .append(StringUtils.repeat(StringUtils.SPACE, 15))
                .append("Ricardo")
                .append(Printer.LINE_BREAK);
        String tree = Printer.treePrinter(hierarchyTriple, emp7);
        assertTrue(sb.toString().trim().equalsIgnoreCase(tree.trim()));
    }
}
