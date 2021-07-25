package com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.Mounts.Mount;
import com.gmail.Annarkwin.Platinum.Mounts.Mounts;

public class MountRide extends PlatinumCommand
{

	public MountRide( String name, String permission, boolean player, String description, String usage )
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

			p.sendMessage("§4[Error]:§f You don't have a mount");

		}
		else if (p.getVehicle() != null)
		{

			p.sendMessage("§4[Error]:§f You are already mounted");

		}
		else
		{

			m.mount(p);

		}
		return true;

	}

}
