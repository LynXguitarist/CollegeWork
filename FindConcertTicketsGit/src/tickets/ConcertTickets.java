/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package tickets;

import java.time.LocalDate;

import shows.Shows;

public class ConcertTickets extends Tickets implements ConcertTicketsInterface {

	private LocalDate date;

	public ConcertTickets(Shows associatedShow, int price, int numberOfTickets, LocalDate date) {
		super(associatedShow, price, numberOfTickets);
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public String conteudo() {
		return associatedShow.getName() + "\n" + date + "\n" + numberOfTickets + "\n" + price;
	}

	public Shows getAssociatedShow() {
		return associatedShow;
	}

}
