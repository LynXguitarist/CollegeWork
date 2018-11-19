/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package location;

public class StormtrooperOrange extends Location implements StormtrooperOrangeInterface{

	public StormtrooperOrange(int row, int col) {
		super(row, col);

	}

	public char symbol() {
		return StormtrooperOrangeInterface.ORANGE;
	}
}
