package cs2030.simulator;
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
    *@return The time at which the customer arrives, along with the customer's id and the customer type.
    */
    @Override
    public String toString() {
        String customerType = "";
        if (getCustomer().getType() == "Greedy") {
            customerType = "(greedy)";
        }

        return String.format("%.3f %d%s arrives", getEventTime(), getCustomerId(), customerType);
    }
}
