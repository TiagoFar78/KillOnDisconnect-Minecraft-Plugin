package redehexen.killOnDisconnect.commands;

import org.bukkit.command.CommandSender;

import redehexen.killOnDisconnect.KillOnDisconnect;
import redehexen.killOnDisconnect.managers.ConfigManager;
import redehexen.killOnDisconnect.managers.RegionManager;

public class ReloadSubcommand implements KillOnDisconnectSubcommand {

	@Override
	public void execute(CommandSender sender, String cmdlabel, String[] args) {
		ConfigManager configManager = ConfigManager.getInstance();
		
		if (!sender.hasPermission(KillOnDisconnect.SET_REGION_PERMISSION)) {
			sender.sendMessage(configManager.getNotAllowedMessage());
			return;
		}
		
		ConfigManager.reload();
		RegionManager.reload();
		
		sender.sendMessage(configManager.getReloadedMessage());
	}
	
	

}
