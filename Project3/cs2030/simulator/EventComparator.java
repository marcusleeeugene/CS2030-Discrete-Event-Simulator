package cs2030.simulator;

import java.util.Comparator;

/**
*EventComparator class will implement the Comparator class with a generic type of Event.
*This class will give priority to event time information that occurs the earliest.
*If there is a tie in time information, priority will be given to the lower customer ID.
*/

public class EventComparator implements Comparator<Event> {
    
    /**
    *Compares two events for the order.
    *@param e1 First event to be compared. 
    *@param e2 Second event to  be compared.
    *@return A negative integer, zero, or a positive  integer depending on earliest occurrence.
    */
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getEventTime() != e2.getEventTime()) {
            if (e1.getEventTime() > e2.getEventTime()) {
                return 1;
            } else if (e1.getEventTime() < e2.getEventTime()) {
                return -1;
            } else {
                return 0;
            }
        } else {
            if (e1.getCustomerId() < e2.getCustomerId()) {
                return -1;
            } else if (e1.getCustomerId() > e2.getCustomerId()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
