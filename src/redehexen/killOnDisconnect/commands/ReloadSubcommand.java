package redehexen.killOnDisconnect.commands;

import org.bukkit.command.CommandSender;

import redehexen.killOnDisconnect.KillOnDisconnect;
import redehexen.killOnDisconnect.managers.ConfigManager;
import redehexen.killOnDisconnect.managers.RegionManager;

public class ReloadSubcommand implements KillOnDisconnectSubcommand {

	@Override
	public void execute(CommandSender sender, String cmdlabel, String[] args) {
		if (!sender.hasPermission(KillOnDisconnect.SET_REGION_PERMISSION)) {
			// TODO mensagem que nao pode
			return;
		}
		
		ConfigManager.reload();
		RegionManager.reload();
		
		// TODO success
	}
	
	

}
