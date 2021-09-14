package de.pixel.bannsystem.commands;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.pixel.bannsystem.method.BanMethod;
import de.pixel.bannsystem.method.ConfigMethod;
import de.pixel.bannsystem.method.PlayerFile;

public class BanCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String arg2, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ban")) {
			if (s instanceof Player) {
				Player p = (Player) s;
				if (p.hasPermission(ConfigMethod.getPermissionBan())
						|| p.hasPermission(ConfigMethod.getPermissionOverall())) {
					if (args.length == 2) {
						Player t = Bukkit.getPlayer(args[0]);
						String uuid = null;
						if (t != null) {
							uuid = t.getUniqueId().toString();
						} else {
							if (PlayerFile.getUUID(args[0]) != null) {
								uuid = PlayerFile.getUUID(args[0]);
							} else {
								p.sendMessage(ConfigMethod.getPlayerNotFound());
							}
						}
						if (uuid != null) {
							String timeformat = null;
							String args1 = args[1];
							Integer times = 0;
							if (args[1].contains("d")) {
								timeformat = "d";
								times++;
							}
							if (args[1].contains("h")) {
								timeformat = "h";
								times++;
							}
							if (args[1].contains("m")) {
								timeformat = "m";
								times++;
							}
							if (args[1].contains("s")) {
								timeformat = "s";
								times++;
							}
							if (times == 1) {
								args1 = args1.replace(timeformat, "");
								long time = 0;
								try {
									time = Long.valueOf(args1);
								} catch (Exception ex) {
									p.sendMessage(ConfigMethod.getIncorrectTime());
								}
								if (timeformat.equals("d")) {
									time = time * 24 * 60 * 60 * 1000;
								}
								if (timeformat.equals("h")) {
									time = time * 60 * 60 * 1000;
								}
								if (timeformat.equals("m")) {
									time = time * 60 * 1000;
								}
								if (timeformat.equals("s")) {
									time = time * 1000;
								}

								if (time != 0) {
									time = time + System.currentTimeMillis();
								}

								BanMethod.banPlayer(uuid, time);

								String banmessage = ConfigMethod.getBanPlayer();

								SimpleDateFormat sdf = new SimpleDateFormat(ConfigMethod.getDateFormat());
								Date d = new Date(time);
								String date = sdf.format(d);

								banmessage = banmessage.replace("%Player%", args[0]);
								banmessage = banmessage.replace("%Date%", date);

								p.sendMessage(banmessage);
							} else {
								p.sendMessage(ConfigMethod.getIncorrectTime());
							}
						}
					} else {
						p.sendMessage(ConfigMethod.getBanUsage());
					}
				} else {
					p.sendMessage(ConfigMethod.getNoPerms());
				}
			}
		}
		return false;
	}

}
