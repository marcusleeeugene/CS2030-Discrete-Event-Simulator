import java.util.Scanner;
import java.util.PriorityQueue;

public class Main { 
    static int customerId = 0; 

    public static void main(String[] args) {
        PriorityQueue<Customer> customerList = addCustomers(new Scanner(System.in));
        Event simulation = new Event(customerList, new Server());
        simulation.allProcess();
    }

    public static PriorityQueue<Customer> addCustomers(Scanner input) {
        PriorityQueue<Customer> customerList =  new PriorityQueue<>(new MyComparator());
        while (input.hasNextDouble()) {
            customerId++;
            customerList.add(new Customer(customerId, input.nextDouble(), State.ARRIVES));
        }
        return customerList;
    } 

}
