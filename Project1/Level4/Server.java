class Server {
	private final Customer SERVING;
	private final double NEXTSERVICE;

	Server() {
		this.SERVING = null;
		this.NEXTSERVICE = 0;
	}
	
	Server(Customer serving, double nextservice) {
		this.SERVING = serving;
		this.NEXTSERVICE = nextservice;
	}

	double getNextService() {
		return this.NEXTSERVICE;
	}

	boolean canServe(Customer anotherCustomer) {
		return this.SERVING == null || this.NEXTSERVICE <= anotherCustomer.getArrivalTime();
	}

	Server serve(Customer newcustomer) {
		return new Server(newcustomer, newcustomer.getArrivalTime() + 1.0); 	
	}

	@Override 
	public String toString() {
		return String.format("Customer served; next service @ %.3f", getNextService());
	}
}
