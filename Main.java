import java.io.IOException;

public class Main {

	private static MarketSpace marketspace;
	
	private static String company = "Best Buy";
	
	public static void main(String[] args) {
	
		System.out.println("Welcome to " + company + "\n");
		
		boolean validMarket;
		
		marketspace = MarketSpace.getInstance();
		
		
		try {
			validMarket = marketspace.loadProducts();
		} catch (IOException e) {
			System.out.println("\nSorry there is a problem loading the MarketSpace. \n"
					+ "Please come back later.\n");
			validMarket = false;
		}
		
		int cmd = 0;
		
		while(cmd < 5 && validMarket) {
			
			switch(cmd) {
			case 0:
				cmd = marketspace.marketHome();
				break;
			case 1:
				Computer newDefault = marketspace.addToCart(null,null);
				marketspace.viewCurrentOrder(newDefault);
				cmd = marketspace.listProducts(newDefault);
				break;
			case 2:
				marketspace.viewCart();
				cmd = 0;
				break;
			case 3:
				marketspace.sort(new SortByOrderID());
				cmd = 0;
				break;
			case 4:
				marketspace.sort(new SortByPrice());
				cmd = 0;
				break;
			}
			
		}
		marketspace.userScan.close();
		System.out.println("Thank you for shopping at " + company + ", see you next time.");
	}
	
}
