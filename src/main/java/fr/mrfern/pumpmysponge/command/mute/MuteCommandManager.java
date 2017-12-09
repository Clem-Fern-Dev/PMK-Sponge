package fr.mrfern.pumpmysponge.command.mute;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.Map.Entry;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import fr.mrfern.pumpmysponge.Main;

public class MuteCommandManager {
	
	private static Main mainManager;
	
	public MuteCommandManager(CommandManager manager) {
		cmdManager = manager;
	}
	
	protected static CommandManager cmdManager;
	public static HashMap<UUID, MuteData> hm = new HashMap<>();
	
	private static MuteCommandManager instance = new MuteCommandManager(null);
	
	public static MuteCommandManager commands(Main main, CommandManager manager) {
		cmdManager = manager;
		mainManager = main;
		return instance;
	}
	
	public void setupCommands() {
		CommandSpec muteCommandSpec = CommandSpec.builder()
			.description(Text.of("Commande de mute"))
			.permission("pumpmysponge.command.mute")
			.arguments(
					GenericArguments.flags().flag("all").buildWith(GenericArguments.none()),
    				GenericArguments.player(Text.of("player")),
    				GenericArguments.integer(Text.of("hour")),
    				GenericArguments.integer(Text.of("minute")),		    				
    				GenericArguments.remainingJoinedStrings(Text.of("raison")))
			.executor(new MuteCommand())
			.build();
		CommandSpec unmuteCommandSpec = CommandSpec.builder()
			.description(Text.of("Command d'unmute"))
			.permission("pumpmysponge.command.unmute")
			.arguments(
					GenericArguments.player(Text.of("player")))
			.executor(new UnmuteCommand())
			.build();
		
		Sponge.getCommandManager().register(mainManager, muteCommandSpec, "mute");
		Sponge.getCommandManager().register(mainManager, unmuteCommandSpec, "unmute","demute");
	}
	
	public CommandManager getCmdManager() {
		return cmdManager;
	}
	
	public static HashMap<UUID, MuteData> getMuteHM() {
		return hm;
	}
	
	public static void setMuteHM( HashMap<UUID, MuteData> hashmap ){
		hm = hashmap;
	}
	
	static HashMap<UUID,MuteData> hashmap = MuteCommandManager.getMuteHM();
	
	public static boolean isMute(UUID uuid) {	
		for(Iterator<Entry<UUID, MuteData>> iter = hashmap.entrySet().iterator(); iter.hasNext(); ) {
			Entry<UUID, MuteData> entry = iter.next();
			MuteData data = entry.getValue();
			
			if(System.currentTimeMillis() > data.getUnmuteTimestamp() ) {
				iter.remove(); // C'est censé remove les mute périmés
				return false;
			}
			else if(entry.getKey().equals(uuid) && System.currentTimeMillis() < data.getUnmuteTimestamp()) {
				return true;
			}
		}
		return false;
	}
	
	public static int getHoursRemaining(UUID uuid) {
		long milliSRemaining = System.currentTimeMillis()-hashmap.get(uuid).getUnmuteTimestamp();
		int hour =(int) (milliSRemaining-(milliSRemaining%(3.6*1000000))/3.6*1000000);
		return hour;
	}
	
	public static int getMinutesRemaining(UUID uuid) {
		long milliSRemaining = System.currentTimeMillis()-hashmap.get(uuid).getUnmuteTimestamp();
		long milliSWOH =(long) ( milliSRemaining -( milliSRemaining -( milliSRemaining %(3.6*1000000))));
		int minute = (int) ((milliSWOH -(milliSWOH%60000))/60000);
		return minute;
	}
}
