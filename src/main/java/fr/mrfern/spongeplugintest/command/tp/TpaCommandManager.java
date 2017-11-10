package fr.mrfern.spongeplugintest.command.tp;

import java.util.HashMap;
import java.util.UUID;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import fr.mrfern.spongeplugintest.Main;

public class TpaCommandManager {
	
	@SuppressWarnings("unused")
	private static Main mainManager;
	
	public TpaCommandManager(CommandManager manager) {
		cmdManager = manager;
	}
	
	protected static CommandManager cmdManager;
	public static HashMap<UUID, TeleportData> hm = new HashMap<>();
	
	private static TpaCommandManager instance = new TpaCommandManager(null);
	
	public static TpaCommandManager commands(Main main, CommandManager manager) {
		cmdManager = manager;
		mainManager = main;
		return instance;
	}
	
	public void setupCommands() {
		CommandSpec tpaCommandSpec = CommandSpec.builder()
			.description(Text.of("Tp request command"))
			.permission("spongeplugintest")
			.arguments(
					GenericArguments.onlyOne(GenericArguments.player(Text.of("target"))))
			.executor(new TpaCommand())
			.build();
		
		Sponge.getCommandManager().register(mainManager, tpaCommandSpec, "tpa");
	}
	
	public CommandManager getCmdManager() {
		return cmdManager;
	}
	
	public static HashMap<UUID, TeleportData> getTpHM() {
		return hm;
	}
	
	public static void setTpHM( HashMap<UUID, TeleportData> hashmap ){
		hm = hashmap;
	}
}
