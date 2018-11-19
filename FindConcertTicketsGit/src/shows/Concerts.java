/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package shows;

import java.time.LocalDate;

import group_tickets.Group;

public class Concerts extends Shows implements ConcertsInterface {

	private static final String TYPE = "CONCERT";

	private Group artist;
	private int price;

	public Concerts(String name, Group artist, String desc, LocalDate date, int price, int numberOfTickets) {
		super(name, desc, date, numberOfTickets);
		this.artist = artist;
		this.price = price;

	}

	public int getPrice() {
		return price;
	}

	public Group getArtist() {
		return artist;
	}

	public String getType() {
		return TYPE;
	}

	public String conteudo() {
		return name + "\n" + artist.getName() + "\n" + firstDay + "\n" + price + "\n" + numberOfTickets;
	}

	public LocalDate getLastDay() {
		return firstDay;
	}

	public int getNumberOfDays() {
		return 1;
	}

	public boolean hasEnoughSpace(int num) {
		return numberOfTickets >= num;
	}

}
