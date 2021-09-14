package de.pixel.bannsystem.method;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerFile {

	public static File f = new File("plugins/BannSystem/", "playerfile.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

	public static void setPlayer(Player p) {
		cfg.set(p.getName(), p.getUniqueId().toString());
		saveFile();
	}

	public static String getUUID(String name) {
		if (cfg.get(name) != null) {
			return cfg.getString(name);
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
