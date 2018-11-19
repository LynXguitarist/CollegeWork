package control;

import location.MapIteratorInterface;

public interface ControllerInterface {

	/**
	 * Adiciona um Rebelde, recebendo como parametros, o nome do Rebelede, a
	 * linha e a coluna onde o queremos colocar no mapa.
	 * 
	 * @param name
	 * @param row
	 * @param col
	 */
	void addRebel(String name, int row, int col);

	/**
	 * Retorna os pontos totais, ganhos pelos Rebeldes. (os rebeldes ganham
	 * pontos depois de apanharem armas).
	 * 
	 * @return points
	 */
	int getPoints();

	/**
	 * Este metodo faz o somatorio de todos os pontos de todos os Rebeldes
	 */
	void addPoints();

	/**
	 * Ve se o jogo ja foi iniciado, retornando true se ja foi e false se ainda
	 * nao foi inicado.
	 * 
	 * @return true or false
	 */
	boolean playing();

	/**
	 * Poe o playing a true, ou seja, inicializa o jogo.
	 */
	void setPlaying();

	/**
	 * Retorna o estado do jogo. O estado do jogo pode ser ON,OFF ou OVER, sendo
	 * que quando esta ON, o jogo esta a decorrer, quando esta OFF, o jogo ainda
	 * nao foi inicializado e quando esta OVER, o jogo ja terminou.
	 * 
	 * @return state
	 */
	String getState();

	/**
	 * Poe o state=ON, ou seja, inicia o jogo.
	 */
	void setStateON();

	/**
	 * Poe o state=OVER, ou seja, termina o jogo.
	 */
	void setStateOFF();

	/**
	 * Retorna o tamanho da coluna do mapa.
	 * 
	 * @return col
	 */
	int getCol();

	/**
	 * Retorna o tamanho da linha do mapa.
	 * 
	 * @return row
	 */
	int getRow();

	/**
	 * Retorna o tempo decorrido no jogo.
	 * 
	 * @return timer
	 */
	int getTimer();

	/**
	 * Incrementa o timer.
	 */
	void incTimer();

	/**
	 * Verifica se o Rebelde com o nome recebido como parametro, existe ou nao,
	 * retornando true se existir e false se nao existir.
	 * 
	 * @param name
	 * @return true or false
	 */
	boolean hasRebel(String name);

	/**
	 * Verifica se existe um Rebelde na posicao passada como parametro,
	 * retornando true se existir e false se nao existir.
	 * 
	 * @param row
	 * @param col
	 * @return true or false
	 */
	boolean hasRebelThere(int row, int col);

	/**
	 * Procura no array rebel, quantos Rebeldes estao ou ACTIVE ou SUPERCHARGED.
	 * Sempre que encontra um rebelde que satisfaca esta condicao, incrementa o
	 * contador. No final retorna o contador(numero de Rebeldes ACTIVE ou
	 * SUPERCHARGED).
	 * 
	 * @return contador
	 */
	int numberOfRebels();

	/**
	 * Retorna um Rebelde com o nome dado pelo parametro.
	 * 
	 * @param name
	 * @return Rebel
	 */
	Rebel getRebel(String name);

	/**
	 * Retorna um Rebel que esteja na posicao dada pelo parametro.
	 * 
	 * @param row
	 * @param col
	 * @return Rebel
	 */
	Rebel getRebelPosition(int row, int col);

	/**
	 * Verifica se o StormTrooper com o nome recebido como parametro, existe ou
	 * nao, retornando true se existir e false se nao existir.
	 * 
	 * @param name
	 * @return true or false
	 */
	boolean hasStormTrooper(String name);

	/**
	 * Verifica se o mapa ja foi definido. Se este nao foi definido retorna
	 * false, se ja foi definido retorna true.
	 * 
	 * @return true or false
	 */
	boolean hasMap();

	/**
	 * Define o mapa com tamanho col e row, dados como parametro.
	 * 
	 * @param map
	 * @param row
	 * @param col
	 */
	void setMap(char[][] map, int row, int col);

	/**
	 * Verifica se a localizacao dada como parametro esta dentro dos limites do
	 * mapa, se tem la um StormTrooper ou uma parede. Caso se verifique esta
	 * condicao retorna true, se nao se verificar retorna false.
	 * 
	 * @param row
	 * @param col
	 * @return true or false
	 */
	boolean isLocationTaken(int row, int col);

	/**
	 * Verifica se a localizacao dada como parametro tem la um StormTrooper.
	 * Caso se verifique esta condicao retorna true, se nao se verificar retorna
	 * false.
	 * 
	 * @param row
	 * @param col
	 * @return true or false
	 */
	boolean isLocationStorm(int row, int col);

	/**
	 * Verifica se a localizacao dada como parametro tem la uma parede. Caso se
	 * verique esta condicao retorna true, se nao se verificar retorna false.
	 * 
	 * @param row
	 * @param col
	 * @return true or false
	 */
	boolean isLocationImpossible(int row, int col);

	/**
	 * Retorna o StormTrooper com o nome recebido como parametro.
	 * 
	 * @param name
	 * @return StormTrooper
	 */
	StormTrooper getStormTrooper(String name);

	/**
	 * Este metodo verifica se existe uma pocao a direita do Rebelde se o
	 * Rebelde e SuperRebelde ou se e um Rebelde normal. Se existir uma pocao,
	 * este passa a SuperRebelde, tendo 4 jogadas disponiveis como tal. Se for
	 * SuperRebelde, este move se para a direita e decrementa uma jogada como
	 * SuperRebelde. Se for um Rebelde normal, simplesmente move se para a
	 * direita. Se na posicao para onde se vai mover,estiver um StormTrooper e
	 * este for SuperRebelde, o StormTrooper e capturado.
	 * 
	 * @param row
	 * @param col
	 */
	void moveRight(int row, int col);

	/**
	 * Este metodo verifica se existe uma pocao a esquerda do Rebelde se o
	 * Rebelde e SuperRebelde ou se e um Rebelde normal. Se existir uma pocao,
	 * este passa a SuperRebelde, tendo 4 jogadas disponiveis como tal. Se for
	 * SuperRebelde, este move se para a esquerda e decrementa uma jogada como
	 * SuperRebelde. Se for um Rebelde normal, simplesmente move se para a
	 * esquerda. Se na posicao para onde se vai mover,estiver um StormTrooper e
	 * este for SuperRebelde, o StormTrooper e capturado.
	 * 
	 * @param row
	 * @param col
	 */
	void moveLeft(int row, int col);

	/**
	 * * Este metodo verifica se existe uma pocao abaixo do Rebelde se o Rebelde
	 * e SuperRebelde ou se e um Rebelde normal. Se existir uma pocao, este
	 * passa a SuperRebelde, tendo 4 jogadas disponiveis como tal. Se for
	 * SuperRebelde, este move se para baixo e decrementa uma jogada como
	 * SuperRebelde. Se for um Rebelde normal, simplesmente move se para baixo .
	 * Se na posicao para onde se vai mover,estiver um StormTrooper e este for
	 * SuperRebelde, o StormTrooper e capturado.
	 * 
	 * @param row
	 * @param col
	 */
	void moveDown(int row, int col);

	/**
	 * * Este metodo verifica se existe uma pocao acima do Rebelde se o Rebelde
	 * e SuperRebelde ou se e um Rebelde normal. Se existir uma pocao, este
	 * passa a SuperRebelde, tendo 4 jogadas disponiveis como tal. Se for
	 * SuperRebelde, este move se para cima e decrementa uma jogada como
	 * SuperRebelde. Se for um Rebelde normal, simplesmente move se para cima.
	 * Se na posicao para onde se vai mover,estiver um StormTrooper e este for
	 * SuperRebelde, o StormTrooper e capturado.
	 * 
	 * @param row
	 * @param col
	 */
	void moveUp(int row, int col);

	/**
	 * Este metodo move um Stormtrooper para a direita. Se existir uma colisao
	 * com um Rebelde, o StormTrooper captura o.
	 * 
	 * @param row
	 * @param col
	 */
	void moveRightStorm(int row, int col);

	/**
	 * Este metodo move um Stormtrooper para a esquerda. Se existir uma colisao
	 * com um Rebelde, o StormTrooper captura o.
	 * 
	 * @param row
	 * @param col
	 */
	void moveLeftStorm(int row, int col);

	/**
	 * Este metodo move um Stormtrooper para cima. Se existir uma colisao com um
	 * Rebelde, o StormTrooper captura o.
	 * 
	 * @param row
	 * @param col
	 */
	void moveUpStorm(int row, int col);

	/**
	 * Este metodo move um Stormtrooper para baixo. Se existir uma colisao com
	 * um Rebelde, o StormTrooper captura o.
	 * 
	 * @param row
	 * @param col
	 */
	void moveDownStorm(int row, int col);

	/**
	 * Este metodo ordena os movimentos dos StormTroopers. Se o Stormtrooper for
	 * do tipo BLACK ele primeiro move se para cima ate nao poder mais, para
	 * baixo ate nao poder mais, para a esquerda ate nao poder mais e para a
	 * direita ate nao poder mais, depois volta ao inicio da ordem. Se o
	 * Stormtrooper for do tipo ORANGE ele primeiro move se para a direita ate
	 * nao poder mais, para baixo ate nao poder mais, para a esquerda ate nao
	 * poder mais e para cima ate nao poder mais, depois volta ao inicio da
	 * ordem. Se o Stormtrooper for do tipo WHITE ele primeiro move se para a
	 * esquerda ate nao poder mais, para a direita ate nao poder mais, para
	 * baixo ate nao poder mais e para cima ate nao poder mais, depois volta ao
	 * inicio da ordem.
	 * 
	 * @param row
	 * @param col
	 */
	void moveOrder(int row, int col);

	/**
	 * Este metodo faz reset ao jogo, pondo tudo no seu estado inicial(como no
	 * construtor).
	 */
	void reset();

	/**
	 * Retorna o Iterador do mapa.
	 * 
	 * @return mapIterator
	 */
	MapIteratorInterface listarMap();

	/**
	 * Retorna o Iterador do vetor Rebeldes.
	 * 
	 * @return rebelIterator
	 */
	RebelIteratorInterface listarRebels();

	/**
	 * Retorna o Iterador dos stormTroopers.
	 * 
	 * @return stormIterator
	 */
	StormTrooperIteratorInterface listarStormTroopers();

	/**
	 * Retorna o Iterador dos Rebeldes capturados pelo StormTrooper com o nome
	 * recebido como parametro.
	 * 
	 * @param name
	 * @return capturedIterator
	 */
	CaptureIteratorInterface listCaptured(String name);

}
