package net.karolek.revoguild.listeners;

import net.karolek.revoguild.objects.guild.Alliance;
import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.AllianceManager;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.utils.Util;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String format = e.getFormat();
        Player p = e.getPlayer();
        User u = UserManager.getUser(p);
        if (u == null) {
            return;
        }
        Guild g = GuildManager.getGuild(p);
        String guildTag = g != null ? Messages.parse(Config.CHAT_FORMAT_TAG, g) : "";
        String rank = Config.CHAT_FORMAT_RANK.replace("{RANK}", Integer.toString(u.getPoints()));
        String local = StringUtils.replace(format, "{GUILD}", guildTag);
        local = local.replace("{RANK}", Util.fixColor(rank));
        e.setFormat(local);
        localChat(e);
    }

    private void localChat(AsyncPlayerChatEvent e) {
        if (!Config.CHAT_LOCAL_ENABLED) {
            return;
        }
        Player p = e.getPlayer();
        String msg = e.getMessage();
        Guild g = GuildManager.getGuild(p);
        if (msg.startsWith(Config.CHAT_LOCAL_CHAR + Config.CHAT_LOCAL_CHAR)) {
            if (g == null) {
                Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);
                e.setCancelled(true);
                return;
            }
            String format = Config.CHAT_LOCAL_FORMAT_ALLIANCE;
            format = Messages.parse(format, g);
            format = Messages.parse(format, p);
            format = format.replace("{MESSAGE}", ChatColor.stripColor(Util.fixColor(msg)));
            format = format.replaceFirst(Config.CHAT_LOCAL_CHAR + Config.CHAT_LOCAL_CHAR, "");
            for (Alliance a : AllianceManager.getGuildAlliances(g)) {
                Guild o = (a.getGuild1().equals(g)) ? a.getGuild2() : a.getGuild1();
                Util.sendMessage(o.getOnlineMembers(), format);
            }
            Util.sendMessage(g.getOnlineMembers(), format);
            e.setCancelled(true);
        } else if (msg.startsWith(Config.CHAT_LOCAL_CHAR)) {
            if (g == null) {
                Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);
                e.setCancelled(true);
                return;
            }
            String format = Config.CHAT_LOCAL_FORMAT_GUILD;
            format = Messages.parse(format, p);
            format = format.replace("{MESSAGE}", ChatColor.stripColor(Util.fixColor(msg)));
            format = format.replaceFirst(Config.CHAT_LOCAL_CHAR, "");
            Util.sendMessage(g.getOnlineMembers(), format);
            e.setCancelled(true);
        }
    }

}
