/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package users;

import java.util.Iterator;

import tickets.Tickets;

public class Client extends Users implements UsersInterface{

	private static final String TYPE = "CLIENT";

	public Client(String mail) {
		super(mail);
	}

	public String getType() {
		return TYPE;
	}

	public Iterator<Tickets> listTickets() {
		return tickets.listIterator();
	}

}
