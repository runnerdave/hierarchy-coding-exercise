package net.runnerdave;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by davidajimenez on 31/05/2017.
 */
public class PrinterTest {

    private Map<Employee, List<Employee>> hierarchySimple;
    private Map<Employee, List<Employee>> hierarchyDouble;
    private List<Employee> sarahTeam;
    private List<Employee> juanaTeam;

    {
        hierarchySimple = new HashMap<>();
        hierarchyDouble = new HashMap<>();

        Employee emp1 = new Employee("Dan", 1);
        Employee emp2 = new Employee("Sarah", 2);
        Employee emp3 = new Employee("Mary", 3);
        Employee emp4 = new Employee("Pedro", 4);
        Employee emp5 = new Employee("Juana", 5);
        Employee emp6 = new Employee("Berta", 6);

        sarahTeam = Arrays.asList(emp1, emp3);
        juanaTeam = Arrays.asList(emp2, emp4, emp6);

        hierarchySimple.put(emp2, sarahTeam);
        hierarchyDouble.put(emp5, juanaTeam);
        hierarchyDouble.put(emp2, sarahTeam);
    }

    @Test
    public void testTreePrinterSimple() {
        StringBuilder sb = new StringBuilder();
        String target = "Sarah" + Printer.LINE_BREAK + Printer.SPACER + "Dan"
                            + Printer.LINE_BREAK + Printer.SPACER + "Mary" + Printer.LINE_BREAK;
        System.out.println(target);
        System.out.println("#####");
        System.out.println(Printer.branchPrinter(hierarchySimple, sarahTeam, sb));
        //assertTrue(target.equalsIgnoreCase(Printer.branchPrinter(hierarchySimple)));
    }

    @Test
    public void testTreePrinterDouble() {
        StringBuilder sb = new StringBuilder();
//        String target = "Juana" + Printer.LINE_BREAK + Printer.SPACER + "Sarah" + Printer.LINE_BREAK + Printer.SPACER + "Dan"
//                + Printer.LINE_BREAK + Printer.SPACER + "Mary" + Printer.LINE_BREAK;
//        System.out.println(target);
        System.out.println("#####");
        System.out.println(Printer.branchPrinter(hierarchyDouble, juanaTeam, sb));
//        assertTrue(target.equalsIgnoreCase(Printer.branchPrinter(hierarchyDouble)));
    }
}
