/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package control;

import java.time.LocalDate;
import java.util.Iterator;

import group_tickets.Group;
import shows.Concerts;
import shows.Festival;
import shows.Shows;
import users.Users;

public interface FindConcertTicketInterface {

	/**
	 * Adiciona um user recebendo como parametros o tipo(admin ou client) e o
	 * mail.
	 * 
	 * @param type
	 * @param mail
	 */
	void addUser(String type, String mail);

	/**
	 * Adiciona um grupo recebendo como parametros o tipo(artist ou band) e o
	 * nome.
	 * 
	 * @param type
	 * @param name
	 */
	void addGroup(String type, String name);

	/**
	 * Adiciona um concerto recebendo como parametros o nome do concerto, nome
	 * do artista, a descricao do concerto, a data, o preco de um bilhete e o
	 * numero de bilhetes a venda.
	 * 
	 * @param name
	 * @param artist
	 * @param desc
	 * @param date
	 * @param price
	 * @param ticketsForSale
	 */
	void addConcert(String name, Group artist, String desc, LocalDate date, int price, int ticketsForSale);

	/**
	 * Adiciona um festival recebendo como parametro o nome do festival, a
	 * descricao do festival, o primeiro dia do festival e o numero de bilhetes
	 * a venda para cada dia.
	 * 
	 * @param name
	 * @param desc
	 * @param firstDay
	 * @param numberOfTickets
	 */
	void addFestival(String name, String desc, LocalDate firstDay, int numberOfTickets);

	/**
	 * Remove um show recebendo como parametro o nome do show.
	 * 
	 * @param name
	 */
	void removeShow(String name);

	/**
	 * Adiciona um bilhete recebendo como parametro a escolha de show(concert ou
	 * festival), o show associado , o preco do bilhete e o numero de
	 * bilhetes(compra do bilhete).
	 * 
	 * @param choice
	 * @param associatedShow
	 * @param price
	 * @param num
	 */
	void addTicket(String choice, Shows associatedShow, int price, int num);

	/**
	 * Retorna o numero do admin(ordem de registo para obter a pass).
	 * 
	 * @return numberAdmin
	 */
	int getNumberAdmin();

	/**
	 * Retorna o numero do client(ordem de registo para obter a pass).
	 * 
	 * @return numberClient
	 */
	int getNumberClient();

	/**
	 * Ve se existe um user loggado, retorna true se estiver logged in senao
	 * retorna false.
	 * 
	 * @return true or false
	 */
	boolean getLogged();

	/**
	 * Muda o valor de logged para true(indica que um user esta logged in).
	 */
	void logIn();

	/**
	 * Muda o valor de logged para false(indica que um user esta logged off).
	 * 
	 */
	void logOff();

	/**
	 * Verifica se o user existe. Recebe como parametro o mail, se existir
	 * retorna true caso contrario retorna false.
	 * 
	 * @param mail
	 * @return true or false
	 */
	boolean hasUser(String mail);

	/**
	 * Procura no vetor users um user com o mail dado como parametro. Retorna
	 * user sendo que user pode ser null caso nao exista nenhum user com esse
	 * mail.
	 * 
	 * @param mail
	 * @return user
	 */
	Users getUser(String mail);

	/**
	 * Procura no vetor users um user que esteja logged in. Retorna o user
	 * logged in,sendo que pode ser null caso nao exista nenhum logged in.
	 * 
	 * @return user
	 */
	Users getLoggedUser();

	/**
	 * Verifica se o grupo existe. Recebe como parametro o nome do grupo, se
	 * existir retorna true caso contrario retorna false.
	 * 
	 * @param name
	 * @return true or false
	 */
	boolean hasGroup(String name);

	/**
	 * Procura no vetor group um grupo com o nome dado pelo parametro. Retorna g
	 * sendo que pode ser null caso nao exista nenhum.
	 * 
	 * @param name
	 * @return g
	 */
	Group getGroup(String name);

	/**
	 * Procura no vetor shows por um show com o nome dado pelo parametro.
	 * Retorna show sendo que pode ser null caso nao exista nenhum.
	 * 
	 * @param name
	 * @return show
	 */
	Shows getShow(String name);

	/**
	 * Procura no vetor shows por show com o nome e data dados pelos parametros.
	 * Retorna show sendo que pode ser null caso nao exista nenhum.
	 * 
	 * @param name
	 * @param date
	 * @return show
	 */
	Shows getShowDate(String name, LocalDate date);

	/**
	 * Verifica se existe um show com um nome dado pelo parametro. Retorna true
	 * se existir caso contrario retorna false.
	 * 
	 * @param name
	 * @return true or false
	 */
	boolean hasShow(String name);

	/**
	 * Verifica se existe um show com um nome e uma data pelos parametros.
	 * Retorna true se existir caso contrario retorna false.
	 * 
	 * @param name
	 * @param date
	 * @return true or false
	 */
	boolean hasShowDate(String name, LocalDate date);

	/**
	 * Procura no vetor festivals por um festival com um nome dado pelo
	 * parametro. Retorna festival sendo que pode ser null caso nao exista
	 * nenhum.
	 * 
	 * @param name
	 * @return festival
	 */
	Festival getFestival(String name);

	/**
	 * Procura no vetor festivals por festival com um nome e uma data dados pelo
	 * parametros. Retorna festival sendo que pode ser null caso nao exista
	 * nenhum.
	 * 
	 * @param name
	 * @param date
	 * @return festival
	 */
	Festival getFestivalDate(String name, LocalDate date);

	/**
	 * Procura no vetor concerts por um concerto com um nome e uma data dados
	 * pelo parametros. Retorna concert sendo que pode ser null caso nao exista
	 * nenhum.
	 * 
	 * @param name
	 * @param date
	 * @return concert
	 */
	Concerts getConcertsDate(String name, LocalDate date);

	/**
	 * Retorna os todos os shows.
	 * 
	 * @return shows.listIterator
	 */
	Iterator<Shows> listShows();

	/**
	 * Retorna todos os users.
	 * 
	 * @return users.listIterator
	 */
	Iterator<Users> listUsers();

	/**
	 * Retorna todos os shows ordenados por bilhetes mais vendidos,depois por
	 * data e por fim por nome.
	 * 
	 * @return shows.listIterator
	 */
	Iterator<Shows> listSortedShows();

}
