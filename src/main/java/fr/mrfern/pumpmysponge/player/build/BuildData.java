package fr.mrfern.pumpmysponge.player.build;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.type.CarriedInventory;

public class BuildData {

	private CarriedInventory<? extends Carrier> inventory;

	public BuildData(Player targetEntity) {
		this.setInventory(targetEntity.getInventory());
	}

	public CarriedInventory<? extends Carrier> getInventory() {
		return inventory;
	}

	public void setInventory(CarriedInventory<? extends Carrier> inventory) {
		this.inventory = inventory;
	}

}
