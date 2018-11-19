/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package control;

public class PathIterator implements PathIteratorInterface{

	private String[] path;
	private int counter;
	private int current;

	public PathIterator(String[] path, int counter) {
		this.path = path;
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
	public String next() {
		return path[current++];
	}

}
