/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package users;

import java.util.ArrayList;
import java.util.Iterator;

import shows.Festival;
import shows.Shows;
import tickets.ConcertTickets;
import tickets.FestivalTickets;
import tickets.Tickets;

public abstract class Users {

	protected String mail;
	protected String password;
	protected boolean logged;
	protected ArrayList<Tickets> tickets;

	public Users(String mail) {
		this.mail = mail;
		password = null;
		logged = false;
		tickets = new ArrayList<Tickets>();
	}

	public void addTickets(String choice, Shows associatedShow, int price, int num) {
		if (choice.equals("CONCERT")) {
			ConcertTickets ticket = new ConcertTickets(associatedShow, price, num, associatedShow.getFirstDay());
			tickets.add(ticket);
			associatedShow.setNumberOfTickets(num);
			associatedShow.setTotal(num);
		} else if (choice.equals("FESTIVAL")) {
			FestivalTickets ticket = new FestivalTickets(associatedShow, price, num);
			tickets.add(ticket);
			associatedShow.setNumberOfTickets(num);
			associatedShow.setTotal(num);
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public boolean getLogged() {
		return logged;
	}

	public void logIN() {
		logged = true;
	}

	public void logOut() {
		logged = false;
	}

	public FestivalTickets getFestivalTicket(Festival fest) {
		FestivalTickets ticket = null;
		for (int i = 0; i < tickets.size(); i++) {
			if (tickets.get(i).getAssociatedShow().equals(fest)) {
				ticket = (FestivalTickets) tickets.get(i);
			}
		}
		return ticket;

	}

	public abstract String getType();

	public abstract Iterator<Tickets> listTickets();

}
