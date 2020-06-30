package cs2030.simulator;
/**
 * DoneEvent class stores the event in which the customer is done being served..
 */

public class DoneEvent extends Event {

    /**
    *DoneEvent constructor to set up event information.
    *@param customer Customer that has been done serving.
    *@param eventTime Time of occurence of the event.
    *@param state State the event is in.
    *@param server Server that has finished serving the customer.
    */
    public DoneEvent(Customer customer, double eventTime, State state, Server server) {
        super(customer, eventTime, state, server);
    }
    
    /**
    *Overrides the toString() method in String class.
    *@return The time at which the customer of a certain type is done being served by the server of a certain type, along with the server's id.
    */
    @Override
    public String toString() {
        String serverType = "";
        if (getServer().getType() == "Machine") {
            serverType = "self-check";
        } else {
            serverType = "server";
        }

        String customerType = "";
        if (getCustomer().getType() == "Greedy") {
            customerType = "(greedy)";
        }

        return String.format("%.3f %d%s done serving by %s %d", getEventTime(), getCustomerId(), customerType, serverType, getServer().getServerId());
    }
}
