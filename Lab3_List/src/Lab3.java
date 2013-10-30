public class Lab3 {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.displayMenu();
        for(String line : InputHelper.readFile("input.txt")) {
            System.out.println("");
            menu.processInput(line);
        }
    }
}
