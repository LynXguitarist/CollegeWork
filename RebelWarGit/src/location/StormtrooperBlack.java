/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package location;

public class StormtrooperBlack extends Location implements StormtrooperBlackInterface{

	public StormtrooperBlack(int row, int col) {
		super(row, col);

	}

	public char symbol() {
		return StormtrooperBlackInterface.BLACK;
	}
}
