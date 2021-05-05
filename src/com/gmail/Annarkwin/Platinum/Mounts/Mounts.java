package com.gmail.Annarkwin.Platinum.Mounts;

import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount.CommandMount;

public class Mounts extends JavaPlugin
{

	public static MountManager mount_manager;

	@Override
	public void onEnable()
	{

		// Load configuration serializable classes
		registerSerializables();

		// Retrieve file data
		mount_manager = new MountManager(this, "Mounts.yml");

		// Enable plugin features
		enableListeners();
		enableCommands();

		// Initialize update event
	}

	@Override
	public void onDisable()
	{

		mount_manager.saveData();

	}

	private void registerSerializables()
	{

		ConfigurationSerialization.registerClass(Mount.class, "Mount");

	}

	private void enableCommands()
	{

		getCommand("Mount").setExecutor(new CommandMount());

	}

	private void enableListeners()
	{

		getServer().getPluginManager().registerEvents(new ListenerMounts(), this);

	}

}
