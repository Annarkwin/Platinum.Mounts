package com.gmail.Annarkwin.Platinum.Mounts;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@SerializableAs("Mount")
public class Mount implements ConfigurationSerializable
{

	private String color = Horse.Color.BLACK.toString();
	private String style = Horse.Style.WHITE.toString();
	private String armor = Material.AIR.toString();
	private boolean saddled = false;
	private double speed = .2;
	private double jumpstrength = .2;
	private double maxhealth = 20;
	private double currenthealth = 20;

	public Mount()
	{

	}

	public Mount( Horse h )
	{

		color = h.getColor().toString();
		style = h.getStyle().toString();
		if (h.getInventory().getArmor() != null)
			armor = h.getInventory().getArmor().getType().toString();
		if (h.getInventory().getSaddle() != null)
			saddled = true;

		jumpstrength = h.getJumpStrength();
		speed = h.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue();
		currenthealth = h.getHealth();
		maxhealth = h.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

	}

	public Mount( Map<String, Object> o )
	{

		// Get fields of object and retrieve them from map
		for (Field f : getClass().getDeclaredFields())
		{

			try
			{

				if (o.get(f.getName()) != null)
					f.set(this, o.get(f.getName()));

			}
			catch (Exception e)
			{

				// Default
			}

		}

	}

	@Override
	public Map<String, Object> serialize()
	{

		// Get fields of object and map them with value
		HashMap<String, Object> s = new HashMap<String, Object>();

		for (Field f : getClass().getDeclaredFields())
		{

			try
			{

				s.put(f.getName(), f.get(this));

			}
			catch (Exception e)
			{

				e.printStackTrace();

			}

		}

		return s;

	}

	public Color getColor()
	{

		return Color.valueOf(color);

	}

	public Style getStyle()
	{

		return Style.valueOf(style);

	}

	public double getSpeed()
	{

		return speed;

	}

	public Material getArmor()
	{

		return Material.valueOf(armor);

	}

	public double getJumpStrength()
	{

		return jumpstrength;

	}

	public double getMaxHealth()
	{

		return maxhealth;

	}

	public double getCurrentHealth()
	{

		return currenthealth;

	}

	public void setColor( Color clr )
	{

		color = clr.toString();

	}

	public void setStyle( Style sty )
	{

		style = sty.toString();

	}

	public void setSpeed( double spd )
	{

		speed = spd;

	}

	public void setJumpStrength( double str )
	{

		jumpstrength = str;

	}

	public void setArmor( Material barding )
	{

		armor = barding.toString();

	}

	public void setMaxHealth( double hp )
	{

		maxhealth = hp;

	}

	public void setCurrentHealth( double hp )
	{

		currenthealth = hp;

	}

	public void mount( Player p )
	{

		Horse h = (Horse) p.getWorld().spawnEntity(p.getLocation(), EntityType.HORSE);

		h.setCustomNameVisible(false);
		h.setCustomName("§2" + p.getDisplayName() + "'s mount");
		h.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxhealth);
		h.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
		h.setAdult();
		h.setOwner(p);
		h.getInventory().setArmor(new ItemStack(getArmor()));
		h.setColor(getColor());
		h.setStyle(getStyle());
		if (saddled)
			h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
		h.setJumpStrength(jumpstrength);
		h.setHealth(currenthealth);

		h.addPassenger(p);

	}

}
