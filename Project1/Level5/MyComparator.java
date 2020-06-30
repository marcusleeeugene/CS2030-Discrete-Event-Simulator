import java.util.Comparator;

/**
*MyComparator class will implement the Comparator class with a generic type of Customer.
*This class will give priority to customers' time information that occurs the earliest
*and if there is a tie in time information, priority will be given to the lower customer ID.
*/

public class MyComparator implements Comparator<Customer> {

    /**
    *Compares two customers for order.
    *@param c1 First customer to be compared.
    *@param c2 Second customer to be compared.
    *@return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second
    */
    public int compare(Customer c1, Customer c2) {
        if (c1.getTimeInfo() == c2.getTimeInfo()) {
            return c1.getCustomerId() - c2.getCustomerId();
        } else {
            if (c1.getTimeInfo() < c2.getTimeInfo()) {
                return -1;
            } else if (c1.getTimeInfo() > c2.getTimeInfo()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
