public class Lab5 {
    public static void main(String[] args) {
        Company company = new Company();
        System.out.println("-----------------------------------");
        System.out.println("Read from employee list");
        company.parseFile("employees.txt");
        company.displayEmployees();
        System.out.println("-----------------------------------");
        System.out.println("Add an employee");
        company.addEmployee("5290 George Truman 16110.68");
        company.displayEmployees();
        System.out.println("-----------------------------------");
        System.out.println("Remove an employee");
        company.removeEmployee("4892");
        company.displayEmployees();
        System.out.println("-----------------------------------");
        System.out.println("Get an employee");
        System.out.println(company.getEmployee("3924").toString());
        System.out.println("-----------------------------------");
        System.out.println("Modify an employee");
        System.out.println(company.updateSalary("3924 20000").toString());
        System.out.println("-----------------------------------");
        System.out.println("Display the employees");
        company.displayEmployees();
        System.out.println("-----------------------------------");
        System.out.println("Writing the employees");
        company.writeFile("new_employees.txt");
        System.out.println("-----------------------------------");
    }
}
