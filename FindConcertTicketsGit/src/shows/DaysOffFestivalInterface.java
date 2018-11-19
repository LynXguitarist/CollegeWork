/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package shows;

import java.time.LocalDate;
import java.util.Iterator;

import group_tickets.Group;

public interface DaysOffFestivalInterface {

	/**
	 * Adiciona ao vetor alinhamento um grupo dado como parametro.
	 * 
	 * @param group
	 */
	void addGroup(Group group);

	/**
	 * Retorna o preco do dia.
	 * 
	 * @return price
	 */
	int getPrice();

	/**
	 * Muda o valor de price para o valor dado como o parametro.
	 * 
	 * @param price
	 */
	void setPrice(int price);

	/**
	 * Subtrai o valor dado como parametro ao numero de bilhetes(acontece quando
	 * existe uma compra).
	 * 
	 * @param num
	 */
	void setNumberOfTickets(int num);

	/**
	 * Retorna o numero de bilhetes.
	 * 
	 * @return numberOfTickets
	 */
	int getNumberOfTickets();

	/**
	 * Retorna a data.
	 * 
	 * @return date
	 */
	LocalDate getDate();

	/**
	 * Retorna o iterador de alinhamento, todas as bandas que vao tocar nesse
	 * dia.
	 * 
	 * @return alinhamento.listIterator
	 */
	Iterator<Group> listAlinhamento();
}
