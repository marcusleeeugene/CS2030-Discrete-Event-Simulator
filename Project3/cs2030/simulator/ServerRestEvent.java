package cs2030.simulator;

/**
 * ServerRestEvent stores the event in which the server goes to rest.
 */
public class ServerRestEvent extends Event {

    /**
     * ServerRestEvent constructor to set up event information.
     * @param customer Customer that should be involved in this event.
     * @param eventTime Time of occurence of the event.
     * @param state State the event is in.
     * @param server Server that is going to rest.
     */
    public ServerRestEvent(Customer customer, double eventTime, State state, Server server) {
        super(null, eventTime, state, server);
    }

}
