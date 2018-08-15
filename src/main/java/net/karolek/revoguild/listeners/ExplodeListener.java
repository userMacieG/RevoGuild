package net.karolek.revoguild.listeners;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.utils.enums.Time;
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

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        Guild g = GuildManager.getGuild(e.getEntity().getLocation());
        if (g == null) {
            return;
        }
        if (Config.TNT_PROTECTION_ENABLED) {
            if (g.getCreateTime() + Time.DAY.getTime(Config.TNT_PROTECTION_TIME) < System.currentTimeMillis()) {
                e.setCancelled(true);
            }
        }
        if (!Config.TNT_CANT$BUILD_ENABLED) {
            return;
        }
        g.setLastExplodeTime(System.currentTimeMillis());
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (!Config.TNT_CANT$BUILD_ENABLED) {
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
        if (System.currentTimeMillis() - g.getLastExplodeTime() >= Time.SECOND.getTime(Config.TNT_CANT$BUILD_TIME)) {
            return;
        }
        e.setCancelled(true);
        Util.sendMessage(p, Messages.ERROR_EXPLODE$TNT);

    }
}
