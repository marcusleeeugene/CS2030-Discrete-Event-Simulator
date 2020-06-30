package cs2030.simulator;

import java.util.ArrayList;

/**
 *Machine Server class contains the machine server information.
 */
public class MachineServer extends Server {
    private static ArrayList<Customer> machineCustomerWaiting = new ArrayList<Customer>();

    /**
     *Machine Server constructor inistializes a machine server with server id.
     * @param serverId The id of the server.
     * @param type Machine type of server.
     */
    public MachineServer(int serverId, String type) {
        super(serverId, type);
    }

    /**
     *Overrides the getWaitingCapacity method in Server class.
     *@return The waiting list size of the machine server.
     */
    @Override
    public int getWaitingCapacity() {
        return this.machineCustomerWaiting.size();
    }

    /**
     * Overrides the addWaitingCustomer method in Server class.
     * @param customer The customer that is going into wait.
     */
    @Override
    public void addWaitingCustomer(Customer customer) {
        this.machineCustomerWaiting.add(customer);
    }

    /**
     * Overrides the removeWaitingCustomer method in Server class.
     * @param customer The customer that is has been served.
     */
    @Override
    public void removeWaitingCustomer(Customer customer) {
        this.machineCustomerWaiting.remove(customer);
    }

    /**
     * Overrides the customerInWaiting method in Server class.
     * @param customer The customer that has been served.
     * @return True if the wait list contains customer, else false.
     */
    @Override
    public boolean customerInWaiting(Customer customer) {
        return this.machineCustomerWaiting.contains(customer);
    }

    /**
     * Overrides the getNextWaitingCustomer method in Server class.
     * @return The first customer in the waitlist of the machine server.
     */
    @Override
    public Customer getNextWaitingCustomer() {
        return this.machineCustomerWaiting.get(0);
    }

    /**
     * Overrides the setResting method in Server class to always have the restStatus to be false.
     * @param restStatus Resting status.
     */
    @Override
    public void setResting(boolean restStatus) {
        super.resting = false;
    }

}
