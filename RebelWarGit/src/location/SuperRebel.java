/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package location;

public class SuperRebel extends Location implements SuperRebelInterface {
	public SuperRebel(int row, int col) {
		super(row, col);

	}

	public char symbol() {
		return SuperRebelInterface.SUPERREBEL;
	}
}
