package redehexen.killOnDisconnect.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.configuration.file.YamlConfiguration;

import redehexen.killOnDisconnect.KillOnDisconnect;

public class ConfigManager {
	
	private static final String REGION_PREFIX = "Regions";
	
	private static ConfigManager instance = new ConfigManager();
	
	public static ConfigManager getInstance() {
		return instance;
	}
	
	public static void reload() {
		instance = new ConfigManager();
	}
	
	private List<String> _regions;
	
	private ConfigManager() {
		YamlConfiguration config = KillOnDisconnect.getYamlConfiguration();
		
		_regions = config.getKeys(false).stream().filter(p -> p.startsWith(REGION_PREFIX) && p.lastIndexOf(".") == REGION_PREFIX.length()).collect(Collectors.toList());
	}
	
	public List<String> getRegionsPath() {
		return _regions;
	}
	
	

}
