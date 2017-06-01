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

        emp1 = new Employee("Dan", 1);
        emp2 = new Employee("Sarah", 2);
        emp3 = new Employee("Mary", 3);
        emp4 = new Employee("Pedro", 4);
        emp5 = new Employee("Juana", 5);
        emp6 = new Employee("Berta", 6);
        emp7 = new Employee("Marion", 7);
        emp8 = new Employee("Juan", 8);
        emp9 = new Employee("Ricardo", 9);

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

    @Test
    public void testTreePrinterWithIndentingDouble() {
        StringBuilder sb = new StringBuilder();
        System.out.println("#####");
        System.out.println(Printer.branchPrinterWithIndenting(hierarchyDouble, emp5, sb, 0));
    }

    @Test
    public void testBranchPrinterWithIndentingTriple() {
        StringBuilder sb = new StringBuilder();
        System.out.println("#####");
        System.out.println(Printer.branchPrinterWithIndenting(hierarchyTriple, emp7, sb, 0));
    }

    @Test
    public void testTreePrinterWithIndenting() {
        System.out.println(Printer.treePrinter(hierarchyTriple, emp7));
    }
}
