
public class Lab2 {
    public static void main(String[] args) {
        Menu menu = new Menu();
        while(true) {
            menu.displayMenu();
            int input = menu.promptInput();
            menu.processInput(input);
            menu.pause();
        }
    }
}
