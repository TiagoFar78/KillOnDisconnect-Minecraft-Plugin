package redehexen.killOnDisconnect;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import redehexen.killOnDisconnect.managers.RegionManager;

public class Events implements Listener {
	
	@EventHandler
	public void playerQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		if (RegionManager.isOnKillRegion(player.getLocation())) {
			player.setHealth(0);
		}
	}

}
