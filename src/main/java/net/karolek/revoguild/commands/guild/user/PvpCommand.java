package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.NameTagManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class PvpCommand extends SubCommand {

    public PvpCommand() {
        super(Commands.GUILD_USER_PVP_NAME, Commands.GUILD_USER_PVP_DESCRIPTION, Commands.GUILD_USER_PVP_USAGE, Commands.GUILD_USER_PVP_PERMISSION, Commands.GUILD_USER_PVP_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Guild g = GuildManager.getGuild(p);

        if (g == null)
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);

        if (!g.isOwner(UserManager.getUser(p).getUuid()))
            return Util.sendMessage(p, Messages.ERROR_YOU$ARENT$OWNER);

        g.setPvp(!g.isPvp());
        g.update(false);

        for (Player o : g.getOnlineMembers())
            Util.sendMessage(o, g.isPvp() ? Messages.INFO_PVP_ON : Messages.INFO_PVP_OFF);

        NameTagManager.setPvp(g);

        return true;
    }
}
