package net.runnerdave;

/**
 * Represents an employee in the company.
 * <p>
 * Created by davidajimenez on 31/05/2017.
 */
public class Employee {
    private String name;
    private Integer id;
    private Integer managerId;
    private boolean isCEO;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public boolean isCEO() {
        return isCEO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!getName().equals(employee.getName())) return false;
        if (!getId().equals(employee.getId())) return false;
        return getManagerId() != null ? getManagerId().equals(employee.getManagerId()) : employee.getManagerId() == null;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getId().hashCode();
        result = 31 * result + (getManagerId() != null ? getManagerId().hashCode() : 0);
        return result;
    }

    public Employee(String name, Integer id, Integer managerId, boolean isCEO) {

        this.name = name;
        this.id = id;
        this.managerId = managerId;
        this.isCEO = isCEO;
    }
}
