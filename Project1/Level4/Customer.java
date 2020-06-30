class Customer {
	public static final int ARRIVES = 1;
	public static final int SERVED = 2;
	public static final int LEAVES = 3;
	public static final int DONE = 4;
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
	
	int getCustomerState() {
		return this.customerState;
	}

	void changeCustomerState(int state) {
		this.customerState = state;
	}

	double completionTime() {
		if (customerState == DONE) {
			return this.ARRIVALTIME + 1.0;
		} else {
			return this.ARRIVALTIME;
		}
	}
	
	@Override
	public String toString() {
		String statestr;
		double arrivaltime = getArrivalTime();

		if (customerState == ARRIVES) {
			statestr = "arrives";
		} else if (customerState == SERVED) {
			statestr = "served";
		} else if (customerState == LEAVES) {
			statestr = "leaves";
		} else {
			statestr = "done";
		}
		return String.format("%.3f %d ", completionTime(), getCustomerId()) + statestr;
	}
}
