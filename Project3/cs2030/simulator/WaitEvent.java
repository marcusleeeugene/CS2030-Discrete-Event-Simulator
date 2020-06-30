package cs2030.simulator;

/**
 * WaitEvent class stores the event in which the customer waits for a server.
 */

public class WaitEvent extends Event {
    
    /**
    *WaitEvent constructor to set up event information.
    *@param customer Customer that has to wait.
    *@param eventTime Time of occurence of the event.
    *@param state State the event is in.
    *@param server Server that the customer has to wait for.
    */
    public WaitEvent(Customer customer, double eventTime, State state, Server server) {
        super(customer, eventTime, state, server);
    }

    /**
    *Overrides the toString() method in String class.
    *@return The time at which the customer of a certain type waits for the server of a server type and the server's id.
    */
    @Override
    public String toString() {
        String serverType = "";
        if (getServer() instanceof MachineServer) {
            serverType = "self-check";
        } else {
            serverType = "server";
        }

        String customerType = "";
        if (getCustomer() instanceof GreedyCustomer) {
            customerType = "(greedy)";
        }

        return String.format("%.3f %d%s waits to be served by %s %d", getEventTime(), getCustomerId(), customerType, serverType, getServer().getServerId());
    }
}
