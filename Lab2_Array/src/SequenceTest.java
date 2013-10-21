import java.util.List;

/**
 * This program will perform operations on a DoubleArraySeq based on the users input.
 * 
 * @author Joe Cumbo
 * @since 10/7/2013
 */
public class SequenceTest {
    private boolean currentSequence = true;
    private DoubleArraySeq seqA = new DoubleArraySeq(); // The original sequence.
    private DoubleArraySeq seqB = new DoubleArraySeq(); // The second sequence.
    private DoubleArraySeq seqCloned = new DoubleArraySeq(); // The cloned sequence.

    /**
     * Sets the 'seq' variable to a new DoubleArraySeq with the given values.
     * 
     * @param initalValues The values to add to the sequence.
     */
    public void createSequence(List<Double> initalValues) {
        long[] array = new long[2];
        array[0] = new Integer(4);
        
        if(currentSequence) {
            seqA = new DoubleArraySeq(initalValues.size());
            for(double value : initalValues) {
                seqA.addEnd(value);
            }
            currentSequence = !currentSequence;
        } else {
            seqB = new DoubleArraySeq(initalValues.size());
            for(double value : initalValues) {
                seqB.addEnd(value);
            }
            currentSequence = !currentSequence;
        }
    }

    /**
     * Scans the 'seq' for the given number, deleting it if found.
     * 
     * @param number The number to find and delete.
     */
    public void deleteNumber(double number) {
        int currentIndex = seqA.find(number);
        if(currentIndex != -1) {
            seqA.removeCurrent();
        } else {
            System.out.println("Unable to find number " + number + "");
        }
    }

    /**
     * Deletes the first number in 'seq'
     */
    public void deleteFirstNumber() {
        seqA.removeFront();
    }

    /**
     * Adds 'number' before the 'index'. If the index is out of bounds, nothing happens.
     * 
     * @param number The number to be added.
     * @param index The index to place the number at.
     */
    public void addNumberBeforeOther(int index, double number) {
        try {
            seqA.setCurrent(index);
            seqA.addBefore(number);
        } catch(Exception e) {
            System.out.println("The given index " + index + " is out of bounds.");
        }
    }

    /**
     * Adds 'number' after the 'index'. If the index is out of bounds, nothing happens.
     * 
     * @param number The number to be added.
     * @param index The index to place the number at.
     */
    public void addNumberAfterOther(int index, double number) {
        try {
            seqA.setCurrent(index);
            seqA.addAfter(number);
        } catch(Exception e) {
            System.out.println("The given index " + index + " is out of bounds.");
        }
    }

    /**
     * Appends the given number to the end of 'seq'.
     * 
     * @param number The number to add.
     */
    public void addNumberToEnd(double number) {
        seqA.addEnd(number);
    }

    /**
     * Displays the number at the desired index. This method can throw an exception if the
     * given index is out of bounds.
     * 
     * @param index The index to read.
     */
    public void displayNumber(int index) {
        System.out.println("The number at index " + index + " is: " + seqA.retrieveElement(index));
    }

    /**
     * Displays the last number in the sequence.
     */
    public void displaLastNumber() {
        seqA.gotoEnd();
        System.out.println("The last number in the sequence is " + seqA.getCurrent());
    }

    /**
     * Replaces the 'numberToFind' with the 'numberToAdd'.
     * 
     * @param numberToAdd The number to add.
     * @param numberToFind The number to find and be replaced.
     */
    public void replaceNumber(double numberToFind, double numberToAdd) {
        int currentIndex = seqA.find(numberToFind);
        if(currentIndex != -1) {
            seqA.removeCurrent();
            seqA.addBefore(numberToAdd);
        } else {
            System.out.println("Unable to find number [" + numberToFind + "]");
        }
    }

    /**
     * Appends the list of given values to the end of the original sequence.
     * 
     * @param initalValues The inital values to be appended.
     */
    public void appendedSequence() {
        seqA.addAll(seqB);
    }

    /**
     * Creates a cloned sequence of the original sequence and stores it in the 'seqCloned'
     * variable.
     */
    public void createCloned() {
        seqCloned = seqA.clone();
    }

    /**
     * Prints the sequence.
     * 
     * @param sequence 2 prints the second sequence 3 prints the cloned sequence, anything
     *        else prints the original sequence.
     */
    public void printSequence(int sequence) {
        switch(sequence) {
        case 2:
            System.out.println("Secondary Sequence:");
            System.out.println(seqB.toString());
            break;
        case 3:
            System.out.println("Cloned Sequence:");
            System.out.println(seqCloned.toString());
            break;
        default:
            System.out.println("Original Sequence:");
            System.out.println(seqA.toString());
        }
    }
}
