import java.util.List;

public class SequenceTest {
    private DoubleArraySeq seq1 = new DoubleArraySeq(); // The original sequence.
    private DoubleArraySeq seq2; // The second sequence to be created.
    private DoubleArraySeq seq3; // The cloned sequence.

    public void displayMenu() {
        System.out.println("6. Add a number to the end of the sequence");
        System.out.println("7. Display a number at a certain index");
        System.out.println("8. Display the last element in the sequence");
        System.out.println("9. Replace a number with another number");
        System.out.println("10. Append another sequence to the first sequence");
        System.out.println("11. Create a clone sequence");
        System.out.println("12  Print the sequence");
        System.out.println("13. Quit\n");
    }

    public void createSequence(List<Double> initalValues) {
        seq1 = new DoubleArraySeq(initalValues.size());
        for(double value : initalValues) {
            seq1.addEnd(value);
        }
    }
    
    public void deleteNumber(double number) {
        int currentIndex = seq1.find(number);
        if(currentIndex != -1) {
            seq1.removeCurrent();
        } else {
            System.out.println("Unable to find number [" + number + "]");
        }
    }
    
    public void deleteFirstNumber() {
        seq1.removeFront();
    }
    
    public void addNumberBeforeOther(double numberToAdd, double numberToFind) {
        int currentIndex = seq1.find(numberToFind);
        if(currentIndex != -1) {
            seq1.addBefore(numberToAdd);
        } else {
            System.out.println("Unable to find number [" + numberToFind + "]");
        }
    }
    
    public void addNumberAfterOther(double numberToAdd, double numberToFind) {
        int currentIndex = seq1.find(numberToFind);
        if(currentIndex != -1) {
            seq1.addAfter(numberToAdd);
        } else {
            System.out.println("Unable to find number [" + numberToFind + "]");
        }
    }
    
    public void printSequence() {
        System.out.println(seq1.toString());
    }
}
