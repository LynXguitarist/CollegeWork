/**
* @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package shows;

import java.time.LocalDate;
import java.util.ListIterator;

public interface FestivalInterface {
	/**
	 * Adiciona um dia ao festival com uma data e um preco do bilhete como
	 * parametros.
	 * 
	 * @param date
	 * @param price
	 */
	void addDay(LocalDate date, int price);

	/**
	 * Retorna o nome do festival.
	 * 
	 * @return conteudo
	 */
	String conteudo();

	/**
	 * Retorna o tipo(neste caso o tipo e festival).
	 * 
	 * @return type
	 */
	String getType();

	/**
	 * Procura no vetor day por um dia de festival com data dada como parametro.
	 * Retorna o dia do festival, sendo que pode ser null caso nao exista nenhum
	 * dia com essa data.
	 * 
	 * @param date
	 * @return day
	 */
	DaysOfFestival getDayOfFestival(LocalDate date);

	/**
	 * Retorna o ulitmo dia do festival.
	 * 
	 * @return lastDay
	 */
	LocalDate getLastDay();

	/**
	 * Procura no vetor day pela posicao dada como parametro. Retorna o dia do
	 * festival nesssa posicao,caso exista, sendo que pode ser null caso nao
	 * exista nenhum dia nessa posicao.
	 * 
	 * @param index
	 * @return day
	 */
	DaysOfFestival getDayByIndex(int index);

	/**
	 * Retorna o iterador que lista os dias do festival.
	 * 
	 * @return
	 */
	ListIterator<DaysOfFestival> listDays();

	/**
	 * Retorna o tamanho do vetor days(numero de dias de um festival).
	 * 
	 * @return size
	 */
	int getNumberOfDays();

	/**
	 * Verifica se existe espaco no show, ou seja, se existem bilhetes
	 * suficientes para o valor dado como parametro(compra de num bilhetes)..
	 * 
	 * @param num
	 * @return true or false
	 */
	boolean hasEnoughSpace(int num);
}
