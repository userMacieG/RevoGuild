package net.karolek.revoguild.tasks;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
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
                Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_EXPIRED, g).replace("{X}", Integer.toString(g.getCuboid().getCenterX())).replace("{Z}", Integer.toString(g.getCuboid().getCenterZ())));
            }
        }
    }

}
