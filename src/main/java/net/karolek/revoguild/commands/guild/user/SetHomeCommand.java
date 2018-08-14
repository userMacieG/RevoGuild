package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class SetHomeCommand extends SubCommand {

    public SetHomeCommand() {
        super(Commands.GUILD_USER_SETHOME_NAME, Commands.GUILD_USER_SETHOME_DESCRIPTION, Commands.GUILD_USER_SETHOME_USAGE, Commands.GUILD_USER_SETHOME_PERMISSION, Commands.GUILD_USER_SETHOME_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Guild g = GuildManager.getGuild(p);

        if (g == null)
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);

        if (!g.isOwner(UserManager.getUser(p).getUuid()))
            return Util.sendMessage(p, Messages.ERROR_YOU$ARENT$OWNER);

        Guild o = GuildManager.getGuild(p.getLocation());

        if (!g.equals(o))
            return Util.sendMessage(p, Messages.ERROR_CANT_SET$HOME$OUTSIDE$CUBOID);

        g.setHome(p.getLocation());
        g.update(true);

        return Util.sendMessage(p, Messages.INFO_SET_HOME);
    }
}
