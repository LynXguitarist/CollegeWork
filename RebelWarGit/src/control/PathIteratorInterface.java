package control;

public interface PathIteratorInterface {

	/**
	 * Inicia o iterator path, pondo a posicao corrente a 0(iniciando-a).
	 */
	void init();

	/**
	 * Retorna true se existir uma posicao seguinte, caso nao exista retorna
	 * false.
	 * 
	 * @return true or false.
	 */
	boolean hasNext();

	/**
	 * Retorna a proxima posicao no array path.
	 * 
	 * @return String
	 */
	String next();

}
