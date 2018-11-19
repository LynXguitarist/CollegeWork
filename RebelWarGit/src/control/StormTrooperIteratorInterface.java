package control;

public interface StormTrooperIteratorInterface {

	/**
	 * Inicia o iterator storm, pondo a posicao corrente a 0(iniciando-a).
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
	 * Retorna a proxima posicao no array storm, ou seja, um StormTrooper.
	 * 
	 * @return Stormtrooper
	 */
	StormTrooper next();

	/**
	 * Retorna a posicao corrente no array storm, ou seja, um StormTrooper.
	 * 
	 * @return StormTrooper
	 */
	StormTrooper getCurrent();
}
