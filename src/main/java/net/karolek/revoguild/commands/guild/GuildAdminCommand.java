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
        subCommands.add(new TeleportCommand());
        subCommands.add(new ReloadCommand());
        subCommands.add(new DeleteCommand());
        subCommands.add(new SetCuboidCommand());
        subCommands.add(new KickCommand());
        subCommands.add(new BanCommand());
        subCommands.add(new UnBanCommand());
        subCommands.add(new SetCommand());
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
