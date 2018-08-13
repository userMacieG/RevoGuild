package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.base.Guild;
import net.karolek.revoguild.base.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.managers.GuildManager;
import net.karolek.revoguild.managers.NameTagManager;
import net.karolek.revoguild.managers.UserManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LeaveCommand extends SubCommand {

    public LeaveCommand() {
        super("opusc", "opuszczanie gildii", "/g opusc", "revoguild.leave", "leave");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Guild g = GuildManager.getGuild(p);

        if (g == null)
            return Util.sendMessage(p, Lang.ERROR_DONT_HAVE_GUILD);

        User u = UserManager.getUser(p);

        if (g.isOwner(u.getUuid()))
            return Util.sendMessage(p, Lang.ERROR_OWNER_CANT_LEAVE_GUILD);

        if (g.isLeader(u.getUuid()))
            g.setOwner(g.getOwner());

        g.removeMember(u.getUuid());

        NameTagManager.leaveFromGuild(g, p);

        return Util.sendMessage(Bukkit.getOnlinePlayers(), Lang.parse(Lang.BC_GUILD_LEAVED, g, p));
    }
}
