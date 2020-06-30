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
    *@return The time at which the customer is served by the server and the server's id.
    */
    @Override
    public String toString() {
        return String.format("%.3f %d served by %d", getEventTime(), getCustomerId(), getServer().getServerId());
    }
}
