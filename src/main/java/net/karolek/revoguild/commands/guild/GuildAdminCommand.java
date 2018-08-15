package net.karolek.revoguild.commands.guild;

import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.commands.guild.admin.*;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GuildAdminCommand extends SubCommand {

    private static final Set<SubCommand> subCommands = new HashSet<>();

    public GuildAdminCommand() {
        super(Commands.GUILD_ADMIN_MAIN_NAME, Commands.GUILD_ADMIN_MAIN_DESCRIPTION, Commands.GUILD_ADMIN_MAIN_USAGE, Commands.GUILD_ADMIN_MAIN_PERMISSION, Commands.GUILD_ADMIN_MAIN_ALIASES);
        if (Commands.GUILD_ADMIN_TELEPORT_ENABLED) {
            subCommands.add(new TeleportCommand());
        }
        if (Commands.GUILD_ADMIN_RELOAD_ENABLED) {
            subCommands.add(new ReloadCommand());
        }
        if (Commands.GUILD_ADMIN_DELETE_ENABLED) {
            subCommands.add(new DeleteCommand());
        }
        if (Commands.GUILD_ADMIN_SET$CUBOID_ENABLED) {
            subCommands.add(new SetCuboidCommand());
        }
        if (Commands.GUILD_ADMIN_KICK_ENABLED) {
            subCommands.add(new KickCommand());
        }
        if (Commands.GUILD_ADMIN_BAN_ENABLED) {
            subCommands.add(new BanCommand());
        }
        if (Commands.GUILD_ADMIN_UNBAN_ENABLED) {
            subCommands.add(new UnBanCommand());
        }
        if (Commands.GUILD_ADMIN_SET_ENABLED) {
            subCommands.add(new SetCommand());
        }
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length == 0) {
            return Util.sendMessage(p, Messages.GUILD_ADMIN$HELP);
        }
        String name = args[0];
        SubCommand sc = getSub(name);
        if (sc == null) {
            return Util.sendMessage(p, Messages.GUILD_ADMIN$HELP);
        }
        if (!p.hasPermission(sc.getPermission())) {
            return Util.sendMessage(p, "&cYou don't have permissions to run that command! &7(" + sc.getPermission() + ")");
        }
        return sc.onCommand(p, Arrays.copyOfRange(args, 1, args.length));
    }

    private SubCommand getSub(String sub) {
        for (SubCommand sc : subCommands) {
            if (sc.getName().equalsIgnoreCase(sub) || sc.getAliases().contains(sub)) {
                return sc;
            }
        }
        return null;
    }

}
