package fr.mrfern.spongeplugintest.chunk.commands;

import org.spongepowered.api.Sponge;
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
		
		/*CommandSpec commandCreateChunkConfigSpec = CommandSpec.builder()
			    .description(Text.of("Chunk create file config commande"))
			    .permission("spongeplugintest.chunk.commands.op.createConfig")
			    .executor(new CreateChunkConfigCommand())
			    .build();
		*/
		
		CommandSpec commandClaimChunkSpec = CommandSpec.builder()
	    .description(Text.of("Chunk create file config commande"))
	    .permission("spongeplugintest.chunk.commands.claim")
	    .executor(new ClaimChunkCommand())
	    .build();
		
		CommandSpec commandUnClaimChunkSpec = CommandSpec.builder()
			    .description(Text.of("Chunk create file config commande"))
			    .permission("spongeplugintest.chunk.commands.unclaim")
			    .executor(new UnClaimChunkCommand())
			    .build();
		
		CommandSpec commandChunkInfoSpec = CommandSpec.builder()
			    .description(Text.of("ChunkInfo commande"))
			    .executor(new ChunkCommand())
			    .child(commandClaimChunkSpec, "c" , "claim")
			    .child(commandUnClaimChunkSpec, "u","unclaim")
			    .arguments(    			
			    			/*GenericArguments.flags().permissionFlag("spongeplugintest.chunk.commands.op.createConfig","-c").buildWith(GenericArguments.none())
			    		   /*GenericArguments.flags().permissionFlag("spongeplugintest.chunk.commands.op.deleteConfig","-d").buildWith(GenericArguments.none()),*/
			    		   /*GenericArguments.flags().permissionFlag("spongeplugintest.chunk.commands.op.createConfig","-r").buildWith(GenericArguments.none())*/)
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandClaimChunkSpec, "cc","chunk-claim");
		Sponge.getCommandManager().register(mainManager, commandUnClaimChunkSpec, "cu","chunk-unclaim");
		Sponge.getCommandManager().register(mainManager, commandChunkInfoSpec, "c", "chunk");
		
	}

}
