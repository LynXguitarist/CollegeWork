/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
package control;

public class StormTrooper implements StormTrooperInterface{

	private String state, name;
	private int row, col;
	private int placed, order;
	private char type;
	private boolean savedPos, savedPotion;
	private Rebel[] captured;
	private int counter;

	public StormTrooper(char type, int row, int col) {
		this.row = row;
		this.col = col;
		captured = new Rebel[50];
		savedPos = false;
		savedPotion = false;
		counter = 0;
		order = 0;
		this.type = type;
		state = "ACTIVE";
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPlaced() {
		return placed;
	}

	public void setPlaced(int placed) {
		this.placed = placed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getType() {
		return type;
	}

	public void incOrder() {
		order++;
	}

	public int getOrder() {
		return order;
	}

	public void resetOrder() {
		order = 0;
	}

	public void addCaptured(String name, int row, int col) {
		captured[counter] = new Rebel(name, row, col);
		captured[counter].setPosCaptured(row, col);
		counter++;
	}

	public int getCaptured() {
		return counter;
	}

	public boolean getSavedPos() {
		return savedPos;
	}

	public void setSavedPos(boolean savedPos) {
		this.savedPos = savedPos;
	}

	public boolean getSavedPotion() {
		return savedPotion;
	}

	public void setSavedPotion(boolean savedPotion) {
		this.savedPotion = savedPotion;
	}

	public CaptureIteratorInterface listCaptured() {
		return new CaptureIterator(captured, counter);
	}

}
