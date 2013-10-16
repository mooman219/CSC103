import java.util.List;

public class SequenceTest {
    private DoubleArraySeq seq = new DoubleArraySeq(); // The original sequence.
    private DoubleArraySeq seqCloned = new DoubleArraySeq(); // The cloned sequence.

    /**
     * Sets the 'seq' variable to a new DoubleArraySeq with the given values.
     * 
     * @param initalValues The values to add to the sequence.
     */
    public void createSequence(List<Double> initalValues) {
        seq = new DoubleArraySeq(initalValues.size());
        for(double value : initalValues) {
            seq.addEnd(value);
        }
    }

    /**
     * Scans the 'seq' for the given number, deleting it if found.
     * 
     * @param number The number to find and delete.
     */
    public void deleteNumber(double number) {
        int currentIndex = seq.find(number);
        if(currentIndex != -1) {
            seq.removeCurrent();
        } else {
            System.out.println("Unable to find number [" + number + "]");
        }
    }

    /**
     * Deletes the first number in 'seq'
     */
    public void deleteFirstNumber() {
        seq.removeFront();
    }

    /**
     * Adds 'numberToAdd' before the 'numberToFind' if it can find the value.
     * 
     * @param numberToAdd The number to add.
     * @param numberToFind The number to find.
     */
    public void addNumberBeforeOther(double numberToAdd, double numberToFind) {
        int currentIndex = seq.find(numberToFind);
        if(currentIndex != -1) {
            seq.addBefore(numberToAdd);
        } else {
            System.out.println("Unable to find number [" + numberToFind + "]");
        }
    }

    /**
     * Adds the 'numberToAdd' after the 'numberToFind' if it can find the value.
     * 
     * @param numberToAdd The number to add.
     * @param numberToFind The number to find.
     */
    public void addNumberAfterOther(double numberToAdd, double numberToFind) {
        int currentIndex = seq.find(numberToFind);
        if(currentIndex != -1) {
            seq.addAfter(numberToAdd);
        } else {
            System.out.println("Unable to find number [" + numberToFind + "]");
        }
    }

    /**
     * Appends the given number to the end of 'seq'.
     * 
     * @param number The number to add.
     */
    public void addNumberToEnd(double number) {
        seq.addEnd(number);
    }

    /**
     * Displays the number at the desired index. This method can throw an exception if the
     * given index is out of bounds.
     * 
     * @param index The index to read.
     */
    public void displayNumber(int index) {
        System.out.println("The number at index " + index + " is: " + seq.retrieveElement(index));

    }

    /**
     * Displays the last number in the sequence.
     */
    public void displaLastNumber() {
        seq.gotoEnd();
        System.out.println("The last number in the sequence is " + seq.getCurrent());
    }

    /**
     * Replaces the 'numberToFind' with the 'numberToAdd'.
     * 
     * @param numberToAdd The number to add.
     * @param numberToFind The number to find and be replaced.
     */
    public void replaceNumber(double numberToAdd, double numberToFind) {
        int currentIndex = seq.find(numberToFind);
        if(currentIndex != -1) {
            seq.removeCurrent();
            seq.addBefore(numberToAdd);
        } else {
            System.out.println("Unable to find number [" + numberToFind + "]");
        }
    }

    /**
     * Appends the list of given values to the end of the original sequence.
     * 
     * @param initalValues The inital values to be appended.
     */
    public void appendedSequence(List<Double> initalValues) {
        DoubleArraySeq seqAppend = new DoubleArraySeq(initalValues.size());
        for(double value : initalValues) {
            seqAppend.addEnd(value);
        }
        seq.addAll(seqAppend);
    }

    /**
     * Creates a cloned sequence of the original sequence and stores it in the 'seqCloned'
     * variable.
     */
    public void createCloned() {
        seqCloned = seq.clone();
    }

    /**
     * Prints both the original sequence and the cloned sequence to the screen.
     */
    public void printSequence() {
        System.out.println("Original Sequence:");
        System.out.println(seq.toString());
        System.out.println("\nCloned Sequence:");
        System.out.println(seqCloned.toString());
    }
}
