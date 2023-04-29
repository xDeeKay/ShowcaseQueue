package net.dkcraft.showcasequeue.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dkcraft.showcasequeue.Main;
import net.dkcraft.showcasequeue.util.Config;
import net.dkcraft.showcasequeue.util.Util;

public class ShowcaseQueueCommand implements CommandExecutor {

	public Main plugin;
	
	public Config config;

	public Util util;

	public ShowcaseQueueCommand(Main plugin) {
		this.plugin = plugin;
		this.config = this.plugin.config;
		this.util = this.plugin.util;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("showcasequeue") || cmd.getName().equalsIgnoreCase("scq") || cmd.getName().equalsIgnoreCase("sq")) {

			if (args.length == 1) {

				if (args[0].equalsIgnoreCase("start")) {
					util.startQueue();
					sender.sendMessage(ChatColor.GREEN + "Showcase queue started.");

				} else if (args[0].equalsIgnoreCase("stop")) {
					util.stopQueue();
					sender.sendMessage(ChatColor.GREEN + "Showcase queue stopped.");

				} else if (args[0].equalsIgnoreCase("setlocation")) {
					
					if (sender instanceof Player) {

						Player player = (Player) sender;
						
						Location location = player.getLocation();
						String world = location.getWorld().toString();
						double x = location.getX();
						double y = location.getY();
						double z = location.getZ();
						double yaw = location.getYaw();
						double pitch = location.getPitch();
						
						config.updateLocation(world, x, y, z, yaw, pitch);
						player.sendMessage(ChatColor.GREEN + "Showcase location set: " + world + ", " + (int)x + ", " + (int)y + ", " + (int)z + ", " + (int)yaw + ", " + (int)pitch);
						
					} else {
						sender.sendMessage(ChatColor.RED + "You must be a player in-game to set the location.");
					}
				}

			} else if (args.length == 2) {

				if (args[0].equalsIgnoreCase("setgroup")) {
					config.updateGroup(args[1]);
					sender.sendMessage(ChatColor.GREEN + "Showcase group set: " + args[1]);

				} else if (args[0].equalsIgnoreCase("setinterval")) {
					config.updateInterval(Integer.valueOf(args[1]));
					sender.sendMessage(ChatColor.GREEN + "Showcase interval set: " + args[1]);
				}
			}
		}
		return true;
	}
}
