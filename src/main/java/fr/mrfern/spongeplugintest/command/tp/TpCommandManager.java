package fr.mrfern.spongeplugintest.command.tp;

import org.spongepowered.api.command.CommandManager;
import fr.mrfern.spongeplugintest.Main;

public class TpCommandManager {
	
	@SuppressWarnings("unused")
	private static Main mainManager;
	
	public TpCommandManager(CommandManager manager) {
		cmdManager = manager;
	}
	protected static CommandManager cmdManager;
	private static TpCommandManager instance = new TpCommandManager(null);
	
	public static TpCommandManager commands(Main main, CommandManager manager) {
		cmdManager = manager;
		mainManager = main;
		return instance;
	}
	public void setupCommands() {
		
	}
	
	public CommandManager getCmdManager() {
		return cmdManager;
	}
}
