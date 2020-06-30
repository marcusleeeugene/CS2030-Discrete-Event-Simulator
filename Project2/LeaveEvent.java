/**
 * LeaveEvent class stores the event in which the customer leaves.
 */

public class LeaveEvent extends Event {

    /**
    *WaitEvent constructor to set up event information.
    *@param customer Customer that arrived.
    *@param eventTime Time of occurence of the event.
    *@param state State the event is in. 
    */
    public LeaveEvent(Customer customer, double eventTime, State state) {
        super(customer, eventTime, state);
    }
    
    /**
    *Overrides the toString() method in String class.
    *@return The time at which the customer leaves and the customer's id. 
    */
    @Override
    public String toString() {
        return String.format("%.3f %d leaves", getEventTime(), getCustomerId());
    }
}
