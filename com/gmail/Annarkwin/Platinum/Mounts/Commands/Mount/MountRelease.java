package com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.Mounts.Mount;
import com.gmail.Annarkwin.Platinum.Mounts.Mounts;

public class MountRelease implements Subcommand {

	private String description = "Release the mount you're riding";
	private MainCommand main;
	private String name = "release";
	private String permission = "platinum.mount.release";
	private boolean playeronly = true;
	private String usage = "/mount release";

	public MountRelease(MainCommand maincommand) {
		main = maincommand;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public MainCommand getMainCommand() {
		return main;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPermission() {
		return permission;
	}

	@Override
	public String getUsage() {
		return usage;
	}

	@Override
	public boolean isPlayerOnly() {
		return playeronly;
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		
		Player p = (Player) sender;
		Mount m = Mounts.mount_manager.getMount(p);

			if (m == null) {
				
				p.sendMessage("§4[Error]:§f You don't have a mount");
				
			} else {
				
				Entity vehicle = p.getVehicle();
				
				if (vehicle != null && vehicle.getType() == EntityType.HORSE & vehicle.getCustomName().equals("§2" + p.getDisplayName() + "'s mount")) {

					p.sendMessage("§2[Info]:§f You have released your mount");
					Mounts.mount_manager.setMount(p, null);
					vehicle.setCustomName(null);
					vehicle.eject();

				} else p.sendMessage("§4[Error]§f You aren't riding your mount");
				
			}
	}
}
