package location;

public interface LocationInterface {

	/**
	 * Retorna a linha da Localizacao
	 * 
	 * @return row
	 */
	int getRow();

	/**
	 * Retorna a coluna da Localizacao
	 * 
	 * @return col
	 */
	int getCol();

	/**
	 * Devolve o char que representa a localizacao
	 * 
	 * @return char
	 */
	abstract char symbol();
}
