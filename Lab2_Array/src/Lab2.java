/**
 * This program will perform operations on a DoubleArraySeq based on the users input.
 * 
 * @author Joe Cumbo
 * @since 10/7/2013
 */
public class Lab2 {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.displayMenu();
        for(String line : InputHelper.readFile("input.txt")) {
            System.out.println("");
            menu.processInput(line);
        }
    }
}
