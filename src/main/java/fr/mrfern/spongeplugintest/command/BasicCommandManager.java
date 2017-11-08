package fr.mrfern.spongeplugintest.command;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class BasicCommandManager {
	
	public BasicCommandManager(CommandManager manager) {
		cmdManager = manager;
	}
	protected static CommandManager cmdManager;
	private static BasicCommandManager instance = new BasicCommandManager(null);
	
	public static BasicCommandManager commands(CommandManager manager) {
		cmdManager = manager;
		return instance;
	}
	
	public void setupCommands() {
		// tu y mes tout tes builds de commandes
		CommandSpec myCommandSpec = CommandSpec.builder()
			    .description(Text.of("Hello World Command"))
			    .permission("spongeplugintest.command.helloworld")
			    .executor(new HelloWorldCommand())
			    .build();
		CommandSpec messageCommandSpec = CommandSpec.builder()
				.description(Text.of("Message command"))
				.permission("spongeplugintest.command.message")
				.arguments(
						GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))),
						GenericArguments.remainingJoinedStrings(Text.of("message")))
				.executor(new MessageCommand())
				.build();
		
		Sponge.getCommandManager().register(this, myCommandSpec, "helloworld");
		Sponge.getCommandManager().register(this, messageCommandSpec, "msg", "m", "pm", "w");
	}
	
	public CommandManager getCmdManager() {
		return cmdManager;
	}
	
	

}
