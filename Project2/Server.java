/**
*Server class contains server information.
*/

public class Server {
    private static int serverCount = 0;
    private int serverId;
    private Customer customerServed;
    private Customer customerWaited;
    
    /**
    *Server constructor sets up the server id and sets the server to idle.
    */
    public Server() {
        serverCount++;
        this.customerServed = null;
        this.customerWaited = null;
        this.serverId = serverCount;
    }

    public int getServerId() {
        return this.serverId;
    }

    public Customer getCustomerServed() {
        return this.customerServed;
    }

    public void setCustomerServed(Customer newCustomer) {
        this.customerServed = newCustomer;
    }

    public void setCustomerWaited(Customer newCustomer) {
        this.customerWaited = newCustomer;
    }

    /**
    *Checks whether the server can serve a customer.
    *@return True if a customer can be served, else false for a customer that cannot be served.
    */
    public boolean canServe() {
        return customerServed == null && customerWaited == null;
    }
    
    /**
    *Checks if the server has any waiting customers.
    *@return True if there is a customer waiting for this server, else false.
    */
    public boolean hasWaiting() {
        return customerServed != null && customerWaited == null;
    }
    
    /**
    *updateServer method updates the customer to be served and unwaits
    *the customer that is waiting once the done event takes place.
    */
    public void updateServer() {
        this.customerServed = this.customerWaited;
        this.customerWaited = null;
    }

}
