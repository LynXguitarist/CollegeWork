/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package shows;

import java.time.LocalDate;

import group_tickets.Group;

public interface ConcertsInterface {

	/**
	 * Retorna o preco de um concerto.
	 * 
	 * @return price
	 */
	int getPrice();

	/**
	 * Retorna o artista que vai tocar no concerto.
	 * 
	 * @return artist
	 */
	Group getArtist();

	/**
	 * Retorna o tipo(neste caso o tipo e concerto).
	 * 
	 * @return type
	 */
	String getType();

	/**
	 * Retorna o conteudo(nome do concerto,nome do artista,data do primeiro
	 * dia,preco e numero de bilhetes).
	 * 
	 * @return conteudo
	 */
	String conteudo();

	/**
	 * Retorna o ultimo dia de um festival(neste caso, lastDay=firstDay).
	 * 
	 * @return lastDay
	 */
	LocalDate getLastDay();

	/**
	 * Retorna o numero de dias(neste caso e 1).
	 * 
	 * @return numberOfDays
	 */
	int getNumberOfDays();

	/**
	 * Verifica se existe espaco no show, ou seja, se existem bilhetes
	 * suficientes para o valor dado como parametro(compra de num bilhetes).
	 * 
	 * @param num
	 * @return true of false
	 */
	boolean hasEnoughSpace(int num);

}
