import java.util.Comparator;

class MyComparator implements Comparator<Customer> {
	public int compare(Customer c1, Customer c2) {
		if (c1.completionTime() == c2.completionTime()) {
			return c1.getCustomerId() - c2.getCustomerId();
		} else {
			if (c1.completionTime() - c2.completionTime() < 0) {
				return -1;
			} else if (c1.completionTime() - c2.completionTime() > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
