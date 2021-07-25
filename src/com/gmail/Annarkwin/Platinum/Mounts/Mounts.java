package com.gmail.Annarkwin.Platinum.Mounts;

import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.Annarkwin.Platinum.API.PlatinumMainCommand;
import com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount.CommandMount;
import com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount.MountClaim;
import com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount.MountHealth;
import com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount.MountHelp;
import com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount.MountJump;
import com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount.MountRelease;
import com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount.MountRide;
import com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount.MountSpeed;

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
		
		PlatinumMainCommand mount = new CommandMount("mount", "platinum.command.mount", true, "Summon a rideable horse", "/mount help (page)");
		mount.addChildCommand(new MountClaim("claim", "platinum.command.mount.claim", true, "Claim the horse you're on", "/mount claim"));
		mount.addChildCommand(new MountHealth("health", "platinum.command.mount.health", true, "Set mount max health", "/mount health <amount>"));
		mount.addChildCommand(new MountHelp("help", "platinum.command.mount.help", true, "Show mount help", "/mount help (page)"));
		mount.addChildCommand(new MountJump("jump", "platinum.command.mount.jump", true, "Set mount jump height", "/mount jump <amount>"));
		mount.addChildCommand(new MountRelease("release", "platinum.command.mount.release", true, "Release your mount", "/mount release"));
		mount.addChildCommand(new MountRide("ride", "platinum.command.mount.ride", true, "Summon your mount", "/mount ride"));
		mount.addChildCommand(new MountSpeed("speed", "platinum.command.mount.speed", true, "Set mount speed", "/mount speed <amount>"));
		getCommand("Mount").setExecutor(mount);

	}

	private void enableListeners()
	{

		getServer().getPluginManager().registerEvents(new ListenerMounts(), this);

	}

}
