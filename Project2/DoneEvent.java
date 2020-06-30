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
    *@return The time at which the customer is done being served by the server and the server's id.
    */
    @Override
    public String toString() {
        return String.format("%.3f %d done serving by %d", getEventTime(), getCustomerId(), getServer().getServerId());
    }
}
