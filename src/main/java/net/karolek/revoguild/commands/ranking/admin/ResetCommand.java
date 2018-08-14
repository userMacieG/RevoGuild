package net.karolek.revoguild.commands.ranking.admin;

import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.tablist.update.TabThread;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class ResetCommand extends SubCommand {

    public ResetCommand() {
        super(Commands.RANKING_ADMIN_RESET_NAME, Commands.RANKING_ADMIN_RESET_DESCRIPTION, Commands.RANKING_ADMIN_RESET_USAGE, Commands.RANKING_ADMIN_RESET_PERMISSION, Commands.RANKING_ADMIN_RESET_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 1) {
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));
        }
        User u = UserManager.getUserByName(args[0]);
        if (u == null) {
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_USER);
        }
        u.setPoints(Config.RANKING_START$POINTS);
        u.setKills(0);
        u.setDeaths(0);
        u.update(false);
        TabThread.restart();
        return Util.sendMessage(p, Messages.INFO_RESETED);
    }
}
