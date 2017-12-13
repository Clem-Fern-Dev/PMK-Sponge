package fr.mrfern.pumpmysponge;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameLoadCompleteEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.network.ChannelBinding.RawDataChannel;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.SpongeExecutorService;

import com.google.inject.Inject;

import fr.mrfern.pimpmysponge.command.tp.TpaCommandManager;
import fr.mrfern.pumpmysponge.command.BasicCommandManager;
import fr.mrfern.pumpmysponge.config.Config;
import fr.mrfern.pumpmysponge.network.donation.DonationMob;
import fr.mrfern.pumpmysponge.player.PlayerListenerManager;
import fr.mrfern.pumpmysponge.player.config.PlayerConfig;
import me.lucko.luckperms.api.Group;
import me.lucko.luckperms.api.LuckPermsApi;



@Plugin(id = "pumpmysponge", name = "PumpMySponge", version = "1.0.4")
public class Main {
	
	protected static String pluginName = "PumpMySponge";
	
	protected static String defaultpath = "mods/plugins/"+ pluginName +"/";
	protected static String playerpath = "mods/plugins/"+ pluginName +"/player";
	protected static String chunkpath = "mods/plugins/"+ pluginName +"/chunk";
	
	protected static LuckPermsApi api;
	protected static Set<Group> groupList;
	protected static SpongeExecutorService minecraftExecutor;
	
	public static SpongeExecutorService getExecutorService() {
		return minecraftExecutor;
	}
	
	public static LuckPermsApi getPermsAPI() {
		return api;
	}
	
	public static Set<Group> getGroupList(){
		return groupList;
	}
	
	public static String getPluginName() {		return pluginName;	}
	
	@Inject
	private Logger logger;

	private static RawDataChannel bungeeChannel;
	
	public static RawDataChannel getBungeeChannel() {
		return bungeeChannel;
	}
	
	public Logger getLogger() {
		return this.logger;
	}
	
	@Listener
	public void onPreInit(GamePreInitializationEvent event) throws IOException {
		
		logger.info("Plugin preInit " + defaultpath);
		
		
		Config.getInstance().setup();
		PlayerConfig.getInstance().setup();
		//ChunkConfig.getInstance().setup();
    
		/*L’événement GamePreInitializationEvent est levé. Durant cet état, le plugin se prépare à l’initialisation. 
		 * Les accès à l’instance du logger par défaut et aux informations concernant les localisations de fichiers de configurations préférées 
		 * sont disponibles.
		 */		
	}
	
	@Listener
	public void onInit(GameInitializationEvent event) {
		
		logger.info("Plugin Init");
		/*L’événement GameInitializationEvent est levé. Durant cet état, le plugin devrait avoir finit tout ce qu’il avait à faire afin de fonctionner. 
		 * Les gestionnaires d’événements sont traités à ce moment là.
		 */
		
		//ChunkListenerManager.listener(this).setup();
		PlayerListenerManager.listener(this).setup();
		
	}
	
	@Listener
	public void onPostInit(GamePostInitializationEvent event) {
		
		logger.info("Plugin Post Init");
		
		bungeeChannel = Sponge.getChannelRegistrar().getOrCreateRaw(this, "BungeeCord");
		
		logger.info("BungeeChannel Init OK !");		
		
		Optional<LuckPermsApi> provider = Sponge.getServiceManager().provide(LuckPermsApi.class);
		if (provider.isPresent()) {
		    api = provider.get();
		    logger.info(" LuckPermsApi.class init OK ! ");
		    
		    groupList = api.getGroups();
		    
		}else {
			logger.info(" LuckPermsApi.class init NOT OK ! ");
		}
		
		DonationMob.listener(this).setup();
		
		/* L’événement GamePostInitializationEvent est levé. Par cet état, les communications inter-plugin devraient être prêtes à se produire. 
		 * Les plugins fournissant une API devraient être prêts à accepter des requêtes de base.
		 */
		
	}
	
	@Listener
	public void onInitComplete(GameLoadCompleteEvent event) {
		
		logger.info("Plugin Load complete");
		
		
		//L’événement GameLoadCompleteEvent est levé. Par cet état, toutes les initialisations des plugins devraient être terminées.
		
	}
	
	@Listener
	public void onStartServer(GameStartingServerEvent event) {
		
		// Tu crée ton commands manager
		CommandManager cmdManager = Sponge.getCommandManager();
		//Tu appelles ta classe, tu l'instancie en y ajoutant le command manager par la methods commands, et enfin tu fait un setup pour build les commands
		//ChunkCommandManager.commands(this).setupCommands();
		BasicCommandManager.commands(this,cmdManager).setupCommands();
		TpaCommandManager.commands(this, cmdManager).setupCommands();
	}
}

