/**
   * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package group_tickets;

import java.time.LocalDate;
import java.util.Iterator;

import shows.Shows;

public interface GroupInterface {

	/**
	 * Adiciona um show a lista de shows(espectaculos onde o grupo vai tocar).
	 * 
	 * @param show
	 */
	void addShow(Shows show);

	/**
	 * Retorna o nome do grupo.
	 * 
	 * @return name
	 */
	String getName();

	/**
	 * Muda a data para a date dada como parametro.
	 * 
	 * @param date
	 */
	void setDate(LocalDate date);

	/**
	 * lista os membros de uma banda, no caso de ser so um artista, retorna o
	 * artista.
	 * 
	 * @return listMembers
	 */
	abstract Object listMembers();

	/**
	 * Retorna o tipo(artista ou grupo).
	 * 
	 * @return type
	 */
	abstract String getType();

	/**
	 * Retorna todos os shows onde a banda vai tocar.
	 * 
	 * @return shows.listIterator
	 */
	Iterator<Shows> listShows();
}
