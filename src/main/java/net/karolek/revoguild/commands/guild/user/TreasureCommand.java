package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.base.Guild;
import net.karolek.revoguild.base.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.managers.GuildManager;
import net.karolek.revoguild.managers.UserManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class TreasureCommand extends SubCommand {

    public TreasureCommand() {
        super("skarbiec", "skarbiec gildii", "/g skarbiec [dodaj <gracz> / usun <gracz> / lista]", "revoguild.treasure", "skrzynia", "treasure");
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(Player p, String[] args) {

        Guild g = GuildManager.getGuild(p);

        if (g == null)
            return Util.sendMessage(p, Lang.ERROR_DONT_HAVE_GUILD);

        User pU = UserManager.getUser(p);

        if (args.length == 0) {
            if (g.isTreasureUser(pU.getUuid())) {
                if (Config.TREASURE_OPENONLYONGUILD) {
                    Guild o = GuildManager.getGuild(p.getLocation());
                    if (!g.equals(o))
                        return Util.sendMessage(p, Lang.ERROR_CANT_OPEN_TREASURE_OUTSIDE_CUBOID);
                }
                g.openTreasure(p);
                return Util.sendMessage(p, Lang.INFO_TREASURE_OPENED);
            }
            return Util.sendMessage(p, Lang.ERROR_CANT_OPEN_TREASURE);
        } else {
            if (!g.isOwner(pU.getUuid()))
                return Util.sendMessage(p, Lang.ERROR_NOT_OWNER);
            OfflinePlayer op;
            User u;
            switch (args[0]) {
                case "dodaj":
                    if (args.length != 2)
                        return Util.sendMessage(p, Lang.parse(Lang.CMD_CORRECT_USAGE, this));

                    op = Bukkit.getOfflinePlayer(args[1]);
                    u = UserManager.getUser(op);

                    if (!g.isMember(u.getUuid()))
                        return Util.sendMessage(p, Lang.ERROR_PLAYER_ISNT_MEMBER);

                    if (g.isTreasureUser(u.getUuid()))
                        return Util.sendMessage(p, Lang.ERROR_PLAYER_IS_TREASURE_USER);

                    g.addTreasureUser(u.getUuid());
                    if (op.isOnline())
                        Util.sendMessage(op.getPlayer(), Lang.parse(Lang.INFO_TREASURE_USER_ADD_INFO, p));
                    return Util.sendMessage(p, Lang.parse(Lang.INFO_TREASURE_USER_ADD, op));
                case "usun":
                    if (args.length != 2)
                        return Util.sendMessage(p, Lang.parse(Lang.CMD_CORRECT_USAGE, this));

                    op = Bukkit.getOfflinePlayer(args[1]);
                    u = UserManager.getUser(op);

                    if (!g.isMember(u.getUuid()))
                        return Util.sendMessage(p, Lang.ERROR_PLAYER_ISNT_MEMBER);

                    if (!g.isTreasureUser(u.getUuid()))
                        return Util.sendMessage(p, Lang.ERROR_PLAYER_ISNT_TREASURE_USER);

                    g.removeTreasureUser(u.getUuid());
                    if (op.isOnline())
                        Util.sendMessage(op.getPlayer(), Lang.parse(Lang.INFO_TREASURE_USER_REMOVE_INFO, p));
                    return Util.sendMessage(p, Lang.parse(Lang.INFO_TREASURE_USER_REMOVE, op));
                case "lista":
                    return Util.sendMessage(p, Lang.INFO_TREASURE_USERS.replace("{USERS}", Lang.getTreasureUsers(g)));
                default:
                    return Util.sendMessage(p, Lang.parse(Lang.CMD_CORRECT_USAGE, this));
            }
        }

    }
}
