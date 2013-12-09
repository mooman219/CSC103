public class Lab5 {
    public static void main(String[] args) {
        Company company = new Company();
        System.out.println("-----------------------------------");
        System.out.println("Read from employee list");
        company.menu("1 - employees.txt");
        System.out.println("-----------------------------------");
        System.out.println("Add an employee");
        company.menu("2 - 5290 George Truman 16110.68");
        System.out.println("-----------------------------------");
        System.out.println("Remove an employee");
        company.menu("3 - 4892");
        System.out.println("-----------------------------------");
        System.out.println("Get an employee");
        company.menu("4 - 3924");
        System.out.println("-----------------------------------");
        System.out.println("Modify an employee");
        company.menu("5 - 3924 20000");
        System.out.println("-----------------------------------");
        System.out.println("Display the employees");
        company.menu("6");
        System.out.println("-----------------------------------");
        System.out.println("Writing the employees");
        company.menu("7 - new_employees.txt");
        System.out.println("-----------------------------------");
    }
}
