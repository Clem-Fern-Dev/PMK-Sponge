package fr.mrfern.spongeplugintest.command.tp;

import java.util.HashMap;
import java.util.UUID;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import fr.mrfern.pumpmysponge.Main;

public class TpaCommandManager {
	
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
			.permission("spongeplugintest.command.tpa")
			.arguments(
					GenericArguments.onlyOne(GenericArguments.player(Text.of("target"))))
			.executor(new TpaCommand())
			.build();
		CommandSpec tpacceptCommandSpec = CommandSpec.builder()
				.description(Text.of("Accpetion tp request command"))
				.permission("spongeplugintest.command.tpaccept")
				.executor(new TpacceptCommand())
				.build();
		
		Sponge.getCommandManager().register(mainManager, tpaCommandSpec, "tpa");
		Sponge.getCommandManager().register(mainManager, tpacceptCommandSpec, "tpaccept","tpyes");
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
