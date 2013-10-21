import java.io.File;
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
    /*
     * The string used when splitting a line for the StringTokenizer.
     */
    private static final String splitString = " ,-";

    /**
     * Gets the scanner that reads from the default input stream.
     * 
     * @return The scanner that reads from the default input stream.
     */
    public static Scanner getScanner() {
        return scanner;
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
     * Gets a list of doubles that the user enters seperated by either a " ,-".
     * 
     * @param prompt The text to prompt to the user when entering the numbers.
     * @return The list of numbers that the user entered.
     */
    public static List<Double> nextDoubles(String prompt) {
        List<Double> ret = new ArrayList<Double>();
        while(true) {
            ret.clear();
            System.out.print(prompt);
            StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine(), splitString);
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
     * Gets a list of integers that the user enters seperated by either a " ,-".
     * 
     * @param prompt The text to prompt to the user when entering the numbers.
     * @return The list of numbers that the user entered.
     */
    public static List<Integer> nextIntegers(String prompt) {
        List<Integer> ret = new ArrayList<Integer>();
        while(true) {
            ret.clear();
            System.out.print(prompt);
            StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine(), splitString);
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
     * Reads a line and parses it into an double. If there are multiple values in the line,
     * it will try to read the first value.
     * 
     * @param line The line to parse.
     * @return A double represented by the line.
     * @throws IllegalArgumentException If the length of the line is not long enough.
     * @throws NumberFormatException If there is an error processing the line.
     */
    public static double parseDouble(String line) {
        if(line.length() == 0) {
            throw new IllegalArgumentException("Line not long enough");
        }
        StringTokenizer tokenizer = new StringTokenizer(line, splitString);
        if(tokenizer.countTokens() >= 1) {
            return Double.parseDouble(tokenizer.nextToken());
        }
        return Double.parseDouble(line);
    }

    /**
     * Reads a line and parses it into a list of doubles. If there is an error while
     * processing a double, the int is skipped and not added to the list.
     * 
     * @param line The line to parse.
     * @return A list of doubles represented in the line.
     * @throws IllegalArgumentException If the length of the line is not long enough.
     */
    public static List<Double> parseDoubles(String line) {
        if(line.length() == 0) {
            throw new IllegalArgumentException("Line not long enough");
        }
        List<Double> ret = new ArrayList<Double>();
        StringTokenizer tokenizer = new StringTokenizer(line, splitString);
        while(tokenizer.hasMoreTokens()) {
            try {
                ret.add(Double.parseDouble(tokenizer.nextToken()));
            } catch(NumberFormatException e) {
            }
        }
        return ret;
    }

    /**
     * Reads a line and parses it into an int. If there are multiple values in the line,
     * it will try to read the first value.
     * 
     * @param line The line to parse.
     * @return An int represented by the line.
     * @throws IllegalArgumentException If the length of the line is not long enough.
     * @throws NumberFormatException If there is an error processing the line.
     */
    public static int parseInteger(String line) {
        if(line.length() == 0) {
            throw new IllegalArgumentException("Line not long enough");
        }
        StringTokenizer tokenizer = new StringTokenizer(line, splitString);
        if(tokenizer.countTokens() >= 1) {
            return Integer.parseInt(tokenizer.nextToken());
        }
        return Integer.parseInt(line);
    }

    /**
     * Reads a line and parses it into a list of integers. If there is an error while
     * processing a int, the int is skipped and not added to the list.
     * 
     * @param line The line to parse.
     * @return A list of ints represented in the line.
     * @throws IllegalArgumentException If the length of the line is not long enough.
     */
    public static List<Integer> parseIntegers(String line) {
        if(line.length() == 0) {
            throw new IllegalArgumentException("Line not long enough");
        }
        List<Integer> ret = new ArrayList<Integer>();
        StringTokenizer tokenizer = new StringTokenizer(line, splitString);
        while(tokenizer.hasMoreTokens()) {
            try {
                ret.add(Integer.parseInt(tokenizer.nextToken()));
            } catch(NumberFormatException e) {
            }
        }
        return ret;
    }

    /**
     * Reads each line in the file at 'path' and return the lines. This function will
     * return an empty list if the file does not exist.
     * 
     * @param path The location of the file.
     * @return A list of strings.
     */
    public static List<String> readFile(String path) {
        List<String> ret = new ArrayList<String>();
        File file = new File(path);
        if(file.exists()) {
            try(Scanner reader = new Scanner(file)) {
                while(reader.hasNextLine()) {
                    ret.add(reader.nextLine());
                }
            } catch(Exception e) {
                System.out.println("Error reading File.");
                e.printStackTrace();
            }
        }
        return ret;
    }
}
