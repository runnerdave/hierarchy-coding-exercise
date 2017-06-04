package net.runnerdave;

import java.util.List;
import java.util.Map;

/**
 * Prints company hierarchies.
 *
 * Created by davidajimenez on 31/05/2017.
 */
public class EmployeePrinter {

    private static final String SPACER = " ";
    private static final int NUMBER_OF_SPACES = 5;
    public static final String LINE_BREAK = "\r\n";

    /**
     * Prints a branch given a root and a hierarchy.
     * @param hierarchy
     * @param root
     * @param sb
     * @param indent
     * @return
     */
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

    /**
     * Prints a node or Employee with the proper indentation.
     * @param sb
     * @param indent
     * @param emp
     */
    private static void printNode(StringBuilder sb, int indent, Employee emp) {
        sb
                .append(generateIndenting(indent * NUMBER_OF_SPACES))
                .append(emp.getName())
                .append(LINE_BREAK);
    }

    /**
     * Builds the whole tree, or hierarchy as a String.
     * @param hierarchy
     * @param root
     * @return
     */
    public static String treePrinter(Map<Employee, List<Employee>> hierarchy, Employee root) {
        StringBuilder sb = new StringBuilder();
        //print root
        sb.append(root.getName()).append(LINE_BREAK);
        //print branches underneath
        sb.append(branchPrinter(hierarchy, root, new StringBuilder(), 1));
        return sb.toString();
    }

    /**
     * Creates indentation.
     * @param numberOfSpaces
     * @return
     */
    private static String generateIndenting(int numberOfSpaces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfSpaces; i++) {
            sb.append(SPACER);
        }
        return sb.toString();
    }
}
