/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package shows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;

public class Festival extends Shows implements FestivalInterface{

	private static final String TYPE = "FESTIVAL";
	private LocalDate lastDay;

	private ArrayList<DaysOfFestival> days;

	public Festival(String name, String desc, LocalDate firstDay, int numberOfTickets) {
		super(name, desc, firstDay, numberOfTickets);
		days = new ArrayList<DaysOfFestival>();
	}

	public void addDay(LocalDate date, int price) {
		DaysOfFestival day = new DaysOfFestival(date, price, numberOfTickets);
		days.add(day);
		lastDay = day.getDate();
	}

	public String conteudo() {
		return name + "\n";
	}

	public String getType() {
		return TYPE;
	}

	public DaysOfFestival getDayOfFestival(LocalDate date) {
		DaysOfFestival day = null;
		for (int i = 0; i < days.size(); i++) {
			if (days.get(i).getDate().equals(date)) {
				day = days.get(i);
			}
		}
		return day;

	}

	public LocalDate getLastDay() {
		return lastDay;
	}

	public DaysOfFestival getDayByIndex(int index) {
		DaysOfFestival day = null;
		for (int i = 0; i < days.size(); i++) {
			if (i == index) {
				day = days.get(i);
			}
		}
		return day;
	}

	public ListIterator<DaysOfFestival> listDays() {
		return days.listIterator();
	}

	public int getNumberOfDays() {
		return days.size();
	}

	public boolean hasEnoughSpace(int num) {
		int total = 0;
		for (int i = 0; i < days.size(); i++) {
			total = total + days.get(i).getNumberOfTickets();
		}
		if (total >= num) {
			return true;
		}
		return false;
	}

}
