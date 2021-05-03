package com.gmail.Annarkwin.Platinum.Mounts;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

public class ListenerMounts implements Listener {

//	//TODO check for if mount/dismount horse is the same as your mount
//
//	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
//	public void mountlistener(EntityMountEvent e) {
//		if (e.getMount().getType() == EntityType.HORSE) {
//			if (e.getEntity() instanceof Player) {
//				Horse h = (Horse) e.getMount();
//				Player p = (Player) e.getEntity();
//				Mount m = Mounts.mount_manager.getMount(p);
//
//				if (m == null) {
//					p.sendMessage("§2[Info]:§f This is now your mount");
//					m = Mounts.mount_manager.setMount(p, new Mount(h));
//					h.remove();
//					e.setCancelled(true);
//				} else if (h.getCustomName() == null) {
//					p.sendMessage("§2[Info]:§f You already have a mount");
//				}
//			}
//		}
//	}

	@EventHandler(ignoreCancelled = false)
	public void dismountlistener(EntityDismountEvent e) {
		if (e.getDismounted().getType() == EntityType.HORSE) {
			if (e.getEntity() instanceof Player) {
				Horse h = (Horse) e.getDismounted();
				Player p = (Player) e.getEntity();
				Mount m = Mounts.mount_manager.getMount(p);

				if (h.getHealth() != 0 && m != null && h.getCustomName() != null && h.getCustomName().equals("§2" + p.getDisplayName() + "'s mount")){
					Mounts.mount_manager.setMount(p, new Mount(h));
					p.sendMessage("§2[Info]:§f You have put your mount away");
					h.remove();
				}
			}
		}
	}

//	@EventHandler(ignoreCancelled = false)
//	public void openHorseInventory(InventoryOpenEvent e) {
//		if (e.getInventory().getHolder() instanceof Horse) {
//			e.setCancelled(true);
//		}
//	}
//
//	@EventHandler(ignoreCancelled = false)
//	public void horseEquip(PlayerSwapHandItemsEvent e) {
//		if (e.getPlayer().getVehicle() != null && e.getPlayer().getVehicle() instanceof Horse) {
//			Player p = e.getPlayer();
//			Horse h = (Horse) p.getVehicle();
//			ItemStack hand = e.getOffHandItem();
//			e.setCancelled(true);
//
//			if (hand.getType() != Material.AIR) {
//				if (hand.getType() == Material.IRON_HORSE_ARMOR || hand.getType() == Material.GOLDEN_HORSE_ARMOR || hand.getType() == Material.DIAMOND_HORSE_ARMOR) {
//					ItemStack temp = null;
//					if (h.getInventory().getArmor() != null) temp = h.getInventory().getArmor();
//					h.getInventory().setArmor(hand);
//					p.getInventory().setItemInMainHand(temp);
//				} else if (hand.getType() == Material.SADDLE) {
//					ItemStack temp = null;
//					if (h.getInventory().getSaddle() != null) temp = h.getInventory().getSaddle();
//					h.getInventory().setSaddle(hand);
//					p.getInventory().setItemInMainHand(temp);
//				}
//			} else {
//				if (h.getInventory().getArmor() != null) {
//					p.getInventory().setItemInMainHand(h.getInventory().getArmor());
//					h.getInventory().setArmor(null);
//				} else if (h.getInventory().getSaddle() != null) {
//					p.getInventory().setItemInMainHand(h.getInventory().getSaddle());
//					h.getInventory().setSaddle(null);
//				}
//			}
//		}
//	}

	@SuppressWarnings("deprecation")
	@EventHandler(ignoreCancelled = false)
	public void horseDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Horse) {
			if (e.getEntity().getPassenger() != null) {
				Player p = (Player) e.getEntity().getPassenger();

				p.sendMessage("§2[Info]:§f Your mount has died");
				Mounts.mount_manager.setMount(p, null);
			}
		}
	}
}
