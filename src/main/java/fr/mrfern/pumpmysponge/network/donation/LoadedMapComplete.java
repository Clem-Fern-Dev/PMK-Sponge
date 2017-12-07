package fr.mrfern.pumpmysponge.network.donation;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.spongepowered.api.GameRegistry;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.effect.particle.ParticleOption;
import org.spongepowered.api.effect.particle.ParticleOptions;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.projectile.Projectile;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.living.humanoid.HandInteractEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.util.blockray.BlockRay;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.Extent;

import com.flowpowered.math.vector.Vector3d;

import fr.mrfern.pumpmysponge.Main;

public class LoadedMapComplete {

	private Main main;

	public LoadedMapComplete(Main mainManager) {
		this.main = mainManager;
	}
	
	@Listener
	public void OnItemNavInteract(HandInteractEvent event, @First Player player) {
		Optional<ItemStack> itemOptionnal = player.getItemInHand(HandTypes.MAIN_HAND);
		if(itemOptionnal.isPresent()) {
			ItemStack item = itemOptionnal.get();
			
			String itemName = item.get(Keys.DISPLAY_NAME).get().toPlain();
			
			if(itemName.equals(Text.builder("ParticlesSpawn").color(TextColors.LIGHT_PURPLE).build().toPlain())) {
				
				Player p = player;
				
				p.sendMessage(Text.of(p.getHeadRotation().getX() + " " + p.getHeadRotation().getY() + " " + p.getHeadRotation().getZ()));
				p.sendMessage(Text.of(p.getVelocity().getX() + " " + p.getVelocity().getY() + " " + p.getVelocity().getZ())); 
				
				
				Optional<World> optionnalWorld = Sponge.getServer().getWorld("spawn");
				World world = optionnalWorld.get();
				
				// spawn particles
				//premier pilier
				
				world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-530.52, 28.5 , 1365.492), 30);
				world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-530.52, 28.5 , 1379.492), 30);
				world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-544.52, 28.5 , 1365.492), 30);
				world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-544.52, 28.5 , 1379.492), 30);
				
				//faiseau entre un est deux
				
				//deuxième pilier
				
				world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-541.52, 29.5 , 1376.492), 30);
				world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-541.52, 29.5 , 1368.492), 30);
				world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-533.52, 29.5 , 1376.492), 30);
				world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-533.52, 29.5 , 1368.492), 30);
				
				world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.CLOUD).quantity(30).option(ParticleOptions.VELOCITY, new Vector3d(1,1,1)).build(), new Vector3d(-537, 32 , 1372), 30);
				world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-537, 32 , 1372), 30);
			}
			
			
		}
	}
	
	@Listener
	public void InitLazerMob(GameStartedServerEvent event) {
		Optional<World> optionnalWorld = Sponge.getServer().getWorld("spawn");
		
		if(optionnalWorld.isPresent()) {
			
			World world = optionnalWorld.get();
			
			//BlockRay<World> blockRayStartPoint_1 = BlockRay.from(new Location<World>(world, 0,0,0)).direction(new Vector3d()).build();
			
			//BlockRay<World> blockRay = BlockRay.from(Sponge.getServer().getPlayer("test").get())
				   // .stopFilter(BlockRay.continueAfterFilter(BlockRay.onlyAirFilter(), 1)).build();
			
			/*Task.Builder taskBuilder = Task.builder();
			
			GameRegistry gameRegistry = Sponge.getRegistry();
			
			 taskBuilder.execute(
				    () -> {
				    	world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.FIREWORKS_SPARK).velocity(new Vector3d(1, 1, 1)).build(), 
								new Vector3d(-534.52, 27.4 , 1365.492));
				    	main.getLogger().info("Particle");
				    }
				).async().interval(500, TimeUnit.MILLISECONDS);*/
			
			
			//BlockRay<World> blockRayStartPoint_2 = BlockRay.from(new Location<World>(world, 0,0,0)).direction(new Vector3d()).distanceLimit(2).build();
			
			
			
			
		}else {
			main.getLogger().warn("Impossible d'init DonationMob, missing optionnal map");
		}
		
	}

	
	
}
