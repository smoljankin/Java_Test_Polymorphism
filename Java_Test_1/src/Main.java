import java.math.BigDecimal;
import java.util.*;

class Employee {
    private String name;
    private int experience;
    private BigDecimal basePayment;

    public Employee(String name, int experience, BigDecimal basePayment) {
        this.name = name;
        this.experience = experience;
        this.basePayment = basePayment;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public BigDecimal getPayment() {
        return basePayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return experience == employee.experience && Objects.equals(name, employee.name) &&
                Objects.equals(basePayment, employee.basePayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, experience, basePayment);
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", experience=" + experience + ", basePayment=" + basePayment + "]";
    }
}

class Manager extends Employee {
    private double coefficient;

    public Manager(String name, int experience, BigDecimal basePayment, double coefficient) {
        super(name, experience, basePayment);
        this.coefficient = coefficient;
    }

    @Override
    public BigDecimal getPayment() {
        return super.getPayment().multiply(BigDecimal.valueOf(coefficient));
    }

    @Override
    public String toString() {
        return "Manager [name=" + getName() + ", experience=" + getExperience() + ", basePayment=" + getPayment() + ", coefficient=" + coefficient + "]";
    }
}

class MyUtils {
    public List<Employee> largestEmployees(List<Employee> workers) {
        List<Employee> largestEmployees = new ArrayList<>();

        // Find the maximum experience and payment
        int maxExperience = Integer.MIN_VALUE;
        BigDecimal maxPayment = BigDecimal.valueOf(Double.MIN_VALUE);
        for (Employee employee : workers) {
            if (employee.getExperience() > maxExperience) {
                maxExperience = employee.getExperience();
                maxPayment = employee.getPayment();
            } else if (employee.getExperience() == maxExperience) {
                maxPayment = maxPayment.max(employee.getPayment());
            } else {
                // do nothing
            }
        }

        // Add employees with maximum experience or payment to the result set

        Set<Employee> resultSet = new HashSet<>();
        for (Employee employee : workers) {
            if (employee.getExperience() == maxExperience && employee.getPayment().equals(maxPayment)) {
                resultSet.add(employee);
            }
        }

        // Sort the result set by name
        largestEmployees.addAll(resultSet);
        Collections.sort(largestEmployees, Comparator.comparing(Employee::getName));

        return largestEmployees;
    }
}


public class Main {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Ivan", 10, BigDecimal.valueOf(3000.00)));
        employees.add(new Manager("Petro", 9, BigDecimal.valueOf(3000.00), 1.5));
        employees.add(new Employee("Stepan", 8, BigDecimal.valueOf(4000.00)));
        employees.add(new Employee("Andriy", 7, BigDecimal.valueOf(3500.00)));
        employees.add(new Employee("Ihor", 5, BigDecimal.valueOf(4500.00)));
        employees.add(new Manager("Vasyl", 8, BigDecimal.valueOf(2000.00), 2.0));

        MyUtils myUtils = new MyUtils();
       List<Employee> l = myUtils.largestEmployees(employees);
       for(int i =0 ; i < l.size(); i++)
       {
           System.out.println(l.get(i));
       }
    }
}
