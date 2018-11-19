/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package shows;

import java.time.LocalDate;

public abstract class Shows implements ShowsInterface {

	protected String name, desc;
	protected LocalDate firstDay;
	protected int numberOfTickets, total;

	public Shows(String name, String desc, LocalDate firstDay, int numberOfTickets) {
		this.name = name;
		this.desc = desc;
		this.firstDay = firstDay;
		this.numberOfTickets = numberOfTickets;
		total = 0;
	}

	public String getName() {
		return name;
	}

	public LocalDate getFirstDay() {
		return firstDay;
	}

	public abstract LocalDate getLastDay();

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int num) {
		numberOfTickets = numberOfTickets - num;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = this.total + total;
	}

	public abstract boolean hasEnoughSpace(int num);

	public abstract String conteudo();

	public abstract String getType();

	public abstract int getNumberOfDays();

}
