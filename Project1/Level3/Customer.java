class Customer {
	final private int CUSTOMERID;
	final private double ARRIVALTIME;
	private int customerState;

	Customer(int customerid, double arrivaltime, int customerstate) {
		this.CUSTOMERID = customerid;
		this.ARRIVALTIME = arrivaltime;
		this.customerState = customerstate;
	}

	int getCustomerId() {
		return this.CUSTOMERID;
	}

	double getArrivalTime() {
		return this.ARRIVALTIME;
	}

	void changeCustomerState(int state) {
		this.customerState = state;
	}
	
	@Override
	public String toString() {
		String statestr;
		if (customerState == 1) {
			statestr = "arrives";
		} else if (customerState == 2) {
			statestr = "served";
		} else {
			statestr = "leaves";
		}
		return String.format("%.3f %d ", getArrivalTime(), getCustomerId()) + statestr;
	}
}
