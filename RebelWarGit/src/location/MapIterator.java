/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package location;

public class MapIterator implements MapIteratorInterface {

	private Location[][] map;
	private int row;
	private int col;
	private int currentR, currentC;

	public MapIterator(Location[][] map, int row, int col) {
		this.map = map;
		this.row = row + 1;
		this.col = col + 1;
		currentR = 0;
		currentC = 0;
		init();
	}

	public void init() {
		currentR = 1;
		currentC = 1;
	}

	public boolean hasNext() {
		if (currentC == col) {
			currentR++;
			currentC = 1;
		}
		return currentC < col && currentR < row;
	}

	// Pre: hasNext()
	public Location next() {
		return map[currentR][currentC++];
	}

	public Location getCurrent(int row, int col) {
		return map[row][col];
	}

	public boolean searchNext(char symbol) {

		boolean found = false;
		while (currentR < row && currentC < col && !found) {

			if ((map[currentR][currentC].symbol() == symbol)) {
				found = true;
			} else {
				currentC++;
			}
			if (currentC == col && currentR != row) {
				currentC = 1;
				currentR++;
			}

		}
		if (found) {
			return true;
		} else {
			return false;
		}
	}

}
