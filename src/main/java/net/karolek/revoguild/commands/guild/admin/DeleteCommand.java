package net.karolek.revoguild.commands.guild.admin;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DeleteCommand extends SubCommand {

    public DeleteCommand() {
        super(Commands.GUILD_ADMIN_DELETE_NAME, Commands.GUILD_ADMIN_DELETE_DESCRIPTION, Commands.GUILD_ADMIN_DELETE_USAGE, Commands.GUILD_ADMIN_DELETE_PERMISSION, Commands.GUILD_ADMIN_DELETE_ALIASES);
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
        GuildManager.removeGuild(g);
        return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_ADMIN_DELETED, g, p));
    }
}
