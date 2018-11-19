/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package control;

public class CaptureIterator implements CaptureIteratorInterface {

	private Rebel[] capture;
	private int counter;
	private int current;

	public CaptureIterator(Rebel[] capture, int counter) {
		this.capture = capture;
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
		return capture[current++];
	}

	public Rebel getCurrent() {
		return capture[current];
	}
}
