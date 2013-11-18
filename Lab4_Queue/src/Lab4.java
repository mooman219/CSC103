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
        double takeoffProbability = 1d / averageTakeoffArrival;
        double landingProbability = 1d / averageLandingArrival;

        for(int currentTime = 0; currentTime < simulationTime; currentTime++) {
            System.out.println("\nTime: " + currentTime);
            /**
             * This block calculates if a plane is taking off or not. If a plane
             * is taking off, it is added to the takeoff queue.
             */
            if(Math.random() < takeoffProbability) {
                Plane takeoffPlane = new Plane(Plane.Operation.TAKEING_OFF, currentTime);
                takeoff.add(takeoffPlane);
                System.out.println("Arrived for take off: " + takeoffPlane);
            } else {
                System.out.println("Arrived for take off: ");
            }
            /**
             * This block calculates if a plane is landing or not. If a plane is
             * landing, it is added to the landing queue.
             */
            if(Math.random() < landingProbability) {
                Plane landingPlane = new Plane(Plane.Operation.LANDING, currentTime);
                landing.add(landingPlane);
                System.out.println("Arrived for landing : " + landingPlane);
            } else {
                System.out.println("Arrived for landing : ");
            }
            /**
             * Calculate the runway.
             */
            if(runway == null) {
                /**
                 * Try to add a plane from the landing queue first. If the plane
                 * has been in the air too long then mark it as crashed and find
                 * the next plane in the landing queue. This continues until
                 * there are no more planes in the landing queue or if an
                 * acceptable plane was chosen to land.
                 */
                while(landing.peek() != null && runway == null) {
                    runway = landing.poll();
                    if(currentTime - runway.getArrivalTime() > maximumLandingTime) {
                        totalCrashed++;
                        System.out.println(
                                runway + " has [CRASHED]."+ 
                                " ArrivalTime: " + runway.getArrivalTime());
                        runway = null;
                    }
                }
                /**
                 * If there wasn't a plane that needed to land, check the takeoff queue for planes needing to take off.
                 */
                if(runway == null) {
                    runway = takeoff.poll();
                }
                /**
                 * Finally, display the status of the runway.
                 */
                if(runway == null) {
                    System.out.println("Runway: Empty");
                } else {
                    System.out.println(
                            "Runway: " + runway + 
                            " " + runway.getOperation() + 
                            " Duration: " + runway.getActiveTime());
                }
            } else {
                runway.incrementActivetime();
                System.out.print("Runway: " + runway + " " + runway.getOperation() + " Duration: " + runway.getActiveTime());
                switch(runway.getOperation()) {
                case LANDING:
                    if(runway.getActiveTime() >= landingTime) {
                        System.out.println(" (FINISHED)");
                        totalLanded++;
                        runway = null;
                    } else {
                        System.out.println();
                    }
                    break;
                case TAKEING_OFF:
                    if(runway.getActiveTime() >= takeoffTime) {
                        System.out.println(" (FINISHED)");
                        totalTakeoff++;
                        runway = null;
                    } else {
                        System.out.println();
                    }
                    break;
                default:
                    System.out.println("Error with " + runway + ". Removing plane from runway.");
                    runway = null;
                    break;
                }
            }
        }

        /**
         * Simulation statistics output
         */
        System.out.println();
        System.out.println("# of planes that crashed : " + totalCrashed);
        System.out.println("# of planes that came to the runway for take off : " + totalTakeoff);
        System.out.println("# of planes that came to the runway for landing  : " + totalLanded);
        System.out.println("The average time a plane spent on the take off queue is : " + averageTakeoff);
        System.out.println("The average time a plane spent on the landing queue is  : " + averageLanding);
    }
}
