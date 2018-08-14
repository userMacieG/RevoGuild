package net.karolek.revoguild.commands.ranking;

import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.commands.ranking.admin.ResetCommand;
import net.karolek.revoguild.commands.ranking.admin.SetCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RankingAdminCommand extends SubCommand {

    private static final Set<SubCommand> subCommands = new HashSet<>();

    public RankingAdminCommand() {
        super(Commands.RANKING_ADMIN_MAIN_NAME, Commands.RANKING_ADMIN_MAIN_DESCRIPTION, Commands.RANKING_ADMIN_MAIN_USAGE, Commands.RANKING_ADMIN_MAIN_PERMISSION, Commands.RANKING_ADMIN_MAIN_ALIASES);
        subCommands.add(new ResetCommand());
        subCommands.add(new SetCommand());
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length == 0) {
            return Util.sendMessage(p, Messages.RANKING$ADMIN$HELP);
        }
        String name = args[0];
        SubCommand sc = getSub(name);
        if (sc == null) {
            return Util.sendMessage(p, Messages.RANKING$ADMIN$HELP);
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
