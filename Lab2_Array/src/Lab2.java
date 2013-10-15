public class Lab2 {
    public static void main(String[] args) {
        Menu menu = new Menu();
        while(true) {
            menu.displayMenu();
            menu.processInput(InputHelper.nextInteger("Please enter an option: "));
        }
    }
}
