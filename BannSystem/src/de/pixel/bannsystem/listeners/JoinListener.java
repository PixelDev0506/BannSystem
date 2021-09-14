package de.pixel.bannsystem.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent.Result;

import de.pixel.bannsystem.method.BanMethod;
import de.pixel.bannsystem.method.ConfigMethod;
import de.pixel.bannsystem.method.PlayerFile;

@SuppressWarnings("deprecation")
public class JoinListener implements Listener {

	@EventHandler
	public void onPreJoin(PlayerPreLoginEvent e) {
		if (BanMethod.isBanned(e.getUniqueId().toString())) {
			if (Long.valueOf(BanMethod.getBanTime(e.getUniqueId().toString())) != 0) {
				if (Long.valueOf(BanMethod.getBanTime(e.getUniqueId().toString())) < System.currentTimeMillis()) {
					BanMethod.unbanPlayer(e.getUniqueId().toString());
				}
			}
		}
		if (BanMethod.isBanned(e.getUniqueId().toString())) {

			String banmessage = ConfigMethod.getPlayerBanned();
			String banmessageperm = ConfigMethod.getPlayerBannedPermanent();

			SimpleDateFormat sdf = new SimpleDateFormat(ConfigMethod.getDateFormat());
			Date d = new Date(Long.valueOf(BanMethod.getBanTime(e.getUniqueId().toString())));
			String date = sdf.format(d);

			banmessage = banmessage.replace("%Date%", date);

			if (Long.valueOf(BanMethod.getBanTime(e.getUniqueId().toString())) == 0) {
				e.setKickMessage(banmessageperm);
			} else {
				e.setKickMessage(banmessage);
			}
			e.setResult(Result.KICK_OTHER);
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		PlayerFile.setPlayer(e.getPlayer());
	}

}
