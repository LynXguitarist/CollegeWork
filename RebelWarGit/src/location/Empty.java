/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package location;

public class Empty extends Location implements EmptyInterface {

	public Empty(int row, int col) {
		super(row, col);

	}

	public char symbol() {
		return EmptyInterface.EMPTY;
	}
}
