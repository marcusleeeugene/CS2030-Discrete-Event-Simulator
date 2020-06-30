import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.ArrayList;

class Main {
	public static final int ARRIVES = 1;
	public static final int SERVED = 2;
	public static final int LEAVES = 3;
	public static final int DONE = 4;

	public static void main(String[] args) {
		int customerid = 0;
		PriorityQueue<Customer> customerlist = new PriorityQueue<>(new MyComparator());
		Scanner input = new Scanner(System.in);
		while (input.hasNextDouble()) {
			customerid++;
			customerlist.add(new Customer(customerid, input.nextDouble(), ARRIVES));
		}
		int numcustomers = customerlist.size();
		addArrivals(customerlist);
		queueEvents(customerlist, new Server());
		System.out.println("Number of customers: " + numcustomers);
	}

	static void queueEvents(PriorityQueue<Customer> customerlist, Server servant) {	
		System.out.println();
		if (customerlist.size() > 0) {
			Iterator itr = customerlist.iterator();
			Customer customer = customerlist.poll();
			String event = "# Get next event: " + customer.toString();
			System.out.println(event);
				
			if (customer.getCustomerState() == ARRIVES) {
				if (servant.canServe(customer)) {
					servant = servant.serve(customer);
					customer.changeCustomerState(2);
				} else {
					customer.changeCustomerState(3);
				}
				customerlist.add(customer);
			} else if (customer.getCustomerState() == SERVED) {
				customer.changeCustomerState(4);
				customerlist.add(customer);
			} 
			
			Iterator itr2 = customerlist.iterator();
			while (itr2.hasNext()) {
				System.out.println(itr2.next());
			}

			queueEvents(customerlist, servant);
		}	

	}

	static void addArrivals(PriorityQueue<Customer> customerlist) {
		System.out.println("# Adding arrivals");
		Iterator itr = customerlist.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	

}
