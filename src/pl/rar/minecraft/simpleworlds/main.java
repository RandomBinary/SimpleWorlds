package pl.rar.minecraft.simpleworlds;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
	@Override
	public void onEnable(){
		getLogger().info("Hello");
	}
	
	@Override
	public void onDisable(){
		getLogger().info("Bye");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args){
	Player player = (Player) sender;
	if (command.getLabel().equalsIgnoreCase("newworld")) {
		if (player.hasPermission("SimpleWorlds.newworld")) {
			WorldCreator wc = new WorldCreator(args[1]);
			if (args[0].equalsIgnoreCase("normal")) {
				wc.environment(Environment.NORMAL);
				wc.createWorld();
				player.sendMessage(ChatColor.GREEN + "[SimpleWorlds] The world " + args[1] + " was created.");
			} else if (args[0].equalsIgnoreCase("nether")) {
				wc.environment(Environment.NETHER);
				wc.createWorld();
				player.sendMessage(ChatColor.GREEN + "[SimpleWorlds] The world " + args[1] + " was created.");
			} else if (args[0].equalsIgnoreCase("end")) {
				wc.environment(Environment.THE_END);
				wc.createWorld();
				player.sendMessage(ChatColor.GREEN + "[SimpleWorlds] The world " + args[1] + " was created.");
			} else {
				player.sendMessage(ChatColor.RED + "[SimpleWorlds] A " + args[0] + " environment? Sorry, never heard of it.");
			}
			return true;
		}
		return false;
	} else if (command.getLabel().equalsIgnoreCase("gotoworld")) {
		if (player.hasPermission("SimpleWorlds.gotoworld")) {
			World argworld = Bukkit.getServer().getWorld(args[0]);
			if (argworld != null) {
				player.teleport(argworld.getSpawnLocation());
				player.sendMessage(ChatColor.GREEN + "[SimpleWorlds] Moved you to " + args[0] + "!");
			} else {
				player.sendMessage(ChatColor.RED + "[SimpleWorlds] Sorry, no such world exists.");
			}
			return true;
		}
	} else if (command.getLabel().equalsIgnoreCase("unloadworld")) {
		if (player.hasPermission("SimpleWorlds.unloadworld")) {
			World argworld = Bukkit.getServer().getWorld(args[0]);
			if (argworld != null) {
				org.bukkit.Bukkit.unloadWorld(args[0], true);
				player.sendMessage(ChatColor.GREEN + "[SimpleWorlds] Unloaded world " + args[0] + "!");
			} else {
				player.sendMessage(ChatColor.RED + "[SimpleWorlds] Sorry, no such world exists.");
			}
			return true;
		}
	}
	return true;
	}
}
