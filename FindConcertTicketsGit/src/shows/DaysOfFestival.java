/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package shows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import group_tickets.Group;

public class DaysOfFestival implements DaysOffFestivalInterface {

	private ArrayList<Group> alinhamento;
	private int price;
	private LocalDate date;
	private int numberOfTickets;

	public DaysOfFestival(LocalDate date, int price, int numberOfTickets) {
		this.date = date;
		this.price = price;
		alinhamento = new ArrayList<Group>();
		this.numberOfTickets = numberOfTickets;
	}

	public void addGroup(Group group) {
		alinhamento.add(group);
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setNumberOfTickets(int num) {
		numberOfTickets = numberOfTickets - num;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public LocalDate getDate() {
		return date;
	}

	public Iterator<Group> listAlinhamento() {
		return alinhamento.listIterator();
	}

}
