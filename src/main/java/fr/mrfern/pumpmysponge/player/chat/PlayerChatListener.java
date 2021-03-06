package fr.mrfern.pumpmysponge.player.chat;

import java.util.Optional;
import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.text.Text;

import fr.mrfern.pumpmysponge.Main;
import fr.mrfern.pumpmysponge.command.mute.MuteCommandManager;
import me.lucko.luckperms.api.Contexts;
import me.lucko.luckperms.api.Group;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.User;

public class PlayerChatListener {

	@SuppressWarnings("unused")
	private Logger logger;
	@SuppressWarnings("unused")
	private Main main;
	
	public PlayerChatListener(Main main) {
		this.main = main;
		this.logger = main.getLogger();
	}
	
	@Listener
	public void onPlayerSendMessage(MessageChannelEvent.Chat event, @First Player player) {
		
		final LuckPermsApi api = Main.getPermsAPI();
		String message;
		
		Optional<User> user = api.getUserSafe(player.getUniqueId());
		if(MuteCommandManager.isMute(player.getUniqueId())) {
			event.setCancelled(true);
			if(MuteCommandManager.getHoursRemaining(player.getUniqueId())>0) {
				String sendmessage = new String("Vous êtes mute pendant " + MuteCommandManager.getHoursRemaining(player.getUniqueId()) + "h " + MuteCommandManager.getMinutesRemaining(player.getUniqueId())+"minutes");
				player.sendMessage(Text.of(sendmessage));
			}
			else {
				String sendmessage = new String("Vous êtes mute pendant " + MuteCommandManager.getMinutesRemaining(player.getUniqueId())+ "minutes");
				player.sendMessage(Text.of(sendmessage));
			}
		}
		else if(!MuteCommandManager.isMute(player.getUniqueId())) {
			if(user.isPresent()) {
				
				message = "";
				
				for (Group group : Main.getGroupList()) {				
					if(player.hasPermission("group." + group.getName())) {
						message += " " + group.getCachedData().getMetaData(makeContexts(user.get(), api)).getPrefix();
					}				
				}
				
				message += user.get().getPrimaryGroup() + "  " + player.getName() + " ";
				event.setMessage(Text.builder().append(Text.of(message),event.getRawMessage().toBuilder().build()));
				
			}
		}
	}
	
	private Contexts makeContexts(User user,LuckPermsApi api) {
        Contexts contexts = api.getContextForUser(user).orElse(null);
        if (contexts == null) {
            contexts = Contexts.allowAll();
        }
        return contexts;
    }
	
}
