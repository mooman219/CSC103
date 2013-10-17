import java.util.List;

/**
 * This program will perform operations on a DoubleArraySeq based on the users input.
 * 
 * @author Joe Cumbo
 * @since 10/7/2013
 */
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
        System.out.println("\n[#] Option");
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
    public void processInput(String line) {
        System.out.println("\nInput line: \"" + line + "\"");
        List<Double> arguments = InputHelper.parseDoubles(line);
        if(arguments.size() <= 0) {
            System.out.println("Unable to process line, lack of arguments.");
        } else {
            int input = arguments.get(0).intValue();
            arguments.remove(0);
            switch(input) {
            case 1:
                printInfo("1. Create a sequence");
                if(arguments.size() <= 0) {
                    System.out.println("Not enough information.");
                } else {
                    sequenceInstance.createSequence(arguments);
                    sequenceInstance.printSequence(1);
                }
                break;
            case 2:
                printInfo("2. Delete a number");
                if(arguments.size() <= 0) {
                    System.out.println("Not enough information.");
                } else {
                    sequenceInstance.deleteNumber(arguments.get(0));
                    sequenceInstance.printSequence(1);
                }
                break;
            case 3:
                printInfo("3. Delete the first number from the sequence");
                sequenceInstance.deleteFirstNumber();
                sequenceInstance.printSequence(1);
                break;
            case 4:
                printInfo("4. Add a number before another number");
                if(arguments.size() <= 1) {
                    System.out.println("Not enough information.");
                } else {
                    sequenceInstance.addNumberBeforeOther(arguments.get(0).intValue(), arguments.get(1));
                    sequenceInstance.printSequence(1);
                }
                break;
            case 5:
                printInfo("5. Add a number after a number");
                if(arguments.size() <= 1) {
                    System.out.println("Not enough information.");
                } else {
                    sequenceInstance.addNumberAfterOther(arguments.get(0).intValue(), arguments.get(1));
                    sequenceInstance.printSequence(1);
                }
                break;
            case 6:
                printInfo("6. Add a number to the end of the sequence");
                if(arguments.size() <= 0) {
                    System.out.println("Not enough information.");
                } else {
                    sequenceInstance.addNumberToEnd(arguments.get(0));
                    sequenceInstance.printSequence(1);
                }
                break;
            case 7:
                printInfo("7. Display a number at a certain index");
                sequenceInstance.displayNumber(arguments.get(0).intValue());
                break;
            case 8:
                printInfo("8. Display the last element in the sequence");
                sequenceInstance.displaLastNumber();
                break;
            case 9:
                printInfo("9. Replace a number with another number");
                if(arguments.size() <= 1) {
                    System.out.println("Not enough information.");
                } else {
                    sequenceInstance.replaceNumber(arguments.get(0), arguments.get(1));
                    sequenceInstance.printSequence(1);
                }
                break;
            case 10:
                printInfo("10. Append another sequence to the first sequence");
                if(arguments.size() <= 0) {
                    System.out.println("Not enough information.");
                } else {
                    sequenceInstance.appendedSequence(arguments);
                    sequenceInstance.printSequence(1);
                }
                break;
            case 11:
                printInfo("11. Create a clone sequence");
                sequenceInstance.createCloned();
                break;
            case 12:
                printInfo("12. Print the sequence");
                if(arguments.size() <= 0) {
                    System.out.println("Not enough information.");
                } else {
                    sequenceInstance.printSequence(arguments.get(0).intValue());
                }
                break;
            case 13:
                printInfo("13. Quit");
                System.exit(0);
                break;
            default: // Invalid entry
                throw new IllegalArgumentException("Input is not valid.");
            }
        }
    }
    
    /**
     * Helper method to add the seperator below the description.
     * @param info The info to print.
     */
    private void printInfo(String info) {
        System.out.println(info);
        System.out.println("-------------------------");
    }
}
