package location;

import control.StormTrooper;
import control.StormTrooperIteratorInterface;

public interface MapInterface {

	/**
	 * Adiciona um rebelde ao mapa, na posicao dada pelos parametros(linha e
	 * coluna).
	 * 
	 * @param caracter
	 * @param row
	 * @param col
	 */
	void addRebel(char caracter, int row, int col);

	/**
	 * Adiciona um espaco branco ao mapa, na posicao dada pelos parametros(linha
	 * e coluna).
	 *
	 * @param caracter
	 * @param row
	 * @param col
	 */
	void addBlank(char caracter, int row, int col);

	/**
	 * Adiciona uma arma, na posicao dada pelos parametros(linha e coluna).
	 * 
	 * @param caracter
	 * @param row
	 * @param col
	 */
	void addWeapon(char caracter, int row, int col);

	/**
	 * Adiciona uma pocao ao mapa, na posicao dada pelos parametros(linha e
	 * coluna).
	 * 
	 * @param caracter
	 * @param row
	 * @param col
	 */
	void addPotion(char caracter, int row, int col);

	/**
	 * Adiciona um superRebelde ao mapa, na posicao dada pelos parametros(linha
	 * e coluna).
	 * 
	 * @param caracter
	 * @param row
	 * @param col
	 */
	void addSuperRebel(char caracter, int row, int col);

	/**
	 * Adiciona um StormStrooper ao mapa, na posicao dada pelos parametros(linha
	 * e coluna) e no vetor de StormTroopers.
	 * 
	 * @param caracter
	 * @param row
	 * @param col
	 */
	void addStormTrooper(char caracter, int row, int col);

	/**
	 * Adiciona um char StormTrooper, ou seja, W,B ou O no mapa.
	 * 
	 * @param row
	 * @param col
	 */
	void addStormTrooperSymbol(int row, int col);

	/**
	 * Retorna coluna do mapa.
	 * 
	 * @return col
	 */
	int getCol();

	/**
	 * Retorna linha do mapa.
	 * 
	 * @return row
	 */
	int getRow();

	/**
	 * Ve se existe uma arma na posicao dada pelo parametro(linha e coluna), se
	 * sim retorna true, se nao retorna false(chama o metodo getSaved() da
	 * classe StormTrooper).
	 * 
	 * @param row
	 * @param col
	 * @return true or false
	 */
	boolean getSavedPos(int row, int col);

	/**
	 * Retorna a posicao do mapa(linha e coluna). Recebe parametros(linha e
	 * coluna).
	 * 
	 * @param row
	 * @param col
	 * @return row col
	 */
	Location getLocation(int row, int col);

	/**
	 * Retorna o StormTrooper na posicao dada pelos parametro(row,col).
	 * 
	 * @param row
	 * @param col
	 * @return StormTrooper
	 */
	StormTrooper getStormTrooperPosition(int row, int col);

	/**
	 * Retorma StormTrooper com o nome dado pelo parametro(name).
	 * 
	 * @param name
	 * @return storm
	 */
	StormTrooper getStormTrooper(String name);

	/**
	 * Ve se existe algum StormTrooper com o nome dado pelo parametro. Se sim
	 * retorna true, se nao retorna false.
	 * 
	 * @param name
	 * @return true or false
	 */
	boolean hasStormTrooper(String name);

	/**
	 * Ve se existe algum StormTrooper na posicao dada pelos parametros(linha e
	 * coluna). Se sim, retorna true, se nao retorna false.
	 * 
	 * @param row
	 * @param col
	 * @return true or false
	 */
	boolean hasStormTrooper(int row, int col);

	/**
	 * Antes de mover o StormTrooper, ve se na posicao anterior, havia uma pocao
	 * ou arma. Se existia uma arma, entao cria uma arma na posicao anterior, se
	 * havia uma pocao cria uma pocao, se nao deixa em branco(Empty). Este
	 * metodo verifica se existe uma arma ou pocao a direita da
	 * posicao(row,col). Se sim mete getSavedPos a true(caso de existir uma
	 * arma), ou mete getPotionPos() a true(caso de existir uma pocao). Depois
	 * verifica se a sua direita existe um SuperRebel, se sim, o StormTrooper e
	 * capturado, se nao, ve o tipo de StormTrooper e move o para a direita.
	 * 
	 * 
	 * @param row
	 * @param col
	 */
	void moveRight(int row, int col);

	/**
	 * Antes de mover o StormTrooper, ve se na posicao anterior, havia uma pocao
	 * ou arma. Se existia uma arma, entao cria uma arma na posicao anterior, se
	 * havia uma pocao cria uma pocao, se nao deixa em branco(Empty). Este
	 * metodo verifica se existe uma arma ou pocao a esquerda da
	 * posicao(row,col). Se sim mete getSavedPos a true(caso de existir uma
	 * arma), ou mete getPotionPos() a true(caso de existir uma pocao). Depois
	 * verifica se a sua esquerda existe um SuperRebel, se sim, o StormTrooper e
	 * capturado, se nao, ve o tipo de StormTrooper e move o para a esquerda.
	 * 
	 * @param row
	 * @param col
	 */
	void moveLeft(int row, int col);

	/**
	 * Antes de mover o StormTrooper, ve se na posicao anterior, havia uma pocao
	 * ou arma. Se existia uma arma, entao cria uma arma na posicao anterior, se
	 * havia uma pocao cria uma pocao, se nao deixa em branco(Empty). Este
	 * metodo verifica se existe uma arma ou pocao em cima da posicao(row,col).
	 * Se sim mete getSavedPos a true(caso de existir uma arma), ou mete
	 * getPotionPos() a true(caso de existir uma pocao). Depois verifica se em
	 * cima existe um SuperRebel, se sim, o StormTrooper e capturado, se nao, ve
	 * o tipo de StormTrooper e move o para cima.
	 * 
	 * @param row
	 * @param col
	 */
	void moveUp(int row, int col);

	/**
	 * Antes de mover o StormTrooper, ve se na posicao anterior, havia uma pocao
	 * ou arma. Se existia uma arma, entao cria uma arma na posicao anterior, se
	 * havia uma pocao cria uma pocao, se nao deixa em branco(Empty). Este
	 * metodo verifica se existe uma arma ou pocao embaixo da posicao(row,col).
	 * Se sim mete getSavedPos a true(caso de existir uma arma), ou mete
	 * getPotionPos() a true(caso de existir uma pocao). Depois verifica embaixo
	 * existe um SuperRebel, se sim, o StormTrooper e capturado, se nao, ve o
	 * tipo de StormTrooper e move o para baixo.
	 * 
	 * @param row
	 * @param col
	 */
	void moveDown(int row, int col);

	/**
	 * Retorna o iterador de StormTroopers.
	 * 
	 * @return StormTrooperInterator
	 */
	StormTrooperIteratorInterface listarStormTroopers();

	/**
	 * Retorna o iterador do mapa.
	 * 
	 * @return MapIterator
	 */
	MapIteratorInterface listarMap();

}
