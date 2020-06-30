package cs2030.simulator;
/**
*Customer class contains customer information. 
*/

public class Customer {
    protected final int customerId;
    protected final String type;
    protected final double arrivalTime;

    /**
    * Customer constructor sets up the arrival time and customer id.
    * @param arrivalTime The time of arrival of customer.
    * @param customerId Id of the customer.
    * @param type Greedy or Normal type of customer.
     */
    public Customer(double arrivalTime, int customerId, String type) {
        this.arrivalTime = arrivalTime;
        this.type = type;
        this.customerId = customerId;
    }

    /**
     * getCustomerId is a getter method that retrieves the customer id.
     * @return Customer id.
     */
    public int getCustomerId() {
        return this.customerId;
    }

    /**
     * getType is a getter method to retrieve the customer type.
     * @return The type of the customer.
     */
    public String getType() {
        return this.type;
    }

    /**
     * getArrivalTime is a getter method that retrieves the arrival time of the customer.
     * @return The arrival time.
     */
    public double getArrivalTime() {
        return this.arrivalTime;
    }

}
