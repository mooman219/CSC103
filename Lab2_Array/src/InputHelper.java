import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The InputHelper class helps with getting input from the user.
 * 
 * @author Joseph Cumbo
 */
public class InputHelper {
    /*
     * The 'scanner' object is used to collect user input.
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Gets the scanner that reads from the default input stream.
     * 
     * @return The scanner that reads from the default input stream.
     */
    public static Scanner getScanner() {
        return scanner;
    }

    /**
     * Gets the first valid integer the user enters.
     * 
     * @param prompt The text to prompt to the user when entering the number.
     * @return The number that the user entered.
     */
    public static int nextInteger(String prompt) {
        int ret = -1;
        while(true) {
            try {
                System.out.print(prompt);
                ret = Integer.parseInt(scanner.nextLine());
                break;
            } catch(NumberFormatException e) {
                System.out.println("<Invalid input. Malformed integer.>");
            }
        }
        return ret;
    }

    /**
     * Gets the first valid double the user enters.
     * 
     * @param prompt The text to prompt to the user when entering the number.
     * @return The number that the user entered.
     */
    public static double nextDouble(String prompt) {
        double ret = -1;
        while(true) {
            try {
                System.out.print(prompt);
                ret = Double.parseDouble(scanner.nextLine());
                break;
            } catch(NumberFormatException e) {
                System.out.println("<Invalid input. Malformed double.>");
            }
        }
        return ret;
    }

    /**
     * Gets a list of integers that the user enters seperated by either a ' ' or ','.
     * 
     * @param prompt The text to prompt to the user when entering the numbers.
     * @return The list of numbers that the user entered.
     */
    public static List<Integer> nextIntegers(String prompt) {
        List<Integer> ret = new ArrayList<Integer>();
        while(true) {
            ret.clear();
            System.out.print(prompt);
            StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine(), " ,");
            if(tokenizer.countTokens() > 0) {
                try {
                    while(tokenizer.hasMoreTokens()) {
                        ret.add(Integer.parseInt(tokenizer.nextToken()));
                    }
                    break;
                } catch(NumberFormatException e) {
                    System.out.println("<Invalid input. Malformed integer.>");
                }
            } else {
                System.out.println("<Invalid input. Enter at least one number");
            }
        }
        return ret;
    }

    /**
     * Gets a list of doubles that the user enters seperated by either a ' ' or ','.
     * 
     * @param prompt The text to prompt to the user when entering the numbers.
     * @return The list of numbers that the user entered.
     */
    public static List<Double> nextDoubles(String prompt) {
        List<Double> ret = new ArrayList<Double>();
        while(true) {
            ret.clear();
            System.out.print(prompt);
            StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine(), " ,");
            if(tokenizer.countTokens() > 0) {
                try {
                    while(tokenizer.hasMoreTokens()) {
                        ret.add(Double.parseDouble(tokenizer.nextToken()));
                    }
                    break;
                } catch(NumberFormatException e) {
                    System.out.println("<Invalid input. Malformed double.>");
                }
            } else {
                System.out.println("<Invalid input. Enter at least one number.>");
            }
        }
        return ret;
    }
}
