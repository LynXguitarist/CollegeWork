/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package tickets;

import java.time.LocalDate;

import shows.Shows;

public interface ConcertTicketsInterface {

	/**
	 * Retorna a data.
	 * 
	 * @return date
	 */
	LocalDate getDate();

	/**
	 * Retorna o o nome, a data, o numero de bilhetes e o preco do
	 * associatedShow(o show associado).
	 * 
	 * @return name, date, numberOfTickets, price
	 */
	String conteudo();

	/**
	 * Retorna o associatedShow(o show associado).
	 * 
	 * @return associatedShow
	 */
	Shows getAssociatedShow();

}
