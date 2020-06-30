package cs2030.simulator;

/**
 * ServerBackEvent stores the event in which the server returns from rest.
 */
public class ServerBackEvent extends Event {

    /**
     * ServerBackEvent constructor to set up event information.
     * @param customer Customer that should be involved in this event.
     * @param eventTime Time of occurence of the event.
     * @param state State the event is in.
     * @param server Server that is returning back from resting.
     */
    public ServerBackEvent(Customer customer, double eventTime, State state, Server server) {
        super(null, eventTime, state, server);
    }

}

