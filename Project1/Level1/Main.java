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
		for (int i = 0; i < customerlist.size(); i++) {
			Customer customer = customerlist.get(i);
			System.out.println(customer.toString());
		}
		System.out.println(String.format("Number of customers: %d", customerlist.size()));
	}

}
