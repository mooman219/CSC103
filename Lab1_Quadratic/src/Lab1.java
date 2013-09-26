import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * @author Joe Cumbo & Pratik Sampat
 * @date 9/25/2013
 * @description TODO
 */
public class Lab1 {
    public static void main(String[] args) {
        FileIO input = new FileIO("input.txt");
        QuadTest quadTest = new QuadTest();
        int currentTest = 0;
        
        try {
            System.setOut(new PrintStream(FileHelper.create("output.txt")));
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        for(String line : input.getBody()) {
            currentTest++;
            System.out.println("\n*****************************");
            System.out.println("Test #" + currentTest);
            System.out.println("------------\n");
            quadTest.parse(line);
        }
    }
}
