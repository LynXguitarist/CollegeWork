/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package location;

public class Weapon extends Location implements WeaponInterface {

	public Weapon(int row, int col) {
		super(row, col);

	}

	public char symbol() {
		return WeaponInterface.WEAPON;
	}
}
