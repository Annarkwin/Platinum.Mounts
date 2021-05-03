package com.gmail.Annarkwin.Platinum.Mounts.Commands.Mount;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.CommandHelper;
import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.Mounts.Mount;
import com.gmail.Annarkwin.Platinum.Mounts.Mounts;

public class MountSpeed implements Subcommand {

	private String description = "Set mount speed";
	private MainCommand main;
	private String name = "speed";
	private String permission = "platinum.mount.speed";
	private boolean playeronly = true;
	private String usage = "/mount speed <#>";

	public MountSpeed(MainCommand maincommand) {
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
			p.sendMessage("�4[Error]:�f You don't have a mount");
		} else {
			if (args.length <= 1 || !CommandHelper.isPositiveDouble(args[1])) {p.sendMessage("�4[Error]: �fEnter a positive number"); return;}

			m.setSpeed(CommandHelper.getDouble(args[1]));
			
			p.sendMessage("�2[Info]: �fMount speed set to " + args[1]);
		}
	}
}
