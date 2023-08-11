package redehexen.killOnDisconnect;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class KillOnDisconnect extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
	}
	
	public static YamlConfiguration getYamlConfiguration() {
		return YamlConfiguration.loadConfiguration(configFile());
	}
	
	private static File configFile() {
		return new File(getKillOnDisconnect().getDataFolder(), "config.yml");
	}
	
	public static KillOnDisconnect getKillOnDisconnect() {
		return (KillOnDisconnect)Bukkit.getServer().getPluginManager().getPlugin("TF_KillOnDisconnect");
	}
	
	public static void saveConfiguration(YamlConfiguration config) {
		File configFile = configFile();
		
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
