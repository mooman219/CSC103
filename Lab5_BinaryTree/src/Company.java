import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class Company {
    private TreeBag<Employee> employees = new TreeBag<>();

    /**
     * This creates a company with no employees.
     */
    public Company() {}

    /**
     * The menu takes an input line and produces a result.
     * 
     * @param line Line to read from.
     */
    public void menu(String line) {
        int option = InputHelper.parseInteger(line);
        switch(option) {
        case 1:
            read(line.substring(4));
            displayEmployees();
            break;
        case 2:
            addEmployee(line.substring(4));
            break;
        case 3:
            removeEmployee(line.substring(4));
            break;
        case 4:
            System.out.println(getEmployee(line.substring(4)));
            break;
        case 5:
            System.out.println(updateSalary(line.substring(4)));
            break;
        case 6:
            displayEmployees();
            break;
        case 7:
            Company.write(this, line.substring(4));
            break;
        default:
            break;
        }
    }

    /**
     * This will read from the given file and take all valid employees from the
     * file and set them as the company's employees.
     * 
     * @param fileName The file to read from
     * @precondition The file exists.
     * @postcondition The company object will have only the employees read from
     *                the file.
     */
    public void read(String fileName) {
        System.out.println("Reading from '" + fileName + "'...");
        for(String line : InputHelper.readFile(fileName)) {
            addEmployee(line);
        }
    }

    /**
     * Adds the employee derived from the line.
     * 
     * @param line The line to derive the employee from.
     * @precondition The line contains the information to construct an employee.
     * @postcondition The employees tree will contain the given employee if
     *                there wasn't an employee with the same Id.
     */
    public void addEmployee(String line) {
        System.out.println("Adding employee '" + line + "'...");
        employees.add(parseEmployee(line));
    }

    /**
     * Removes an employee derived from the line.
     * 
     * @param line The line to derive the employee from.
     * @precondition The line contains the information to construct an employee.
     * @postcondition The employees tree will contain the given employee.
     */
    public void removeEmployee(String line) {
        System.out.println("Removing employee '" + line + "'...");
        employees.remove(parseEmployee(line));
    }

    /**
     * Gets an employee based on the employee derived from the line.
     * 
     * @param line The line to derive the employee to search for from.
     * @precondition The line contains the information to construct an employee.
     * @return The employee from the employees list if it was found, null
     *         otherwise.
     */
    public Employee getEmployee(String line) {
        System.out.println("Getting employee '" + line + "'...");
        return employees.get(parseEmployee(line));
    }

    /**
     * Gets an employee based on the first token in the given line, then updates
     * that employee's salary based on the second token of the line. If the
     * employee could not be found, nothing is changed.
     * 
     * @param line The given live to derive the employee and new salary from.
     * @return The modified employee, null if no employee could be found.
     * @precondition There are at least two tokens in the line.
     * @postcondition If the employee existed, it will now have the updated
     *                salary.
     */
    public Employee updateSalary(String line) {
        String[] lineTokens = line.split(" ");
        if(lineTokens.length < 2) {
            return null;
        }
        System.out.println("Setting employee '" + lineTokens[0] + "' salary to '" + lineTokens[1] + "'...");
        Employee employee = employees.get(parseEmployee(lineTokens[0]));
        if(employee != null) {
            employee.setSalary(Double.parseDouble(lineTokens[1]));
        }
        return employee;
    }

    /**
     * Displays the employees in assending order of Id for the company.
     */
    public void displayEmployees() {
        employees.display();
    }

    /**
     * This will take a line and parse it into an employee. For searching
     * reasons, it accepts a line with one token in it if that token is a valid
     * integer. This allows for easy searching of an employee.
     * 
     * @param line The line to be read from.
     * @return An employee derived from the line. If there wasn't enough tokens
     *         then this returns null.
     */
    public static Employee parseEmployee(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line, " ,-");
        if(tokenizer.countTokens() == 4) {
            return new Employee(Integer.parseInt(tokenizer.nextToken()), tokenizer.nextToken(),

            tokenizer.nextToken(), Double.parseDouble(tokenizer.nextToken()));
        } else if(tokenizer.countTokens() == 1) {
            return new Employee(Integer.parseInt(tokenizer.nextToken()), null, null, Integer.MIN_VALUE);
        }
        return null;

    }

    /**
     * Writes the current list of employees to the given file.
     * 
     * @param fileName The file name to write to.
     */
    public static void write(Company company, String fileName) {
        File file = new File(fileName);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        try(PrintStream fileStream = new PrintStream(file);) {
            PrintStream out = System.out;
            System.setOut(fileStream);
            company.displayEmployees();
            System.setOut(out);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
