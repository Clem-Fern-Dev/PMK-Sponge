package fr.mrfern.spongeplugintest.chunk.commands;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import fr.mrfern.spongeplugintest.Main;

@Deprecated
public class ChunkCommandManager {
	
	private static ChunkCommandManager instance = new ChunkCommandManager();
	private static Main mainManager;

	public static ChunkCommandManager commands(Main main) {
		mainManager = main;
		return instance;
	}
	
	public void setupCommands() {
		
		CommandSpec commandClaimChunkSpec = CommandSpec.builder()
	    .description(Text.of("Claim d'une zone"))
	    .permission("spongeplugintest.chunk.commands.claim")
	    .executor(new ClaimChunkCommand())
	    .build();
		
		CommandSpec commandUnClaimChunkSpec = CommandSpec.builder()
			    .description(Text.of("Unclaim d'une zone"))
			    .permission("spongeplugintest.chunk.commands.unclaim")
			    .executor(new UnClaimChunkCommand())
			    .build();
		
		CommandSpec commandGroupAddChunkSpec = CommandSpec.builder()
			    .description(Text.of("Manager les groupes de chunk"))
			    .permission("spongeplugintest.chunk.commands.changegroup.add")
			    .executor(new ChangeGroupAddChunk())
			    .arguments(GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))))
			    .build();
		
		CommandSpec commandGroupDelChunkSpec = CommandSpec.builder()
			    .description(Text.of("Manager les groupes de chunk"))
			    .permission("spongeplugintest.chunk.commands.changegroup.del")
			    .executor(new ChangeGroupDelChunk())
			    .arguments(GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))))
			    .build();
		
		
		
		CommandSpec commandChunkInfoSpec = CommandSpec.builder()
			    .description(Text.of("ChunkInfo commande"))
			    .executor(new ChunkCommand())
			    .child(commandClaimChunkSpec, "c" , "claim")
			    .child(commandUnClaimChunkSpec, "u","unclaim")
			    .child(commandGroupAddChunkSpec, "g-add")
			    .child(commandGroupDelChunkSpec, "g-del")
			    .arguments(    			
			    			/*GenericArguments.flags().permissionFlag("spongeplugintest.chunk.commands.op.createConfig","-c").buildWith(GenericArguments.none())
			    		   /*GenericArguments.flags().permissionFlag("spongeplugintest.chunk.commands.op.deleteConfig","-d").buildWith(GenericArguments.none()),*/
			    		   /*GenericArguments.flags().permissionFlag("spongeplugintest.chunk.commands.op.createConfig","-r").buildWith(GenericArguments.none())*/)
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandGroupAddChunkSpec, "cg-add","chunk-groupadd");
		Sponge.getCommandManager().register(mainManager, commandGroupDelChunkSpec, "cg-del","chunk-groupdel");
		
		Sponge.getCommandManager().register(mainManager, commandClaimChunkSpec, "cc","chunk-claim");
		Sponge.getCommandManager().register(mainManager, commandUnClaimChunkSpec, "cu","chunk-unclaim");
		Sponge.getCommandManager().register(mainManager, commandChunkInfoSpec, "c", "chunk");
		
	}

}
