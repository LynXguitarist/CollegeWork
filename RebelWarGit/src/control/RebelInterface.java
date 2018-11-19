package control;

public interface RebelInterface {

	/**
	 * Retorna o nome do Rebelde.
	 * 
	 * @return name
	 */
	String getName();

	/**
	 * Retorna o estado do Rebelde, podendo ser CAPTURED, ACTIVE ou
	 * SUPERCHARGED.
	 * 
	 * @return state
	 */
	String getState();

	/**
	 * Muda o state do Rebelde para o state dado como parametro.
	 * 
	 * @param state
	 */
	void setState(String state);

	/**
	 * Retorna os pontos do Rebelde.
	 * 
	 * @return points
	 */
	int getPoints();

	/**
	 * Adiciona 10 pontos aos pontos obtidos pelo Rebelde.
	 */
	void addPoints();

	/**
	 * Retorna a linha onde o Rebelde se encontra.
	 * 
	 * @return row
	 */
	int getRow();

	/**
	 * Muda a linha onde o Rebelde se encontra para a linha recebida como
	 * parametro.
	 * 
	 * @param row
	 */
	void setRow(int row);

	/**
	 * Retorna a coluna onde o Rebelde se encontra.
	 * 
	 * @return col
	 */
	int getCol();

	/**
	 * Muda a coluna onde o Rebelde se encontra para a coluna recebida como
	 * parametro.
	 * 
	 * @param col
	 */
	void setCol(int col);

	/**
	 * Retorna as jogadas que o Rebelde ainda pode fazer como SuperRebelde.
	 * 
	 * @return timer
	 */
	int getTimer();

	/**
	 * Muda o timer para 4(numero de jogadas como SuperRebelde).
	 */
	void setTimer();

	/**
	 * Decrementa o timer(a seguir de cada jogada como SuperRebelde).
	 * 
	 */
	void decTimer();

	/**
	 * Retorna o numero de passos dada por um Rebelde.
	 * 
	 * @return counter
	 */
	int getSteps();

	/**
	 * Poe a posicao captura como row e col(dados como prarmetro), serve para
	 * guardar a posicao onde sao capturados por StormTroopers.
	 * 
	 * @param row
	 * @param col
	 */
	void setPosCaptured(int row, int col);

	/**
	 * Retorna a posicao onde o Rebelde foi capturado.
	 * 
	 * @return posCaptured
	 */
	String getPosCaptured();

	/**
	 * Adiciona um passo(recebido como parametro(row,col)) ao vetor path.
	 * 
	 * @param row
	 * @param col
	 */
	void setPath(int row, int col);

	/**
	 * Retorna o Iterador path.
	 * 
	 * @return pathIterator
	 */
	PathIteratorInterface listPath();

}
