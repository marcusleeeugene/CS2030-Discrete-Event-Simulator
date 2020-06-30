package cs2030.simulator;

/**
 * Abstract Event class consists of the following subclasses.
 * ArriveEvent, LeaveEvent, DoneEvent, WaitEvent, ServedEvent.
 * This class is used to store information of each event.
 */
public abstract class Event {
    protected final Customer customer;
    protected final double eventTime;
    protected final State state;
    protected Server server;

    /**
    *Event constructor to set up basic information.
    *@param customer Customer involved in the event.
    *@param eventTime Time of occurence of the event.
    *@param state The state the event is in.
    */
    public Event(Customer customer, double eventTime, State state) {
        this.customer = customer;
        this.eventTime = eventTime;
        this.state = state;
    }

    /**
    *Event overloaded constructor to set up basic information, but includes if a server is involve.
    *@param customer Customer involved in the event.
    *@param eventTime Time of occurence of the event.
    *@param state The state the event is in.
    *@param server The server involved in the event.
    */   
    public Event(Customer customer, double eventTime, State state, Server server) {
        this.customer = customer;
        this.eventTime = eventTime;
        this.state = state;
        this.server = server;
    }

    /**
     * getServer is a getter method to retrieve the server.
     * @return The server involved in the event.
     */
    public Server getServer() {
        return this.server;
    }

    /**
     * getEventTime is a getter method to retrieve the event time.
     * @return The time of occurrence of the event.
     */
    public double getEventTime() {
        return this.eventTime;
    }

    /**
     * getEventTime is a getter method to retrieve the event time.
     * @return The time of occurrence of the event.
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * getCustomerId is a getter method to retrieve the customer id.
     * @return The customer id.
     */
    public int getCustomerId() {
        return this.customer.getCustomerId();
    }

    /**
     * getState is a getter method to retrieve the state of the event.
     * @return The state of the event.
     */
    public State getState() {
        return this.state;
    }

}
