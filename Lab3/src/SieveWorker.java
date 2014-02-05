/**
 * 
 */

/**
 * @author glavin
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
		int p = manager.getNextPrime();
		while (p != 0)
		{
			int n = 1;
			do
			{
				n++;
			} while ( manager.markItem(p*n) );
			
			// Get next
			p = manager.getNextPrime();
		}
	}
	
}
