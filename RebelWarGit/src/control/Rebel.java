/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package control;

public class Rebel implements RebelInterface {

	// array capturas
	private String[] path;
	private int counter;
	private String name, state;
	private String posCaptured;
	private int row, col;
	private int points;
	private int timer;
	private static final int DEFAULT_SIZE = 50;

	public Rebel(String name, int row, int col) {
		path = new String[DEFAULT_SIZE];
		counter = 0;
		timer = 0;
		this.name = name;
		state = "ACTIVE";
		this.row = row;
		this.col = col;
		points = 0;
	}

	public String getName() {
		return name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints() {
		points = points + 10;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer() {
		timer = 4;
	}

	public void decTimer() {
		timer = timer - 1;
	}

	public int getSteps() {
		return counter;
	}

	public void setPosCaptured(int row, int col) {
		posCaptured = row + " " + col;
	}

	public String getPosCaptured() {
		return posCaptured;
	}

	public void setPath(int row, int col) {
		path[counter] = row + " " + col;
		counter++;
	}

	public PathIteratorInterface listPath() {
		return new PathIterator(path, counter);
	}

}
