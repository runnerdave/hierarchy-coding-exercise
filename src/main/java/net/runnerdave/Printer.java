package net.runnerdave;

import java.util.List;
import java.util.Map;

/**
 * Created by davidajimenez on 31/05/2017.
 */
public class Printer {

    public static final String SPACER = " ";
    public static final int NUMBER_OF_SPACES = 5;
    public static final String LINE_BREAK = "\r\n";


    public static StringBuilder branchPrinter(Map<Employee, List<Employee>> hierarchy,
                                              List<Employee> team, StringBuilder sb) {
        int indent = 0;
        for (Employee emp : team
                ) {

            if (hierarchy.containsKey(emp)) {
                //print manager
                indent++;
                sb.append(indent).append(Printer.SPACER).append(emp.getName()).append(LINE_BREAK);
                //print team
                branchPrinter(hierarchy, hierarchy.get(emp), sb);
                indent--;
            } else {
                sb.append(indent).append(Printer.SPACER).append(emp.getName()).append(LINE_BREAK);
            }
        }

        return sb;
    }

    public static StringBuilder branchPrinterWithIndenting(Map<Employee, List<Employee>> hierarchy,
                                                           Employee root, StringBuilder sb, int indent) {
        for (Employee emp : hierarchy.get(root)
                ) {

            if (hierarchy.containsKey(emp)) {
                //print manager
                sb.append(indent).append(generateIndenting(indent*NUMBER_OF_SPACES)).append(emp.getName()).append(LINE_BREAK);
                //print team
                branchPrinterWithIndenting(hierarchy, emp, sb, ++indent);
                indent--;
            } else {
                sb.append(indent).append(generateIndenting(indent*NUMBER_OF_SPACES)).append(emp.getName()).append(LINE_BREAK);
                hierarchy.remove(emp);
            }
        }

        return sb;
    }

    public static String treePrinter(Map<Employee, List<Employee>> hierarchy, Employee root) {
        StringBuilder sb = new StringBuilder();
        //print root
        sb.append(root.getName()).append(LINE_BREAK);
        //print branches underneath
        sb.append(branchPrinterWithIndenting(hierarchy, root, new StringBuilder(), 1));
        return sb.toString();
    }

    private static String generateIndenting(int numberOfSpaces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfSpaces; i++) {
            sb.append(SPACER);
        }
        return sb.toString();
    }
}
