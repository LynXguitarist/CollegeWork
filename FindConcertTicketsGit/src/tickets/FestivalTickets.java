/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package tickets;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;


import shows.Shows;

public class FestivalTickets extends Tickets implements FestivalTicketsInterface{

	private ArrayList<LocalDate> days;

	public FestivalTickets(Shows associatedShow, int price, int numberOfTickets) {
		super(associatedShow, price, numberOfTickets);
		days = new ArrayList<LocalDate>();
	}

	public void addDay(LocalDate date) {
		days.add(date);
	}

	public int getPrice() {
		return price;
	}

	public String conteudo() {
		return associatedShow.getName();
	}

	public Iterator<LocalDate> listDays() {
		return days.listIterator();
	}

}
