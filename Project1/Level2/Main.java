import java.util.Scanner;
import java.util.ArrayList;

class Main {

	public static void main(String[] args) {
		int customerid = 0;
		ArrayList<Customer> customerlist = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		while(input.hasNextDouble()) {
			customerid++;
			customerlist.add(new Customer(customerid, input.nextDouble()));
		}
		outputCustomerList(customerlist);
	}

	static void outputCustomerList(ArrayList<Customer> customerlist) {
		Server servant = new Server();
		for (int i = 0; i < customerlist.size(); i++) {
			Customer customer = customerlist.get(i);
			System.out.println(customer.toString());
			if (servant.canServe(customer)) {
				servant = servant.serve(customer);
				System.out.println(servant.toString());
			} else {
				System.out.println("Customer leaves");
			}
		}
		System.out.println(String.format("Number of customers: %d", customerlist.size()));
	}

	

}
