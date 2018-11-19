
/**
 * @author Frederico Lopes 42764 Andre Pinto 41671
 */
import java.util.Scanner;

import control.*;
import location.*;

public class Main {
	// linha de comandos
	private static final String UPLOAD = "UPLOAD";
	private static final String PRINT = "PRINT";
	private static final String REBEL = "REBEL";
	private static final String START = "START";
	private static final String MOVE = "MOVE";
	private static final String LIST = "LIST";
	private static final String RESET = "RESET";
	private static final String EXIT = "EXIT";

	private static final String MAZEACCEPTED = "Maze accepted.";
	private static final String MAZEUNDEFINED = "Maze is undefined.";
	private static final String MAZEDEFINED = "Maze already defined.";
	private static final String GAMENOTSTART = "Game has not started.";
	private static final String GAMESETUPFIN = "Game setup has already finished.";
	private static final String GAMENEEDSREBEL = "Game needs a rebel.";
	private static final String GAMENOTON = "Game is not on.";
	private static final String REBELEXIST = "Rebel name already exists.";
	private static final String REBELNOTEXIST = "Rebel does not exist.";
	private static final String STORMNOTEXIST = "Stormtrooper does not exist.";
	private static final String INVPOS = "Invalid maze position.";
	private static final String REBELADD = "Rebel added.";
	private static final String NOTHINGLIST = "Nothing to list.";
	private static final String GAMERESET = "Game was reset.";
	private static final String QUIT = "Exiting.";

	public static void main(String[] args) {
		Controller control = new Controller();
		Scanner in = new Scanner(System.in);
		String comm = getCommand(in);

		while (!comm.equals(EXIT)) {
			switch (comm) {
			case UPLOAD:
				upload(in, control);
				break;
			case PRINT:
				print(control);
				break;
			case REBEL:
				rebel(in, control);
				break;
			case START:
				start(control);
				break;
			case MOVE:
				move(in, control);
				break;
			case LIST:
				list(in, control);
				break;
			case RESET:
				reset(control);
				break;
			default:
				System.out.println("ERRO");
			}
			System.out.println();
			comm = getCommand(in);
		}
		System.out.println(QUIT);
		System.out.println();
		in.close();
	}

	private static String getCommand(Scanner in) {
		String input;
		input = in.next().toUpperCase().trim();
		return input;
	}

	/**
	 * Comando Upload. O Utilizador cria o mapa, escrevendo na consola o row e
	 * col(dimensao do mapa) e o mapa em si, podendo preenche lo com
	 * armas,pocoes, paredes e StormTroopers.
	 * 
	 * @param in
	 * @param control
	 */
	private static void upload(Scanner in, Controller control) {

		int row = in.nextInt();
		int col = in.nextInt();
		in.nextLine();

		char[][] map = new char[row + 1][col + 1];
		for (int r = 1; r <= row; r++) {
			String line = in.nextLine();
			for (int c = 1; c <= col; c++) {
				map[r][c] = line.charAt(c - 1);
			}
		}
		if (control.hasMap()) {
			System.out.println(MAZEDEFINED);
		} else {
			control.setMap(map, row, col);
			System.out.println(MAZEACCEPTED);
		}

	}

	/**
	 * Comando Print. Este comando imprime o mapa(criado no Upload). Este
	 * comando falha caso o mapa ainda nao tenha sido definido. No final faz um
	 * print com os pontos ganhos pelos Rebeldes, o tempo decorrido, o numero de
	 * Rebeldes e o estado do jogo.
	 * 
	 * @param control
	 */
	private static void print(Controller control) {

		if (!control.hasMap()) {
			System.out.println(MAZEUNDEFINED);
		} else {
			MapIteratorInterface it = control.listarMap();
			it.init();
			int col = control.getCol();
			int i = 1;
			while (it.hasNext()) {
				System.out.print(it.next().symbol());
				if (i == col) {
					System.out.println();
					i = 1;
				} else {
					i++;
				}
			}
			if (control.playing()) {
				if (gameOver(control)) {
					control.setStateOFF();
				}
				System.out.println("Points: " + control.getPoints() + " Timer: " + control.getTimer() + " Rebels: "
						+ control.numberOfRebels() + " Game: " + control.getState());
			}
		}

	}

	/**
	 * Comando Rebel. Este comando cria um Rebelde numa posicao dada pelo
	 * Utilizador(escrita na consola) e o seu devido nome. Este comando falha
	 * caso o mapa nao esteja definido, o jogo ja tenha comecado, se ja existir
	 * um Rebelde com o mesmo nome ou se a posicao for invalida. Se tudo correr
	 * bem, o Rebelde e adicionado e sai um print a dizer que o Rebelde foi
	 * adicionado corretamente.
	 * 
	 * @param in
	 * @param control
	 */
	private static void rebel(Scanner in, Controller control) {

		String name = in.next();
		int row = in.nextInt();
		int col = in.nextInt();

		if (!control.hasMap()) {
			System.out.println(MAZEUNDEFINED);
		} else if (control.playing()) {
			System.out.println(GAMESETUPFIN);
		} else if (control.hasRebel(name)) {
			System.out.println(REBELEXIST);
		} else if (col >= control.getCol() || row >= control.getRow() || col < 1 || row < 1
				|| control.hasRebelThere(row, col) || control.isLocationTaken(row, col)) {
			System.out.println(INVPOS);
		} else {
			control.addRebel(name, row, col);
			System.out.println(REBELADD);
		}
	}

	/**
	 * Comando Start. Este comando inicia o jogo. Este comando falha caso o mapa
	 * nao esteja definido, caso o jogo ja tenha sido iniciado ou se ainda nao
	 * foram criados Rebeldes. Se tudo correr bem sai um print com os mesmos
	 * dados que o comando Print(pontos ganhos pelos Rebeldes, o tempo
	 * decorrido, o numero de Rebeldes e o estado do jogo).
	 * 
	 * @param control
	 */
	private static void start(Controller control) {

		if (!control.hasMap()) {
			System.out.println(MAZEUNDEFINED);
		} else if (control.playing()) {
			System.out.println(GAMESETUPFIN);
		} else if (control.numberOfRebels() == 0) {
			System.out.println(GAMENEEDSREBEL);
		} else {
			control.setStateON();
			control.setPlaying();
			System.out.println("Points: " + control.getPoints() + " Timer: " + control.getTimer() + " Rebels: "
					+ control.numberOfRebels() + " Game: " + control.getState());

		}
	}

	/**
	 * Comando Move. Este comando move primeiro os Rebeldes e no final de todos
	 * os Rebeldes se terem movido, move os StormTroopers. Movimentam se para
	 * onde o Utilizador quiser(R,L,D,U)(direita,esquerda,baixo,cima)(caso se
	 * possam mover(nao sair do mapa nem chocar contra paredes ou outros
	 * Rebeldes). Este comando falha caso o jogo ainda nao tenha sido iniciado.
	 * No final faz um print igual ao do comando Print(pontos ganhos pelos
	 * Rebeldes, o tempo decorrido, o numero de Rebeldes e o estado do jogo).
	 * 
	 * @param in
	 * @param control
	 */
	private static void move(Scanner in, Controller control) {

		RebelIteratorInterface it = control.listarRebels();
		it.init();
		MapIteratorInterface iterator = control.listarMap();
		iterator.init();

		while (it.hasNext()) {
			if (it.getCurrent().getState().equals("CAPTURED")) {
				if (it.hasNext()) {
					it.next();
				} else {
					break;
				}
				break;
			} else {
				String option = in.next().toUpperCase();
				if (!control.playing()) {
					System.out.println(GAMENOTON);
					break;
				} else {

					switch (option) {
					case "R":
						if ((it.getCurrent().getCol()) >= control.getCol()
								|| control.hasRebelThere(it.getCurrent().getRow(), it.getCurrent().getCol() + 1)
								|| control.isLocationImpossible(it.getCurrent().getRow(),
										it.getCurrent().getCol() + 1)) {

							it.next();
							break;
						} else {

							if (iterator.getCurrent(it.getCurrent().getRow(), it.getCurrent().getCol() + 1)
									.symbol() == WeaponInterface.WEAPON) {
								it.getCurrent().addPoints();

							}
							control.moveRight(it.getCurrent().getRow(), it.getCurrent().getCol());

							it.next();

						}

						break;
					case "L":
						if (it.getCurrent().getCol() <= 1
								|| control.hasRebelThere(it.getCurrent().getRow(), it.getCurrent().getCol() - 1)
								|| control.isLocationImpossible(it.getCurrent().getRow(),
										it.getCurrent().getCol() - 1)) {

							it.next();
							break;
						} else {
							if (iterator.getCurrent(it.getCurrent().getRow(), it.getCurrent().getCol() - 1)
									.symbol() == WeaponInterface.WEAPON) {
								it.getCurrent().addPoints();

							}
							control.moveLeft(it.getCurrent().getRow(), it.getCurrent().getCol());

							it.next();

						}

						break;
					case "U":
						if (it.getCurrent().getRow() <= 1
								|| control.hasRebelThere(it.getCurrent().getRow() - 1, it.getCurrent().getCol())
								|| control.isLocationImpossible(it.getCurrent().getRow() - 1,
										it.getCurrent().getCol())) {

							it.next();
							break;
						} else {
							if (iterator.getCurrent(it.getCurrent().getRow() - 1, it.getCurrent().getCol())
									.symbol() == WeaponInterface.WEAPON) {
								it.getCurrent().addPoints();

							}
							control.moveUp(it.getCurrent().getRow(), it.getCurrent().getCol());

							it.next();

						}

						break;
					case "D":
						if ((it.getCurrent().getRow()) >= control.getRow()
								|| control.hasRebelThere(it.getCurrent().getRow() + 1, it.getCurrent().getCol())
								|| control.isLocationImpossible(it.getCurrent().getRow() + 1,
										it.getCurrent().getCol())) {

							it.next();
							break;
						} else {
							if (iterator.getCurrent(it.getCurrent().getRow() + 1, it.getCurrent().getCol())
									.symbol() == WeaponInterface.WEAPON) {
								it.getCurrent().addPoints();

							}
							control.moveDown(it.getCurrent().getRow(), it.getCurrent().getCol());

							it.next();

						}

						break;
					}

				}

			}

		}
		moveStormTroopers(control);
		if (control.playing()) {
			if (gameOver(control)) {
				control.setStateOFF();
			}
			control.addPoints();
			control.incTimer();

			System.out.println("Points: " + control.getPoints() + " Timer: " + control.getTimer() + " Rebels: "
					+ control.numberOfRebels() + " Game: " + control.getState());
		}
	}

	/**
	 * Comando List. Este comando lista ou um path de um Rebelde(comando
	 * ListPath) ou prisioneiros de um dado StormTrooper(comando ListPrisoners)
	 * ou os Rebeldes activos, ou os StormTroopers activos, ou ainda todos os
	 * avatars(Rebeldes e Stormtroopers). Este comando falha caso o mapa ainda
	 * nao esteja definido.
	 * 
	 * @param in
	 * @param control
	 */
	private static void list(Scanner in, Controller control) {

		String choice = in.next().toUpperCase();
		if (choice.equals("PATH")) {
			listPath(in, control);
		} else if (choice.equals("PRISONERS")) {
			listPrisoners(in, control);

		} else {
			if (!control.hasMap()) {
				System.out.println(MAZEUNDEFINED);
			} else {
				if (choice.equals("REBELS")) {
					listRebels(control);
				} else if (choice.equals("STORMTROOPERS")) {
					listStormTrooper(control);
				} else if (choice.equals("ALL")) {
					System.out.println("All avatars:");
					StormTrooperIteratorInterface it = control.listarStormTroopers();
					it.init();
					RebelIteratorInterface it2 = control.listarRebels();
					it2.init();
					if (!it.hasNext() && !it2.hasNext()) {
						System.out.println(NOTHINGLIST);
					} else {
						while (it.hasNext()) {
							System.out.println("ST-" + it.getCurrent().getType() + "-" + it.getCurrent().getPlaced()
									+ " " + it.getCurrent().getRow() + " " + it.getCurrent().getCol() + " "
									+ it.getCurrent().getState());
							it.next();
						}
						while (it2.hasNext()) {

							System.out.println(it2.getCurrent().getName() + " " + it2.getCurrent().getRow() + " "
									+ it2.getCurrent().getCol() + " " + it2.getCurrent().getState() + " "
									+ it2.getCurrent().getPoints());
							it2.next();
						}
					}

				}

			}
		}

	}

	/**
	 * Comando ListPath. Este comando lista o caminho do Rebelde com o nome dado
	 * pelo Utilizador(escrito na consola). Este comando falha caso o jogo ainda
	 * nao tenha sido comecado ou o Rebelde nao exista. No final sai um print a
	 * dizer os pacos que o Rebelde deu e o caminho percorrido.
	 * 
	 * @param in
	 * @param control
	 */
	private static void listPath(Scanner in, Controller control) {
		String name = in.next();
		if (!control.playing()) {
			System.out.println(GAMENOTSTART);
		} else if (!control.hasRebel(name)) {
			System.out.println(REBELNOTEXIST);
		} else {
			System.out.println("Rebel " + name + " has taken " + control.getRebel(name).getSteps() + " steps and is "
					+ control.getRebel(name).getState() + ":");
			PathIteratorInterface it = control.getRebel(name).listPath();
			it.init();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		}
	}

	/**
	 * ListRebels chamado no comando List, lista os Rebeldes que estao activos.
	 * 
	 * @param control
	 */
	private static void listRebels(Controller control) {

		RebelIteratorInterface it = control.listarRebels();
		it.init();
		if (control.numberOfRebels() == 0) {
			System.out.println(NOTHINGLIST);
		} else {
			System.out.println("Rebels:");
			while (it.hasNext()) {
				if (it.getCurrent().getState().equals("CAPTURED")) {
					if (it.hasNext()) {
						it.next();
					} else {
						break;
					}
				} else {
					System.out.println(
							it.getCurrent().getName() + " " + it.getCurrent().getRow() + " " + it.getCurrent().getCol()
									+ " " + it.getCurrent().getState() + " " + it.getCurrent().getPoints());
					it.next();
				}
			}
		}
	}

	/**
	 * ListStormTrooper chamado no comando no List , lista os StormTroopers
	 * activos.
	 * 
	 * @param control
	 */
	private static void listStormTrooper(Controller control) {
		StormTrooperIteratorInterface it = control.listarStormTroopers();
		it.init();
		if (!it.hasNext()) {
			System.out.println(NOTHINGLIST);
		} else {
			System.out.println("Stormtroopers:");
			while (it.hasNext()) {
				if (it.getCurrent().getState().equals("CAPTURED")) {
					if (it.hasNext()) {
						it.next();
					} else {
						break;
					}
				} else {
					System.out.println(it.getCurrent().getName() + " " + it.getCurrent().getRow() + " "
							+ it.getCurrent().getCol() + " " + it.getCurrent().getState());

					it.next();
				}
			}
		}
	}

	/**
	 * Comando ListPrisoners. Este comando lista todos os Rebeldes capturados
	 * pelo StormTrooper com o identificador escrito pelo Utilizador na consola.
	 * 
	 * @param in
	 * @param control
	 */
	private static void listPrisoners(Scanner in, Controller control) {
		String name = in.next();
		if (!control.hasStormTrooper(name)) {
			System.out.println(STORMNOTEXIST);
		} else {
			CaptureIteratorInterface it = control.listCaptured(name);
			it.init();
			System.out.println("Stormtrooper " + name + " has captured " + control.getStormTrooper(name).getCaptured()
					+ " rebels and is " + control.getStormTrooper(name).getState() + ":");

			while (it.hasNext()) {
				System.out.println(it.getCurrent().getName() + " " + it.getCurrent().getPosCaptured());
				it.next();
			}
		}
	}

	/**
	 * MoveStormTroopers chamado no comando Move, move os StormTroopers que
	 * estao activos.
	 * 
	 * @param control
	 */
	private static void moveStormTroopers(Controller control) {
		StormTrooperIteratorInterface it = control.listarStormTroopers();
		it.init();
		while (it.hasNext()) {
			if (it.getCurrent().getState().equals("ACTIVE")) {
				if (it.getCurrent().getType() == StormtrooperOrangeInterface.ORANGE) {

					control.moveOrder(it.getCurrent().getRow(), it.getCurrent().getCol());
					it.next();
				} else if (it.getCurrent().getType() == StormtrooperBlackInterface.BLACK) {

					control.moveOrder(it.getCurrent().getRow(), it.getCurrent().getCol());
					it.next();
				} else if (it.getCurrent().getType() == StormtrooperWhiteInterface.WHITE) {

					control.moveOrder(it.getCurrent().getRow(), it.getCurrent().getCol());
					it.next();
				}
			} else {
				it.next();
			}
		}
	}

	/**
	 * Comando Reset. Este comando da reset ao jogo, chama o metodo reset() da
	 * classe Controller. Este comando no final faz print de uma mensagem a
	 * dizer que o jogo foi corretamente reiniciado, este comando tem sempre
	 * sucesso.
	 * 
	 * @param control
	 */
	private static void reset(Controller control) {
		control.reset();
		System.out.println(GAMERESET);
	}

	/**
	 * GameOver verifica se o jogo ainda esta activo(ON), percorrendo o Iterador
	 * do mapa a ver se ainda existem Rebeldes ou armas. Caso um deles ja nao
	 * exista no mapa, este fica true(gameOver).
	 * 
	 * @param control
	 * @return true or false
	 */
	private static boolean gameOver(Controller control) {
		MapIteratorInterface it = control.listarMap();
		it.init();
		if (!it.searchNext(WeaponInterface.WEAPON) || control.numberOfRebels() == 0) {
			control.setStateOFF();
			return true;

		} else {
			return false;
		}
	}

}
