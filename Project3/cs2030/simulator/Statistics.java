package cs2030.simulator;

/**
*Statistics class does the calculation for the statistics for the entire simulation.
*/
public class Statistics {
    private double totalWaitingTime;
    private int customerLeft;
    private int customerServed;
    
    /**
    *Statistics constructor sets up the initial stats of the simulation.
    */
    public Statistics() {
        totalWaitingTime = 0;
        customerLeft = 0;
        customerServed = 0;
    }
    
    /**
    *increaseServed method increments the number of customer served.
    */
    public void increaseServed() {
        customerServed++;
    }
    
    /**
    *increaseLeft method increments the number of customers who left.
    */
    public void increaseLeft() {
        customerLeft++;
    }
    
    /**
    *increaseWaitingTime method updates the total waiting time.
    *@param latestTime The latest waiting time.
    */
    public void increaseWaitingTime(double latestTime) {
        totalWaitingTime = totalWaitingTime + latestTime;
    }
    
    /**
    *Overrides the toString() method in String class.
    *@return The average waiting time, customer served and customers who left during simulation. 
    */
    @Override
    public String toString() {
        double averageWaitingTime;
        if (customerServed == 0) {
            averageWaitingTime = totalWaitingTime;
        } else {
            averageWaitingTime = totalWaitingTime / customerServed;
        }
        return String.format("[%.3f %d %d]", averageWaitingTime, customerServed, customerLeft);
    }
}
