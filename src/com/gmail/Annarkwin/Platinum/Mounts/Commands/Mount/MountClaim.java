package com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.Mounts.Mount;
import com.gmail.Annarkwin.Platinum.Mounts.Mounts;

public class MountClaim extends PlatinumCommand
{

	public MountClaim( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Entity vehicle = p.getVehicle();
		Mount m = Mounts.mount_manager.getMount(p);

		if (vehicle != null && vehicle.getType() == EntityType.HORSE)
		{

			Horse h = (Horse) vehicle;

			if (m == null)
			{

				p.sendMessage("§2[Info]:§f This is now your mount");
				m = Mounts.mount_manager.setMount(p, new Mount(h));
				h.remove();

			}
			else
			{

				p.sendMessage("§2[Info]:§f You already have a mount");

			}

		}
		return true;

	}

}
