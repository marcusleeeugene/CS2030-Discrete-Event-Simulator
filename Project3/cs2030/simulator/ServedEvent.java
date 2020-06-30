package cs2030.simulator;

/**
 * ServedEvent class stores the event in which the customer is served.
 */

public class ServedEvent extends Event {

    /**
    *ServedEvent constructor to set up event information.
    *@param customer Customer that has been served.
    *@param eventTime Time of occurence of the event.
    *@param state State the event is in.
    *@param server Server that served the customer.
    */
    public ServedEvent(Customer customer, double eventTime, State state, Server server) {
        super(customer, eventTime, state, server);
    }

    /**
    *Overrides the toString() method in String class.
    *@return The time at which the customer of a certain type is served by the server of a certain type and the server's id.
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

        return String.format("%.3f %d%s served by %s %d", getEventTime(), getCustomerId(), customerType, serverType, getServer().getServerId());
    }
}
