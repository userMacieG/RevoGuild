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
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class KickCommand extends SubCommand {

    public KickCommand() {
        super(Commands.GUILD_USER_KICK_NAME, Commands.GUILD_USER_KICK_DESCRIPTION, Commands.GUILD_USER_KICK_USAGE, Commands.GUILD_USER_KICK_PERMISSION, Commands.GUILD_USER_KICK_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 1) {
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));
        }
        Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);
        }
        if (!g.isLeader(UserManager.getUser(p).getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_YOU$ARENT$LEADER);
        }
        OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
        User u = UserManager.getUser(op);
        if (g.isLeader(u.getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_CANT_KICK$LEADER$OR$OWNER);
        }
        if (!g.removeMember(u.getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_PLAYER$ISNT_MEMBER);
        }
        NameTagManager.leaveFromGuild(g, op);
        return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_KICKED, g, op));
    }
}
