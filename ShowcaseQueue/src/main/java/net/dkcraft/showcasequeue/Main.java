package net.dkcraft.showcasequeue;

import java.util.Map;
import java.util.TreeMap;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dkcraft.showcasequeue.attendee.Attendee;
import net.dkcraft.showcasequeue.commands.ShowcaseQueueCommand;
import net.dkcraft.showcasequeue.util.Config;
import net.dkcraft.showcasequeue.util.EventListener;
import net.dkcraft.showcasequeue.util.Util;

public class Main extends JavaPlugin {

	public Main main;

	public Config config;
	public Util util;

	// Maps
	public Map<String, Attendee> queue = new TreeMap<String, Attendee>(String.CASE_INSENSITIVE_ORDER);

	public void onEnable() {

		this.main = this;

		// Util
		config = new Config(this);
		util = new Util(this);

		// Commands
		this.getCommand("showcasequeue").setExecutor(new ShowcaseQueueCommand(this));

		// Listeners
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EventListener(this), this);

		// Loading
		config.loadConfig();
	}

	public void onDisable() {

	}
}
