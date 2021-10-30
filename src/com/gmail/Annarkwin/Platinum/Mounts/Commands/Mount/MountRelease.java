package com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.Mounts.Mount;
import com.gmail.Annarkwin.Platinum.Mounts.Mounts;

public class MountRelease extends PlatinumCommand
{

	public MountRelease( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Mount m = Mounts.mount_manager.getMount(p);

		if (m == null)
		{

			p.sendMessage("�4[Error]:�f You don't have a mount");

		}
		else
		{

			Entity vehicle = p.getVehicle();

			if (vehicle != null && vehicle.getType() == EntityType.HORSE
					& vehicle.getCustomName().equals("�2" + p.getDisplayName() + "'s mount"))
			{

				p.sendMessage("�2[Info]:�f You have released your mount");
				Mounts.mount_manager.setMount(p, null);
				vehicle.setCustomName(null);
				vehicle.eject();

			}
			else
				p.sendMessage("�4[Error]�f You aren't riding your mount");

		}

		return true;

	}

}
