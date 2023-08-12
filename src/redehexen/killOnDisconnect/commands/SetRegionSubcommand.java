package redehexen.killOnDisconnect.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import redehexen.killOnDisconnect.KillOnDisconnect;
import redehexen.killOnDisconnect.managers.ConfigManager;

public class SetRegionSubcommand implements KillOnDisconnectSubcommand {

	@Override
	public void execute(CommandSender sender, String cmdlabel, String[] args) {
		ConfigManager configManager = ConfigManager.getInstance();
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(configManager.getMustBePlayerMessage());
			return;
		}
		
		if (!sender.hasPermission(KillOnDisconnect.SET_REGION_PERMISSION)) {
			sender.sendMessage(configManager.getNotAllowedMessage());
			return;
		}
		
		if (args.length != 2) {
			sender.sendMessage(configManager.getSetPositionUsageMessage());
			return;
		}
		
		String regionName = args[0];
		int positionIndex = convertToInteger(args[1]);
		if (positionIndex != 1 && positionIndex != 2) {
			sender.sendMessage(configManager.getWrongIndexMessage());
			return;
		}
		
		Location playerLoc = ((Player) sender).getLocation();
		String worldName = playerLoc.getWorld().getName();
		int x = playerLoc.getBlockX();
		int y = playerLoc.getBlockY();
		int z = playerLoc.getBlockZ();
		
		String path = "Regions." + regionName + ".";
		
		YamlConfiguration config = KillOnDisconnect.getYamlConfiguration();
		
		config.set(path + "World", worldName);
		path += "Loc" + positionIndex + ".";
		config.set(path + "X", x);
		config.set(path + "Y", y);
		config.set(path + "Z", z);
		
		KillOnDisconnect.saveConfiguration(config);
		
		sender.sendMessage(configManager.getPositionSetMessage());
	}
	
	private int convertToInteger(String arg) {
		try {
			return Integer.parseInt(arg);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

}
