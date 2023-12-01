
public abstract class ComputerDecorator implements Computer {

	private Computer newComputer;
	
	public ComputerDecorator(Computer c) {
		newComputer = c;
	}
	
	@Override
	public String getDescription() {
		return newComputer.getDescription();
	}

	@Override
	public double getPrice() {
		return newComputer.getPrice();
	}

	@Override
	public String getOrderID() {
		return newComputer.getOrderID();
	}
	
	@Override	
	public String toString() {
		return newComputer.getDescription() + ", and total price is " + newComputer.getPrice();
	}

}
