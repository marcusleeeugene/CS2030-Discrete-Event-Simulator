/**
* ArriveEvent class stores the event in which the customer first arrives.
*/

public class ArriveEvent extends Event {

    /**
    *ArriveEvent constructor to set up event information.
    *@param customer Customer that arrived.
    *@param eventTime Time of occurence of the event.
    *@param state State the event is in.
    */
    public ArriveEvent(Customer customer, double eventTime, State state) {
        super(customer, eventTime, state);
    }

    /**
    *Overrides the toString() method in String class.
    *@return The time at which the customer arrives and the customer's id.
    */
    @Override
    public String toString() {
        return String.format("%.3f %d arrives", getEventTime(), getCustomerId());
    }
}
