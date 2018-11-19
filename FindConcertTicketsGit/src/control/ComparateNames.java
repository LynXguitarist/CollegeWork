/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package control;

import java.util.Comparator;

import shows.Shows;

public class ComparateNames implements Comparator<Shows> {

	public int compare(Shows o1, Shows o2) {
		if (o1.getTotal() > o2.getTotal()) {
			return -1;
		} else if (o1.getTotal() < o2.getTotal()) {
			return 1;
		} else if (o1.getFirstDay().equals(o2.getFirstDay())) {
			return o1.getName().compareTo(o2.getName());
		} else {
			return o1.getFirstDay().compareTo(o2.getFirstDay());
		}
	}
}
