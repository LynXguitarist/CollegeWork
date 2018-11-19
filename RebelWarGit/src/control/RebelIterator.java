/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package control;

public class RebelIterator implements RebelIteratorInterface {
	private Rebel[] rebel;
	private int counter;
	private int current;
	

	public RebelIterator(Rebel[] rebel, int counter) {
		this.rebel = rebel;
		this.counter = counter;
		current = -1;
		init();
	}

	public void init() {
		current = 0;
	}

	public boolean hasNext() {
		return (current >= 0) && (current < counter);
	}

	// pre: hasNext()
	public Rebel next() {
		return rebel[current++];
	}

	public Rebel getCurrent() {
		return rebel[current];
	}
}
