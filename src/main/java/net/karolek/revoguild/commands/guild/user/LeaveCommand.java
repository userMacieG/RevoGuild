package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.NameTagManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LeaveCommand extends SubCommand {

    public LeaveCommand() {
        super(Commands.GUILD_USER_LEAVE_NAME, Commands.GUILD_USER_LEAVE_DESCRIPTION, Commands.GUILD_USER_LEAVE_USAGE, Commands.GUILD_USER_LEAVE_PERMISSION, Commands.GUILD_USER_LEAVE_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);
        }
        User u = UserManager.getUser(p);
        if (g.isOwner(u.getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_OWNER$CANT$LEAVE$GUILD);
        }
        if (g.isLeader(u.getUuid())) {
            g.setOwner(g.getOwner());
        }
        g.removeMember(u.getUuid());
        NameTagManager.leaveFromGuild(g, p);
        return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_LEAVED, g, p));
    }
}
