package net.runnerdave;

/**
 * Created by davidajimenez on 31/05/2017.
 */
public class Employee {
    private String name;
    private Integer id;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!getName().equals(employee.getName())) return false;
        return getId().equals(employee.getId());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getId().hashCode();
        return result;
    }

    public Employee(String name, Integer id) {

        this.name = name;
        this.id = id;
    }
}
