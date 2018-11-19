/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package users;

import java.util.Iterator;

import shows.Festival;
import shows.Shows;
import tickets.FestivalTickets;
import tickets.Tickets;

public interface UsersInterface {

	/**
	 * Adiciona um bilhete recebendo como parametros uma escolha(concert ou
	 * festival), o show associado, o preco e o numero de bilhetes.
	 * 
	 * @param choice
	 * @param associatedShow
	 * @param price
	 * @param num
	 */
	void addTickets(String choice, Shows associatedShow, int price, int num);

	/**
	 * Retorna a password do user.
	 * 
	 * @return password
	 */
	String getPassword();

	/**
	 * Muda a password para password dada como parametro.
	 * 
	 * @param password
	 */
	void setPassword(String password);

	/**
	 * Retorna o email do user.
	 * 
	 * @return mail
	 */
	String getMail();

	/**
	 * Ve se o user esta logged, retorna true se o user estiver logged in
	 * senao retorna false.
	 * 
	 * @return true or false
	 */
	boolean getLogged();

	/**
	 * Muda o valor de logged para true(indica que um user fez login).
	 */
	void logIN();

	/**
	 * Muda o valor de logged para true(indica que um user fez logout).
	 */
	void logOut();

	/**
	 * Procura no vetor festivaltickets por um festival dado como parametro.
	 * Retorna ticket sendo que pode ser null caso nao exista nenhum.
	 * 
	 * @param fest
	 * @return ticket
	 */
	FestivalTickets getFestivalTicket(Festival fest);

	/**
	 * Retorna o tipo de user(admin ou client).
	 * 
	 * @return type
	 */
	abstract String getType();

	/**
	 * Retorna o iterador com os bilhetes desse user.
	 * 
	 * @return listTickets
	 */
	Iterator<Tickets> listTickets();
}
