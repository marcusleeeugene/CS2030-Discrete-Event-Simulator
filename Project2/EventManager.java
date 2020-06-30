import java.util.PriorityQueue;
import java.util.Scanner;

/**
*EventManager class computes each the series of events and organize them in a sequence.
*/

public class EventManager { 
    private PriorityQueue<Event> queueEvents =  new PriorityQueue<>(new EventComparator());
    private Server[] servers; 
    private static final double SERVICE_FREQUENCY = 1.0;
    private Statistics statistics = new Statistics();
    State ARRIVES = State.ARRIVES;
    State SERVED = State.SERVED;
    State LEAVES = State.LEAVES;
    State DONE = State.DONE;
    State WAIT = State.WAIT;
    
    /**
    *setUpSimulation method initializes the customers and servers required for the simulation.
    */
    void setUpSimulation() {
        Scanner input = new Scanner(System.in);
        int numServers = input.nextInt();
        this.servers = new Server[numServers];
        for (int i = 0; i < numServers; i++) {
            this.servers[i] = new Server();
        }
        input.nextLine();
        int customerId = 0;
        while (input.hasNext()) {
            double arriveTime =  input.nextDouble();
            customerId++;
            Customer newCustomer = new Customer(arriveTime, customerId);
            Event arriveEvent = new ArriveEvent(newCustomer, arriveTime, ARRIVES);
            this.queueEvents.add(arriveEvent);
        }
        input.close();
    }

    /**
    *checkFreeServer method checks if any of the server is idle. 
    *@param servers The array of servers in the simulation
    *@return null if unavailable or returns the server that if available.
    */
    Server checkFreeServer(Server[] servers) {
        int numServers = servers.length;
        boolean hasWaitingSlot = false;
        Server waitingServer = null;

        for (int i = 0; i < numServers; i++) {
            Server newServer = servers[i];
            if (newServer.canServe()) {
                return newServer;
            }
            if (newServer.hasWaiting() && !hasWaitingSlot) {
                hasWaitingSlot = true;
                waitingServer = newServer;
            }
        }

        if (hasWaitingSlot) {
            return waitingServer;
        } else {
            return null;
        }
    }
    
    /**
    *arriveProcess method checks if server is idle or not, before deciding the next event. 
    *@param event The arrival Event.  
    */
    public void arriveProcess(Event event) {
        int numServers = this.servers.length;
        Customer currentCustomer = event.getCustomer();
        double currentTime = event.getEventTime();
        Server freeServer = checkFreeServer(this.servers);

        if (freeServer != null) {
            if (freeServer.canServe()) {
                Event nextEvent = new ServedEvent(currentCustomer, currentTime, SERVED, freeServer);
                freeServer.setCustomerServed(currentCustomer);
                currentCustomer.setUpdatedTime(currentTime + SERVICE_FREQUENCY);
                queueEvents.add(nextEvent);
            } else if (freeServer.hasWaiting()) {
                Event nextEvent = new WaitEvent(currentCustomer, currentTime, WAIT, freeServer);
                freeServer.setCustomerWaited(currentCustomer);
                queueEvents.add(nextEvent);
            }
        } else {
            Event nextEvent = new LeaveEvent(currentCustomer, currentTime, LEAVES);
            queueEvents.add(nextEvent);
            statistics.increaseLeft();
        }
    }
    
    /**
    *waitProcess method checks if the server has a waiting customer and sets up the served event for the customer.
    *@param event Event to be processed.
    */
    public void waitProcess(Event event) {
        if (!event.getServer().hasWaiting()) {
            Customer currentCustomer = event.getCustomer();
            double serviceTime = event.getServer().getCustomerServed().getUpdatedTime();
            Event nextEvent = new ServedEvent(currentCustomer, serviceTime, SERVED, event.getServer());
            nextEvent.getServer().setCustomerWaited(currentCustomer);
            currentCustomer.setUpdatedTime(serviceTime + SERVICE_FREQUENCY);
            queueEvents.add(nextEvent);
        }
    }
    
    /**
    *servedProcess method sets up the done event for the customer.
    *@param event Event to be processed.
    */
    public void servedProcess(Event event) {
        Customer currentCustomer = event.getCustomer();
        double serviceCompletionTime = event.getEventTime() + SERVICE_FREQUENCY;
        Event nextEvent = new DoneEvent(currentCustomer, serviceCompletionTime, DONE, event.getServer());
        nextEvent.getServer().setCustomerServed(currentCustomer);
        queueEvents.add(nextEvent);
        double updatedTime = event.getEventTime() - event.getCustomer().getArrivalTime();
        statistics.increaseWaitingTime(updatedTime);
        statistics.increaseServed();
    }

    /**
    *doneProcess updates the server customer served and unwaits the customer.
    *@param event Event to be processed.
    */
    public void doneProcess(Event event) {
        event.getServer().updateServer();
    }
    
    /**
    *simulation method runs the sequence of events that are suppose to happen.
    */
    public void simulation() {
        setUpSimulation();
        while (!queueEvents.isEmpty()) {
            Event event = queueEvents.poll();
            State state = event.getState();
            System.out.println(event);
            if (state == ARRIVES) {
                arriveProcess(event);
            } else if (state == WAIT) {
                waitProcess(event);
            } else if (state == SERVED) {
                servedProcess(event);
            } else if (state == DONE) {
                doneProcess(event);
            }
        }
        System.out.println(statistics);
    }
}
