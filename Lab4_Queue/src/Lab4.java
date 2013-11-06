import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


public class Lab4 {
    public static void main(String[] args) {
        Queue<Plane> takeoff = new LinkedBlockingQueue<Plane>();
        Queue<Plane> landing = new LinkedBlockingQueue<Plane>();
        Plane runway = null;
        /**
         * Simulation properties
         */
        int simulationTime = InputHelper.nextInteger("The time of simulation is: ");
        int takeoffTime = InputHelper.nextInteger("The time needed for one plane to take off is: ");
        int landingTime = InputHelper.nextInteger("The time needed for one plane to land is: ");
        int averageTakeoffArrival = InputHelper.nextInteger("The average amount of time between arrival of planes to the take off queue is: ");
        int averageLandingArrival = InputHelper.nextInteger("The average amount of time between arrival of planes to the landing queue is: ");
        int maximumLandingTime = InputHelper.nextInteger("The maximum time a plane can stay in the landing queue before crashing is: ");
        /**
         * Simulation statistics
         */
        int totalCrashed = 0;
        int totalTakeoff = 0;
        int totalLanded = 0;
        int averageTakeoff = 0;
        int averageLanding = 0;
        /**
         * Temporary simulation vairables
         */
        double takeoffProbability = 1d / takeoffTime;
        double landingProbability = 1d / landingTime;
        
        for(int currentTime = 0; currentTime < simulationTime; currentTime++) {
            if(Math.random() < takeoffProbability) {
                Plane takeoffPlane = new Plane();
                System.out.println("Arrived for take off: " + takeoffPlane);
            } else {
                System.out.println("Arrived for take off: ");
            }
            if(Math.random() < landingProbability) {
                Plane landingPlane = new Plane();
                System.out.println("Arrived for landing : " + landingPlane);
            } else {
                System.out.println("Arrived for landing : ");
            }
        }
        
        /**
         * Simulation statistics output
         */
        System.out.println("# of planes that crashed : " + totalCrashed);
        System.out.println("# of planes that came to the runway for take off : " + totalTakeoff);
        System.out.println("# of planes that came to the runway for landing  : " + totalLanded);
        System.out.println("The average time a plane spent on the take off queue is : " + averageTakeoff);
        System.out.println("The average time a plane spent on the landing queue is  :  " + averageLanding);
    }
}
