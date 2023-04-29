package net.dkcraft.showcasequeue.util;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import net.dkcraft.showcasequeue.Main;
import net.dkcraft.showcasequeue.attendee.Attendee;
import net.md_5.bungee.api.ChatColor;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Util {

	public Main plugin;

	public Config config;

	public BukkitTask task;

	public Util(Main plugin) {
		this.plugin = plugin;
		this.config = this.plugin.config;
	}

	public String getPlayerGroup(Player player) {
		PermissionUser user = PermissionsEx.getUser(player);
		List<String> ranks = user.getParentIdentifiers();
		String rank = ranks.get(0);
		return rank;
	}

	public void startQueue() {

		// Collect online players from group and add them to the queue
		for (Player online : plugin.getServer().getOnlinePlayers()) {
			if (getPlayerGroup(online).equals(config.getGroup())) {
				plugin.queue.put(online.getName(), new Attendee(online.getName(), config.getGroup(), null));
			}
		}
		
		if (plugin.queue.size() >= 1) {
			
			String firsPlayerName = plugin.queue.keySet().stream().findFirst().get();
			//Object firsPlayerName = plugin.queue.keySet().toArray()[0];

			if (plugin.getServer().getPlayer(firsPlayerName) != null) {

				Player firstPlayer = plugin.getServer().getPlayer(firsPlayerName);

				firstPlayer.teleport(getShowcaseLocation());
				firstPlayer.sendMessage(ChatColor.GREEN + "You have been teleported to the showcase for " + config.getInterval() + " seconds.");
			}

			this.task = new BukkitRunnable() {
				public void run() {
					
					if (plugin.queue.size() >= 1) {
						
						String currentPlayerName = plugin.queue.keySet().stream().findFirst().get();
						Player currentPlayer = plugin.getServer().getPlayer(currentPlayerName);

						currentPlayer.teleport(plugin.queue.get(currentPlayerName).getOrigin());
						currentPlayer.sendMessage(ChatColor.GREEN + "You have been teleported to your original location. Thanks for viewing!");

						plugin.queue.remove(currentPlayerName);
						
						String nextPlayerName = plugin.queue.keySet().stream().findFirst().get();
						Player nextPlayer = plugin.getServer().getPlayer(nextPlayerName);
						
						nextPlayer.teleport(getShowcaseLocation());
						nextPlayer.sendMessage(ChatColor.GREEN + "You have been teleported to the showcase for " + config.getInterval() + " seconds.");
					} else {
						task.cancel();
						plugin.getServer().broadcastMessage("No more players left in queue. Stopping queue task.");
					}
				}
			}.runTaskLater(plugin, config.getInterval() * 20);
		} else {
			plugin.getServer().broadcastMessage("No players online for queue. Aborting queue start.");
		}
	}

	public void stopQueue() {
		
		task.cancel();
		
		for (String queue : plugin.queue.keySet()) {
			
			if (plugin.getServer().getPlayer(queue) != null) {
				
				Player queuePlayer = plugin.getServer().getPlayer(queue);
				
				if (plugin.queue.get(queue).getOrigin() != null) {
					
					queuePlayer.teleport(plugin.queue.get(queue).getOrigin());
					queuePlayer.sendMessage(ChatColor.GREEN + "You have been teleported to your original location. Thanks for viewing!");
					
				}
			}
			
			plugin.queue.remove(queue);
		}
	}
	
	public Location getShowcaseLocation() {
		
		World world = plugin.getServer().getWorld(config.getWorld());
		double x = config.getX();
		double y = config.getY();
		double z = config.getZ();
		float yaw = (float)config.getYaw();
		float pitch = (float)config.getPitch();
		
		Location showcase = new Location(world, x, y, x, yaw, pitch);
		
		return showcase;
	}
}
