/**
 * @author Joe Cumbo & Pratik Sampat
 * @date 9/25/2013
 * @description TODO
 */
public class Lab1 {
    public static void main(String[] args) {
        FileIO file = new FileIO("inputCoef.txt");
        QuadTest quadTest = new QuadTest();

        for (String line : file.getBody()) {
            quadTest.parse(line);
        }
    }
}
