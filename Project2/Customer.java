/**
*Customer class contains customer information. 
*/

public class Customer {
    private final int customerId;
    private final double arrivalTime;
    private double updatedTime;
    
    /**
    *Customer constructor sets up the arrival time and customer id.
    *@param arrivalTime The time of arrival of customer.
    *@param customerId Id of the customer.
    */
    public Customer(double arrivalTime, int customerId) {
        this.arrivalTime = arrivalTime;
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }
    
    /**
    *getUpdatedTime method returns the latest timing for the event the customer has went through.
    *@return The latest timing for the customer.
    */
    public double getUpdatedTime() {
        return this.updatedTime;
    }
    
    /**
    *setUpdatedTime method updates the latest timing for events that occured involving the customer.
    *@param latestTime New time depending on events that happened for the customer.
    */
    public void setUpdatedTime(double latestTime) {
        this.updatedTime = latestTime;
    }
}
