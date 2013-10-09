public class Lab2 {
    public static void main(String[] args) {
        DoubleArraySeq seq = new DoubleArraySeq();
        System.out.println(seq.toString() + "\n");
        seq.addAfter(1.91);
        seq.addAfter(4.01);
        seq.addAfter(9.23);
        seq.addAfter(21.3);
        System.out.println(seq.toString() + "\n");
        seq.addEnd(9001);
        seq.addFront(9001);
        System.out.println(seq.toString() + "\n");
        seq.trimToSize();
        System.out.println(seq.toString() + "\n");
        seq.addBefore(80085);
        System.out.println(seq.toString() + "\n");
        seq.setCurrent(3);
        System.out.println(seq.toString() + "\n");
        seq.removeCurrent();
        System.out.println(seq.toString() + "\n");
        seq.removeFront();
        System.out.println(seq.toString() + "\n");
        seq.removeEnd();
        System.out.println(seq.toString() + "\n");
    }
}
