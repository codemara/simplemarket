
public class DefaultComputer implements Computer{

	public String OrderID;
	
	public DefaultComputer() {
		do {
			int id = (int)(Math.random()*101);
			OrderID = "OrderID@"+id;
		} while(MarketSpace.hasSameID(OrderID));
	}
	
	@Override
	public String getDescription() {
		return "Default Computer";
	}

	@Override
	public double getPrice() {
		return 700.0;
	}

	@Override
	public String getOrderID() {
		return OrderID;
	}
	
	public String toString() {
		return getDescription() + ", and total price is " + getPrice();
	}
	
}