/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package location;

public abstract class Location implements LocationInterface {

	private int row, col;

	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public abstract char symbol();

}
