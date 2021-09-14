package de.pixel.bannsystem.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.pixel.bannsystem.commands.BanCommand;
import de.pixel.bannsystem.commands.UnbanCommand;
import de.pixel.bannsystem.listeners.JoinListener;
import de.pixel.bannsystem.method.BanMethod;
import de.pixel.bannsystem.method.ConfigMethod;
import de.pixel.bannsystem.method.PlayerFile;

public class BannSystem extends JavaPlugin {

	public static BannSystem instance;

	public void onEnable() {
		instance = this;
		BanMethod.loadFile();
		PlayerFile.loadFile();
		if (!ConfigMethod.f.exists()) {
			saveDefaultConfig();
		}
		ConfigMethod.loadFile();

		getCommand("ban").setExecutor(new BanCommand());
		getCommand("unban").setExecutor(new UnbanCommand());
		Bukkit.getPluginManager().registerEvents(new JoinListener(), instance);
	}

}
