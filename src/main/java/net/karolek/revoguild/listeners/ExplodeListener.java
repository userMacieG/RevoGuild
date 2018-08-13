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
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ExplodeListener implements Listener {

    private static final Calendar calendar = new GregorianCalendar();

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onExplode(EntityExplodeEvent e) {

        if (Config.TNT_OFF_ENABLED) {
            if (Config.TNT_OFF_HOURS.contains(calendar.getTime().getHours()))
                e.setCancelled(true);
        }

        Guild g = GuildManager.getGuild(e.getEntity().getLocation());

        if (g == null)
            return;

        if (Config.TNT_PROTECTION_ENABLED)
            if (g.getCreateTime() + TimeUtil.DAY.getTime(Config.TNT_PROTECTION_TIME) < System.currentTimeMillis())
                e.setCancelled(true);

        if (!Config.TNT_CANTBUILD_ENABLED)
            return;
        g.setLastExplodeTime(System.currentTimeMillis());

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (!Config.TNT_CANTBUILD_ENABLED) {
            return;
        }
        Player p = e.getPlayer();
        Guild g = GuildManager.getGuild(e.getBlockPlaced().getLocation());
        if (g == null) {
            return;
        }
        if (!g.isMember(UserManager.getUser(p).getUuid())) {
            return;
        }
        if (System.currentTimeMillis() - g.getLastExplodeTime() >= TimeUtil.SECOND.getTime(Config.TNT_CANTBUILD_TIME)) {
            return;
        }
        e.setCancelled(true);
        Util.sendMessage(p, Lang.ERROR_EXPLODE_TNT);

    }
}
