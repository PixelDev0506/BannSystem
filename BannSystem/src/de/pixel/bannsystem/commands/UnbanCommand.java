package de.pixel.bannsystem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.pixel.bannsystem.method.BanMethod;
import de.pixel.bannsystem.method.ConfigMethod;
import de.pixel.bannsystem.method.PlayerFile;

public class UnbanCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String arg2, String[] args) {
		if (cmd.getName().equalsIgnoreCase("unban")) {
			if (s instanceof Player) {
				Player p = (Player) s;
				if (p.hasPermission(ConfigMethod.getPermissionUnban())
						|| p.hasPermission(ConfigMethod.getPermissionOverall())) {
					if(args.length == 1) {
						String uuid = PlayerFile.getUUID(args[0]);
						if(uuid != null) {
							BanMethod.unbanPlayer(uuid);
							String unbanmessage = ConfigMethod.getPlayerUnbanned();
							unbanmessage = unbanmessage.replace("%Player%", args[0]);
							p.sendMessage(unbanmessage);
						} else {
							p.sendMessage(ConfigMethod.getPlayerNotFound());
						}
					}
				}
			}
		}
		return false;
	}

}
