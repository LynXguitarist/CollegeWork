/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package location;

public class Wall extends Location implements WallInterface {
	public Wall(int row, int col) {
		super(row, col);

	}

	public char symbol() {
		return WallInterface.WALL;
	}
}
