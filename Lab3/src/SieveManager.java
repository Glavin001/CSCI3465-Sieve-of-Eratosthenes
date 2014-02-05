import java.util.ArrayList;

/*
 * Description from: http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes 
 To find all the prime numbers less than or equal to a given integer n by Eratosthenes' method:
- Create a list of consecutive integers from 2 through n: (2, 3, 4, ..., n).
- Initially, let p equal 2, the first prime number.
- Starting from p, enumerate its multiples by counting to n in increments of p, and mark them in the list (these will be 2p, 3p, 4p, etc.; the p itself should not be marked).
- Find the first number greater than p in the list that is not marked. If there was no such number, stop. Otherwise, let p now equal this new number (which is the next prime), and repeat from step 3.
- When the algorithm terminates, all the numbers in the list that are not marked are prime.
 */

/**
 * @author Glavin Wiechert
 *
 */
public class SieveManager 
{

	private ArrayList<Marker> list;
	private int numberOfWorkers;
	private int n;
	private Integer p;
	private Runnable callback;
	
	/**
	 * Constructor.
	 * @param n
	 * @param 
	 */
	public SieveManager(int n, int workers) 
	{
		// Init
		initList(n);
		numberOfWorkers = workers;
	}
	
	/**
	 * 
	 * @param n
	 */
	private void initList(int n)
	{
		list = new ArrayList<Marker>();
		for (int i=0; i<=n; i++)
		{
			list.add(new Marker());
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Marker> getList()
	{
		return list;
	}
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public boolean calcPrimes(Runnable c) 
	{
		// Set Completion Callback
		callback = c;
		// Start at p=2
		p = 2;
		// Create Workers
		for (int i=0; i<numberOfWorkers; i++) {
			Thread t = new Thread(new SieveWorker(this));
			t.start();
		}
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public synchronized int getNextPrime() 
	{
		// Check if *already* completed.
		if (p == 0)
		{
			// Completed.
			return p;
		}
		// Not yet completed.
		int next = 0;
		for (int i=(p+1); i<list.size(); i++) 
		{
			// Check if marked
			Marker curr = list.get(i);
			if (!curr.isMarked()) {
				next = i;
				break;
			}
		}
		// Set the current, p, to the next prime.
		p = next;
		// Check if no next prime was found 
		if (p == 0)
		{
			// First time completed.
			broadcastCompletition();
		}
		// return next prime (current p).
		return p;
	}
	
	public boolean markItem(int idx)
	{
		//System.out.println("mark "+idx);
		if (idx < list.size())
		{
			Marker m = list.get(idx);
			m.mark();
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	private void broadcastCompletition()
	{
		Thread t = new Thread(callback);
		t.start();
	}

}
