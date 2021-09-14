package de.pixel.bannsystem.method;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.pixel.bannsystem.main.BannSystem;

public class ConfigMethod {

	public static File f = new File("plugins/BannSystem/", "config.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

	public static String getPermissionOverall() {
		if (cfg.getConfigurationSection("permissions") != null) {
			if (cfg.getConfigurationSection("permissions").getString("overallperm") != null) {
				return cfg.getConfigurationSection("permissions").getString("overallperm");
			}
		}
		resetFile();
		return cfg.getConfigurationSection("permissions").getString("overallperm");
	}

	public static String getPermissionBan() {
		if (cfg.getConfigurationSection("permissions") != null) {
			if (cfg.getConfigurationSection("permissions").getString("ban") != null) {
				return cfg.getConfigurationSection("permissions").getString("ban");
			}
		}
		resetFile();
		return cfg.getConfigurationSection("permissions").getString("ban");
	}

	public static String getPermissionUnban() {
		if (cfg.getConfigurationSection("permissions") != null) {
			if (cfg.getConfigurationSection("permissions").getString("unban") != null) {
				return cfg.getConfigurationSection("permissions").getString("unban");
			}
		}
		resetFile();
		return cfg.getConfigurationSection("permissions").getString("unban");
	}

	public static String getNoPerms() {
		if (cfg.getConfigurationSection("messages") != null) {
			if (cfg.getConfigurationSection("messages").getString("nopermissions") != null) {
				String a = cfg.getConfigurationSection("messages").getString("nopermissions");
				a = ChatColor.translateAlternateColorCodes('&', a);
				return a;
			}
		}
		resetFile();
		String a = cfg.getConfigurationSection("messages").getString("incorrecttime");
		a = ChatColor.translateAlternateColorCodes('&', a);
		return a;
	}

	public static String getIncorrectTime() {
		if (cfg.getConfigurationSection("messages") != null) {
			if (cfg.getConfigurationSection("messages").getString("incorrecttime") != null) {
				String a = cfg.getConfigurationSection("messages").getString("incorrecttime");
				a = ChatColor.translateAlternateColorCodes('&', a);
				return a;
			}
		}
		resetFile();
		String a = cfg.getConfigurationSection("messages").getString("incorrecttime");
		a = ChatColor.translateAlternateColorCodes('&', a);
		return a;
	}

	public static String getPlayerNotFound() {
		if (cfg.getConfigurationSection("messages") != null) {
			if (cfg.getConfigurationSection("messages").getString("playernotfound") != null) {
				String a = cfg.getConfigurationSection("messages").getString("playernotfound");
				a = ChatColor.translateAlternateColorCodes('&', a);
				return a;
			}
		}
		resetFile();
		String a = cfg.getConfigurationSection("messages").getString("playernotfound");
		a = ChatColor.translateAlternateColorCodes('&', a);
		return a;
	}

	public static String getUnbanUsage() {
		if (cfg.getConfigurationSection("messages") != null) {
			if (cfg.getConfigurationSection("messages").getString("unbancommandusage") != null) {
				String a = cfg.getConfigurationSection("messages").getString("unbancommandusage");
				a = ChatColor.translateAlternateColorCodes('&', a);
				return a;
			}
		}
		resetFile();
		String a = cfg.getConfigurationSection("messages").getString("unbancommandusage");
		a = ChatColor.translateAlternateColorCodes('&', a);
		return a;
	}

	public static String getBanUsage() {
		if (cfg.getConfigurationSection("messages") != null) {
			if (cfg.getConfigurationSection("messages").getString("bancommandusage") != null) {
				String a = cfg.getConfigurationSection("messages").getString("bancommandusage");
				a = ChatColor.translateAlternateColorCodes('&', a);
				return a;
			}
		}
		resetFile();
		String a = cfg.getConfigurationSection("messages").getString("bancommandusage");
		a = ChatColor.translateAlternateColorCodes('&', a);
		return a;
	}

	public static String getBanPlayer() {
		if (cfg.getConfigurationSection("messages") != null) {
			if (cfg.getConfigurationSection("messages").getString("banplayer") != null) {
				String a = cfg.getConfigurationSection("messages").getString("banplayer");
				a = ChatColor.translateAlternateColorCodes('&', a);
				return a;
			}
		}
		resetFile();
		String a = cfg.getConfigurationSection("messages").getString("banplayer");
		a = ChatColor.translateAlternateColorCodes('&', a);
		return a;
	}

	public static String getPlayerBanned() {
		if (cfg.getConfigurationSection("messages") != null) {
			if (cfg.getConfigurationSection("messages").getString("playerbanned") != null) {
				String a = cfg.getConfigurationSection("messages").getString("playerbanned");
				a = ChatColor.translateAlternateColorCodes('&', a);
				return a;
			}
		}
		resetFile();
		String a = cfg.getConfigurationSection("messages").getString("playerbanned");
		a = ChatColor.translateAlternateColorCodes('&', a);
		return a;
	}
	
	public static String getPlayerBannedPermanent() {
		if (cfg.getConfigurationSection("messages") != null) {
			if (cfg.getConfigurationSection("messages").getString("playerbannedpermanent") != null) {
				String a = cfg.getConfigurationSection("messages").getString("playerbannedpermanent");
				a = ChatColor.translateAlternateColorCodes('&', a);
				return a;
			}
		}
		resetFile();
		String a = cfg.getConfigurationSection("messages").getString("playerbannedpermanent");
		a = ChatColor.translateAlternateColorCodes('&', a);
		return a;
	}

	public static String getPlayerUnbanned() {
		if (cfg.getConfigurationSection("messages") != null) {
			if (cfg.getConfigurationSection("messages").getString("playerunbanned") != null) {
				String a = cfg.getConfigurationSection("messages").getString("playerunbanned");
				a = ChatColor.translateAlternateColorCodes('&', a);
				return a;
			}
		}
		resetFile();
		String a = cfg.getConfigurationSection("messages").getString("playerunbanned");
		a = ChatColor.translateAlternateColorCodes('&', a);
		return a;
	}

	public static String getDateFormat() {
		if (cfg.getConfigurationSection("messages") != null) {
			if (cfg.getConfigurationSection("messages").getString("dateformat") != null) {
				return cfg.getConfigurationSection("messages").getString("dateformat");
			}
		}
		resetFile();
		return cfg.getConfigurationSection("messages").getString("dateformat");
	}

	public static void resetFile() {
		System.out.println("Something went wrong in config. Resetting.");
		try {
			FileUtils.forceDelete(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BannSystem.instance.saveDefaultConfig();
		loadFile();
	}

	public static void saveFile() {
		try {
			cfg.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadFile() {
		if (f.exists()) {
			try {
				cfg.load(f);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
		} else {
			saveFile();
			loadFile();
		}
	}

}
