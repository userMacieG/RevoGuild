package net.karolek.revoguild.commands.guild.admin;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class KickCommand extends SubCommand {

    public KickCommand() {
        super(Commands.GUILD_ADMIN_KICK_NAME, Commands.GUILD_ADMIN_KICK_DESCRIPTION, Commands.GUILD_ADMIN_KICK_USAGE, Commands.GUILD_ADMIN_KICK_PERMISSION, Commands.GUILD_ADMIN_KICK_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 2)
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));

        Guild g = GuildManager.getGuild(args[0]);

        if (g == null)
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_GUILD);

        User u = UserManager.getUserByName(args[1]);

        if (u == null)
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_USER);

        if (!g.isMember(u.getUuid()))
            return Util.sendMessage(p, Messages.ERROR_PLAYER$ISNT_MEMBER);

        if (g.isOwner(u.getUuid()))
            return Util.sendMessage(p, Messages.ERROR_CANT_KICK$LEADER$OR$OWNER);

        if (g.isLeader(u.getUuid()))
            g.setLeader(g.getOwner());

        g.removeMember(u.getUuid());

        return Util.sendMessage(p, Messages.INFO_USER$KICKED);
    }
}
