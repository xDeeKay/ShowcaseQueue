package net.dkcraft.showcasequeue.attendee;

import org.bukkit.Location;

public class Attendee {
	
	private String username;
	
	private String group;
	
	private Location origin;
	
	public Attendee(String username, String group, Location origin) {
		this.setUsername(username);
		this.setGroup(group);
		this.setOrigin(origin);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Location getOrigin() {
		return origin;
	}

	public void setOrigin(Location origin) {
		this.origin = origin;
	}
}
