import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class SortByOrderID implements SortStrategy{

	@Override
	public void sort(List<Computer> cart) {
		
		Collections.sort(cart, new Comparator<Computer>() {
            @Override
            public int compare(Computer c1, Computer c2) {
                return c1.getOrderID().compareTo(c2.getOrderID());              
            }
        });
		
		Collections.reverse(cart);

		System.out.println("The Shopping cart has been sorted by order ID\n");
	}
}
