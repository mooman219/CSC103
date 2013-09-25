public class QuadTest {
    public QuadTest() {
    }

    /**
     * This prints out a description of what the program will be doing.
     */
    public void intro() {
        // TODO
    }

    public void calculations(Quadratic quad1, Quadratic quad2, double scale, int x) {
        System.out.println("The first quadratic is:");
        System.out.println(quad1.toString());
        System.out.println("The values of the first quadratic expression with x = " + x + " is: " + quad1.evalExpression(x));
        System.out.println("The first quadratic with scaling R = 2 is:");
        System.out.println(Quadratic.scale(quad1, 2).toString());
        System.out.println("Number of roots: " + quad1.getRootNum());
        switch(quad1.getRootNum()) {
        case 2:
            System.out.println("Value of root 2: " + quad1.getRootTwo());
        case 1:
            System.out.println("Value of root 1: " + quad1.getRootOne());
        default:
            break;
        }
        System.out.println("The second quadratic is:");
        System.out.println(quad2.toString());
        System.out.println("The quadratic which is the sum of first and second quadratics is:");
        System.out.println(Quadratic.sum(quad1, quad2).toString());
        System.out.println("A clone of the first quadratic is:");
        System.out.println(quad1.clone().toString());
        System.out.println("The first quadratic and the clone " + (quad1.isAlias(quad1.clone()) ? "are" : "are not") + " aliases, but " + (quad1.equals(quad1.clone()) ? "are" : "are not") + " equal to each other.\n");
    }

    public void parse(String line) {
        String[] arguments = line.split(" ");
        Quadratic quad1 = null;
        int x = 0;
        double scale = 0;
        Quadratic quad2 = null;

        if (arguments.length != 8) {
            System.out.println("Not enough data.\n");
            return;
        }

        try {
            quad1 = new Quadratic(Double.parseDouble(arguments[0]), Double.parseDouble(arguments[1]), Double.parseDouble(arguments[2]));
            x = Integer.parseInt(arguments[3]);
            scale = Double.parseDouble(arguments[4]);
            quad2 = new Quadratic(Double.parseDouble(arguments[5]), Double.parseDouble(arguments[6]), Double.parseDouble(arguments[7]));
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("Invalid data.\n");
            return;
        }

        calculations(quad1, quad2, scale, x);
    }

    public void output() {

    }
}
