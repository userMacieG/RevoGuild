package net.karolek.revoguild.listeners;

import net.karolek.revoguild.base.Guild;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.managers.GuildManager;
import net.karolek.revoguild.managers.UserManager;
import net.karolek.revoguild.utils.TimeUtil;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class MoveListener implements Listener {

    private static final HashMap<Guild, Long> times = new HashMap<>();

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent e) {
        if (!Config.MOVEMENT_NOTIFY_ENABLED) {
            return;
        }
        int xfrom = e.getFrom().getBlockX();
        int zfrom = e.getFrom().getBlockZ();
        int yfrom = e.getFrom().getBlockY();
        int xto = e.getTo().getBlockX();
        int yto = e.getTo().getBlockY();
        int zto = e.getTo().getBlockZ();
        if ((xfrom != xto) || (zfrom != zto) || (yfrom != yto)) {
            Player p = e.getPlayer();
            Guild from = GuildManager.getGuild(e.getFrom());
            Guild to = GuildManager.getGuild(e.getTo());
            if ((from == null) && (to != null)) {
                Util.sendMessage(p, Lang.parse(Lang.INFO_MOVE_IN, to));
                if (Config.MOVEMENT_NOTIFY_INTRUDER_ENABLED) {
                    if (to.isMember(UserManager.getUser(e.getPlayer()).getUuid())) {
                        return;
                    }
                    Long time = times.get(to);
                    if (time == null || System.currentTimeMillis() - time >= TimeUtil.SECOND.getTime(30)) {
                        Util.sendMessage(to.getOnlineMembers(), Lang.INFO_MOVE_INTRUDER);
                        times.put(to, System.currentTimeMillis());
                    }
                }
            } else if ((from != null) && (to == null)) {
                Util.sendMessage(p, Lang.parse(Lang.INFO_MOVE_OUT, from));
            }

        }

    }

}
