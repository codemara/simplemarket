import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByPrice implements SortStrategy {

	@Override
	public void sort(List<Computer> cart) {
		
		Collections.sort(cart, new Comparator<Computer>() {
            @Override
            public int compare(Computer c1, Computer c2) {
                int comp =(int)(c1.getPrice() - c2.getPrice());
                return comp;
            }
        });
		
		Collections.reverse(cart);
		
		System.out.println("The Shopping cart has been sorted by price\n");
	}
}
