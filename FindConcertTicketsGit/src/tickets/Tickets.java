/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package tickets;

import shows.*;

public abstract class Tickets implements TicketsInterface {

	protected Shows associatedShow; // espectaculo associado
	protected int price;
	protected int numberOfTickets;

	public Tickets(Shows associatedShow, int price, int numberOfTickets) {
		this.associatedShow = associatedShow;
		this.price = price;
		this.numberOfTickets = numberOfTickets;
	}

	public Shows getAssociatedShow() {
		return associatedShow;
	}

	public abstract String conteudo();

}
