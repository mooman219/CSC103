public class Employee implements Cloneable, Comparable<Employee> {
    private final int accountId;
    private final String firstName;
    private final String lastName;
    private double salary;

    public Employee(int accountId, String firstName, String lastName, double salary) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    /**
     * Gets the salary of the employee.
     * 
     * @return The salary.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets the salary for the employee.
     * 
     * @param salary The desired salary of the employee.
     * @postcondition The new salary of the employee will match the given
     *                salary.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Gets the id of the employee.
     * 
     * @return The Id.
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * Gets the first name of the emplopyee.
     * 
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the employee.
     * 
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Compares this employee to the given employee.
     * 
     * @param The employee to compare against the this employee.
     * @return If the Id of this employee is greater than the Id of the given
     *         employee, this returns -1. If the Id of this employee is less,
     *         this returns 1. If the Id's match, this returns 0.
     */
    @Override
    public int compareTo(Employee employee) {
        if(employee.getAccountId() < this.getAccountId()) {
            return 1;
        } else if(employee.getAccountId() == this.getAccountId()) {
            return 0;
        }
        return -1;
    }

    /**
     * Creates a string representation of the employee.
     * 
     * @return The employee as a string.
     */
    @Override
    public String toString() {
        return "(Id: " + accountId + " Name: " + firstName + " " + lastName + " Salary: " + salary + ")";
    }
}
