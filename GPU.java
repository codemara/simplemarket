
public class GPU extends ComputerDecorator{

	String key = MarketSpace.getProductKey("GPU");
	
	public GPU(Computer c) {
		super(c);
	}
	
	@Override
	public String getDescription() {
		return super.getDescription() + " \n+ " + key;
	}

	@Override
	public double getPrice() {
		return super.getPrice() + MarketSpace.getProductPrice(key);
	}
	
	@Override	
	public String toString() {
		return getDescription() + ", and total price is " + String.format("%.2f", getPrice());
	}
}
