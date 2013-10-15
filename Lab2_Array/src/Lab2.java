
public class Lab2 {
    public static void main(String[] args) {
        Menu menu = new Menu();
        while(true) {
            menu.displayMenu();
            int input = InputHelper.nextInteger("Please enter an option: ");
            menu.processInput(input);
        }
    }
}
