import java.util.PriorityQueue;
import java.util.ArrayList;

/**
*Event class consists of possible event processes: Arrival, Wait, Leaves, Done.
*/

public class Event {
    private final State ARRIVES = State.ARRIVES;
    private final State SERVED = State.SERVED;
    private final State LEAVES = State.LEAVES;
    private final State DONE = State.DONE;
    private final State WAIT = State.WAIT;
    private PriorityQueue<Customer> queueEvents;
    private Server servant;
    private ArrayList<Customer> waitList;
    private double totalWaitingTime;
    private int customerServed;
    private int customerLeft;
    
    /**
    *Event constructor to set up information relating to statistics and customers in the queue.
    *@param customerList Customers in the queue.
    *@param servant Creates the very first servant.
    */
    public Event(PriorityQueue<Customer> customerList, Server servant) {
        this.queueEvents = customerList;
        this.servant = servant;
        this.waitList = new ArrayList<>();
        this.totalWaitingTime = 0;
        this.customerServed = 0;
        this.customerLeft = 0;
    }
    
    /**
    *Takes care of the first Arrival of each customer where: 
    *A server serves the customer when the customer arrives and need not wait.
    *The customer has to wait if the server is serving a customer.
    *The customer leaves if there is someone already waiting.
    *@param c The customer that just arrived.
    */
    public void arrivalProcess(Customer c) {
        if (servant.canServe(c) && waitList.isEmpty()) {
            servant = servant.serve(c);
            c.changeCustomerState(SERVED);
        } else {
            if (!waitList.isEmpty()) { 
                c.changeCustomerState(LEAVES);
                customerLeft++;
            } else {
                c.changeCustomerState(WAIT);
            }
        }
        queueEvents.add(c);
    }

    /**
    *The customer has to wait, and will be waitlisted. 
    *@param c The customer that has to wait.
    */
    public void waitProcess(Customer c) {
        c.setTimeInfo(servant.getNextService());
        servant = servant.serve(c);
        c.changeCustomerState(SERVED);
        waitList.add(c);
    }

    /**
    *The customer can be served, and will be added back to the queue.
    *@param c The customer that can be served.
    */
    public void serveProcess(Customer c) {
        totalWaitingTime += c.getWaitTime();
        c.changeCustomerState(DONE);
        c.setTimeInfo(servant.getNextService());
        customerServed++;
        queueEvents.add(c); 
    }
    
    /**
    *The customer has been completely served and if there is a waitlisted customer,
    *the customer can be added back into the queue.
    */
    public void doneProcess() {
        if (!waitList.isEmpty()) {
            queueEvents.add(waitList.get(0));
            waitList.clear();
        }
    }
    
    /**
    *Prints all the statistics about the queue events which consist of:
    *The average waiting time for customers who have been served.
    *The number of customers served.
    *The number of customers who left without being served.
    */
    public void printStatistics() {
        System.out.println(String.format("[%.3f %d %d]", totalWaitingTime / customerServed, customerServed, customerLeft));
 
    }
    
    /**
    *Handles all the events happening in the queue.
    */
    public void allProcess() {
        while (!queueEvents.isEmpty()) {
            Customer c = queueEvents.poll();
            State state = c.getCustomerState();
            System.out.println(c);
            if (state == ARRIVES) {
                arrivalProcess(c);
            } else if (state == WAIT) {
                waitProcess(c);
            } else if (state == SERVED) {
                serveProcess(c);
            } else if (state == DONE) {
                doneProcess();
            }
        }
        printStatistics();
    }

}
