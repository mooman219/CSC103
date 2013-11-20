
public class Lab4 {
    public static void main(String[] args) {
        /**
         * Simulation properties
         */
        int simulationTime = InputHelper.nextInteger("The time of simulation is: ");
        int takeoffTime = InputHelper.nextInteger("The time needed for one plane to take off is: ");
        int landingTime = InputHelper.nextInteger("The time needed for one plane to land is: ");
        int averageTakeoffArrival = InputHelper.nextInteger("The average amount of time between arrival of planes to the take off queue is: ");
        int averageLandingArrival = InputHelper.nextInteger("The average amount of time between arrival of planes to the landing queue is: ");
        int maximumLandingTime = InputHelper.nextInteger("The maximum time a plane can stay in the landing queue before crashing is: ");
        Runway runway = new Runway(takeoffTime, landingTime, averageTakeoffArrival, averageLandingArrival, maximumLandingTime);

        for(int currentTime = 0; currentTime < simulationTime; currentTime++) {
            System.out.println("\nTime: " + currentTime);
            runway.populate(currentTime);
            runway.process(currentTime);
        }

        runway.output();
    }
}
