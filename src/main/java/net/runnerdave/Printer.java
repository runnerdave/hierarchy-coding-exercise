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
                                              Employee root, StringBuilder sb, int indent) {
        for (Employee emp : hierarchy.get(root)
                ) {
            if (hierarchy.containsKey(emp)) {
                //print manager
                printNode(sb, indent, emp);
                //print team
                branchPrinter(hierarchy, emp, sb, ++indent);
                indent--;
            } else {
                printNode(sb, indent, emp);
                hierarchy.remove(emp);
            }
        }
        return sb;
    }

    private static void printNode(StringBuilder sb, int indent, Employee emp) {
        sb
                //.append(indent)TODO: remove this line once is all working
                .append(generateIndenting(indent * NUMBER_OF_SPACES))
                .append(emp.getName())
                .append(LINE_BREAK);
    }

    public static String treePrinter(Map<Employee, List<Employee>> hierarchy, Employee root) {
        StringBuilder sb = new StringBuilder();
        //print root
        sb.append(root.getName()).append(LINE_BREAK);
        //print branches underneath
        sb.append(branchPrinter(hierarchy, root, new StringBuilder(), 1));
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
