public class Plane {
    private static int nextPlaneIndex = 0;
    private final Operation operation;
    private final int arrivalTime;
    private final int id;
    private int activeTime = 1;

    public Plane(Operation operation, int arrivalTime) {
        this.operation = operation;
        this.arrivalTime = arrivalTime;
        this.id = nextPlaneIndex;
        Plane.nextPlaneIndex++;
    }

    /**
     * This gets the plane's current operation.
     * 
     * @return The operation.
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * This gets when the plane arrived at the airport.
     * 
     * @return The arrival time.
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * This will get the unique id of the plane.
     * 
     * @return The id;
     */
    public int getId() {
        return id;
    }

    /**
     * This will get how long the plane has been active on the runway.
     * 
     * @return The active time.
     */
    public int getActiveTime() {
        return activeTime;
    }

    /**
     * This will increment the planes active time by one.
     * 
     * @postcondition The planes active time has been incremented by one.
     */
    public void incrementActivetime() {
        activeTime++;
    }

    /**
     * Standard toString.
     */
    public String toString() {
        return "Plane #" + id;
    }

    /**
     * This gets the total planes used during the simulation.
     * 
     * @return The total planes.
     */
    public static int getTotalPlanes() {
        return nextPlaneIndex;
    }

    public static enum Operation {
        LANDING,
        TAKEING_OFF;
    }
}
