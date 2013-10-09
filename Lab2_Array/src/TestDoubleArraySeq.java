import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TestDoubleArraySeq {
    private static InputStreamReader reader = new InputStreamReader(System.in);
    private static BufferedReader stdin = new BufferedReader(reader);

    public static void main(String[] args) throws IOException {
        DoubleArraySeq seq = new DoubleArraySeq();
        boolean done = false;

        display("seq", seq);

        while(!done) {
            try {
                switch(getMenuChoice()) {
                case 0:
                    done = true;
                    break;
                case 1:
                    seq = newDASeq();
                    break;
                case 2:
                    testAddAfter(seq);
                    break;
                case 3:
                    testAddBefore(seq);
                    break;
                case 4:
                    seq.removeCurrent();
                    break;
                case 5:
                    testIsCurrent(seq);
                    break;
                case 6:
                    testGetCurrent(seq);
                    break;
                case 7:
                    seq.gotoStart();
                    break;
                case 8:
                    seq.advance();
                    break;
                case 9:
                    seq.trimToSize();
                    break;
                case 10:
                    testEnsureCapacity(seq);
                    break;
                case 11:
                    testAddAll(seq);
                    break;
                case 12:
                    testConcatenation(seq);
                }
            } catch(IllegalArgumentException e1) {
                System.out.println();
                System.out.print("CAUGHT: IllegalArgumentException: ");
                System.out.println(e1.getMessage());
            } catch(IllegalStateException e2) {
                System.out.println();
                System.out.print("CAUGHT: IllegalStateException: ");
                System.out.println(e2.getMessage());
            }
            display("seq", seq);
        }
    }

    private static void display(String label, DoubleArraySeq seq) {
        DoubleArraySeq temp = seq.clone();
        String outElement;
        int currPos = PosOfCurrent(seq.clone());

        System.out.println();
        System.out.print(label + ": {");

        temp.gotoStart();
        for(int i = 0; i < temp.getSize(); i++) {
            outElement = Double.toString(temp.getCurrent());
            if(i == currPos) {
                System.out.print("[" + outElement + "]");
            } else {
                System.out.print("<" + outElement + ">");
            }
            temp.advance();
        }
        for(int i = temp.getSize(); i < temp.getCapacity(); i++) {
            System.out.print("<>");
        }

        System.out.println("}");
    }

    private static int PosOfCurrent(DoubleArraySeq seq) {
        int count = 0;

        while(seq.isCurrent()) {
            seq.advance();
            count++;
        }
        return seq.getSize() - count;
    }

    private static int getMenuChoice() throws IOException {
        System.out.println();
        System.out.println("Menu:");
        System.out.println("------------------------------------------------");
        System.out.println("1 Constructor    5 isCurrent    9 trimToSize    ");
        System.out.println("2 addAfter       6 getCurrent  10 ensureCapacity");
        System.out.println("3 addBefore      7 start       11 addAll        ");
        System.out.println("4 removeCurrent  8 advance     12 concatenation ");
        System.out.println("------------------------------------------------");
        System.out.print("Number of method to test (0 to quit): ");

        return Integer.parseInt(stdin.readLine());
    }

    private static DoubleArraySeq newDASeq() throws IOException {
        System.out.println();
        System.out.print("Enter initial capacity: ");
        return new DoubleArraySeq(Integer.parseInt(stdin.readLine()));
    }

    private static void testAddAfter(DoubleArraySeq seq) throws IOException {
        System.out.println();
        System.out.print("Enter double value to be added: ");
        seq.addAfter(Double.parseDouble(stdin.readLine()));
    }

    private static void testAddBefore(DoubleArraySeq seq) throws IOException {
        System.out.println();
        System.out.print("Enter double value to be added: ");
        seq.addBefore(Double.parseDouble(stdin.readLine()));
    }

    private static void testIsCurrent(DoubleArraySeq seq) {
        System.out.println();
        if(seq.isCurrent()) {
            System.out.println("A current element exists.");
        } else {
            System.out.println("No current element exists.");
        }
    }

    private static void testGetCurrent(DoubleArraySeq seq) {
        System.out.println();
        System.out.println("Current element: " + Double.toString(seq.getCurrent()));
    }

    private static void testEnsureCapacity(DoubleArraySeq seq) throws IOException {
        System.out.println();
        System.out.print("Enter minimum capacity: ");
        seq.ensureCapacity(Integer.parseInt(stdin.readLine()));
    }

    private static void testAddAll(DoubleArraySeq seq) throws IOException {
        StringTokenizer st;
        DoubleArraySeq addend = new DoubleArraySeq();

        System.out.println();
        System.out.print("Enter 0 or more double values for addend: ");
        st = new StringTokenizer(stdin.readLine(), " ,");

        while(st.hasMoreTokens()) {
            addend.addAfter(Double.parseDouble(st.nextToken()));
        }
        display("addend", addend);

        seq.addAll(addend);
    }

    private static void testConcatenation(DoubleArraySeq s1) throws IOException {
        StringTokenizer st;
        DoubleArraySeq s2 = new DoubleArraySeq();

        display("s1", s1);
        System.out.println();
        System.out.print("Enter 0 or more double values for s2: ");
        st = new StringTokenizer(stdin.readLine(), " ,");

        while(st.hasMoreTokens()) {
            s2.addAfter(Double.parseDouble(st.nextToken()));
        }
        display("s2", s2);

        display("result", DoubleArraySeq.concatenation(s1, s2));
    }

}
