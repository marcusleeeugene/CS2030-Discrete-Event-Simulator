package cs2030.simulator;

/**
 *GreedyCustomer class contains greedy customer information.
 */
public class GreedyCustomer extends Customer {
    /**
     * Customer constructor sets up the arrival time and customer id.
     * @param arrivalTime The time of arrival of customer.
     * @param customerId Id of the customer.
     * @param type Greedy type of customer
     */
    public GreedyCustomer(double arrivalTime, int customerId, String type) {
        super(arrivalTime, customerId, type);
    }

}
