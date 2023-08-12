package redehexen.killOnDisconnect.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import redehexen.killOnDisconnect.KillOnDisconnect;
import redehexen.killOnDisconnect.regions.Region;

public class RegionManager {

	private static final String REGION_PREFIX = "Regions";
	
	private static List<Region> killOnDisconnectRegions = populateRegionsList();
	
	public static void reload() {
		killOnDisconnectRegions = populateRegionsList();
	}
	
	private static List<Region> populateRegionsList() {
		YamlConfiguration config = KillOnDisconnect.getYamlConfiguration();
		
		List<Region> regionsList = new ArrayList<Region>();
		
		List<String> regionsPath = config.getKeys(true).stream().filter(p -> p.startsWith(REGION_PREFIX) && p.lastIndexOf(".") == REGION_PREFIX.length()).collect(Collectors.toList());
		
		for (String regionPath : regionsPath) {
			Location loc1 = getLocationFromConfig(config, regionPath, "1");
			Location loc2 = getLocationFromConfig(config, regionPath, "2");
			
			regionsList.add(new Region(loc1, loc2));
		}
		
		return regionsList;
	}
	
	private static Location getLocationFromConfig(YamlConfiguration config, String path, String locationIndex) {
		String worldName = config.getString(path + ".World");
		int x = config.getInt(path + ".Loc" + locationIndex + ".X");
		int y = config.getInt(path + ".Loc" + locationIndex + ".Y");
		int z = config.getInt(path + ".Loc" + locationIndex + ".Z");
		
		return new Location(Bukkit.getWorld(worldName), x, y, z);
	}
	
	public static List<Region> getAllRegions() {
		return killOnDisconnectRegions;
	}
	
	public static boolean isOnKillRegion(Location loc) {
		for (Region region : killOnDisconnectRegions) {
			if (region.isOnRegion(loc)) {
				return true;
			}
		}
		
		return false;
	}
	
}
