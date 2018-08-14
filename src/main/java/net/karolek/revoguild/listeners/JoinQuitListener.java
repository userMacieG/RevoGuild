package net.karolek.revoguild.listeners;

import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.*;
import net.karolek.revoguild.packetlistener.PacketManager;
import net.karolek.revoguild.tablist.update.TabThread;
import net.karolek.revoguild.utils.UptakeUtil;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        PacketManager.registerPlayer(p);
        NameTagManager.initPlayer(p);
        UserManager.joinToGame(p);
        UserManager.initUser(p);
        if (Config.TABLIST_ENABLED) {
            TabManager.createTab(p);
        }
        if (Config.ESCAPE_ENABLED) {
            CombatManager.createPlayer(p);
        }
        UptakeUtil.init(p);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        escape(e.getPlayer());
        UserManager.leaveFromGame(e.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        escape(e.getPlayer());
        UserManager.leaveFromGame(e.getPlayer());
    }

    private void escape(Player p) {
        if (!Config.ESCAPE_ENABLED) {
            return;
        }
        if (CombatManager.isInFight(p)) {
            return;
        }
        User u = UserManager.getUser(p);
        Guild pGuild = GuildManager.getGuild(p);
        String pGuildTag = pGuild != null ? Messages.parse(Config.RANKING_DEATH_TAG, pGuild) : "";
        String mes = Config.ESCAPE_BROADCAST;
        mes = mes.replace("{PGUILD}", pGuildTag);
        mes = mes.replace("{PLAYER}", p.getName());
        u.addDeaths(1);
        u.removePoints(Config.ESCAPE_LOSE$POINTS);
        p.setHealth(0D);
        TabThread.restart();
        Util.sendMessage(Bukkit.getOnlinePlayers(), mes);
    }

}
