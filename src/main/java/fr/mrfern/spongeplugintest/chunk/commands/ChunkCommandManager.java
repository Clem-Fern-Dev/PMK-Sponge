package fr.mrfern.spongeplugintest.chunk.commands;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import fr.mrfern.spongeplugintest.Main;

public class ChunkCommandManager {
	
	private static ChunkCommandManager instance = new ChunkCommandManager();
	private static Main mainManager;

	public static ChunkCommandManager commands(Main main) {
		mainManager = main;
		return instance;
	}
	
	public void setupCommands() {
		
		CommandSpec commandChunkInfoSpec = CommandSpec.builder()
			    .description(Text.of("ChunkInfo commande"))
			    .permission("spongeplugintest.chunk.commands.info")
			    .executor(new ChunkCommand())
			    .build();
		
		CommandSpec commandCreateChunkConfigSpec = CommandSpec.builder()
			    .description(Text.of("Chunk create file config commande"))
			    .permission("spongeplugintest.chunk.commands.createConfig")
			    .executor(new CreateChunkConfigCommand())
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandCreateChunkConfigSpec, "chunk-add");
		Sponge.getCommandManager().register(mainManager, commandChunkInfoSpec, "chunk");
		
	}

}
