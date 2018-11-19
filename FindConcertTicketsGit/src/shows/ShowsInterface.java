/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package shows;

import java.time.LocalDate;

public interface ShowsInterface {

	/**
	 * Retorna o nome do show.
	 * 
	 * @return name
	 */
	String getName();

	/**
	 * Retorna o primeiro dia.
	 * 
	 * @return firstDay
	 */
	LocalDate getFirstDay();

	/**
	 * Retorna o ultimo dia(no caso de ser concerto, lastDay=firstDay).
	 * 
	 * @return lastDay
	 */
	abstract LocalDate getLastDay();

	/**
	 * Retorna o numero de bilhetes.
	 * 
	 * @return numberOfTickets
	 */
	int getNumberOfTickets();

	/**
	 * Decrementa o numero de bilhetes pelo numero dado como parametro(acontece
	 * quando existe uma compra).
	 * 
	 * @param num
	 */
	void setNumberOfTickets(int num);

	/**
	 * Retorna o total dos bilhetes postos a venda
	 * 
	 * @return total
	 */
	int getTotal();

	/**
	 * Adiciona o valor de total ao total dado pelo parametro.
	 * 
	 * @param total
	 */
	void setTotal(int total);

	/**
	 * Verifica se existe espaco no show, ou seja, se existem bilhetes
	 * suficientes para o valor dado como parametro(compra de num bilhetes).
	 * 
	 * @param num
	 * @return true or false
	 */
	abstract boolean hasEnoughSpace(int num);

	/**
	 * Retorna o conteudo(muda de acordo com tipo).
	 * 
	 * @return conteudo
	 */
	abstract String conteudo();

	/**
	 * Retorna o tipo.
	 * 
	 * @return type
	 */
	abstract String getType();

	/**
	 * Retorna o numero de dias(duracao de um show).
	 * 
	 * @return numberofDays
	 */
	abstract int getNumberOfDays();
}
