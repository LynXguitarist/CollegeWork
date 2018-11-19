/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */

package group_tickets;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import shows.Shows;

public abstract class Group implements GroupInterface {

	protected String name;
	protected LocalDate date;
	private ArrayList<Shows> shows;

	public Group(String name) {
		this.name = name;
		shows = new ArrayList<Shows>();
	}

	public void addShow(Shows show) {
		shows.add(show);
	}

	public String getName() {
		return name;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public abstract Object listMembers();

	public abstract String getType();

	public Iterator<Shows> listShows() {
		return shows.listIterator();
	}

}
