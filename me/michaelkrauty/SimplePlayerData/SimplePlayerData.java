package me.michaelkrauty.SimplePlayerData;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
public class SimplePlayerData extends JavaPlugin implements Listener{
	public void onEnable(){
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e){
		}
        getServer().getPluginManager().registerEvents(this, this);
		PluginDescriptionFile pdffile = this.getDescription();
		Server server = Bukkit.getServer();
		ConsoleCommandSender console = server.getConsoleSender();
		saveConfig();
		console.sendMessage(ChatColor.GOLD + "[PlayerData] Enabled version " + pdffile.getVersion());
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		String playerName = player.getName();
		Location joinerLocation = player.getLocation();
		String itemName = player.getInventory().getItemInHand().getType().toString();
		getConfig().set("players." + playerName + ".Login_Info" + ".Login_Location" + ".x", joinerLocation.getBlockX());
		getConfig().set("players." + playerName + ".Login_Info" + ".Login_Location" + ".y", joinerLocation.getBlockY());
		getConfig().set("players." + playerName + ".Login_Info" + ".Login_Location" + ".z", joinerLocation.getBlockZ());
		getConfig().set("players." + playerName + ".Login_Info" + ".Login_World", player.getWorld().getName());
		getConfig().set("players." + playerName + ".Login_Info" + ".is_OP", player.isOp());
		getConfig().set("players." + playerName + ".Login_Info" + ".is_flying", player.isFlying());
		getConfig().set("players." + playerName + ".Login_Info" + ".Gamemode", player.getGameMode().name());
		getConfig().set("players." + playerName + ".Login_Info" + ".Logged_in_dead", player.isDead());
		getConfig().set("players." + playerName + ".Login_Info" + ".IP_address", player.getAddress().toString());
		getConfig().set("players." + playerName + ".Login_Info" + ".Port", player.getAddress().getPort());
		getConfig().set("players." + playerName + ".Login_Info" + ".Item_In_Hand", itemName);
		saveConfig();
		reloadConfig();
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		String playerName = player.getName();
		Location joinerLocation = player.getLocation();
		String itemName = player.getInventory().getItemInHand().getType().toString();
		getConfig().set("players." + playerName + ".Logout_Info" + ".Logout_Location" + ".x", joinerLocation.getBlockX());
		getConfig().set("players." + playerName + ".Logout_Info" + ".Logout_Location" + ".y", joinerLocation.getBlockY());
		getConfig().set("players." + playerName + ".Logout_Info" + ".Logout_Location" + ".z", joinerLocation.getBlockZ());
		getConfig().set("players." + playerName + ".Logout_Info" + ".Logout_World", player.getWorld().getName());
		getConfig().set("players." + playerName + ".Logout_Info" + ".is_OP", player.isOp());
		getConfig().set("players." + playerName + ".Logout_Info" + ".is_flying", player.isFlying());
		getConfig().set("players." + playerName + ".Logout_Info" + ".Gamemode", player.getGameMode().name());
		getConfig().set("players." + playerName + ".Logout_Info" + ".Logged_out_dead", player.isDead());
		getConfig().set("players." + playerName + ".Logout_Info" + ".Item_In_Hand", itemName);
		getConfig().set("players." + playerName + ".Login_Info" + ".Hostname", player.getAddress().getHostName());
		saveConfig();
		reloadConfig();
	}
}