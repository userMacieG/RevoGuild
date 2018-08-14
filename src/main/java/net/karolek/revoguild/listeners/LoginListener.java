package net.karolek.revoguild.listeners;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class LoginListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLogin(PlayerLoginEvent e) {
        Guild g = GuildManager.getGuild(e.getPlayer());
        if (g == null)
            return;
        if (!g.isBanned())
            return;

        String kickMsg = Messages.parse(Messages.BAN$REASON, g);

        e.disallow(Result.KICK_BANNED, kickMsg);

    }

}
