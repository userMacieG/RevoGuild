package net.karolek.revoguild.commands.ranking.admin;

import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.tablist.update.TabThread;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class SetCommand extends SubCommand {

    public SetCommand() {
        super(Commands.RANKING_ADMIN_SET_NAME, Commands.RANKING_ADMIN_SET_DESCRIPTION, Commands.RANKING_ADMIN_SET_USAGE, Commands.RANKING_ADMIN_SET_PERMISSION, Commands.RANKING_ADMIN_SET_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 3) {
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));
        }
        User u = UserManager.getUserByName(args[0]);
        if (u == null) {
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_USER);
        }
        if (!Util.isInteger(args[2])) {
            return Util.sendMessage(p, Messages.ERROR_BAD$INTEGER);
        }
        int value = Integer.parseInt(args[2]);
        if (value < 0) {
            return Util.sendMessage(p, Messages.ERROR_BAD$INTEGER);
        }
        switch (args[1]) {
            case "points":
            case "punkty":
            case "ranking": {
                u.setPoints(value);
                break;
            }
            case "kills":
            case "zabicia": {
                u.setKills(value);
                break;
            }
            case "deaths":
            case "zgony": {
                u.setDeaths(value);
                break;
            }
            default: {
                return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));
            }
        }
        u.update(false);
        TabThread.restart();
        return Util.sendMessage(p, Messages.INFO_SETTED);
    }
}
