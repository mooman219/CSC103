import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
    private SequenceTest sequenceInstance;
    
    public Menu(SequenceTest sequenceInstance) {
        this.sequenceInstance = sequenceInstance;
    }
    
    /**
     * Displays all possible options for the user to enter.
     */
    public void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Create a sequence");
        System.out.println("2. Delete a number");
        System.out.println("3. Delete the first number from the sequence");
        System.out.println("4. Add a number before another number");
        System.out.println("5. Add a number after a number");
        System.out.println("6. Add a number to the end of the sequence");
        System.out.println("7. Display a number at a certain index");
        System.out.println("8. Display the last element in the sequence");
        System.out.println("9. Replace a number with another number");
        System.out.println("10. Append another sequence to the first sequence");
        System.out.println("11. Create a clone sequence");
        System.out.println("12  Print the sequence");
        System.out.println("13. Quit\n");
    }

    /**
     * Prompts the user to enter a menu item number corresponding to the options
     * in the displayMenu method. If the user enters an invalid input, this
     * method will re-prompt the user.
     * 
     * @return The option the user has entered.
     */
    public int promptInput() {
        int input = -1;
        while(true) {
            System.out.print("\nPlease enter an option: ");
            try(Scanner scanner = new Scanner(System.in)) {
                input = Integer.parseInt(scanner.nextLine());
                if(input > 0 && input <= 13) {
                    break;
                } else {
                    System.out.println("\nInvalid entry. Please enter a valid option.");
                }
            } catch(NumberFormatException | NoSuchElementException e) {
                System.out.println("\nInvalid entry. Please enter an integer.");
            }
        }
        return input;
    }

    /**
     * This method will take the input and run the corresponding option related
     * to the input command.
     * 
     * @param input The integer corresponding to the options in the displayMenu method.
     * @exception IllegalArgumentException If the corresponding option does not exist,
     *            this method will throw an IllegalArgumentException.
     */
    public void processInput(DoubleArraySeq array, int input) {
        switch(input) {
        case 1:
            System.out.println("You selected [1. Create a sequence]");
            sequenceInstance = new SequenceTest();
            break;
        case 2:
            System.out.println("You selected [2. Delete a number]");
            break;
        case 3:
            System.out.println("You selected [3. Delete the first number from the sequence]");
            break;
        case 4:
            System.out.println("You selected [4. Add a number before another number]");
            break;
        case 5:
            System.out.println("You selected [5. Add a number after a number]");
            break;
        case 6:
            System.out.println("You selected [6. Add a number to the end of the sequence]");
            break;
        case 7:
            System.out.println("You selected [7. Display a number at a certain index]");
            break;
        case 8:
            System.out.println("You selected [8. Display the last element in the sequence]");
            break;
        case 9:
            System.out.println("You selected [9. Replace a number with another number]");
            break;
        case 10:
            System.out.println("You selected [10. Append another sequence to the first sequence]");
            break;
        case 11:
            System.out.println("You selected [11. Create a clone sequence]");
            break;
        case 12:
            System.out.println("You selected [12. Print the sequence]");
            break;
        case 13:
            System.out.println("You selected [13. Quit]");
            System.exit(0);
            break;
        default: // Invalid entry
            throw new IllegalArgumentException("Input is not valid.");
        }
    }
}
