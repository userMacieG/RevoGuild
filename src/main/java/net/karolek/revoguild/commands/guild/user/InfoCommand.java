package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class InfoCommand extends SubCommand {

    public InfoCommand() {
        super(Commands.GUILD_USER_INFO_NAME, Commands.GUILD_USER_INFO_DESCRIPTION, Commands.GUILD_USER_INFO_USAGE, Commands.GUILD_USER_INFO_PERMISSION, Commands.GUILD_USER_INFO_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 1) {
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));
        }
        Guild g = GuildManager.getGuild(args[0]);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_GUILD);
        }
        return Util.sendMessage(p, Messages.parse(Messages.INFO_GUILD, g));
    }
}
