import java.util.ArrayList;

/**
 * @author Glavin Wiechert
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Variables
		int n = 121;
		int workers = 8;
		
		// Initialize
		final SieveManager m = new SieveManager(n, workers);
		// Start!
		System.out.println("Start!");
		boolean successfullyStarted = m.calcPrimes(new Runnable() {
		    public void run() {
		        // stuff here
				System.out.println("Results:");
				ArrayList<Marker> list = m.getList();
				for (int i=2, len=list.size(); i<len; i++)
				{
					Marker curr = list.get(i); 
					if (!curr.isMarked())
					{
						System.out.print(i+", ");
					}
				}
		    }
		});
		
	}
}
