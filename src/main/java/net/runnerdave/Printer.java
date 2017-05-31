package net.runnerdave;

import java.util.List;
import java.util.Map;

/**
 * Created by davidajimenez on 31/05/2017.
 */
public class Printer {

    public static final String SPACER = "    ";
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

    public static String treePrinter(Map<Employee, List<Employee>> hierarchy) {
        StringBuilder str = new StringBuilder();
        hierarchy.forEach((k, v) -> {
        });
        return str.toString();
    }
}
