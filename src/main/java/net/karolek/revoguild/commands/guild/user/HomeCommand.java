package net.karolek.revoguild.commands.guild.user;


import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.TeleportManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class HomeCommand extends SubCommand {

    public HomeCommand() {
        super(Commands.GUILD_USER_HOME_NAME, Commands.GUILD_USER_HOME_DESCRIPTION, Commands.GUILD_USER_HOME_USAGE, Commands.GUILD_USER_HOME_PERMISSION, Commands.GUILD_USER_HOME_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Guild g = GuildManager.getGuild(p);

        if (g == null)
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);

        TeleportManager.teleport(p, g.getHome(), Config.TIME_TELEPORT);
        return true;
    }
}
