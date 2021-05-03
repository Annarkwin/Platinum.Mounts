package com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.Mounts.Mount;
import com.gmail.Annarkwin.Platinum.Mounts.Mounts;

public class MountClaim implements Subcommand {

	private String description = "Claim the mount you're riding";
	private MainCommand main;
	private String name = "claim";
	private String permission = "platinum.mount.claim";
	private boolean playeronly = true;
	private String usage = "/mount claim";

	public MountClaim(MainCommand maincommand) {
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
		Entity vehicle = p.getVehicle();
		Mount m = Mounts.mount_manager.getMount(p);
		
		if (vehicle != null && vehicle.getType() == EntityType.HORSE) {
			Horse h = (Horse) vehicle;

			if (m == null) {
				p.sendMessage("§2[Info]:§f This is now your mount");
				m = Mounts.mount_manager.setMount(p, new Mount(h));
				h.remove();
			} else {
				p.sendMessage("§2[Info]:§f You already have a mount");
			}
		}
	}
}
