import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Lab4 {
    public static void main(String[] args) {
        Queue<Plane> takeoff = new LinkedBlockingQueue<Plane>();
        Queue<Plane> landing = new LinkedBlockingQueue<Plane>();
        Deque<Plane> crashes = new ArrayDeque<Plane>();
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
        //Runway runway = new Runway(takeoffTime, landingTime, averageTakeoffArrival, averageLandingArrival, maximumLandingTime);
        /**
         * Simulation statistics
         */
        int totalCrashed = 0;
        int totalTakeoff = 0;
        int totalLanded = 0;
        Averager averageTakeoff = new Averager();
        Averager averageLanding = new Averager();
        /**
         * Temporary simulation vairables
         */
        BooleanSource takeoffProbability = new BooleanSource(1d / averageTakeoffArrival);
        BooleanSource landingProbability = new BooleanSource(1d / averageLandingArrival);

        for(int currentTime = 0; currentTime < simulationTime; currentTime++) {
            System.out.println("\nTime: " + currentTime);
            /**
             * This block calculates if a plane is taking off or not. If a plane
             * is taking off, it is added to the takeoff queue.
             */
            if(takeoffProbability.query()) {
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
            if(landingProbability.query()) {
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
                        crashes.push(runway);
                        System.out.println(runway + " has [CRASHED]." + " ArrivalTime: " + runway.getArrivalTime());
                        runway = null;
                    } else {
                        averageLanding.addNumber(currentTime - runway.getArrivalTime());
                    }
                }
                /**
                 * If there wasn't a plane that needed to land, check the
                 * takeoff queue for planes needing to take off.
                 */
                if(runway == null && takeoff.peek() != null) {
                    runway = takeoff.poll();
                    averageTakeoff.addNumber(currentTime - runway.getArrivalTime());
                }
                /**
                 * Finally, display the status of the runway.
                 */
                if(runway == null) {
                    System.out.println("Runway: Empty");
                } else {
                    System.out.println("Runway: " + runway + " " + runway.getOperation() + " Duration: " + runway.getActiveTime());
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
        System.out.println("Plane Crashes:");
        while(crashes.peek() != null) {
            Plane crashedPlane = crashes.pop();
            System.out.println(crashedPlane + " crashed " + (crashedPlane.getArrivalTime() + maximumLandingTime + 1) + " minutes in.");
        }
        System.out.println();
        System.out.println("# of planes that crashed : " + totalCrashed);
        System.out.println("# of planes that came to the runway for take off : " + totalTakeoff);
        System.out.println("# of planes that came to the runway for landing  : " + totalLanded);
        System.out.println("The average time a plane spent on the take off queue is : " + averageTakeoff.average());
        System.out.println("The average time a plane spent on the landing queue is  : " + averageLanding.average());
    }
}
