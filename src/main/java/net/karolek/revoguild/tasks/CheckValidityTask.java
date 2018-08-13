package net.karolek.revoguild.tasks;

import net.karolek.revoguild.base.Guild;
import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.managers.GuildManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class CheckValidityTask extends BukkitRunnable {

    @Override
    public void run() {
        Collection<Guild> c = GuildManager.getGuilds().values();
        for (Guild g : c) {
            if (g.getExpireTime() < System.currentTimeMillis()) {
                GuildManager.removeGuild(g);
                Util.sendMessage(Bukkit.getOnlinePlayers(), Lang.parse(Lang.BC_GUILD_EXPIRED, g).replace("{X}", Integer.toString(g.getCuboid().getCenterX())).replace("{Z}", Integer.toString(g.getCuboid().getCenterZ())));
            }
        }
    }

}
