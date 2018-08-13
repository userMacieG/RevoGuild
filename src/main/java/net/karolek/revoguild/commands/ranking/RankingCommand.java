package net.karolek.revoguild.commands.ranking;

import net.karolek.revoguild.base.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.managers.UserManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class RankingCommand extends SubCommand {

    public RankingCommand() {
        super("ranking", "sprawdzanie rankingu gracza", "/ranking [gracz]", "revoguild.ranking", "elo", "gracz", "points");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        User u = null;
        if (args.length == 0) {
            u = UserManager.getUser(p);
        } else if (args.length == 1) {
            u = UserManager.getUserByName(args[0]);
        }
        if (u == null) {
            return Util.sendMessage(p, Lang.ERROR_CANT_FIND_PLAYER);
        }
        Util.sendMessage(p, u.toString());
        return Util.sendMessage(p, Lang.parse(Lang.INFO_PLAYER, u));
    }
}
