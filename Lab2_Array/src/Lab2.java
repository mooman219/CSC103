/**
 * This program will perform operations on a DoubleArraySeq based on the users input.
 * 
 * @author Joe Cumbo
 * @since 10/7/2013
 */
public class Lab2 {
    public static void main(String[] args) {
        Menu menu = new Menu();
        while(true) {
            menu.displayMenu();
            menu.processInput(InputHelper.nextInteger("Please enter an option: "));
        }
    }
}
