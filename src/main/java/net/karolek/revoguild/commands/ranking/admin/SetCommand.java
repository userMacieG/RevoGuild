package net.karolek.revoguild.commands.ranking.admin;

import net.karolek.revoguild.base.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.managers.UserManager;
import net.karolek.revoguild.tablist.update.TabThread;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class SetCommand extends SubCommand {

    public SetCommand() {
        super("set", "ustawianie wartosci wybranego gracza", "/ra set <gracz> <kills|deaths|points> <wartosc>", "revoguild.admin.set", "ustaw");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 3) {
            return Util.sendMessage(p, Lang.parse(Lang.CMD_CORRECT_USAGE, this));
        }
        User u = UserManager.getUserByName(args[0]);
        if (u == null) {
            return Util.sendMessage(p, Lang.ERROR_CANT_FIND_USER);
        }
        if (!Util.isInteger(args[2])) {
            return Util.sendMessage(p, Lang.ERROR_BAD_INTEGER);
        }
        int value = Integer.parseInt(args[2]);
        if (value < 0) {
            return Util.sendMessage(p, Lang.ERROR_BAD_INTEGER);
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
                return Util.sendMessage(p, Lang.parse(Lang.CMD_CORRECT_USAGE, this));
            }
        }
        u.update(false);
        TabThread.restart();
        return Util.sendMessage(p, Lang.INFO_SETTED);
    }
}
