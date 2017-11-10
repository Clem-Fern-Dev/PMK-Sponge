package fr.mrfern.spongeplugintest.chunk;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import fr.mrfern.spongeplugintest.Main;

public class ChunkCommandManager {
	
	public ChunkCommandManager(CommandManager manager) {
		cmdManager = manager;
	}
	
	protected static CommandManager cmdManager;
	private static ChunkCommandManager instance = new ChunkCommandManager(null);
	private static Main mainManager;

	public static ChunkCommandManager commands(Main main,CommandManager manager) {
		cmdManager = manager;
		mainManager = main;
		return instance;
	}
	
	public void setupCommands() {
		
		CommandSpec commandChunkInfoSpec = CommandSpec.builder()
			    .description(Text.of("ChunkInfo commande"))
			    .permission("spongeplugintest.chunk.commands.info")
			    .executor(new ChunkCommand())
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandChunkInfoSpec, "chunk");
		
		CommandSpec commandCreateChunkConfigSpec = CommandSpec.builder()
			    .description(Text.of("Chunk create file config commande"))
			    .permission("spongeplugintest.chunk.commands.createConfig")
			    .executor(new CreateChunkConfigCommand())
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandCreateChunkConfigSpec, "chunk-add");
		
	}

	public CommandManager getCmdManager() {
		return cmdManager;
	}

	
}
