package redehexen.killOnDisconnect.commands;

import org.bukkit.command.CommandSender;

public interface KillOnDisconnectSubcommand {

	void execute(CommandSender sender, String cmdlabel, String[] args);

}
