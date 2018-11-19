/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package location;

public class RebelLocation extends Location implements RebelLocationInterface {

	public RebelLocation(int row, int col) {
		super(row, col);

	}

	public char symbol() {

		return RebelLocationInterface.REBEL;
	}

}
