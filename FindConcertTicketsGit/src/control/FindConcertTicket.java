/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import group_tickets.*;
import shows.*;
import tickets.*;
import users.*;

public class FindConcertTicket implements FindConcertTicketInterface {

	private ArrayList<Users> users;
	private ArrayList<Group> group;
	private ArrayList<Shows> shows;
	private ArrayList<Tickets> tickets;

	private boolean logged;
	private int numberClient, numberAdmin;

	public FindConcertTicket() {
		group = new ArrayList<Group>();
		users = new ArrayList<Users>();
		shows = new ArrayList<Shows>();
		tickets = new ArrayList<Tickets>();
		logged = false;
		numberClient = 0;
		numberAdmin = 0;
	}

	public void addUser(String type, String mail) {
		if (type.equals("ADMIN")) {
			Admin user = new Admin(mail);
			users.add(user);
			numberAdmin++;
			user.setPassword("admin" + getNumberAdmin());
		} else if (type.equals("CLIENT")) {
			Client user = new Client(mail);
			users.add(user);
			numberClient++;
			user.setPassword("client" + getNumberClient());
		}
	}

	public void addGroup(String type, String name) {
		if (type.equals("ARTIST")) {
			Artist artist = new Artist(name);
			group.add(artist);
		} else if (type.equals("BAND")) {
			Band band = new Band(name);
			group.add(band);
		}
	}

	public void addConcert(String name, Group artist, String desc, LocalDate date, int price, int ticketsForSale) {
		Concerts concert = new Concerts(name, artist, desc, date, price, ticketsForSale);
		shows.add(concert);
	}

	public void addFestival(String name, String desc, LocalDate firstDay, int numberOfTickets) {
		Festival festival = new Festival(name, desc, firstDay, numberOfTickets);
		shows.add(festival);
	}

	public void removeShow(String name) {
		shows.remove(getShow(name));
	}

	public void addTicket(String choice, Shows associatedShow, int price, int num) {
		if (choice.equals("CONCERT")) {
			ConcertTickets ticket = new ConcertTickets(associatedShow, price, num, associatedShow.getFirstDay());
			tickets.add(ticket);
		} else if (choice.equals("FESTIVAL")) {
			FestivalTickets ticket = new FestivalTickets(associatedShow, price, num);
			tickets.add(ticket);
		}
	}

	public int getNumberAdmin() {
		return numberAdmin;
	}

	public int getNumberClient() {
		return numberClient;
	}

	public boolean getLogged() {
		return logged;
	}

	public void logIn() {
		logged = true;
	}

	public void logOff() {
		logged = false;
	}

	public boolean hasUser(String mail) {
		boolean found = false;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getMail().equals(mail)) {
				found = true;
			}
		}
		if (found) {
			return true;
		}
		return false;
	}

	public Users getUser(String mail) {
		Users user = null;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getMail().equals(mail)) {
				user = users.get(i);
			}
		}
		return user;
	}

	public Users getLoggedUser() {
		Users user = null;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getLogged()) {
				user = users.get(i);
			}
		}
		return user;
	}

	public boolean hasGroup(String name) {
		boolean found = false;

		for (int i = 0; i < group.size(); i++) {
			if (group.get(i).getName().equals(name)) {
				found = true;
			}
		}
		if (found) {
			return true;
		}
		return false;
	}

	public Group getGroup(String name) {
		Group g = null;
		for (int i = 0; i < group.size(); i++) {
			if (group.get(i).getName().equals(name)) {
				g = group.get(i);
			}
		}
		return g;
	}

	public Shows getShow(String name) {
		Shows show = null;
		for (int i = 0; i < shows.size(); i++) {
			if (shows.get(i).getName().equals(name)) {
				show = shows.get(i);
			}
		}
		return show;
	}

	public Shows getShowDate(String name, LocalDate date) {
		Shows show = null;
		for (int i = 0; i < shows.size(); i++) {
			if (shows.get(i).getName().equals(name) && shows.get(i).getFirstDay().equals(date)) {
				show = shows.get(i);
			}
		}
		return show;
	}

	public boolean hasShow(String name) {
		boolean found = false;

		for (int i = 0; i < shows.size(); i++) {
			if (shows.get(i).getName().equals(name)) {
				found = true;
			}
		}
		if (found) {
			return true;
		}
		return false;
	}

	public boolean hasShowDate(String name, LocalDate date) {
		boolean found = false;

		for (int i = 0; i < shows.size(); i++) {
			if (shows.get(i).getName().equals(name) && shows.get(i).getFirstDay().equals(date)) {
				found = true;
			}
		}
		if (found) {
			return true;
		}
		return false;
	}

	public Festival getFestival(String name) {
		Festival festival = null;
		for (int i = 0; i < shows.size(); i++) {
			if (shows.get(i).getName().equals(name)) {
				festival = (Festival) shows.get(i);
			}
		}
		return festival;
	}

	public Festival getFestivalDate(String name, LocalDate date) {
		Festival festival = null;
		for (int i = 0; i < shows.size(); i++) {
			if (shows.get(i).getName().equals(name) && shows.get(i).getFirstDay().equals(date)) {
				festival = (Festival) shows.get(i);
			}
		}
		return festival;
	}

	public Concerts getConcertsDate(String name, LocalDate date) {
		Concerts concert = null;
		for (int i = 0; i < shows.size(); i++) {
			if (shows.get(i).getName().equals(name) && shows.get(i).getFirstDay().equals(date)) {
				concert = (Concerts) shows.get(i);
			}
		}
		return concert;
	}

	public Iterator<Shows> listShows() {
		return shows.listIterator();
	}

	public Iterator<Users> listUsers() {
		return users.listIterator();
	}

	public Iterator<Shows> listSortedShows() {
		Collections.sort(shows, new ComparateNames());
		return shows.listIterator();
	}


}
