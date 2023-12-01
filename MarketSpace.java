import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MarketSpace {

	private static MarketSpace market;

	private static List<Computer> cart = new ArrayList<>();
	private static Map<String,Double> products = new HashMap<String,Double>();
	
	public Scanner userScan = new Scanner(System.in);  // Create a Scanner object
	
	private MarketSpace(){}
	
	public static MarketSpace getInstance() {

		if(market == null) {
			market = new MarketSpace();
		}	
		return market;
	}
	

	public boolean loadProducts() throws IOException {
		
		String filePath = "products.txt";
		
		File file = new File(ClassLoader.getSystemResource(filePath).getFile());
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String strProducts; 	 
		while ((strProducts = br.readLine()) != null) {
			String[] arrProducts = strProducts.split(",");
			if (arrProducts != null) products.put(arrProducts[0], Double.parseDouble(arrProducts[1]));
		}
		br.close();
		return true;
	}

	public Computer addToCart(Computer currentOrder, String component) {
		
		Computer newBuilt;
		
		if (currentOrder==null) {
			newBuilt = new DefaultComputer();
			cart.add(newBuilt);
		} else {
		
		
			switch(component) {
		
			case "Mouse" : 
				newBuilt = new Mouse(currentOrder);
				cart.set(cart.indexOf(currentOrder), newBuilt);
				break;
			case "Monitor" : 
				newBuilt = new Monitor(currentOrder);
				cart.set(cart.indexOf(currentOrder), newBuilt);
				break;
			case "Speaker" : 
				newBuilt = new Speaker(currentOrder);
				cart.set(cart.indexOf(currentOrder), newBuilt);
				break;
			case "Keyboard" : 
				newBuilt = new Keyboard(currentOrder);
				cart.set(cart.indexOf(currentOrder), newBuilt);
				break;
			case "GPU 3080 Ti" : 
				newBuilt = new GPU(currentOrder);
				cart.set(cart.indexOf(currentOrder), newBuilt);
				break;
			case "Microphone" : 
				newBuilt = new Microphone(currentOrder);
				cart.set(cart.indexOf(currentOrder), newBuilt);
				break;
			case "USB HD camera" : 
				newBuilt = new Camera(currentOrder);
				cart.set(cart.indexOf(currentOrder), newBuilt);
				break;
			case "Ben's Picture" : 
				newBuilt = new Picture(currentOrder);
				cart.set(cart.indexOf(currentOrder), newBuilt);
				break;			
			default :
				newBuilt = currentOrder;
				break;
			}
		}
		
		return newBuilt;
		
	}

	public void viewCart() {

		String strPrint="\nShopping Cart:";
		
		if (!cart.isEmpty()) {

			for (Computer c:cart) 
				strPrint += "\n___________________________\n" + c.getOrderID() + ": " + c.getDescription() + "\nTotal = " + String.format("%.2f", c.getPrice())+"\n";
			
		} else strPrint += " No items\n";
			
		System.out.println(strPrint);
	}
	
	public void viewCurrentOrder(Computer currentOrder) {
		System.out.println("\nCurrent Build: " + cart.get(cart.indexOf(currentOrder)).toString());
	}
	
	
	public int listProducts(Computer currentOrder) {

		int cmd;

		do {

			Map<Integer,String> menu = new HashMap<Integer,String>();
		
			String strPrint = "\nWhat component would ou like to add?\n";
		
			int i = 0;
		
			for (String key : products.keySet()) {
		
				strPrint += ++i + ": " + key + " " + products.get(key)+"\n";
				menu.put(i,key);
		
			}
		
			strPrint += "9: Done";
		
			System.out.println(strPrint);
		
			cmd = selectChoice(9);
		
			if (cmd < 9) {
			
				String component = menu.get(cmd);
				currentOrder = addToCart(currentOrder, component);
				viewCurrentOrder(currentOrder);
		
			} else cmd = 0;
		
		}while (cmd !=0);
		
		return cmd;
	}
	
	public int marketHome() {
		 
		 System.out.println("What would you like to do?\n"
		 		+ "1: Buy a computer\n"
		 		+ "2: See my shopping cart\n"
		 		+ "3: Sort by order ID (Descending order)\n"
		 		+ "4: Sort by order price (Descending order)\n"
		 		+ "5: Quit");
  
		 return selectChoice(5);
		 
	}
	
	public int selectChoice(int max) {
		
		int cmd;
		String userInput;
		 
		 while(true) {

			 userInput = userScan.nextLine();
			 
			 try {
			     cmd = Integer.parseInt(userInput);
			 } catch (NumberFormatException nfe) {
			     cmd = 0;
			 }
			 
			 if(cmd > 0 && cmd <= max) {
				 break;
			 }
			 
			 System.out.println("Invalid entry, try again.");
			 
		 }
		 
		 return cmd;
		
	}
	
	public void sort(SortStrategy sortMethod) {
		if (!cart.isEmpty() && cart.size()>1) sortMethod.sort(cart);
		else System.out.println("\nYou need more items your cart to sort them\n");
	}
	
	
	public static String getProductKey(String component) {
		for (String key : products.keySet()) 
			if (key.contains(component)) return key;	

		return null;
	}
	
	public static Double getProductPrice(String key) {
		return products.get(key);
	}
	
	public static boolean hasSameID(String newID) {

		for(Computer c:cart)
			if (newID == c.getOrderID()) return true;
		
		return false;
	}
	
}
