/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package control;

public class StormTrooperIterator implements StormTrooperIteratorInterface {
	private StormTrooper[] storm;
	private int counter;
	private int current;

	public StormTrooperIterator(StormTrooper[] storm, int counter) {
		this.storm = storm;
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
	public StormTrooper next() {
		return storm[current++];
	}

	public StormTrooper getCurrent() {
		return storm[current];
	}
}
