/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package location;

public class StormtrooperWhite extends Location implements StormtrooperWhiteInterface {

	public StormtrooperWhite(int row, int col) {
		super(row, col);

	}

	public char symbol() {
		return StormtrooperWhiteInterface.WHITE;
	}
}
