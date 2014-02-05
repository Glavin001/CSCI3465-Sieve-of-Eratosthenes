/**
 * 
 */

/**
 * @author Glavin Wiechert
 *
 */
public class SieveWorker implements Runnable {

	private SieveManager manager;
	
	public SieveWorker(SieveManager m)
	{	
		manager = m;
	}
	
	@Override
	public void run() {
		//System.out.println("Starting working");
		
		int p = manager.getNextPrime();
		//System.out.println("First prime: "+p);
		while (p != 0)
		{
			
			int n = 1;
			do
			{
				n++;
			} while ( manager.markItem(p*n) );
			
			// Get next
			p = manager.getNextPrime();
			//System.out.println("Next prime: "+p);
			
		}
		//System.out.println("Done.");
		
	}
	
}
