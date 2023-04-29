package net.dkcraft.showcasequeue.util;

import net.dkcraft.showcasequeue.Main;

public class Config {

	public Main plugin;

	public Config(Main plugin) {
		this.plugin = plugin;
	}

	public void loadConfig() {

		plugin.getConfig().options().copyDefaults(true);
		plugin.saveDefaultConfig();

		// Group
		this.setGroup(plugin.getConfig().getString("group"));

		// Interval
		this.setInterval(plugin.getConfig().getInt("interval"));

		// Location
		this.setWorld(plugin.getConfig().getString("location.world"));
		this.setX(plugin.getConfig().getDouble("location.x"));
		this.setY(plugin.getConfig().getDouble("location.y"));
		this.setZ(plugin.getConfig().getDouble("location.z"));
		this.setYaw(plugin.getConfig().getDouble("location.yaw"));
		this.setPitch(plugin.getConfig().getDouble("location.pitch"));
	}

	// Group
	private String group;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
	public void updateGroup(String group) {
		setGroup(group);
		plugin.getConfig().set("group", group);
		plugin.saveConfig();
	}

	// Interval
	private int interval;
	
	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	public void updateInterval(int interval) {
		setInterval(interval);
		plugin.getConfig().set("interval", interval);
		plugin.saveConfig();
	}

	// Location
	private String world;
	private double x;
	private double y;
	private double z;
	private double yaw;
	private double pitch;

	public String getWorld() {
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getYaw() {
		return yaw;
	}

	public void setYaw(double yaw) {
		this.yaw = yaw;
	}

	public double getPitch() {
		return pitch;
	}

	public void setPitch(double pitch) {
		this.pitch = pitch;
	}
	
	public void updateLocation(String world, double x, double y, double z, double yaw, double pitch) {
		setWorld(world);
		setX(x);
		setY(y);
		setZ(z);
		setYaw(yaw);
		setPitch(pitch);
		
		plugin.getConfig().set("location.world", world);
		plugin.getConfig().set("location.x", x);
		plugin.getConfig().set("location.y", y);
		plugin.getConfig().set("location.z", z);
		plugin.getConfig().set("location.yaw", yaw);
		plugin.getConfig().set("location.pitch", pitch);
		plugin.saveConfig();
	}
}