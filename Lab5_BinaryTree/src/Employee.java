
public class Employee implements Cloneable, Comparable<Employee> {
    private final int accountId;
    private final String firstName;
    private final String lastName;
    private double salary;

    public Employee(int accountId, String firstName, String lastName, double salary) {
        super();
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }
    
    
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    @Override
    public int compareTo(Employee employee) {
        if(employee.getSalary() < this.getSalary()) {
            return 1;
        } else if(employee.getSalary() == this.getSalary()) {
            return 0;
        }
        return -1;
    }
}
