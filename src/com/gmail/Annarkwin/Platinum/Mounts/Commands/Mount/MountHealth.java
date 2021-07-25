package com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.CommandHelper;
import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.Mounts.Mount;
import com.gmail.Annarkwin.Platinum.Mounts.Mounts;

public class MountHealth extends PlatinumCommand
{

	public MountHealth( String name, String permission, boolean player, String description, String usage )
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

			if (args.length <= 1 || !CommandHelper.isPositiveInt(args[1]))
			{

				p.sendMessage("�4[Error]: �fEnter a positive number");
				return true;

			}

			m.setMaxHealth(Double.parseDouble(args[1]));
			m.setCurrentHealth(m.getMaxHealth());

			p.sendMessage("�2[Info]: �fMount health set to " + args[1]);

		}
		return true;

	}

}
