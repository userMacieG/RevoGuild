package net.karolek.revoguild.listeners;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class GuildCommandsListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();

        Guild g = GuildManager.getGuild(p.getLocation());


        if (!Config.CUBOID_DISABLED$COMMANDS_ENABLED) return;
        if (g == null) return;
        if (g.isMember(UserManager.getUser(p).getUuid())) return;
        for (String s : Config.CUBOID_DISABLED$COMMANDS_COMMANDS) {
            if (!msg.contains("/" + s))
                continue;
            e.setCancelled(true);
            if (Config.CUBOID_DISABLED$COMMANDS_NOTIFY_ENABLED)
                Util.sendMessage(p, Util.fixColor(Config.CUBOID_DISABLED$COMMANDS_NOTIFY_MESSAGE));
        }

    }

}
