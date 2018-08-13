package net.karolek.revoguild.commands.guild.admin;

import net.karolek.revoguild.base.Guild;
import net.karolek.revoguild.base.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.managers.GuildManager;
import net.karolek.revoguild.managers.UserManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class KickCommand extends SubCommand {

    public KickCommand() {
        super("wyrzuc", "wyrzuca gracz z wybranej gildii", "/ga wyrzuc <tag/nazwa> <gracz>", "revoguild.admin.kick", "kick");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 2)
            return Util.sendMessage(p, Lang.parse(Lang.CMD_CORRECT_USAGE, this));

        Guild g = GuildManager.getGuild(args[0]);

        if (g == null)
            return Util.sendMessage(p, Lang.ERROR_CANT_FIND_GUILD);

        User u = UserManager.getUserByName(args[1]);

        if (u == null)
            return Util.sendMessage(p, Lang.ERROR_CANT_FIND_USER);

        if (!g.isMember(u.getUuid()))
            return Util.sendMessage(p, Lang.ERROR_PLAYER_ISNT_MEMBER);

        if (g.isOwner(u.getUuid()))
            return Util.sendMessage(p, Lang.ERROR_CANT_KICK_LEADER_OR_OWNER);

        if (g.isLeader(u.getUuid()))
            g.setLeader(g.getOwner());

        g.removeMember(u.getUuid());

        return Util.sendMessage(p, Lang.INFO_USER_KICKED);
    }
}
