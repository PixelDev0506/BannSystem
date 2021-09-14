package de.pixel.bannsystem.method;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class BanMethod {

	public static File f = new File("plugins/BannSystem/", "Bans.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

	public static void banPlayer(String uuid, Long time) {
		if (Bukkit.getPlayer(UUID.fromString(uuid)) != null) {
			String banmessage = ConfigMethod.getPlayerBanned();
			String banmessagepermanent = ConfigMethod.getPlayerBannedPermanent();

			SimpleDateFormat sdf = new SimpleDateFormat(ConfigMethod.getDateFormat());
			Date d = new Date(Long.valueOf(time));
			String date = sdf.format(d);

			banmessage = banmessage.replace("%Date%", date);
			if (time != 0) {
				Bukkit.getPlayer(UUID.fromString(uuid)).kickPlayer(banmessage);
			} else {
				Bukkit.getPlayer(UUID.fromString(uuid)).kickPlayer(banmessagepermanent);
			}
		}
		cfg.set(uuid, time);
		saveFile();
	}

	public static void unbanPlayer(String uuid) {
		if (isBanned(uuid)) {
			cfg.set(uuid, null);
			saveFile();
		}
	}

	public static boolean isBanned(String uuid) {
		if (cfg.get(uuid) != null) {
			return true;
		}
		return false;
	}

	public static String getBanTime(String uuid) {
		if (cfg.get(uuid) != null) {
			return cfg.getString(uuid);
		}
		return null;
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
