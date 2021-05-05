package com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;

public class CommandMount implements CommandExecutor , MainCommand
{

	private final Subcommand[] subcommands =
	{
			new MountRide(this), new MountClaim(this), new MountRelease(this), new MountHelp(this),
			new MountHealth(this), new MountJump(this), new MountSpeed(this)
	};

	public Subcommand[] getSubcommands()
	{

		return subcommands;

	}

	@Override
	public boolean onCommand( CommandSender sender, Command cmd, String label, String[] args )
	{

		boolean isplayer = sender instanceof Player;
		if (args.length > 0)
			for (Subcommand command : subcommands)
			{

				if (command.getName().equalsIgnoreCase(args[0]) && (!command.isPlayerOnly() || isplayer))
				{

					if (sender.hasPermission(command.getPermission()))
						command.run(sender, args);
					else
						sender.sendMessage("§4[Error]:§f You don't have permission for that command");
					return true;

				}

			}
		return false;

	}

}
