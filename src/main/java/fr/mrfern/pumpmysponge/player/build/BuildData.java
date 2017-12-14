package fr.mrfern.pumpmysponge.player.build;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.type.CarriedInventory;

public class BuildData {

	private Inventory inventory;

	public BuildData(Player targetEntity) {
		inventory = (targetEntity.getInventory());
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
}
