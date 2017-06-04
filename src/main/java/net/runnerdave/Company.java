package net.runnerdave;

import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.*;

/**
 * Represents a company with a CEO and employees.
 * <p>
 * Created by runnerdave on 4/06/17.
 */
public class Company {

    private final ResourceBundle BUNDLE = ResourceBundle.getBundle("bundle");
    private final Logger LOGGER = Logger.getLogger(Company.class);

    private Employee ceo;
    private Map<Employee, List<Employee>> hierarchy;

    public Company(Map<Integer, Employee> employeeMap) throws TooManyBossesException, NoBossException {
        try {
            this.hierarchy = populateHierarchy(employeeMap);
        } catch (IllegalArgumentException e) {
            throw new TooManyBossesException(e.getMessage());
        }
        if (this.getCeo() == null) {
            String noBoss = BUNDLE.getString("message.error.no.boss");
            LOGGER.error(noBoss);
            throw new NoBossException(noBoss);
        }
    }


    public Employee getCeo() {
        return ceo;
    }

    /**
     * returns safe version of map hierarchy.
     *
     * @return Map.
     */
    public Map<Employee, List<Employee>> getHierarchy() {
        Map<Employee, List<Employee>> safeMap = new HashMap<>();
        hierarchy.forEach(safeMap::putIfAbsent);
        return safeMap;
    }

    /**
     * Populates the hierarchy of employees for the company.
     *
     * @param employees map of valid employees.
     * @return Map with a populated hierarchy.
     * @precondition there is a manager and it's id is 0
     */
    private Map<Employee, List<Employee>> populateHierarchy(Map<Integer, Employee> employees) {
        Map<Employee, List<Employee>> hierarchy = new HashMap<>();
        employees.forEach((k, v) -> {
            int teamLeadId = v.getManagerId();
            if (teamLeadId == 0) {//this is the root, set the company ceo as this user and don't add him/her to a team.
                if (this.ceo == null) {
                    this.ceo = v;
                } else {
                    String errorMessage = MessageFormat.format(BUNDLE.getString("message.error.multiple.bosses"),
                            this.ceo.getName(), v.getName());
                    LOGGER.error(errorMessage);
                    throw new IllegalArgumentException(errorMessage);
                }
            } else {//all other employees below the root
                Employee teamLead = employees.get(teamLeadId);
                List<Employee> team = hierarchy.get(teamLead);
                if (team == null) {
                    List<Employee> newTeam = new ArrayList<>();
                    newTeam.add(v);
                    hierarchy.put(teamLead, newTeam);
                } else {
                    team.add(v);
                }
            }

        });
        return hierarchy;
    }

    /**
     * Verifies that employees have managerIds that exist in the map.
     * Logs all occurrences of problematic employees for analysis.
     *
     * @param employeeMap
     * @return false if an employee that is not a manager has a manager id that is not in the company.
     */
    public boolean isValidOrganizationStructure(Map<Integer, Employee> employeeMap) {
        boolean isValid = true;
        for (Employee employee : employeeMap.values()
                ) {
            if (employee.getManagerId() != 0 && !employeeMap.containsKey(employee.getManagerId())) {
                isValid = false;
                LOGGER.warn(MessageFormat.format(BUNDLE.getString("message.warning.employee.invalid.manager"),
                        employee.getName(),
                        employee.getId(),
                        employee.getManagerId()));

            }
        }

        return isValid;
    }
}
