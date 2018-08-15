package net.karolek.revoguild.commands.guild;

import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.commands.guild.user.*;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GuildCommand extends SubCommand {

    private static final Set<SubCommand> subCommands = new HashSet<>();

    public GuildCommand() {
        super(Commands.GUILD_USER_MAIN_NAME, Commands.GUILD_USER_MAIN_DESCRIPTION, Commands.GUILD_USER_MAIN_USAGE, Commands.GUILD_USER_MAIN_PERMISSION, Commands.GUILD_USER_MAIN_ALIASES);
        if (Commands.GUILD_USER_CREATE_ENABLED) {
            subCommands.add(new CreateCommand());
        }
        if (Commands.GUILD_USER_DELETE_ENABLED) {
            subCommands.add(new DeleteCommand());
        }
        if (Commands.GUILD_USER_HOME_ENABLED) {
            subCommands.add(new HomeCommand());
        }
        if (Commands.GUILD_USER_INFO_ENABLED) {
            subCommands.add(new InfoCommand());
        }
        if (Commands.GUILD_USER_INVITE_ENABLED) {
            subCommands.add(new InviteCommand());
        }
        if (Commands.GUILD_USER_JOIN_ENABLED) {
            subCommands.add(new JoinCommand());
        }
        if (Commands.GUILD_USER_KICK_ENABLED) {
            subCommands.add(new KickCommand());
        }
        if (Commands.GUILD_USER_LEADER_ENABLED) {
            subCommands.add(new LeaderCommand());
        }
        if (Commands.GUILD_USER_OWNER_ENABLED) {
            subCommands.add(new OwnerCommand());
        }
        if (Commands.GUILD_USER_LEAVE_ENABLED) {
            subCommands.add(new LeaveCommand());
        }
        if (Commands.GUILD_USER_TOP_ENABLED) {
            subCommands.add(new TopCommand());
        }
        if (Commands.GUILD_USER_PVP_ENABLED) {
            subCommands.add(new PvpCommand());
        }
        if (Commands.GUILD_USER_ENLARGE_ENABLED) {
            subCommands.add(new EnlargeCommand());
        }
        if (Commands.GUILD_USER_SET$HOME_ENABLED) {
            subCommands.add(new SetHomeCommand());
        }
        if (Commands.GUILD_USER_PROLONG_ENABLED) {
            subCommands.add(new ProlongCommand());
        }
        if (Commands.GUILD_USER_ALLIANCE_ENABLED) {
            subCommands.add(new AllianceCommand());
        }
        if (Config.EFFECTS_ENABLED && Commands.GUILD_USER_EFFECT_ENABLED) {
            subCommands.add(new EffectCommand());
        }
        if (Config.TREASURE_ENABLED && Commands.GUILD_USER_TREASURE_ENABLED) {
            subCommands.add(new TreasureCommand());
        }
        if (Commands.GUILD_USER_ITEMS_ENABLED) {
            subCommands.add(new ItemsCommand());
        }
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length == 0) {
            return Util.sendMessage(p, Messages.GUILD_USER$HELP);
        }
        String name = args[0];
        SubCommand sc = getSub(name);
        if (sc == null) {
            return Util.sendMessage(p, Messages.GUILD_USER$HELP);
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
