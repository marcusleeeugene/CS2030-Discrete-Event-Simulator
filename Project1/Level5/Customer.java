/**
*Customer class consist of customer information.
*/

public class Customer { 
    private final int CUSTOMER_ID;
    private final double ARRIVAL_TIME;
    private double timeInfo;
    private State customerState;
    
    /**
    *The customer constructor that creates a customer with information
    *regarding their ID, arrival time and state.
    *@param customerId ID of the customer.
    *@param arrivalTime Arrival time of the customer.
    *@param customerState  State the customer is in. (Arrives, Wait, Leaves or Done)
    */
    public Customer(int customerId, double arrivalTime, State customerState) {
        this.CUSTOMER_ID = customerId;
        this.ARRIVAL_TIME = arrivalTime;
        this.timeInfo = arrivalTime;
        this.customerState = customerState;
    }
     
    public int getCustomerId() {
        return this.CUSTOMER_ID;
    }
     
    public double getArrivalTime() {
        return this.ARRIVAL_TIME;
    }
    
    /**
    *Getter method to get time information of the customer after state change. 
    *@return Time information of the customer.
    */
    public double getTimeInfo() {
        return timeInfo;
    }
    
    /**
    *Setter method to update customer time information after state change.
    *@param newTime The new time information to be updated for the customer.
    */
    public void setTimeInfo(double newTime) {
        this.timeInfo = newTime;
    }
      
    double getWaitTime() {
        return getTimeInfo() - getArrivalTime();
    } 
    
    State getCustomerState() {
        return this.customerState;
    }
    
    /**
    *Setter method to update the state of the customer.
    *@param state The new state to be updated for the customer.
    */
    public void changeCustomerState(State state) {
        this.customerState = state;
    }

    /**
    *Overrides the toString() method in String class.
    *@return Customer time information, Customer ID and state in a String.
    */
    @Override
    public String toString() {
        String stateStr;
        if (customerState == State.ARRIVES) {
            stateStr = "arrives";
        } else if (customerState == State.SERVED) {
            stateStr = "served";
        } else if (customerState == State.LEAVES) {
            stateStr = "leaves";
        } else if (customerState == State.WAIT) {
            stateStr = "waits";
        } else {
            stateStr = "done";
        }
        return String.format("%.3f %d ", getTimeInfo(), getCustomerId()) + stateStr;
    }
}
