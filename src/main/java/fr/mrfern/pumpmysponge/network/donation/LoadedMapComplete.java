package fr.mrfern.pumpmysponge.network.donation;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.effect.particle.ParticleOptions;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.entity.spawn.SpawnType;
import org.spongepowered.api.event.cause.entity.spawn.SpawnTypes;
import org.spongepowered.api.event.entity.living.humanoid.HandInteractEvent;
import org.spongepowered.api.event.entity.projectile.LaunchProjectileEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.World;

import com.flowpowered.math.vector.Vector3d;

import fr.mrfern.pumpmysponge.Main;

public class LoadedMapComplete {

	private Main main;

	public LoadedMapComplete(Main mainManager) {
		this.main = mainManager;
	}
	
	@Listener
	public void onArrowShot(LaunchProjectileEvent e,@Root Player player){
	    player.sendMessage(Text.of(e.getTargetEntity().getVelocity().getX() + " " + e.getTargetEntity().getVelocity().getY() + " " + e.getTargetEntity().getVelocity().getZ()));
	    
	}
	
	@Listener
	public void OnItemNavInteract(HandInteractEvent event, @First Player player) {
		Optional<ItemStack> itemOptionnal = player.getItemInHand(HandTypes.MAIN_HAND);
		if(itemOptionnal.isPresent()) {
			ItemStack item = itemOptionnal.get();
			
			if(item.get(Keys.DISPLAY_NAME).isPresent()) {
				
				String itemName = item.get(Keys.DISPLAY_NAME).get().toPlain();
				
				if(itemName.equals(Text.builder("ParticlesSpawn").color(TextColors.LIGHT_PURPLE).build().toPlain())) {
					
					Player p = player;
					
					p.sendMessage(Text.of(p.getHeadRotation().getX() + " " + p.getHeadRotation().getY() + " " + p.getHeadRotation().getZ()));
					p.sendMessage(Text.of(p.getVelocity().getX() + " " + p.getVelocity().getY() + " " + p.getVelocity().getZ())); 
					
					Optional<World> optionnalWorld = Sponge.getServer().getWorld("spawn");
					World world = optionnalWorld.get();
					
					// spawn particles
					
					
					
					//premier pilier
					

					
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.FIRE_SMOKE).option(ParticleOptions.QUANTITY, 50).build(), new Vector3d(-537, 32 , 1372), 30);
					/*world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-537, 32 , 1372), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-537, 32 , 1372), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-537, 32 , 1372), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-537, 32 , 1372), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-537, 32 , 1372), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-537, 32 , 1372), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-537, 32 , 1372), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-537, 32 , 1372), 30);*/
					
				}
				
			}
			
			
			
		}
	}
	
	@Listener
	public void MobInfoUpdate(GameStartedServerEvent event) {
		Optional<World> optionnalWorld = Sponge.getServer().getWorld("spawn");
		
		if(optionnalWorld.isPresent()) {
			
			World world = optionnalWorld.get();
			
			Entity randomEntity = world.createEntity(EntityTypes.CREEPER, new Vector3d(0,0,0));	
			randomEntity.offer(Keys.AI_ENABLED,false);
			//randomEntity.offer(Keys.)
			world.spawnEntity(randomEntity);
			
			Task task = Task.builder().execute(new Runnable() {
				
				@Override
				public void run() {
					// get bdd info
				}
			}).async().interval(1, TimeUnit.SECONDS).name("Update mob info task").submit(main);
			
		}
	}
	
	@Listener
	public void MobGetInfoUpdate(GameStartedServerEvent event) {
		Optional<World> optionnalWorld = Sponge.getServer().getWorld("spawn");
		
		if(optionnalWorld.isPresent()) {
			
			World world = optionnalWorld.get();
			
			Task task = Task.builder().execute(new Runnable() {
				
				@Override
				public void run() {
					// get bdd info					
				}
			}).async().interval(1, TimeUnit.SECONDS).name("Update mob info task").submit(main);
			
		}
	}
	
	@Listener
	public void InitLazerMob2(GameStartedServerEvent event) {
		Optional<World> optionnalWorld = Sponge.getServer().getWorld("spawn");
		
		if(optionnalWorld.isPresent()) {
			
			World world = optionnalWorld.get();
			
			@SuppressWarnings("unused")
			Task task = Task.builder().execute(new Runnable() {
				
				@Override
				public void run() {
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-530.52, 28.5 , 1365.492), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-530.52, 28.5 , 1379.492), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-544.52, 28.5 , 1365.492), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-544.52, 28.5 , 1379.492), 30);
					
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-530.52, 27 , 1365.492), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-530.52, 27 , 1379.492), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-544.52, 27 , 1365.492), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-544.52, 27 , 1379.492), 30);	
					//deuxième pilier
					
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-541.52, 29.5 , 1376.492), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-541.52, 29.5 , 1368.492), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-533.52, 29.5 , 1376.492), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.ANGRY_VILLAGER).build(), new Vector3d(-533.52, 29.5 , 1368.492), 30);		
					
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-541.52, 28 , 1376.492), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-541.52, 28 , 1368.492), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-533.52, 28 , 1376.492), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.LARGE_SMOKE).quantity(30).build(), new Vector3d(-533.52, 28 , 1368.492), 30);	
					
				}
			}).interval(1000,TimeUnit.MILLISECONDS).name("task1").submit(main);
			
			
		}			
		
	}
	
	@Listener
	public void InitLazerMob(GameStartedServerEvent event) {
		Optional<World> optionnalWorld = Sponge.getServer().getWorld("spawn");
		
		if(optionnalWorld.isPresent()) {
			
			World world = optionnalWorld.get();
			
			@SuppressWarnings("unused")
			Task task = Task.builder().execute(new Runnable()  {
				
				@Override
				public void run() {
					//System.out.println("schedule run");
					Vector3d vec1 = new Vector3d().add(-537.5-(-539.5), 29.5-31 , 1372.5-1374.5);
					Vector3d vec2 = new Vector3d().add(-537.5-(-539.5), 29.5-31 , 1372.5-1370.5);
					Vector3d vec3 = new Vector3d().add(-537.5-(-535.5), 29.5-31 , 1372.5-1374.5);
					Vector3d vec4 = new Vector3d().add(-537.5-(-535.5), 29.5-31 , 1372.5-1370.5);
					
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.CLOUD).quantity(50).option(ParticleOptions.VELOCITY, vec1).build(), new Vector3d(-539.5, 31 , 1374.492), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.CLOUD).quantity(50).option(ParticleOptions.VELOCITY, vec2).build(), new Vector3d(-539.5, 31 , 1370.492), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.CLOUD).quantity(50).option(ParticleOptions.VELOCITY, vec3).build(), new Vector3d(-535.5, 31 , 1374.492), 30);
					world.spawnParticles(ParticleEffect.builder().type(ParticleTypes.CLOUD).quantity(50).option(ParticleOptions.VELOCITY, vec4).build(), new Vector3d(-535.5, 31 , 1370.492), 30);		
					
				}
			}).interval(100,TimeUnit.MILLISECONDS).name("task2").submit(main);;

			
		}else {
			main.getLogger().warn("Impossible d'init DonationMob, missing optionnal map");
		}
		
	}

	
	
}
