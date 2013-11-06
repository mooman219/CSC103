public class Plane {
    private static int nextPlaneId = 0;
    private final int id;
    
    public Plane() {
        this.id = nextPlaneId;
        nextPlaneId++;
    }
    
    public int getId() {
        return id;
    }
    
    public String toString() {
        return "Plane #" + id;
    }
}
