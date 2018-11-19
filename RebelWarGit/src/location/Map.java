/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package location;

import control.*;

public class Map implements MapInterface {

	private Location[][] map;
	private StormTrooper[] storm;
	private int counter;
	private int row, col;
	@SuppressWarnings("unused")
	private boolean savedPos;

	public Map(char[][] map, int row, int col) {
		this.row = row;
		this.col = col;
		this.map = new Location[row + 1][col + 1];
		storm = new StormTrooper[50];
		counter = 0;
		savedPos = false;
		createMap(map);
	}

	public void addRebel(char caracter, int row, int col) {
		this.map[row][col] = new RebelLocation(row, col);
	}

	public void addBlank(char caracter, int row, int col) {
		this.map[row][col] = new Empty(row, col);
	}

	public void addWeapon(char caracter, int row, int col) {
		this.map[row][col] = new Weapon(row, col);
	}

	public void addPotion(char caracter, int row, int col) {
		this.map[row][col] = new Potion(row, col);
	}

	public void addSuperRebel(char caracter, int row, int col) {
		this.map[row][col] = new SuperRebel(row, col);
	}

	public void addStormTrooper(char caracter, int row, int col) {
		storm[counter] = new StormTrooper(caracter, row, col);
		storm[counter].setPlaced(counter + 1);
		storm[counter].setName("ST-" + caracter + "-" + storm[counter].getPlaced());
		counter++;
	}

	public void addStormTrooperSymbol(int row, int col) {
		if (getStormTrooperPosition(row, col).getType() == StormtrooperWhiteInterface.WHITE) {
			map[row][col] = new StormtrooperWhite(row, col);
		} else if (getStormTrooperPosition(row, col).getType() == StormtrooperBlackInterface.BLACK) {
			map[row][col] = new StormtrooperBlack(row, col);
		} else if (getStormTrooperPosition(row, col).getType() == StormtrooperOrangeInterface.ORANGE) {
			map[row][col] = new StormtrooperOrange(row, col);
		}
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public boolean getSavedPos(int row, int col) {
		if (getStormTrooperPosition(row, col).getSavedPos()) {
			return true;
		} else {
			return false;
		}
	}

	public Location getLocation(int row, int col) {
		while (row > this.row)
			row -= this.row;
		while (col > this.col)
			col -= this.col;
		return map[row][col];
	}

	// pre: hasStormTrooper(row,col)
	public StormTrooper getStormTrooperPosition(int row, int col) {
		StormTrooper storm = null;
		for (int i = 0; i < counter; i++) {
			if (this.storm[i].getCol() == col && this.storm[i].getRow() == row) {
				storm = this.storm[i];
			}
		}
		return storm;
	}

	public StormTrooper getStormTrooper(String name) {
		StormTrooper storm = null;
		for (int i = 0; i < counter; i++) {
			if (this.storm[i].getName().equals(name)) {
				storm = this.storm[i];
			}
		}
		return storm;
	}

	public boolean hasStormTrooper(String name) {

		boolean found = false;
		int i = 0;
		while (i < counter && !found) {

			if ((storm[i].getName().equals(name))) {
				found = true;
			} else {
				i++;
			}
		}
		if (found) {
			return true;
		} else {
			return false;
		}
	}

	public boolean hasStormTrooper(int row, int col) {
		if (getLocation(row, col).symbol() == StormtrooperWhiteInterface.WHITE
				|| getLocation(row, col).symbol() == StormtrooperBlackInterface.BLACK
				|| getLocation(row, col).symbol() == StormtrooperOrangeInterface.ORANGE) {
			return true;
		} else {
			return false;
		}
	}

	public void moveRight(int row, int col) {
		if (getStormTrooperPosition(row, col).getSavedPos()) {
			addWeapon(WeaponInterface.WEAPON, row, col);
			getStormTrooperPosition(row, col).setSavedPos(false);
		} else if (getStormTrooperPosition(row, col).getSavedPotion()) {
			addPotion(PotionInterface.POTION, row, col);
		} else {
			addBlank(EmptyInterface.EMPTY, row, col);
		}
		if (getLocation(row, col + 1).symbol() == WeaponInterface.WEAPON) {
			getStormTrooperPosition(row, col).setSavedPos(true);

		} else if (getLocation(row, col + 1).symbol() == PotionInterface.POTION) {
			getStormTrooperPosition(row, col).setSavedPotion(true);
		}

		if (getLocation(row, col + 1).symbol() == SuperRebelInterface.SUPERREBEL) {
			getStormTrooperPosition(row, col).setState("CAPTURED");
			getStormTrooperPosition(row, col).setCol(col + 1);
			addSuperRebel(SuperRebelInterface.SUPERREBEL, row, col + 1);

		} else {
			if (getStormTrooperPosition(row, col).getType() == StormtrooperWhiteInterface.WHITE) {
				map[row][col + 1] = new StormtrooperWhite(row, col + 1);
			} else if (getStormTrooperPosition(row, col).getType() == StormtrooperBlackInterface.BLACK) {
				map[row][col + 1] = new StormtrooperBlack(row, col + 1);
			} else if (getStormTrooperPosition(row, col).getType() == StormtrooperOrangeInterface.ORANGE) {
				map[row][col + 1] = new StormtrooperOrange(row, col + 1);
			}
			getStormTrooperPosition(row, col).setCol(col + 1);
		}
	}

	public void moveLeft(int row, int col) {
		if (getStormTrooperPosition(row, col).getSavedPos()) {
			addWeapon(WeaponInterface.WEAPON, row, col);
			getStormTrooperPosition(row, col).setSavedPos(false);
		} else if (getStormTrooperPosition(row, col).getSavedPotion()) {
			addPotion(PotionInterface.POTION, row, col);
		} else {
			addBlank(EmptyInterface.EMPTY, row, col);
		}
		if (getLocation(row, col - 1).symbol() == WeaponInterface.WEAPON) {
			getStormTrooperPosition(row, col).setSavedPos(true);

		} else if (getLocation(row, col - 1).symbol() == PotionInterface.POTION) {
			getStormTrooperPosition(row, col).setSavedPotion(true);
		}

		if (getLocation(row, col - 1).symbol() == SuperRebelInterface.SUPERREBEL) {
			getStormTrooperPosition(row, col).setState("CAPTURED");
			getStormTrooperPosition(row, col).setCol(col - 1);
			addSuperRebel(SuperRebelInterface.SUPERREBEL, row, col - 1);

		} else {
			if (getStormTrooperPosition(row, col).getType() == StormtrooperWhiteInterface.WHITE) {
				this.map[row][col - 1] = new StormtrooperWhite(row, col - 1);
			} else if (getStormTrooperPosition(row, col).getType() == StormtrooperBlackInterface.BLACK) {
				this.map[row][col - 1] = new StormtrooperBlack(row, col - 1);
			} else if (getStormTrooperPosition(row, col).getType() == StormtrooperOrangeInterface.ORANGE) {
				this.map[row][col - 1] = new StormtrooperOrange(row, col - 1);
			}
			getStormTrooperPosition(row, col).setCol(col - 1);
		}
	}

	public void moveUp(int row, int col) {
		if (getStormTrooperPosition(row, col).getSavedPos()) {
			addWeapon(WeaponInterface.WEAPON, row, col);
			getStormTrooperPosition(row, col).setSavedPos(false);
		} else if (getStormTrooperPosition(row, col).getSavedPotion()) {
			addPotion(PotionInterface.POTION, row, col);
		} else {
			addBlank(EmptyInterface.EMPTY, row, col);
		}
		if (getLocation(row - 1, col).symbol() == WeaponInterface.WEAPON) {
			getStormTrooperPosition(row, col).setSavedPos(true);

		} else if (getLocation(row - 1, col).symbol() == PotionInterface.POTION) {
			getStormTrooperPosition(row, col).setSavedPotion(true);
		}

		if (getLocation(row - 1, col).symbol() == SuperRebelInterface.SUPERREBEL) {
			getStormTrooperPosition(row, col).setState("CAPTURED");
			getStormTrooperPosition(row, col).setRow(row - 1);
			addSuperRebel(SuperRebelInterface.SUPERREBEL, row - 1, col);

		} else {
			if (getStormTrooperPosition(row, col).getType() == StormtrooperWhiteInterface.WHITE) {
				this.map[row - 1][col] = new StormtrooperWhite(row - 1, col);
			} else if (getStormTrooperPosition(row, col).getType() == StormtrooperBlackInterface.BLACK) {
				this.map[row - 1][col] = new StormtrooperBlack(row - 1, col);
			} else if (getStormTrooperPosition(row, col).getType() == StormtrooperOrangeInterface.ORANGE) {
				this.map[row - 1][col] = new StormtrooperOrange(row - 1, col);
			}
			getStormTrooperPosition(row, col).setRow(row - 1);
		}
	}

	public void moveDown(int row, int col) {
		if (getStormTrooperPosition(row, col).getSavedPos()) {
			addWeapon(WeaponInterface.WEAPON, row, col);
			getStormTrooperPosition(row, col).setSavedPos(false);
		} else if (getStormTrooperPosition(row, col).getSavedPotion()) {
			addPotion(PotionInterface.POTION, row, col);
		} else {
			addBlank(EmptyInterface.EMPTY, row, col);
		}
		if (getLocation(row + 1, col).symbol() == WeaponInterface.WEAPON) {
			getStormTrooperPosition(row, col).setSavedPos(true);

		} else if (getLocation(row + 1, col).symbol() == PotionInterface.POTION) {
			getStormTrooperPosition(row, col).setSavedPotion(true);
		}

		if (getLocation(row + 1, col).symbol() == SuperRebelInterface.SUPERREBEL) {
			getStormTrooperPosition(row, col).setState("CAPTURED");
			getStormTrooperPosition(row, col).setRow(row + 1);
			addSuperRebel(SuperRebelInterface.SUPERREBEL, row + 1, col);

		} else {
			if (getStormTrooperPosition(row, col).getType() == StormtrooperWhiteInterface.WHITE) {
				this.map[row + 1][col] = new StormtrooperWhite(row + 1, col);
			} else if (getStormTrooperPosition(row, col).getType() == StormtrooperBlackInterface.BLACK) {
				this.map[row + 1][col] = new StormtrooperBlack(row + 1, col);
			} else if (getStormTrooperPosition(row, col).getType() == StormtrooperOrangeInterface.ORANGE) {
				this.map[row + 1][col] = new StormtrooperOrange(row + 1, col);
			}
			getStormTrooperPosition(row, col).setRow(row + 1);
		}
	}

	public StormTrooperIteratorInterface listarStormTroopers() {
		return new StormTrooperIterator(storm, counter);
	}

	public MapIteratorInterface listarMap() {
		return new MapIterator(map, row, col);
	}

	/**
	 * Metodo auxilir que constroi o mapa de objectos Location.
	 * 
	 * @param map
	 */
	private void createMap(char[][] map) {
		for (int r = 1; r <= row; r++)
			for (int c = 1; c <= col; c++)
				switch (map[r][c]) {
				case WeaponInterface.WEAPON:
					this.map[r][c] = new Weapon(r, c);
					break;
				case WallInterface.WALL:
					this.map[r][c] = new Wall(r, c);
					break;
				case StormtrooperWhiteInterface.WHITE:
					this.map[r][c] = new StormtrooperWhite(r, c);
					addStormTrooper(StormtrooperWhiteInterface.WHITE, r, c);
					break;
				case StormtrooperBlackInterface.BLACK:
					this.map[r][c] = new StormtrooperBlack(r, c);
					addStormTrooper(StormtrooperBlackInterface.BLACK, r, c);
					break;
				case StormtrooperOrangeInterface.ORANGE:
					this.map[r][c] = new StormtrooperOrange(r, c);
					addStormTrooper(StormtrooperOrangeInterface.ORANGE, r, c);
					break;
				case EmptyInterface.EMPTY:
					this.map[r][c] = new Empty(r, c);
					break;
				case PotionInterface.POTION:
					addPotion(PotionInterface.POTION, r, c);
					break;
				default:
				}
	}
}
