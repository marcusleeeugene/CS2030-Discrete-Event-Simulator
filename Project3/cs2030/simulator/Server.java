package cs2030.simulator;

import java.util.ArrayList;

/**
*Server class contains server information.
*/
public class Server {
    protected final int serverId;
    protected final String type;
    protected Customer customerServed;
    protected ArrayList<Customer> customerWaiting;
    protected double time;
    protected boolean resting;

    /**
     * Server constructor sets up the server id and sets the server to idle.
     * @param serverId The server id of the server.
     * @param type Machine or Human type of server.
     */
    public Server(int serverId, String type) {
        this.serverId = serverId;
        this.type = type;
        this.customerServed = null;
        this.customerWaiting = new ArrayList<Customer>();
        this.time = 0;
        this.resting = false;
    }

    /**
     * getServerID is a getter method to retrieve the server id.
     * @return Server id of the server.
     */
    public int getServerId() {
        return this.serverId;
    }

    /**
     * getType is a getter method to retrieve the server type.
     * @return The type of the server.
     */
    public String getType() {
        return this.type;
    }

    /**
     * getCustomerServed is a getter method to retrieve the customer the server is serving.
     * @return The customer that is served.
     */
    public Customer getCustomerServed() {
        return this.customerServed;
    }

    /**
     * setCustomerServed is a setter method to set the customer to be served by the server.
     * @param newCustomer The customer to be served.
     */
    public void setCustomerServed(Customer newCustomer) {
        this.customerServed = newCustomer;
    }

    /**
     * getWaitingCapacity is a getter method to get the size of the waiting list for this server.
     * @return The size of the waiting list.
     */
    public int getWaitingCapacity() {
        return this.customerWaiting.size();
    }

    /**
     * addWaitingCustomer adds the customer to the waitlist of the server.
     * @param customer Customer that goes into waiting.
     */
    public void addWaitingCustomer(Customer customer) {
        this.customerWaiting.add(customer);
    }

    /**
     * removeWaitingCustomer removes the customer from the waitlist of the server.
     * @param customer Customer that has been served.
     */
    public void removeWaitingCustomer(Customer customer) {
        this.customerWaiting.remove(customer);
    }

    /**
     * customerInWaiting checks if the customer is in the waiting list of the server.
     * @param customer Customer to check.
     * @return True if it is in the waiting list, else false.
     */
    public boolean customerInWaiting(Customer customer) {
        return this.customerWaiting.contains(customer);
    }

    /**
     * getNextWaitingCustomer is a getter method that retrieves the first customer in the waiting list.
     * @return First customer in the waiting list.
     */
    public Customer getNextWaitingCustomer() {
        return this.customerWaiting.get(0);
    }

    /**
     * getTime is a getter method that retrieves the updated time of the server.
     * @return Time of process for the server.
     */
    public double getTime() {
        return this.time;
    }

    /**
     * setTime is a setter method that updates the time of the server.
     * @param time Time to be updated with.
     */
    public void setTime(Double time) {
        this.time = time;
    }

    /**
     * setResting is a setter method to update the resting status of the server.
     * @param restStatus True or false.
     */
    public void setResting(boolean restStatus) {
        this.resting = restStatus;
    }

    /**
     * getResting is a getter method to retrieve the resting status of the server.
     * @return True if the server is resting, else false.
     */
    public boolean getResting() {
        return this.resting;
    }

    /**
    * Checks whether the server can serve a customer.
    * @return True if a customer can be served, else false for a customer that cannot be served.
    */
    public boolean canServe() {
        return customerServed == null && !resting;
    }

    /**
     * finishServing completes the serving of the customer.
     */
    public void finishServing() {
        this.customerServed = null;
    }

}
