package cs2030.simulator;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
*EventManager class computes each the series of events and organize them in a sequence.
*/

public class EventManager { 
    private PriorityQueue<Event> queueEvents = new PriorityQueue<>(new EventComparator());
    private Server[] servers;
    private Statistics statistics = new Statistics();
    State ARRIVES = State.ARRIVES;
    State SERVED = State.SERVED;
    State LEAVES = State.LEAVES;
    State DONE = State.DONE;
    State WAIT = State.WAIT;
    State SERVER_REST = State.SERVER_REST;
    State SERVER_BACK = State.SERVER_BACK;

    final int seed;
    final int numServers;
    final int numMachineServers;
    final int maxQueueLength;
    final int numCustomers;
    final double arrivalRate;
    final double serviceRate;
    final double restingRate;
    final double pRestingRate;
    final double pGreedyCustomer;
    final RandomGenerator randomGenerator;

    /**
     * EventManager constructor gets user inputs on the command line and sets up necessary details for simulation to run.
     */
    public EventManager() {
        Scanner input = new Scanner(System.in);
        seed = input.nextInt();
        numServers = input.nextInt();
        numMachineServers = input.nextInt();
        maxQueueLength = input.nextInt();
        numCustomers = input.nextInt();
        arrivalRate = input.nextDouble();
        serviceRate = input.nextDouble();
        restingRate = input.nextDouble();
        pRestingRate = input.nextDouble();
        pGreedyCustomer = input.nextDouble();
        randomGenerator = new RandomGenerator(seed, arrivalRate, serviceRate, restingRate);
    }

    /**
    *setUpSimulation method initializes the customers and servers required for the simulation.
    */
    public void setUpSimulation() {
        double arrivalTimeAccumulation = 0.0;
        //Add customers to arrive event
        for (int i = 0; i < numCustomers; i++) {
            double customerType = randomGenerator.genCustomerType();
            Customer customer;
            if (customerType < pGreedyCustomer) {
                customer = new GreedyCustomer(arrivalTimeAccumulation, i + 1, "Greedy");
            } else {
                customer = new Customer(arrivalTimeAccumulation, i + 1, "Normal");
            }
            Event arriveEvent = new ArriveEvent(customer, arrivalTimeAccumulation, ARRIVES);
            queueEvents.add(arriveEvent);
            arrivalTimeAccumulation += randomGenerator.genInterArrivalTime();
        }

        //Add human servers
        servers = new Server[numServers + numMachineServers]; //size
        for (int i = 0; i < numServers; i++) {
            servers[i] = new Server(i + 1, "Human");
        }

        //Add machine servers
        for (int i = numServers; i < servers.length; i++) {
            servers[i] = new MachineServer(i + 1, "Machine");
        }

    }

    /**
     *idleServer method checks if a server can serve the customer for the arrive process.
     *@return A server if there is a free server, or else returns null.
     * */
    public Server idleServer() {
        //checks for servers who are idle
        Server servableServer = null;
        for (int i = 0; i < servers.length; i++) {
            if (servers[i].canServe()) {
                servableServer = servers[i];
                break;
            }
        }
        return servableServer;
    }

    /**
     * waitableServer method checks for the server that the customer can wait for.
     * @param customer Customer that just arrived.
     * @return The server that is the customer should wait for, or else returns null.
     */
    public Server waitableServer(Customer customer) {
        //checks for servers who are able to wait
        Server waitableServer = null;
        if (customer.getType() == "Greedy") { //greedy customer
            int serverIndex = 0;
            int min = servers[serverIndex].getWaitingCapacity();
            for (int i = 0; i < servers.length; i++) {
                if (servers[i].getWaitingCapacity() != maxQueueLength) {
                    if (servers[i].getWaitingCapacity() < min) {
                        if (min == servers[i].getWaitingCapacity()) {
                            continue;
                        } else {
                            min = servers[i].getWaitingCapacity();
                            serverIndex = i;
                        }
                    }
                    waitableServer = servers[serverIndex];
                }
            }
        } else { //normal customer
            for (int i = 0; i < servers.length; i++) {
                if (servers[i].getWaitingCapacity() != maxQueueLength) {
                    waitableServer = servers[i];
                    break;
                }
            }
        }
        return waitableServer;
    }

    /**
     * getServerIndex method returns the index of the server in the serverList array.
     * @param server Server to check for index in the array.
     * @return The index of the server
     */
    public int getServerIndex(Server server) {
        int serverIndex = 0;
        for (int i = 0; i < servers.length; i++) {
            if (servers[i].getServerId() == server.getServerId()) {
                serverIndex = i;
            }
        }
        return serverIndex;
    }

    /**
    *arriveProcess method checks if server is idle or not, before deciding the next event. 
    *@param event The arrival Event.  
    */
    public void arriveProcess(Event event) {
        Customer customer = event.getCustomer();
        double time = event.getEventTime();
        Server server;
        int serverIndex;

        if (idleServer() != null) { //Serve
            serverIndex = getServerIndex(idleServer());
            server = servers[serverIndex];
            Event servedEvent = new ServedEvent(customer, time, SERVED, server);
            queueEvents.add(servedEvent);
        } else {
            if (waitableServer(customer) != null) { //Wait
                serverIndex = getServerIndex(waitableServer(customer));
                server = servers[serverIndex];
                Event waitEvent = new WaitEvent(customer, time, WAIT, server);
                queueEvents.add(waitEvent);
            } else { //leaves
                Event leaveEvent = new LeaveEvent(customer, time, LEAVES);
                queueEvents.add(leaveEvent);
                statistics.increaseLeft();
            }
        }
    }
    
    /**
    *waitProcess method checks if the server has a waiting customer and sets up the served event for the customer.
    *@param event Event to be processed.
    */
    public void waitProcess(Event event) {
        Customer customer = event.getCustomer();
        double time = event.getEventTime();
        Server server = event.getServer();
        int serverIndex = getServerIndex(server);
        servers[serverIndex].addWaitingCustomer(customer);
    }

    /**
    *servedProcess method sets up the done event for the customer.
    *@param event Event to be processed.
    */
    public void servedProcess(Event event) {
        //generate done event with random service time
        Customer customer = event.getCustomer();
        double time = event.getEventTime();
        Server server = event.getServer();
        int serverIndex = getServerIndex(server);

        double serviceTime = randomGenerator.genServiceTime();
        servers[serverIndex].setTime(time + serviceTime);

        if (servers[serverIndex].customerInWaiting(customer)) { //if customer was previously a waiting customer
            servers[serverIndex].removeWaitingCustomer(customer);
        }

        servers[serverIndex].setCustomerServed(customer);
        statistics.increaseServed();
        Event doneEvent = new DoneEvent(customer, time + serviceTime, DONE, server);
        queueEvents.add(doneEvent);
    }

    /**
    *doneProcess updates the server customer served and unwaits the customer.
    *@param event Event to be processed.
    */
    public void doneProcess(Event event) {
        Server server = event.getServer();
        double time = event.getEventTime();
        int serverIndex = getServerIndex(server);
        servers[serverIndex].finishServing();

        if (server.getType() == "Machine") { //Server is a self-check out type
            //Done serving current customer, so now can serve next in the queue
            if (server.getWaitingCapacity() != 0) {
                Event servedEvent = new ServedEvent(server.getNextWaitingCustomer(), time, SERVED, server);
                double waitTime = time - server.getNextWaitingCustomer().getArrivalTime();
                statistics.increaseWaitingTime(waitTime);
                queueEvents.add(servedEvent);
            }
        } else {
            double randomRest = randomGenerator.genRandomRest();
            if (randomRest < pRestingRate) { //generate Server_rest
                Event serverRestEvent = new ServerRestEvent(null, time, SERVER_REST, server);
                queueEvents.add(serverRestEvent);
            } else { //serve as per normal
                //Done serving current customer, so now can serve next in the queue
                if (server.getWaitingCapacity() != 0) {
                    Event servedEvent = new ServedEvent(server.getNextWaitingCustomer(), time, SERVED, server);
                    double waitTime = time - server.getNextWaitingCustomer().getArrivalTime();
                    statistics.increaseWaitingTime(waitTime);
                    queueEvents.add(servedEvent);
                }
            }
        }

    }

    /**
     * serverRestProcess method handles when the server should go into rest and schedules the serverBack event.
     * @param Event to be processed.
     */
    public void serverRestProcess(Event event) {
        double time = event.getEventTime();
        Server server = event.getServer();
        int serverIndex = getServerIndex(server);
        servers[serverIndex].setTime(time);
        double restTime = randomGenerator.genRestPeriod();
        Event serverBackEvent = new ServerBackEvent(null, time + restTime, SERVER_BACK, server);
        queueEvents.add(serverBackEvent);
        servers[serverIndex].setResting(true);
    }

    /**
     * serverBackProcess method handles when a server comes back from resting and schedules the server to server the next customer in the waiting list.
     * @param event Event to be processed.
     */
    public void serverBackProcess(Event event) {
        Server server = event.getServer();
        double time = event.getEventTime();
        server.setResting(false);
        if (server.getWaitingCapacity() != 0) {
            double waitTime = time - server.getNextWaitingCustomer().getArrivalTime();
            statistics.increaseWaitingTime(waitTime);
            Event servedEvent = new ServedEvent(server.getNextWaitingCustomer(), time, SERVED, server);
            queueEvents.add(servedEvent);
        }

    }

    /**
    *simulation method runs the sequence of events that are suppose to happen and prints out statistics for the event.
    */
    public void simulation() {
        setUpSimulation();
        while (!queueEvents.isEmpty()) {
            Event currentEvent = queueEvents.poll();
            if (currentEvent.getState() == ARRIVES) {
                System.out.println(currentEvent);
                arriveProcess(currentEvent);
            } else if (currentEvent.getState() == SERVED) {
                System.out.println(currentEvent);
                servedProcess(currentEvent);
            } else if (currentEvent.getState() == WAIT) {
                System.out.println(currentEvent);
                waitProcess(currentEvent);
            } else if (currentEvent.getState() == LEAVES) {
                System.out.println(currentEvent);
            } else if (currentEvent.getState() == DONE) {
                System.out.println(currentEvent);
                doneProcess(currentEvent);
            } else if (currentEvent.getState() == SERVER_REST) {
                serverRestProcess(currentEvent);
            } else if (currentEvent.getState() == SERVER_BACK) {
                serverBackProcess(currentEvent);
            }
        }
        System.out.println(statistics);
    }

}
