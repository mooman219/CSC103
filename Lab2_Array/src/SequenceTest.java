
public class SequenceTest {
    private DoubleArraySeq seq1; // The original sequence.
    private DoubleArraySeq seq2; // The second sequence to be created.
    private DoubleArraySeq seq3; // The cloned sequence.
    
    public void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Create a sequence");
        System.out.println("2. Delete a number");
        System.out.println("3. Delete the first number from the sequence");
        System.out.println("4. Add a number before another number");
        System.out.println("5. Add a number after a number");
        System.out.println("6. Add a number to the end of the sequence");
        System.out.println("7. Display a number at a certain index");
        System.out.println("8. Display the last element in the sequence");
        System.out.println("9. Replace a number with another number");
        System.out.println("10. Append another sequence to the first sequence");
        System.out.println("11. Create a clone sequence");
        System.out.println("12  Print the sequence");
        System.out.println("13. Quit\n");
    }
    
    public DoubleArraySeq createSequence(double...initalValues) {
        DoubleArraySeq seq = new DoubleArraySeq(initalValues.length);
        for(double value : initalValues) {
            seq.addEnd(value);
        }
        return seq;
    }
}
