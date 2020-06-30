import java.util.Scanner;
import java.util.ArrayList;

class Main {
	public static final int ARRIVES = 1;
	public static final int SERVED = 2;
	public static final int LEAVES = 3;

	public static void main(String[] args) {
		int customerid = 0;
		ArrayList<Customer> customerlist = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		while(input.hasNextDouble()) {
			customerid++;
			customerlist.add(new Customer(customerid, input.nextDouble(), ARRIVES));
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
				customer.changeCustomerState(SERVED);
			} else {
				customer.changeCustomerState(LEAVES);
			}
			System.out.println(customer.toString());
		}
		System.out.println(String.format("Number of customers: %d", customerlist.size()));
	}

	

}
