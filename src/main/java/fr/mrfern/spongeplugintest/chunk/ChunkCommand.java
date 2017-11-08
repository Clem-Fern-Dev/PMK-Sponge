package fr.mrfern.spongeplugintest.chunk;

import org.spongepowered.api.command.CommandManager;

public class ChunkCommand {
	
	public ChunkCommand(CommandManager manager) {
		cmdManager = manager;
	}
	
	protected static CommandManager cmdManager;
	private static ChunkCommand instance = new ChunkCommand(null);

	public static ChunkCommand commands(CommandManager manager) {
		cmdManager = manager;
		return instance;
	}
	
	public void setupCommands() {
		// tu y mes tout tes builds de commandes
	}

	public CommandManager getCmdManager() {
		return cmdManager;
	}
	
}
