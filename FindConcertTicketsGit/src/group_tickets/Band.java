/**
* @Author Frederico Lopes 42764 Andre Pinto 41671
*/
package group_tickets;

import java.util.ArrayList;
import java.util.Iterator;

public class Band extends Group implements GroupInterface {

	private static final String TYPE= "BAND";
	
	private ArrayList<String> members;

	public Band(String name) {
		super(name);
		members = new ArrayList<String>();
	}

	public void addMembers(String name) {
		members.add(name);
	}

	public Iterator<String> listMembers() {
		return members.listIterator();
	}

	public String getType() {
		return TYPE;
	}

	

}
