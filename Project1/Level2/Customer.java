class Customer {
	final private int CUSTOMERID;
	final private double ARRIVALTIME;

	Customer(int customerid, double arrivaltime) {
		this.CUSTOMERID = customerid;
		this.ARRIVALTIME = arrivaltime;
	}

	int getCustomerId() {
		return this.CUSTOMERID;
	}

	double getArrivalTime() {
		return this.ARRIVALTIME;
	}
	
	@Override
	public String toString() {
		return String.format("%d arrives at %.3f", getCustomerId(), getArrivalTime());
	}
}
