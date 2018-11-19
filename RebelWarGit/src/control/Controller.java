/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package control;

import location.*;

public class Controller implements ControllerInterface {
	private Rebel[] rebel;
	private int counter;
	private int points;
	private int timer;
	private String state;
	private boolean playing;
	private static final int DEFAULT_SIZE = 500;
	private Map map;

	public Controller() {
		map = null;
		rebel = new Rebel[DEFAULT_SIZE];
		counter = 0;
		points = 0;
		timer = 0;
		state = "OFF.";
		playing = false;
	}

	public void addRebel(String name, int row, int col) {
		rebel[counter] = new Rebel(name, row, col);
		rebel[counter].setPath(row, col);
		map.addRebel(RebelLocationInterface.REBEL, row, col);
		counter++;

	}

	public int getPoints() {
		return points;

	}

	public void addPoints() {
		points = 0;
		for (int i = 0; i < counter; i++) {
			points = points + rebel[i].getPoints();

		}
	}

	public boolean playing() {
		return playing;
	}

	public void setPlaying() {
		playing = true;
	}

	public String getState() {
		return state;
	}

	public void setStateON() {
		state = "ON.";
	}

	public void setStateOFF() {
		state = "OVER.";
	}

	public int getCol() {
		return map.getCol();
	}

	public int getRow() {
		return map.getRow();
	}

	public int getTimer() {
		return timer;
	}

	public void incTimer() {
		timer = timer + 1;
	}

	public boolean hasRebel(String name) {

		boolean found = false;
		int i = 0;
		while (i < counter && !found) {

			if ((rebel[i].getName().equals(name))) {
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

	public boolean hasRebelThere(int row, int col) {
		boolean found = false;
		int i = 0;
		while (i < counter && !found) {

			if ((rebel[i].getRow() == row) && (rebel[i].getCol() == col)) {
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

	public int numberOfRebels() {
		int contador = 0;
		for (int i = 0; i < counter; i++) {
			if (rebel[i].getState().equals("ACTIVE") || rebel[i].getState().equals("SUPERCHARGED")) {
				contador++;
			}
		}
		return contador;
	}

	// pre: hasRebel(name)
	public Rebel getRebel(String name) {
		Rebel rebel = null;
		for (int i = 0; i < counter; i++) {
			if (this.rebel[i].getName().equals(name)) {
				rebel = this.rebel[i];
			}
		}
		return rebel;
	}

	// pre: hasRebelThere(row,col)
	public Rebel getRebelPosition(int row, int col) {
		Rebel rebel = null;
		for (int i = 0; i < counter; i++) {
			if (this.rebel[i].getCol() == col && this.rebel[i].getRow() == row) {
				rebel = this.rebel[i];
			}
		}
		return rebel;
	}

	public boolean hasStormTrooper(String name) {
		return map.hasStormTrooper(name);
	}

	public boolean hasMap() {
		return map != null;
	}

	public void setMap(char[][] map, int row, int col) {
		this.map = new Map(map, row, col);
	}

	public boolean isLocationTaken(int row, int col) {

		if (col > map.getCol() || col < 1 || row > map.getRow() || row < 1 || (row > map.getRow() || col > map.getCol())
				|| map.getLocation(row, col).symbol() == WallInterface.WALL
				|| map.getLocation(row, col).symbol() == StormtrooperBlackInterface.BLACK
				|| map.getLocation(row, col).symbol() == StormtrooperWhiteInterface.WHITE
				|| map.getLocation(row, col).symbol() == StormtrooperOrangeInterface.ORANGE) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isLocationStorm(int row, int col) {
		if (map.getLocation(row, col).symbol() == StormtrooperBlackInterface.BLACK
				|| map.getLocation(row, col).symbol() == StormtrooperWhiteInterface.WHITE
				|| map.getLocation(row, col).symbol() == StormtrooperOrangeInterface.ORANGE) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isLocationImpossible(int row, int col) {
		if (map.getLocation(row, col).symbol() == WallInterface.WALL) {
			return true;
		} else {
			return false;
		}
	}

	public StormTrooper getStormTrooper(String name) {
		return map.getStormTrooper(name);
	}

	public void moveRight(int row, int col) {

		if (map.getLocation(row, col + 1).symbol() == PotionInterface.POTION) {
			getRebelPosition(row, col).setState("SUPERCHARGED");
			getRebelPosition(row, col).setCol(col + 1);
			getRebelPosition(row, col + 1).setPath(row, col + 1);
			map.addBlank(EmptyInterface.EMPTY, row, col);
			map.addSuperRebel(SuperRebelInterface.SUPERREBEL, row, col + 1);
			getRebelPosition(row, col + 1).setTimer();
		} else if (getRebelPosition(row, col).getState().equals("SUPERCHARGED")
				&& getRebelPosition(row, col).getTimer() > 0) {
			if (map.hasStormTrooper(row, col + 1)) {
				map.getStormTrooperPosition(row, col + 1).setState("CAPTURED");
				if (map.getSavedPos(row, col + 1)) {
					getRebelPosition(row, col).addPoints();
				}
			}
			getRebelPosition(row, col).setCol(col + 1);
			getRebelPosition(row, col + 1).setPath(row, col + 1);
			map.addBlank(EmptyInterface.EMPTY, row, col);
			map.addSuperRebel(SuperRebelInterface.SUPERREBEL, row, col + 1);
			getRebelPosition(row, col + 1).decTimer();
			if (getRebelPosition(row, col + 1).getTimer() == 0) {
				getRebelPosition(row, col + 1).setState("ACTIVE");
			}
		} else {
			getRebelPosition(row, col).setCol(col + 1);
			getRebelPosition(row, col + 1).setPath(row, col + 1);
			map.addRebel(RebelLocationInterface.REBEL, row, col + 1);
			map.addBlank(EmptyInterface.EMPTY, row, col);
		}
	}

	public void moveLeft(int row, int col) {

		if (map.getLocation(row, col - 1).symbol() == PotionInterface.POTION) {
			getRebelPosition(row, col).setState("SUPERCHARGED");
			getRebelPosition(row, col).setCol(col - 1);
			getRebelPosition(row, col - 1).setPath(row, col - 1);
			map.addBlank(EmptyInterface.EMPTY, row, col);
			map.addSuperRebel(SuperRebelInterface.SUPERREBEL, row, col - 1);
			getRebelPosition(row, col - 1).setTimer();
		} else if (getRebelPosition(row, col).getState().equals("SUPERCHARGED")
				&& getRebelPosition(row, col).getTimer() > 0) {
			if (map.hasStormTrooper(row, col - 1)) {
				map.getStormTrooperPosition(row, col - 1).setState("CAPTURED");
				if (map.getSavedPos(row, col - 1)) {
					getRebelPosition(row, col).addPoints();
				}
			}
			getRebelPosition(row, col).setCol(col - 1);
			getRebelPosition(row, col - 1).setPath(row, col - 1);
			map.addBlank(EmptyInterface.EMPTY, row, col);
			map.addSuperRebel(SuperRebelInterface.SUPERREBEL, row, col - 1);
			getRebelPosition(row, col - 1).decTimer();
			if (getRebelPosition(row, col - 1).getTimer() == 0) {
				getRebelPosition(row, col - 1).setState("ACTIVE");
			}
		} else {

			getRebelPosition(row, col).setCol(col - 1);
			getRebelPosition(row, col - 1).setPath(row, col - 1);
			map.addRebel(RebelLocationInterface.REBEL, row, col - 1);
			map.addBlank(EmptyInterface.EMPTY, row, col);
		}
	}

	public void moveDown(int row, int col) {

		if (map.getLocation(row + 1, col).symbol() == PotionInterface.POTION) {
			getRebelPosition(row, col).setState("SUPERCHARGED");
			getRebelPosition(row, col).setRow(row + 1);
			getRebelPosition(row + 1, col).setPath(row + 1, col);
			map.addBlank(EmptyInterface.EMPTY, row, col);
			map.addSuperRebel(SuperRebelInterface.SUPERREBEL, row + 1, col);
			getRebelPosition(row + 1, col).setTimer();
		} else if (getRebelPosition(row, col).getState().equals("SUPERCHARGED")
				&& getRebelPosition(row, col).getTimer() > 0) {
			if (map.hasStormTrooper(row + 1, col)) {
				map.getStormTrooperPosition(row + 1, col).setState("CAPTURED");
				if (map.getSavedPos(row + 1, col)) {
					getRebelPosition(row, col).addPoints();
				}
			}
			getRebelPosition(row, col).setRow(row + 1);
			getRebelPosition(row + 1, col).setPath(row + 1, col);
			map.addBlank(EmptyInterface.EMPTY, row, col);
			map.addSuperRebel(SuperRebelInterface.SUPERREBEL, row + 1, col);
			getRebelPosition(row + 1, col).decTimer();
			if (getRebelPosition(row + 1, col).getTimer() == 0) {
				getRebelPosition(row + 1, col).setState("ACTIVE");
			}
		} else {
			getRebelPosition(row, col).setRow(row + 1);
			getRebelPosition(row + 1, col).setPath(row + 1, col);
			map.addRebel(RebelLocationInterface.REBEL, row + 1, col);
			map.addBlank(EmptyInterface.EMPTY, row, col);
		}
	}

	public void moveUp(int row, int col) {

		if (map.getLocation(row - 1, col).symbol() == PotionInterface.POTION) {
			getRebelPosition(row, col).setState("SUPERCHARGED");
			getRebelPosition(row, col).setRow(row - 1);
			getRebelPosition(row - 1, col).setPath(row - 1, col);
			map.addBlank(EmptyInterface.EMPTY, row, col);
			map.addSuperRebel(SuperRebelInterface.SUPERREBEL, row - 1, col);
			getRebelPosition(row - 1, col).setTimer();
		} else if (getRebelPosition(row, col).getState().equals("SUPERCHARGED")
				&& getRebelPosition(row, col).getTimer() > 0) {
			if (map.hasStormTrooper(row - 1, col)) {
				map.getStormTrooperPosition(row - 1, col).setState("CAPTURED");
				if (map.getSavedPos(row - 1, col)) {
					getRebelPosition(row, col).addPoints();
				}
			}
			getRebelPosition(row, col).setRow(row - 1);
			getRebelPosition(row - 1, col).setPath(row - 1, col);
			map.addBlank(EmptyInterface.EMPTY, row, col);
			map.addSuperRebel(SuperRebelInterface.SUPERREBEL, row - 1, col);
			getRebelPosition(row - 1, col).decTimer();
			if (getRebelPosition(row - 1, col).getTimer() == 0) {
				getRebelPosition(row - 1, col).setState("ACTIVE");
			}
		} else {
			getRebelPosition(row, col).setRow(row - 1);
			getRebelPosition(row - 1, col).setPath(row - 1, col);
			map.addRebel(RebelLocationInterface.REBEL, row - 1, col);
			map.addBlank(EmptyInterface.EMPTY, row, col);
		}
	}

	public void moveRightStorm(int row, int col) {

		if (map.getLocation(row, col + 1).symbol() == RebelLocationInterface.REBEL) {
			getRebelPosition(row, col + 1).setState("CAPTURED");
			map.getStormTrooperPosition(row, col).addCaptured(getRebelPosition(row, col + 1).getName(), row, col + 1);
		} else if (map.getLocation(row, col).symbol() == RebelLocationInterface.REBEL) {
			getRebelPosition(row, col).setState("CAPTURED");
			map.getStormTrooperPosition(row, col).addCaptured(getRebelPosition(row, col).getName(), row, col);
		}
		map.moveRight(row, col);

	}

	public void moveLeftStorm(int row, int col) {

		if (map.getLocation(row, col - 1).symbol() == RebelLocationInterface.REBEL) {
			getRebelPosition(row, col - 1).setState("CAPTURED");
			map.getStormTrooperPosition(row, col).addCaptured(getRebelPosition(row, col - 1).getName(), row, col - 1);
		} else if (map.getLocation(row, col).symbol() == RebelLocationInterface.REBEL) {
			getRebelPosition(row, col).setState("CAPTURED");
			map.getStormTrooperPosition(row, col).addCaptured(getRebelPosition(row, col).getName(), row, col);
		}

		map.moveLeft(row, col);

	}

	public void moveUpStorm(int row, int col) {

		if (map.getLocation(row - 1, col).symbol() == RebelLocationInterface.REBEL) {
			getRebelPosition(row - 1, col).setState("CAPTURED");
			map.getStormTrooperPosition(row, col).addCaptured(getRebelPosition(row - 1, col).getName(), row - 1, col);
		} else if (map.getLocation(row, col).symbol() == RebelLocationInterface.REBEL) {
			getRebelPosition(row, col).setState("CAPTURED");
			map.getStormTrooperPosition(row, col).addCaptured(getRebelPosition(row, col).getName(), row, col);
		}
		map.moveUp(row, col);

	}

	public void moveDownStorm(int row, int col) {

		if (map.getLocation(row + 1, col).symbol() == RebelLocationInterface.REBEL) {
			getRebelPosition(row + 1, col).setState("CAPTURED");
			map.getStormTrooperPosition(row, col).addCaptured(getRebelPosition(row + 1, col).getName(), row + 1, col);
		} else if (map.getLocation(row, col).symbol() == RebelLocationInterface.REBEL) {
			getRebelPosition(row, col).setState("CAPTURED");
			map.getStormTrooperPosition(row, col).addCaptured(getRebelPosition(row, col).getName(), row, col);
		}
		map.moveDown(row, col);

	}

	public void moveOrder(int row, int col) {
		if (map.getStormTrooperPosition(row, col).getType() == StormtrooperBlackInterface.BLACK) {
			if (map.getStormTrooperPosition(row, col).getOrder() == 0) {
				if (isLocationTaken(row - 1, col)) {
					map.getStormTrooperPosition(row, col).incOrder();
					moveOrder(row, col);
				} else {
					moveUpStorm(row, col);
				}
			} else if (map.getStormTrooperPosition(row, col).getOrder() == 1) {
				if (isLocationTaken(row + 1, col)) {
					map.getStormTrooperPosition(row, col).incOrder();
					moveOrder(row, col);
				} else {
					moveDownStorm(row, col);
				}
			} else if (map.getStormTrooperPosition(row, col).getOrder() == 2) {
				if (isLocationTaken(row, col - 1)) {
					map.getStormTrooperPosition(row, col).incOrder();
					moveOrder(row, col);
				} else {
					moveLeftStorm(row, col);
				}
			} else if (map.getStormTrooperPosition(row, col).getOrder() == 3) {
				if (isLocationTaken(row, col + 1)) {
					map.getStormTrooperPosition(row, col).resetOrder();
					moveOrder(row, col);
				} else {
					moveRightStorm(row, col);
				}
			}
		} else if (map.getStormTrooperPosition(row, col).getType() == StormtrooperOrangeInterface.ORANGE) {
			if (map.getStormTrooperPosition(row, col).getOrder() == 3) {

				if (isLocationTaken(row - 1, col)) {
					map.getStormTrooperPosition(row, col).resetOrder();
					moveOrder(row, col);
				} else {
					moveUpStorm(row, col);
				}
			} else if (map.getStormTrooperPosition(row, col).getOrder() == 1) {
				if (isLocationTaken(row + 1, col)) {
					map.getStormTrooperPosition(row, col).incOrder();
					moveOrder(row, col);
				} else {

					moveDownStorm(row, col);
				}
			} else if (map.getStormTrooperPosition(row, col).getOrder() == 2) {

				if (isLocationTaken(row, col - 1)) {
					map.getStormTrooperPosition(row, col).incOrder();
					moveOrder(row, col);
				} else {
					moveLeftStorm(row, col);
				}
			} else if (map.getStormTrooperPosition(row, col).getOrder() == 0) {

				if (isLocationTaken(row, col + 1)) {
					map.getStormTrooperPosition(row, col).incOrder();
					moveOrder(row, col);
				} else {
					moveRightStorm(row, col);
				}
			}
		} else if (map.getStormTrooperPosition(row, col).getType() == StormtrooperWhiteInterface.WHITE) {
			if (map.getStormTrooperPosition(row, col).getOrder() == 3) {

				if (isLocationTaken(row - 1, col)) {
					map.getStormTrooperPosition(row, col).resetOrder();
					moveOrder(row, col);
				} else {
					moveUpStorm(row, col);
				}
			} else if (map.getStormTrooperPosition(row, col).getOrder() == 2) {
				if (isLocationTaken(row + 1, col)) {
					map.getStormTrooperPosition(row, col).incOrder();
					moveOrder(row, col);
				} else {
					moveDownStorm(row, col);
				}
			} else if (map.getStormTrooperPosition(row, col).getOrder() == 0) {
				if (isLocationTaken(row, col - 1)) {
					map.getStormTrooperPosition(row, col).incOrder();
					moveOrder(row, col);
				} else {
					moveLeftStorm(row, col);
				}
			} else if (map.getStormTrooperPosition(row, col).getOrder() == 1) {

				if (isLocationTaken(row, col + 1)) {
					map.getStormTrooperPosition(row, col).incOrder();
					moveOrder(row, col);
				} else {
					moveRightStorm(row, col);
				}
			}
		}
	}

	public void reset() {
		map = null;
		rebel = new Rebel[DEFAULT_SIZE];
		counter = 0;
		points = 0;
		timer = 0;
		state = "OVER.";
		playing = false;
	}

	public MapIteratorInterface listarMap() {
		return map.listarMap();

	}

	public RebelIteratorInterface listarRebels() {
		return new RebelIterator(rebel, counter);
	}

	public StormTrooperIteratorInterface listarStormTroopers() {
		return map.listarStormTroopers();
	}

	public CaptureIteratorInterface listCaptured(String name) {
		return map.getStormTrooper(name).listCaptured();
	}

}
