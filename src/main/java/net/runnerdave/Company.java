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
        this.hierarchy = populateHierarchy(employeeMap);
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
     */
    private Map<Employee, List<Employee>> populateHierarchy(Map<Integer, Employee> employees) {
        Map<Employee, List<Employee>> hierarchy = new HashMap<>();
        employees.forEach((k, v) -> {
            if (v.isCEO()){//this is the root, set the company ceo as this user and don't add him/her to a team.
                if (this.ceo == null) {
                    this.ceo = v;
                } else {
                    String errorMessage = MessageFormat.format(BUNDLE.getString("message.error.multiple.bosses"),
                            this.ceo.getName(), v.getName());
                    LOGGER.error(errorMessage);
                    throw new TooManyBossesException(errorMessage);
                }
            } else {//all other employees below the root
                Employee teamLead = employees.get(v.getManagerId());
                if (teamLead == null) {
                    LOGGER.warn(MessageFormat.format(BUNDLE.getString("message.warning.employee.invalid.manager"),
                            v.getName(),
                            v.getId(),
                            v.getManagerId()));
                }
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
}
