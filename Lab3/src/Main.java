import java.util.ArrayList;

/**
 * 
 */

/**
 * @author glavin
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int n = 121;
		int workers = 8;
		
		final SieveManager m = new SieveManager(n, workers);
		//
		boolean successfullyStarted = m.getPrimes(new Runnable() {
		    public void run() {
		        // stuff here
				System.out.println("Results:");
				ArrayList<Marker> list = m.getList();
				for (int i=2, len=list.size(); i<len; i++)
				{
					Marker curr = list.get(i); 
					if (!curr.isMarked())
					{
						System.out.print(", "+i);
					}
				}
		    }
		});

		if (successfullyStarted) 
		{
			System.out.println("Running");
		}
	}

}
