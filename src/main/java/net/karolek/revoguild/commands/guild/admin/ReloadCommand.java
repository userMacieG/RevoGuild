package net.karolek.revoguild.commands.guild.admin;

import net.karolek.revoguild.GuildPlugin;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.data.TabScheme;
import net.karolek.revoguild.tablist.update.TabThread;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class ReloadCommand extends SubCommand {

    public ReloadCommand() {
        super(Commands.GUILD_ADMIN_RELOAD_NAME, Commands.GUILD_ADMIN_RELOAD_DESCRIPTION, Commands.GUILD_ADMIN_RELOAD_USAGE, Commands.GUILD_ADMIN_RELOAD_PERMISSION, Commands.GUILD_ADMIN_RELOAD_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        GuildPlugin.getRevoConfig().reloadConfiguration();
        GuildPlugin.getRevoMessages().reloadConfiguration();
        GuildPlugin.getRevoCommands().reloadConfiguration();
        TabScheme.reloadTablist();
        TabThread.restart();
        return Util.sendMessage(p, Messages.INFO_RELOADED);
    }
}
