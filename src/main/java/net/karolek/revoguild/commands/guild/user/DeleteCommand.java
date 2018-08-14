package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.GuildPlugin;
import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DeleteCommand extends SubCommand {

    public DeleteCommand() {
        super(Commands.GUILD_USER_DELETE_NAME, Commands.GUILD_USER_DELETE_DESCRIPTION, Commands.GUILD_USER_DELETE_USAGE, Commands.GUILD_USER_DELETE_PERMISSION, Commands.GUILD_USER_DELETE_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        final Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);
        }
        if (!g.isOwner(UserManager.getUser(p).getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_YOU$ARENT$OWNER);
        }
        if (!g.isPreDeleted()) {
            g.setPreDeleted(true);
            Util.sendMessage(p, Messages.INFO_CONFIRM$DELETE);
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (g.isPreDeleted()) {
                        g.setPreDeleted(false);
                    }
                }
            }.runTaskLater(GuildPlugin.getPlugin(), 20 * 10);
            return true;
        }
        GuildManager.removeGuild(g);
        return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_DELETED, g));
    }

}
