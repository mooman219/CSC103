
public class Menu {
    /*
     * The 'sequenceInstance' is the menus instance of the SequenceTest class. A new
     * instance of SequenceTest is created when a Menu object is created.
     */
    private SequenceTest sequenceInstance = new SequenceTest();

    /**
     * Displays all possible options for the user to enter.
     */
    public void displayMenu() {
      System.out.println("\n[#] Option                              [#] Option");
        System.out.println("[1] Create a sequence                   [7] Display a number at a certain index");
        System.out.println("[2] Delete a number                     [8] Display the last element in the sequence");
        System.out.println("[3] Delete the first number             [9] Replace a number with another number");
        System.out.println("[4] Add a number before another number [10] Append another sequence to the first sequence");
        System.out.println("[5] Add a number after a number        [11] Create a clone sequence");
        System.out.println("[6] Add a number to the end            [12] Print the sequence");
        System.out.println("                                       [13] Quit\n");
    }

    /**
     * This method will take the input and run the corresponding option related
     * to the input command.
     * 
     * @param input The integer corresponding to the options in the displayMenu method.
     * @exception IllegalArgumentException If the corresponding option does not exist,
     *            this method will throw an IllegalArgumentException.
     */
    public void processInput(int input) {
        switch(input) {
        case 1:
            System.out.println("You selected [1. Create a sequence]");
            sequenceInstance.createSequence(InputHelper.nextDoubles("Input: "));
            break;
        case 2:
            System.out.println("You selected [2. Delete a number]");
            sequenceInstance.deleteNumber(InputHelper.nextDouble("Input: "));
            break;
        case 3:
            System.out.println("You selected [3. Delete the first number from the sequence]");
            sequenceInstance.deleteFirstNumber();
            break;
        case 4:
            System.out.println("You selected [4. Add a number before another number]");
            sequenceInstance.addNumberBeforeOther(
                    InputHelper.nextDouble("Input (Number to add): "),
                    InputHelper.nextDouble("Input (Number to find): "));
            break;
        case 5:
            System.out.println("You selected [5. Add a number after a number]");
            sequenceInstance.addNumberAfterOther(
                    InputHelper.nextDouble("Input (Number to add): "),
                    InputHelper.nextDouble("Input (Number to find): "));
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
            sequenceInstance.printSequence();
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
