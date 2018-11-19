/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package tickets;

import java.time.LocalDate;
import java.util.Iterator;

public interface FestivalTicketsInterface {

	/**
	 * Adiciona um dia ao festival com uma data dada como parametro.
	 * 
	 * @param date
	 */
	void addDay(LocalDate date);

	/**
	 * Retorna o preco do dia.
	 * 
	 * @return price
	 */
	int getPrice();

	/**
	 * Retorna o nome do show do associatedShow(show associado ao bilhete).
	 * 
	 * @return name
	 */
	String conteudo();

	/**
	 * Retorna o iterador.
	 * 
	 * @return listIterator
	 */
	Iterator<LocalDate> listDays();

}
