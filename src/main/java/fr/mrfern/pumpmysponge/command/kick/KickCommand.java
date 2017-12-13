package fr.mrfern.pumpmysponge.command.kick;


import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.serializer.TextSerializers;

public class KickCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		// TODO Auto-generated method stub
		 Player pl = args.<Player>getOne("player").get();
	        String r = args.<String>getOne("raison").get();
	        pl.kick(TextSerializers.FORMATTING_CODE.deserialize(r));
	        Text message = Text.builder("Vous avez kick " + pl.getName()+" pour : "+r)
	        					.color(TextColors.RED)
	        					.build();
	        src.sendMessage(message);
	        return CommandResult.success();
	}

}
