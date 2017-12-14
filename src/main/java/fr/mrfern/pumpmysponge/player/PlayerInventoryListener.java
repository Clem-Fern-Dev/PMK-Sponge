package fr.mrfern.pumpmysponge.player;

import java.util.Optional;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.living.humanoid.HandInteractEvent;
import org.spongepowered.api.event.entity.living.humanoid.player.RespawnPlayerEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.filter.type.Exclude;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.entity.Hotbar;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import fr.mrfern.pumpmysponge.Main;

public class PlayerInventoryListener {

	@SuppressWarnings("unused")
	private Main main;

	public PlayerInventoryListener(Main mainManager) {
		this.main = mainManager;
	}
	
	@Listener
	@Exclude(ClickInventoryEvent.NumberPress.class)
	public void onInventoryEvent(ClickInventoryEvent e, @First Player player) {
		if(player.hasPermission("group.build") & !player.gameMode().get().equals(GameModes.SURVIVAL)) {
			return;
		}else {
			e.setCancelled(true);
		}
	}
	
	@Listener
	public void onInventoryGive(ClientConnectionEvent.Join e, @First Player player) {
		Inventory inv = player.getInventory();
		playerGiveNavItem(player,inv);
	}

	@Listener
	public void onInventoryGive(RespawnPlayerEvent e,@First Player player) {
		Inventory inv = player.getInventory();
		playerGiveNavItem(player,inv);		
	}
	
	private void playerGiveNavItem(Player player, Inventory inv) {
		
		inv.clear();
		
		ItemStack navItemServeur_1 = ItemStack.builder().itemType(ItemTypes.CARROT).build();
		ItemStack navItemServeur = ItemStack.builder().itemType(ItemTypes.ANVIL).build();	
		ItemStack navItemServeur_2 = ItemStack.builder().itemType(ItemTypes.APPLE).build();	
		
		navItemServeur_1.offer(Keys.DISPLAY_NAME,Text.builder("PumpMyRagnaMod ").color(TextColors.LIGHT_PURPLE).append(Text.builder("#1").color(TextColors.GREEN).build()).build());	
		
		navItemServeur.offer(Keys.DISPLAY_NAME,Text.builder("Test_Server_Dev").color(TextColors.LIGHT_PURPLE).build());
		
		navItemServeur_2.offer(Keys.DISPLAY_NAME,Text.builder("PumpMyRagnaMod ").color(TextColors.LIGHT_PURPLE).append(Text.builder("#2").color(TextColors.AQUA).build()).build());	
		
		inv.query(Hotbar.class).query(new SlotIndex(3)).set(navItemServeur_1);
		if(player.hasPermission("group.dev")) {
			inv.query(Hotbar.class).query(new SlotIndex(4)).set(navItemServeur);
		}
		inv.query(Hotbar.class).query(new SlotIndex(5)).set(navItemServeur_2);
	}
	
	@Listener
	public void OnItemNavInteract(HandInteractEvent event, @First Player player) {
		Optional<ItemStack> itemOptionnal = player.getItemInHand(HandTypes.MAIN_HAND);
		if(itemOptionnal.isPresent()) {
			ItemStack item = itemOptionnal.get();
			
			if(item.get(Keys.DISPLAY_NAME).isPresent()) {
				
				String itemName = item.get(Keys.DISPLAY_NAME).get().toPlain();
				
				
				Text textNavItem_1 = Text.builder("PumpMyRagnaMod ").color(TextColors.LIGHT_PURPLE).append(Text.builder("#1").color(TextColors.GREEN).build()).build();
				Text textNavItem_2 = Text.builder("PumpMyRagnaMod ").color(TextColors.LIGHT_PURPLE).append(Text.builder("#2").color(TextColors.AQUA).build()).build();			
				Text textNavItem = Text.builder("Test_Server_Dev").color(TextColors.LIGHT_PURPLE).build();
				
				Text refuseMessage = Text.builder("Refusé !").color(TextColors.RED).build();
				
				Text textEnTete;
				Text textConnect;
				
				if(itemName.equals(textNavItem_1.toPlain())) {
					if(player.hasPermission("pumpmysponge.server.ragna1")) {
						textConnect = Text.builder("Requète de connection vers le serveur : ").color(TextColors.YELLOW).append(Text.builder("PumpMyRagnaMod#1").color(TextColors.AQUA).build()).build();
				    	textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textConnect).build();
				    	
				    	player.sendMessage(textEnTete);
						Sponge.getChannelRegistrar().getOrCreateRaw(this, "BungeeCord").sendTo(player, buf -> buf.writeUTF("Connect").writeUTF("ragna1"));
				    	
					}else {
						
						textConnect = Text.builder("Requète de connection vers le serveur : ").color(TextColors.YELLOW).append(Text.builder("PumpMyRagnaMod#1").color(TextColors.AQUA).build(),refuseMessage).build();
				    	textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textConnect).build();
				    	player.sendMessage(textEnTete);
				    	
					}					
				}else if(itemName.equals(textNavItem_2.toPlain())) {
					
					if(player.hasPermission("pumpmysponge.server.ragna2")){
						
						textConnect = Text.builder("Requète de connection vers le serveur : ").color(TextColors.RED).append(Text.builder("PumpMyRagnaMod#2").color(TextColors.AQUA).build()).build();
						textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textConnect).build();
						
						player.sendMessage(textEnTete);
						Sponge.getChannelRegistrar().getOrCreateRaw(this, "BungeeCord").sendTo(player, buf -> buf.writeUTF("PlayerCount").writeUTF("ragna2"));
						
					}else {
						
						textConnect = Text.builder("Requète de connection vers le serveur : ").color(TextColors.YELLOW).append(Text.builder("PumpMyRagnaMod#2").color(TextColors.AQUA).build(),refuseMessage).build();
				    	textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textConnect).build();
				    	player.sendMessage(textEnTete);
				    	
					}
					
				}else if(itemName.equals(textNavItem.toPlain())) {
					if(player.hasPermission("group.dev")) {
						Sponge.getChannelRegistrar().getOrCreateRaw(this, "BungeeCord").sendTo(player, buf -> buf.writeUTF("Connect").writeUTF("lobby2"));	
					}else {
						
						
						
					}
				}
				
			}
			
		}
	}
	
	
	
	
}
