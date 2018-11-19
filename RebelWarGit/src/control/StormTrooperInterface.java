package control;

public interface StormTrooperInterface {

	/**
	 * Retorna a linha onde o StormTrooper se encontra.
	 * 
	 * @return row
	 */
	int getRow();

	/**
	 * Muda a posicao do StormTrooper para a linha dada como parametro.
	 * 
	 * @param row
	 */
	void setRow(int row);

	/**
	 * Retorna a coluna onde o StormTrooper se encontra.
	 * 
	 * @return col
	 */
	int getCol();

	/**
	 * Muda a posicao do StormTrooper para a coluna dada como parametro.
	 *
	 * @param col
	 */
	void setCol(int col);

	/**
	 * Retorna o estado do StormTrooper podendo ser ACTIVE ou CAPTURED.
	 * 
	 * @return state
	 */
	String getState();

	/**
	 * Muda o estado do StormTrooper para o estado dado como parametro.
	 * 
	 * @param state
	 */
	void setState(String state);

	/**
	 * Retorna o numero do tipo do StormTrooper, ou seja, sendo o identificador
	 * do Stormtrooper ST-x-tipo. O x e o placed.
	 * 
	 * @return placed
	 */
	int getPlaced();

	/**
	 * Muda o placed para o dado como parametro.
	 * 
	 * @param placed
	 */
	void setPlaced(int placed);

	/**
	 * Retorna o nome do StormTrooper.
	 * 
	 * @return name
	 */
	String getName();

	/**
	 * Muda o nome do StormTrooper para o nome dado como parametro.
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * Retorna o tipo do StormTrooper.
	 * 
	 * @return type
	 */
	char getType();

	/**
	 * Incrementa a ordem de movimento do StormTrooper.
	 */
	void incOrder();

	/**
	 * Retorna a ordem de movimento do StormTrooper.
	 * 
	 * @return order
	 */
	int getOrder();

	/**
	 * Poe a ordem(order) a 0, ou seja, faz reset da ordem.
	 */
	void resetOrder();

	/**
	 * Adiciona um Rebelde ao array dos Rebeldes capturados pelo StormTrooper. O
	 * nome do Rebelde e a posicao onde e capturado sao dados como
	 * parametro(name,row,col).
	 * 
	 * @param name
	 * @param row
	 * @param col
	 */
	void addCaptured(String name, int row, int col);

	/**
	 * Retorna o numero de Rebeldes capturados.
	 * 
	 * @return counter
	 */
	int getCaptured();

	/**
	 * Retorna true se a posicao esta guardada ou false se nao estiver. Uma
	 * posicao esta guardada caso tenha uma arma nessa posicao.
	 * 
	 * @return true or false
	 */
	boolean getSavedPos();

	/**
	 * Muda savedPos para o valor dado como parametro.
	 * 
	 * @param savedPos
	 */
	void setSavedPos(boolean savedPos);

	/**
	 * Retorna true se a posicao esta guardada ou false se nao estiver. Uma
	 * posicao esta guardada caso tenha uma pocao nessa posicao.
	 * 
	 * @return true or false
	 */
	boolean getSavedPotion();

	/**
	 * Muda a savedPotion para o valor dado como parametro.
	 * 
	 * @param savedPotion
	 */
	void setSavedPotion(boolean savedPotion);

	/**
	 * Retorna o Iterador dos Rebeldes capturados pelo StormTrooper.
	 * 
	 * @return capturedIterator
	 */
	CaptureIteratorInterface listCaptured();
}
