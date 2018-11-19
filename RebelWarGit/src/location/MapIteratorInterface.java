package location;

public interface MapIteratorInterface {

	/**
	 * Inicia o iterator map, pondo a posicao corrente a 0(iniciando-a).
	 */
	void init();

	/**
	 * Retorna true se existir uma posicao seguinte, caso nao exista retorna
	 * false.
	 * 
	 * @return true or false
	 */
	boolean hasNext();

	/**
	 * Retorna a proxima posicao no array map, ou seja, uma localizacao.
	 * 
	 * @return Location
	 */
	Location next();

	/**
	 * Retorna a localizaocao corrente.
	 * 
	 * @param row
	 * @param col
	 * @return Location
	 */
	Location getCurrent(int row, int col);

	/**
	 * Procura no mapa o caracter dado como parametro. Se existir pelo menos um
	 * desses caracteres no mapa, retorn true, senao retorna false.
	 * 
	 * @param symbol
	 * @return true or false
	 */
	boolean searchNext(char symbol);
}
