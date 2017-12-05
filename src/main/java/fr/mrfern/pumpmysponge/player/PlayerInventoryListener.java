package fr.mrfern.pumpmysponge.player;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.living.humanoid.player.RespawnPlayerEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.Color;

import fr.mrfern.pumpmysponge.Main;

public class PlayerInventoryListener {

	private Main main;

	public PlayerInventoryListener(Main mainManager) {
		this.main = mainManager;
	}
	
	@Listener
	public void onInventoryEvent(ChangeInventoryEvent e, @First Player player) {
		e.setCancelled(true);
	}
	
	@Listener
	public void onInventoryGive(RespawnPlayerEvent e,@First Player player) {
		Inventory inv = player.getInventory();
		
		inv.clear();
		
		ItemStack navItem = ItemStack.builder().itemType(ItemTypes.COMPASS).build();
		navItem.offer(Keys.COLOR,Color.BLUE);
		List<Text> itemLore = new ArrayList<>();
		
		itemLore.add(Text.of("item_lore test"));
		
		navItem.offer(Keys.ITEM_LORE,itemLore);
		
		inv.set(navItem);
		
	}

	
	
}
