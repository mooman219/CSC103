
public class StaticModifier {
    public int a = 1;
    
    public StaticModifier(int a) {
        this.a = a;
    }
    
    public static void modify(StaticModifier object) {
        object.a = 10;
    }
}
