/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package control;

import java.util.Comparator;

import shows.Shows;

public class ComparateDates implements Comparator<Shows> {


	public int compare(Shows o1, Shows o2) {
		return o1.getFirstDay().compareTo(o2.getFirstDay());
	}

}
