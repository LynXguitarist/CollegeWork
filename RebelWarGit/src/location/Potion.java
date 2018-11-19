/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package location;

public class Potion extends Location implements PotionInterface {
	
	public Potion(int row, int col) {
		super(row, col);

	}

	public char symbol() {
		return PotionInterface.POTION;
	}

}
