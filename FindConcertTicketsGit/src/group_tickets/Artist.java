/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package group_tickets;

public class Artist extends Group implements GroupInterface {

private static final String TYPE= "ARTIST";

	public Artist(String name) {
		super(name);
	}

	public void addMembers(String name) {
		this.name = name;
	}

	public String listMembers() {
		return name;
	}

	public String getType() {
		return TYPE;
	}

}
