/**
*Server class consist of server information. 
*/

public class Server {
    private final Customer SERVING;
    private final double NEXT_SERVICE;

    /**
    *The default constructor to create the first server who serves no one.
    */
    public Server() {
        this.SERVING = null;
        this.NEXT_SERVICE = Double.MAX_VALUE;
    }

    /**
    *Overloaded Server constructor to create a server who serves a customer with the information of the next service.
    *@param serving Customer the server is serving.
    *@param nextService The next timing that the server can serve.
    */
    public Server(Customer serving, double nextService) {
        this.SERVING = serving;
        this.NEXT_SERVICE = nextService;
    }

    /**
    *Getter method for next available service of the server.
    *@return The next available service of the server.
    */
    public double getNextService() {
        return this.NEXT_SERVICE;
    }

    /**
    *Checks whether the server can serve the customer.
    *@param anotherCustomer The customer to serve.
    *@return True if the customer can be served, else customer cannot be served.
    */
    public boolean canServe(Customer anotherCustomer) {
        return this.SERVING == null || this.NEXT_SERVICE <= anotherCustomer.getTimeInfo();
    }

    /**
    *Sets the server to serve the customer.
    *@param newCustomer The new customer to serve.
    *@return Sets up the server to serve the customer with a new next service time.
    */
    public Server serve(Customer newCustomer) {
        return new Server(newCustomer, newCustomer.getTimeInfo() + 1.0);
    }
}
