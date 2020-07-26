package me.M7mdm3rq.custominventory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {
	public float V = 0.0f;
	Map<String, Long> CD = new HashMap<String, Long>(); 
	Inventory inv = Bukkit.createInventory(null, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "Shop");
	Map<Player, Inventory> Invs = new HashMap<Player, Inventory>(); 
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	@Override
	public void onDisable() {
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(lable.equalsIgnoreCase("openshop") || lable.equalsIgnoreCase("shop")) {
			if(sender instanceof Player) {
				Player plr = (Player) sender;
				if(CreateInv() != null) {
					plr.openInventory(CreateInv());
					inventory(plr);
				}
			}
		}
		return false;
	}
	public void inventory(final Player plr) {
		Inventory stackinv = Bukkit.createInventory(plr, 9, ChatColor.GREEN + "" + ChatColor.BOLD + "Shop");
		Invs.put(plr, stackinv);
	}
	public Inventory CreateInv() {
		// item1 = Diamond, Fee = 5, ItemIndexInventorySlot = 11, ItemDisplayName = "Diamond";
		ItemStack item1 = new ItemStack(Material.DIAMOND);
		ItemMeta meta = item1.getItemMeta();
		List<String> lore = new ArrayList<String>();
		meta.setDisplayName(ChatColor.GRAY + "Diamond");
		meta.setCustomModelData(5);
		lore.add(ChatColor.GOLD + "Buy for " + meta.getCustomModelData() + " Gold");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setLore(lore);
		item1.setItemMeta(meta);
		inv.setItem(11, item1);
		// item2 = Iron_Ingot, Fee = 2, ItemIndexInventorySlot = 13, ItemDisplayName = "Iron ingot";
		ItemStack item2 = new ItemStack(Material.IRON_INGOT);
		lore.clear();
		meta.setCustomModelData(2);
		meta.setDisplayName(ChatColor.GRAY + "Iron ingot");
		lore.add(ChatColor.GOLD + "Buy for " + meta.getCustomModelData() + " Gold");
		meta.setLore(lore);
		item2.setItemMeta(meta);
		inv.setItem(13, item2);
		// item3 = Coal, Fee = 1, ItemIndexInventorySlot = 15, ItemDisplayName = "Coal";
		ItemStack item3 = new ItemStack(Material.COAL);
		lore.clear();
		meta.setCustomModelData(1);
		meta.setDisplayName(ChatColor.GRAY + "Coal");
		lore.add(ChatColor.GOLD + "Buy for " + meta.getCustomModelData() + " Gold");
		meta.setLore(lore);
		item3.setItemMeta(meta);
		inv.setItem(15, item3);
		// item_Exit = Barrier, Fee = NULL, ItemIndexInventorySlot = 9, ItemDisplayName = "Exit";
		ItemStack exit = new ItemStack(Material.BARRIER);
		lore.clear();
		lore.add("click to exit");
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Exit");
		meta.setCustomModelData(0);
		exit.setItemMeta(meta);
		inv.setItem(9, exit);
		// Item_none = Glass, Fee = NULL,;
		for(int i = 0; i < inv.getSize(); i++) {
			Bukkit.getServer().getConsoleSender().sendMessage(String.valueOf(i));
			if(inv.getItem(i) == null) {
				ItemStack none = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
				ItemMeta Meta = none.getItemMeta();
				Meta.setDisplayName("-");
				Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				Meta.setCustomModelData(0);
				none.setItemMeta(Meta);
				inv.setItem(i, none);
			}
		}
		return inv;
	}
	public Inventory itembuyStacks(ItemStack item, Inventory stackinv) {
		ItemStack item1 = item.clone();
		ItemMeta itemmeta = item1.getItemMeta();
		List<String> Lore = new ArrayList<String>();
		Lore.add(ChatColor.GOLD + "Buy 1 for " + 1 * item.getItemMeta().getCustomModelData() + " gold!");
		itemmeta.setLore(Lore);
		item1.setItemMeta(itemmeta);
		stackinv.setItem(4, item1);
		ItemStack item2 = item.clone();
		Lore.clear();
		Lore.add(ChatColor.GOLD + "Buy 5 for " + 5 * item.getItemMeta().getCustomModelData() + " gold!");
		itemmeta.setLore(Lore);
		item2.setItemMeta(itemmeta);
		item2.setAmount(5);
		stackinv.setItem(6, item2);
		ItemStack item3 = item.clone();
		Lore.clear();
		Lore.add(ChatColor.GOLD + "Buy 10 for " + 10 * item.getItemMeta().getCustomModelData() + " gold!");
		itemmeta.setLore(Lore);
		item3.setItemMeta(itemmeta);
		item3.setAmount(10);
		stackinv.setItem(8, item3);
		ItemStack exit = new ItemStack(Material.BARRIER);
		itemmeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Exit");
		Lore.clear();
		itemmeta.setLore(Lore);
		itemmeta.setCustomModelData(0);
		exit.setItemMeta(itemmeta);
		stackinv.setItem(0, exit);
		for(int i = 0; i < stackinv.getSize(); i++) {
			Bukkit.getServer().getConsoleSender().sendMessage(String.valueOf(i));
			if(stackinv.getItem(i) == null) {
				ItemStack none = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
				ItemMeta meta = none.getItemMeta();
				meta.setDisplayName("-");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.setCustomModelData(0);
				none.setItemMeta(meta);
				stackinv.setItem(i, none);
			}
		}
		return stackinv;
	}
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
			if(event.getInventory() == null) return;
			if(event.getInventory().equals(inv)) {
				if(event.getClickedInventory() == null) return;
				
				if(event.getCurrentItem() == null) return;
				if(event.getCursor() == null) return;
				if(event.getResult() == null) return;
				if(event.getClick() == null) return;
				if(event.getCurrentItem() == null) return;
				if(event.getCurrentItem().getItemMeta() == null) return;
				if(event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
				event.setCancelled(true);
				Player plr = (Player) event.getWhoClicked();
				if(event.getClickedInventory().equals(inv)) {
				if(event.getCurrentItem().getType() == Material.BARRIER) {
					event.getWhoClicked().closeInventory();
				}
				if(event.getCurrentItem().getType() != null && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().getType() != Material.BARRIER && event.getCurrentItem().getItemMeta().getCustomModelData() != 0) {
						if(event.getAction() != InventoryAction.NOTHING && event.getAction() != InventoryAction.PICKUP_HALF) {
							if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT,event.getCurrentItem().getItemMeta().getCustomModelData())) {
							if(CD.containsKey(plr.getName())) {
								if(CD.get(plr.getName()) > System.currentTimeMillis()) {
									plr.sendMessage(ChatColor.RED + "Please wait a bit before doing this!");
									float f = 0.1f;
									f += f + 0.1f;
									V += f;
									if(V >= 5) {
										plr.kickPlayer("Please do not spam this command!");
									}
									f -= 0.01f;
									return;
								}
							}	
								V /= V;
								CD.put(plr.getName(), System.currentTimeMillis() + 100);
								final ItemStack Gold = new ItemStack(Material.GOLD_INGOT);
								Gold.setAmount(event.getCurrentItem().getItemMeta().getCustomModelData());
								final Material Mitem = event.getCurrentItem().getType();
								ItemStack Item = new ItemStack(Mitem);
								Item.setAmount(1);
								event.getWhoClicked().getInventory().removeItem(Gold);
								if(plr.getInventory().firstEmpty() != -1) {
									event.getWhoClicked().getInventory().addItem(Item);
								}else {
									plr.getWorld().dropItemNaturally(plr.getLocation(), Item);
								}
								plr.playSound(plr.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
								return;
								}else {
									if(event.getAction() == InventoryAction.PICKUP_HALF && event.getCurrentItem().getItemMeta().getCustomModelData() != 0) {
										plr.openInventory(itembuyStacks(event.getCurrentItem(), Invs.get(event.getWhoClicked())));
										return;
									}
									event.getWhoClicked().sendMessage(ChatColor.RED + "You do not have enough gold! ");
									return;
							}
							}
					}else {
						return;
					}
				if(event.getAction() == InventoryAction.PICKUP_HALF) {
					if(event.getCurrentItem().getType() != Material.BARRIER) {
						plr.openInventory(itembuyStacks(event.getCurrentItem(),Invs.get(event.getWhoClicked())));
					}
				}
			}
		}
		if(event.getInventory() == Invs.get(event.getWhoClicked())) {
			if(event.getClickedInventory() == null) return;
			Inventory stackinv = (Inventory) event.getInventory();
			
				if(event.getCurrentItem() == null) return;
				if(event.getCursor() == null) return;
				if(event.getResult() == null) return;
				if(event.getClick() == null) return;
				if(event.getCurrentItem() == null) return;
				if(event.getCurrentItem().getItemMeta() == null) return;
				if(event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
				event.setCancelled(true);
				Player plr = (Player) event.getWhoClicked();
				if(event.getClickedInventory().equals(stackinv)) {
				if(event.getCurrentItem().getType() == Material.BARRIER) {
					plr.openInventory(inv);
				}
				if(event.getCurrentItem().getType() != null && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().getType() != Material.BARRIER && event.getCurrentItem().getItemMeta().getCustomModelData() != 0) {
					if(event.getAction() != InventoryAction.NOTHING && event.getAction() != InventoryAction.PICKUP_HALF) {
						ItemStack Gold = new ItemStack(Material.GOLD_INGOT);
						Gold.setAmount(event.getCurrentItem().getItemMeta().getCustomModelData() * event.getCurrentItem().getAmount());
						if (plr.getInventory().contains(Material.GOLD_INGOT, Gold.getAmount())) {
							if(CD.containsKey(plr.getName())) {
								if(CD.get(plr.getName()) > System.currentTimeMillis()) {
									plr.sendMessage(ChatColor.RED + "Please wait a bit before doing this!");
									return;
								}
							}
							CD.put(plr.getName(), System.currentTimeMillis() + 100);
							Material mat = event.getCurrentItem().getType();
							ItemStack Item = new ItemStack(mat);
							Item.setAmount(event.getCurrentItem().getAmount());
							if(plr.getInventory().firstEmpty() != -1) {
							plr.getInventory().addItem(Item);
							}else {
								plr.getWorld().dropItemNaturally(plr.getLocation(), Item);
							}
							plr.getInventory().removeItem(Gold);
							plr.playSound(plr.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
						}else {
							event.getWhoClicked().sendMessage(ChatColor.RED + "You do not have enough gold!");
							return;
						}
					}
				}
			}
		}
	}
}