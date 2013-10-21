
public class PrimitveTest {
    public static void main(String[] args) {
        long[] longArray = new long[3];
        longArray[0] = new Integer(1);
        longArray[1] = new Character('a');
        
        int[] intArray = new int[3];
        intArray[0] = new Character('a');
        intArray[1] = new Short("1");
        
        
        StaticModifier staticModifierA = null;
        StaticModifier staticModifierB = new StaticModifier(1);
        
        staticModifierA.modify(staticModifierB);
        System.out.println(staticModifierB.a);
    }
}
