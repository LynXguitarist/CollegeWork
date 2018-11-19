import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

import control.ComparateDates;
import control.FindConcertTicket;
import shows.*;
import tickets.FestivalTickets;
import tickets.Tickets;
import group_tickets.*;

public class Main {

	// linha de comandos
	// comandos 2.1
	private static final String SHOWS = "SHOWS";
	private static final String SHOWSBYCLIENTS = "SHOWSBYCLIENTS";
	private static final String SHOWSBYTYPE = "SHOWSBYTYPE";
	private static final String SHOW = "SHOW";
	private static final String SEARCH = "SEARCH";
	private static final String EXIT = "EXIT";

	// comandos 2.2
	private static final String ADDARTIST = "ADDARTIST";
	private static final String ADDSHOW = "ADDSHOW";

	// comandos 2.3
	private static final String BUYTICKET = "BUYTICKET";
	private static final String MYTICKETS = "MYTICKETS";

	// comandos 2.4
	private static final String REGISTER = "REGISTER";
	private static final String LOGIN = "LOGIN";
	private static final String LOGOUT = "LOGOUT";

	// mensagens
	private static final String QUIT = "Exiting.";

	public static void main(String[] args) {
		FindConcertTicket find = new FindConcertTicket();
		Scanner in = new Scanner(System.in);
		String comm = getCommand(in);

		while (!comm.equals(EXIT)) {
			switch (comm) {
			// comandos 2.1
			case SHOWS:
				shows(find);
				break;
			case SHOWSBYCLIENTS:
				showsByClients(find);
				break;
			case SHOWSBYTYPE:
				showsByType(in, find);
				break;
			case SHOW:
				show(in, find);
				break;
			case SEARCH:
				search(in, find);
				break;
			case REGISTER:
				register(in, find);
				break;
			// comandos 2.2
			case ADDARTIST:
				addArtist(in, find);
				break;
			case ADDSHOW:
				addShow(in, find);
				break;
			// comandos 2.3
			case BUYTICKET:
				buyTicket(in, find);
				break;
			case MYTICKETS:
				myTickets(find);
				break;
			// comandos 2.4

			case LOGIN:
				login(in, find);
				break;
			case LOGOUT:
				logout(find);
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
		input = in.nextLine().toUpperCase();
		return input;
	}

	/**
	 * Lista todos os shows.
	 * 
	 * @param find
	 */
	private static void shows(FindConcertTicket find) {
		System.out.println("All shows:");
		iterateAllShows(find);

	}

	/**
	 * Lista todos os shows por ordem(mais vendidos,data e depois nome).
	 * 
	 * @param find
	 */
	private static void showsByClients(FindConcertTicket find) {
		System.out.println("Most sold shows:");
		iterateByClients(find);

	}

	/**
	 * Lista um tipo de shows, dado como choice.
	 * 
	 * @param in
	 * @param find
	 */
	private static void showsByType(Scanner in, FindConcertTicket find) {
		String choice = in.nextLine().toUpperCase();
		iterateByType(find, choice);

	}

	/**
	 * Lista todos os dados de um show.
	 * 
	 * @param in
	 * @param find
	 */
	private static void show(Scanner in, FindConcertTicket find) {
		String name = in.nextLine();
		String date = in.nextLine();
		LocalDate data = LocalDate.parse(date);
		if (!find.hasShowDate(name, data)) {
			System.out.println("Show does not exist.");
		} else {
			System.out.println(name + " on " + data + ":");
			if (find.getShowDate(name, data).getType().equals("CONCERT")) {
				System.out.println(find.getShowDate(name, data).conteudo());
			} else if (find.getShowDate(name, data).getType().equals("FESTIVAL")) {
				System.out.println(find.getFestivalDate(name, data).getName());
				iterateFestivals(find, name, data);
			}
		}

	}

	/**
	 * Lista os shows onde a banda vai tocar.
	 * 
	 * @param in
	 * @param find
	 */
	private static void search(Scanner in, FindConcertTicket find) {
		String name = in.nextLine();
		iterateSearch(find, name);
	}

	/**
	 * Regista um utilizador(pode ser client ou admin).
	 * 
	 * @param in
	 * @param find
	 */
	private static void register(Scanner in, FindConcertTicket find) {
		String type = in.nextLine().toUpperCase();
		String mail = in.nextLine();
		if (find.getLogged()) {
			if (find.getUser(mail).getLogged()) {
				System.out.println("User already logged in.");
			} else {
				System.out.println("There is a user logged in.");
			}
		} else if (find.hasUser(mail)) {
			System.out.println("User already exists.");
		} else {
			find.addUser(type, mail);
			System.out.println("User was registered: " + find.getUser(mail).getPassword());
		}
	}

	// comandos 2.2

	/**
	 * Adiciona um artista.
	 * 
	 * @param in
	 * @param find
	 */
	private static void addArtist(Scanner in, FindConcertTicket find) {
		String type = null;
		String name = in.nextLine();
		int numberOfAlbuns = in.nextInt();
		in.nextLine();
		while (numberOfAlbuns > 0) {
			in.nextLine();// discos
			numberOfAlbuns--;
		}
		int numberOfElements = in.nextInt();
		in.nextLine();
		if (numberOfElements == 1) {
			type = "ARTIST";
		} else {
			type = "BAND";
			while (numberOfElements > 0) {
				in.nextLine(); // name of the elements of the band
				numberOfElements--;
			}
		}
		if (!find.getLogged() || !find.getLoggedUser().getType().equals("ADMIN")) {
			System.out.println("User cannot execute this command.");
		} else if (find.hasGroup(name)) {
			System.out.println("Artist name already exists.");
		} else {
			find.addGroup(type, name);
			System.out.println("Artist added.");
		}

	}

	/**
	 * Adiciona um show.
	 * 
	 * @param in
	 * @param find
	 */
	private static void addShow(Scanner in, FindConcertTicket find) {
		boolean found = false;
		String name = in.nextLine();
		String desc = in.nextLine();
		int numberOfTicketsAvailable = in.nextInt();
		in.nextLine();
		int contador = 0;
		String[] array = new String[10];
		System.out.println("CONCERT OR FESTIVAL?");
		String choice = in.nextLine().toUpperCase();
		if (choice.equals("CONCERT")) {
			String artist = in.nextLine();
			String date = in.nextLine();
			int price = in.nextInt();
			in.nextLine();
			LocalDate data = LocalDate.parse(date);
			if (find.hasShow(name) && find.getShow(name).getFirstDay().equals(data)) {
				found = true;
				System.out.println("Show already exists.");
			} else if (!find.getLogged() || !find.getLoggedUser().getType().equals("ADMIN")) {
				found = true;
				System.out.println("User cannot execute this command.");
			} else {
				find.addConcert(name, find.getGroup(artist), desc, data, price, numberOfTicketsAvailable);
				if (find.hasGroup(artist)) {
					find.getGroup(artist).addShow(find.getShow(name));
				} else {
					found = true;
					System.out.println("Artist name(s) do(es) not exist(s):");
					System.out.println(artist);
				}
			}
		} else if (choice.equals("FESTIVAL")) {
			int numberOfDays = in.nextInt();
			in.nextLine();
			String date = in.nextLine();
			LocalDate data = LocalDate.parse(date);
			int tmp = numberOfDays;
			if (find.hasShow(name) && find.getShow(name).getFirstDay().equals(data)) {
				found = true;
				System.out.println("Show already exists.");
			} else {
				find.addFestival(name, desc, data, numberOfTicketsAvailable);
			}

			while (tmp > 0) {
				find.getFestival(name).addDay(data, 0);
				int numberOfArtists = in.nextInt();
				in.nextLine();
				while (numberOfArtists > 0) {
					String nameOfArtist = in.nextLine();
					if (!find.hasGroup(nameOfArtist)) {
						array[contador] = nameOfArtist;
						contador++;
					} else {
						find.getFestival(name).getDayOfFestival(data).addGroup(find.getGroup(nameOfArtist));
						find.getGroup(nameOfArtist).addShow(find.getShow(name));
					}
					numberOfArtists--;
				}
				tmp--;
				data = data.plusDays(1);

			}
			while (numberOfDays > 0) {
				int dayX = in.nextInt();
				int pricePerDay = in.nextInt();
				in.nextLine();
				find.getFestival(name).getDayByIndex(dayX - 1).setPrice(pricePerDay);
				numberOfDays--;
			}
			if (contador > 0) {
				found = true;
				System.out.println("Artist name(s) do(es) not exist(s):");
				for (int i = 0; i < contador; i++) {
					System.out.println(array[i]);
				}
			} else if (!find.getLogged() || !find.getLoggedUser().getType().equals("ADMIN")) {
				found = true;
				System.out.println("User cannot execute this command.");
			}

		}
		if (!found) {
			System.out.println("Show added.");
		} else {
			find.removeShow(name);
		}

	}

	// comandos 2.3

	/**
	 * Compra um ou mais bilhetes.
	 * 
	 * @param in
	 * @param find
	 */
	private static void buyTicket(Scanner in, FindConcertTicket find) {
		String name = in.nextLine();
		String date = in.nextLine();
		LocalDate data = LocalDate.parse(date);
		System.out.println("CONCERT OR FESTIVAL?");
		String choice = in.nextLine().toUpperCase();
		if (choice.equals("CONCERT")) {
			int num = in.nextInt();
			in.nextLine();
			if (!find.getLogged() || !find.getLoggedUser().getType().equals("CLIENT")) {
				System.out.println("User cannot execute this command.");
			} else if (!find.hasShowDate(name, data)) {
				System.out.println("Show does not exist.");
			} else if (!find.getShowDate(name, data).hasEnoughSpace(num)) {
				System.out.println("There are not sufficient seats for the request.");
			} else {
				find.getLoggedUser().addTickets(choice, find.getConcertsDate(name, data),
						find.getConcertsDate(name, data).getPrice() * num, num);
				find.addTicket(choice, find.getConcertsDate(name, data),
						find.getConcertsDate(name, data).getPrice() * num, num);
				System.out
						.println("Ticket bought with cost " + find.getConcertsDate(name, data).getPrice() * num + ".");
			}
		} else if (choice.equals("FESTIVAL")) {
			boolean found = false;
			int num = in.nextInt();
			in.nextLine();
			int tmp = num;
			if (!find.getLogged() || !find.getLoggedUser().getType().equals("CLIENT")) {
				found = true;
				System.out.println("User cannot execute this command.");
			} else if (!find.hasShowDate(name, data)) {
				found = true;
				System.out.println("Show does not exist.");
			} else if (!find.getShowDate(name, data).hasEnoughSpace(num)) {
				found = true;
				System.out.println("There are not sufficient seats for the request.");
			} else {
				find.getLoggedUser().addTickets(choice, find.getFestivalDate(name, data),
						find.getFestivalDate(name, data).getDayByIndex(num - 1).getPrice(), num);
				find.addTicket(choice, find.getFestivalDate(name, data),
						find.getFestivalDate(name, data).getDayByIndex(num - 1).getPrice(), num);

			}
			// vou mudar
			while (tmp > 0) {
				String day = in.nextLine();
				LocalDate dayX = LocalDate.parse(day);
				if (found) {
					tmp--;
				} else {
					find.getFestivalDate(name, data).getDayOfFestival(dayX).setNumberOfTickets(1);
					tmp--;
				}

			}
			if (!found) {
				System.out.println("Ticket bought with cost "
						+ find.getFestivalDate(name, data).getDayByIndex(num - 1).getPrice() + ".");
			}
		}

	}

	/**
	 * Lista os bilhetes do user que esta logged in.
	 * 
	 * @param find
	 */
	private static void myTickets(FindConcertTicket find) {
		System.out.println("My tickets:");
		iterateConcertTickets(find);
		iterateFestivalTickets(find);
	}

	// comandos 2.4

	/**
	 * Loga um user.
	 * 
	 * @param in
	 * @param find
	 */
	private static void login(Scanner in, FindConcertTicket find) {
		String mail = in.nextLine();
		String password = in.nextLine();
		if (!find.hasUser(mail)) {
			System.out.println("User does not exist.");
		} else if (find.getUser(mail).getLogged()) {
			System.out.println("User already logged in.");
		} else if (find.getLogged()) {
			System.out.println("Another user is logged in.");
		} else if (!find.getUser(mail).getPassword().equals(password)) {
			System.out.println("Wrong password.");
		} else {
			find.getUser(mail).logIN();
			find.logIn();
			find.getLoggedUser();
			System.out.println("Welcome " + mail);

		}
	}

	/**
	 * Faz logout ao user que esta logged in.
	 * 
	 * @param find
	 */
	private static void logout(FindConcertTicket find) {
		if (!find.getLogged()) {
			System.out.println("No user is logged in.");
		} else {
			System.out.println("Goodbye " + find.getLoggedUser().getMail());
			find.logOff();
			find.getLoggedUser().logOut();
		}
	}

	private static void iterateAllShows(FindConcertTicket find) {
		Iterator<Shows> it = find.listShows();
		while (it.hasNext()) {
			Shows tmp = it.next();
			if (tmp.getType().equals("CONCERT")) {
				System.out.println(tmp.conteudo());
			} else if (tmp.getType().equals("FESTIVAL")) {
				Festival tmp2 = (Festival) tmp;
				System.out.println(tmp2.getName());
				Iterator<DaysOfFestival> iterator = tmp2.listDays();
				while (iterator.hasNext()) {
					DaysOfFestival day = iterator.next();
					System.out.println(day.getDate());
					Iterator<Group> group = day.listAlinhamento();
					while (group.hasNext()) {
						System.out.println(group.next().getName());
					}

				}
				System.out.println(tmp2.getFirstDay());
				System.out.println(tmp2.getLastDay());
				int num = 1;
				Iterator<DaysOfFestival> iterator2 = tmp2.listDays();
				while (iterator2.hasNext()) {
					System.out.println(num + " " + iterator2.next().getPrice());
					num++;
				}
				Iterator<DaysOfFestival> iterator3 = tmp2.listDays();

				while (iterator3.hasNext()) {
					System.out.println(iterator3.next().getDate() + " " + tmp.getNumberOfTickets());
				}

			}
		}
	}

	private static void iterateByClients(FindConcertTicket find) {
		Iterator<Shows> it = find.listSortedShows();
		while (it.hasNext()) {
			Shows tmp = it.next();
			if (tmp.getType().equals("CONCERT")) {
				System.out.println(tmp.conteudo());
			} else if (tmp.getType().equals("FESTIVAL")) {
				Festival tmp2 = (Festival) tmp;
				System.out.println(tmp2.getName());
				Iterator<DaysOfFestival> iterator = tmp2.listDays();
				while (iterator.hasNext()) {
					DaysOfFestival day = iterator.next();
					System.out.println(day.getDate());
					Iterator<Group> group = day.listAlinhamento();
					while (group.hasNext()) {
						System.out.println(group.next().getName());
					}

				}
				System.out.println(tmp2.getFirstDay());
				System.out.println(tmp2.getLastDay());
				int num = 1;
				Iterator<DaysOfFestival> iterator2 = tmp2.listDays();
				while (iterator2.hasNext()) {
					System.out.println(num + " " + iterator2.next().getPrice());
					num++;
				}
				Iterator<DaysOfFestival> iterator3 = tmp2.listDays();
				int day = 0;
				while (iterator3.hasNext()) {
					System.out.println(iterator3.next().getDate() + " " + tmp2.getDayByIndex(day).getNumberOfTickets());
					day++;
				}
			}

		}

	}

	private static void iterateByType(FindConcertTicket find, String choice) {
		Iterator<Shows> it = find.listShows();
		if (choice.equals("CONCERT")) {
			System.out.println("Concerts:");
			ArrayList<Shows> shows = new ArrayList<Shows>();
			while (it.hasNext()) {
				Shows tmp = it.next();
				if (tmp.getType().equals("CONCERT")) {
					shows.add(tmp);
				}
			}
			Collections.sort(shows, new ComparateDates());
			for (int i = 0; i < shows.size(); i++) {
				System.out.println(shows.get(i).conteudo());
			}
		} else if (choice.equals("FESTIVAL")) {
			System.out.println("Festivals:");
			ArrayList<Festival> shows = new ArrayList<Festival>();
			while (it.hasNext()) {
				Shows tmp = it.next();
				if (tmp.getType().equals("FESTIVAL")) {
					Festival tmp2 = (Festival) tmp;
					shows.add(tmp2);
				}
			}
			Collections.sort(shows, new ComparateDates());
			for (int i = 0; i < shows.size(); i++) {
				System.out.println(shows.get(i).getName());
				Iterator<DaysOfFestival> iterator = shows.get(i).listDays();
				while (iterator.hasNext()) {
					DaysOfFestival day = iterator.next();
					System.out.println(day.getDate());
					Iterator<Group> group = day.listAlinhamento();
					while (group.hasNext()) {
						System.out.println(group.next().getName());
					}

				}
				System.out.println(shows.get(i).getFirstDay());
				System.out.println(shows.get(i).getLastDay());
				int num = 1;
				Iterator<DaysOfFestival> iterator2 = shows.get(i).listDays();
				while (iterator2.hasNext()) {
					System.out.println(num + " " + iterator2.next().getPrice());
					num++;
				}
				Iterator<DaysOfFestival> iterator3 = shows.get(i).listDays();

				while (iterator3.hasNext()) {
					System.out.println(iterator3.next().getDate() + " " + shows.get(i).getNumberOfTickets());
				}

			}
		} else {
			System.out.println("Unknown type of show.");
		}

	}

	private static void iterateFestivals(FindConcertTicket find, String name, LocalDate data) {
		Iterator<DaysOfFestival> iterator = find.getFestivalDate(name, data).listDays();
		while (iterator.hasNext()) {
			DaysOfFestival day = iterator.next();
			System.out.println(day.getDate());
			Iterator<Group> group = day.listAlinhamento();
			while (group.hasNext()) {
				System.out.println(group.next().getName());
			}

		}
		System.out.println(find.getFestivalDate(name, data).getFirstDay());
		System.out.println(find.getFestivalDate(name, data).getLastDay());
		int num = 1;
		Iterator<DaysOfFestival> iterator2 = find.getFestivalDate(name, data).listDays();
		while (iterator2.hasNext()) {
			System.out.println(num + " " + iterator2.next().getPrice());
			num++;
		}
		Iterator<DaysOfFestival> iterator3 = find.getFestivalDate(name, data).listDays();

		while (iterator3.hasNext()) {
			System.out
					.println(iterator3.next().getDate() + " " + find.getFestivalDate(name, data).getNumberOfTickets());
		}
	}

	private static void iterateSearch(FindConcertTicket find, String name) {
		if (!find.hasGroup(name)) {
			System.out.println("Concerts of " + name + ":");
			System.out.println("Festivals where " + name + " will play:");
		} else {
			Iterator<Shows> it = find.getGroup(name).listShows();
			ArrayList<Shows> shows = new ArrayList<Shows>();
			System.out.println("Concerts of " + name + ":");
			while (it.hasNext()) {
				Shows tmp = it.next();
				if (tmp.getType().equals("CONCERT")) {
					shows.add(tmp);
				}
			}
			Collections.sort(shows, new ComparateDates());
			for (int i = 0; i < shows.size(); i++) {
				System.out.println(shows.get(i).conteudo());
			}
			System.out.println("Festivals where " + name + " will play:");
			Iterator<Shows> it2 = find.getGroup(name).listShows();
			ArrayList<Festival> festivals = new ArrayList<Festival>();
			while (it2.hasNext()) {
				Shows tmp = it2.next();
				if (tmp.getType().equals("FESTIVAL")) {
					Festival tmp2 = (Festival) tmp;
					festivals.add(tmp2);
				}
			}
			Collections.sort(festivals, new ComparateDates());
			for (int i = 0; i < festivals.size(); i++) {
				System.out.println(festivals.get(i).getName());
				Iterator<DaysOfFestival> iterator = festivals.get(i).listDays();
				while (iterator.hasNext()) {
					DaysOfFestival day = iterator.next();
					System.out.println(day.getDate());
					Iterator<Group> group = day.listAlinhamento();
					while (group.hasNext()) {
						System.out.println(group.next().getName());
					}

				}
				System.out.println(festivals.get(i).getFirstDay());
				System.out.println(festivals.get(i).getLastDay());
				int num = 1;
				Iterator<DaysOfFestival> iterator2 = festivals.get(i).listDays();
				while (iterator2.hasNext()) {
					System.out.println(num + " " + iterator2.next().getPrice());
					num++;
				}
				Iterator<DaysOfFestival> iterator3 = festivals.get(i).listDays();

				while (iterator3.hasNext()) {
					System.out.println(iterator3.next().getDate() + " " + festivals.get(i).getNumberOfTickets());
				}

			}
		}

	}

	private static void iterateConcertTickets(FindConcertTicket find) {
		Iterator<Tickets> it = find.getLoggedUser().listTickets();
		while (it.hasNext()) {
			Tickets tmp = it.next();
			if (tmp.getAssociatedShow().getType().equals("CONCERT")) {
				System.out.println(tmp.conteudo());
			}
		}
	}

	private static void iterateFestivalTickets(FindConcertTicket find) {
		Iterator<Tickets> it = find.getLoggedUser().listTickets();
		while (it.hasNext()) {
			Tickets tmp = it.next();
			if (tmp.getAssociatedShow().getType().equals("FESTIVAL")) {
				FestivalTickets tmp2 = (FestivalTickets) tmp;
				System.out.println(tmp2.conteudo());
				System.out.println(tmp2.getAssociatedShow().getFirstDay());
				if (tmp2.getAssociatedShow().getNumberOfDays() > 2) {
					System.out.println(tmp2.getAssociatedShow().getLastDay());
				}
				Iterator<LocalDate> iterator = tmp2.listDays();
				while (iterator.hasNext()) {
					System.out.println(iterator.next());
				}
				System.out.println(tmp2.getPrice());
			}
		}
	}

}
