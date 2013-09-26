/**
 * @author Joe Cumbo & Pratik Sampat
 * @date 10/2/2013
 * @description TODO
 */

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Lab1 {
    public static void main(String[] args) {
        FileIO input = new FileIO("input.txt");
        QuadTest quadTest = new QuadTest();
        int currentTest = 0;

        /*
         * Setup the output. This will take the default System.out print stream
         * and replace it with our own stream. Our stream will write to a file.
         */
        try {
            System.setOut(new PrintStream(FileHelper.create("output.txt")));
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        /*
         * Read the body of the input.txt file. For each line read, increment
         * the currentTest variable by one, then parse the line.
         */
        for(String line : input.getBody()) {
            currentTest++;
            System.out.println("\n*****************************");
            System.out.println("Test #" + currentTest);
            System.out.println("------------\n");
            quadTest.parse(line);
        }
    }
}
