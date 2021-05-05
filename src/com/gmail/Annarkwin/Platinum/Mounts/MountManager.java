package com.gmail.Annarkwin.Platinum.Mounts;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.Annarkwin.Platinum.API.FileStorage;

public class MountManager
{

	private FileStorage file;
	private String section_mounts = "mounts";

	private HashMap<String, Mount> mounts;

	public MountManager( JavaPlugin plugin, String filename )
	{

		file = new FileStorage(plugin, filename);
		importData();

	}

	public HashMap<String, Mount> getMounts()
	{

		return mounts;

	}

	public Mount getMount( Player p )
	{

		return mounts.get(p.getUniqueId().toString());

	}

	public Mount setMount( Player p, Mount m )
	{

		return mounts.put(p.getUniqueId().toString(), m);

	}

	public void removeMount( UUID player )
	{

		mounts.remove(player.toString());

	}

	private void importData()
	{

		mounts = new HashMap<String, Mount>();
		ConfigurationSection section;

		if ((section = file.getData().getConfigurationSection(section_mounts)) != null)
		{

			for (String key : section.getKeys(false))
			{

				mounts.put(key, (Mount) section.get(key));

			}

		}

	}

	public void saveData()
	{

		file.getData().set(section_mounts, mounts);
		file.saveToDisk();

	}

}
