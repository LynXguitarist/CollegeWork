package control;

public interface RebelIteratorInterface {

	/**
	 * Inicia o iterator rebel, pondo a posicao corrente a 0(iniciando-a).
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
	 * Retorna a proxima posicao no array rebel, ou seja, um rebelde.
	 * 
	 * @return Rebel
	 */
	Rebel next();

	/**
	 * Retorna a posicao corrente no array capture, ou seja, um rebelde.
	 * 
	 * @return Rebel
	 */
	Rebel getCurrent();
}
