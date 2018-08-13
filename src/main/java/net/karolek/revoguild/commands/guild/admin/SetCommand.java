package net.karolek.revoguild.commands.guild.admin;

import net.karolek.revoguild.base.Guild;
import net.karolek.revoguild.base.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.managers.GuildManager;
import net.karolek.revoguild.managers.UserManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SetCommand extends SubCommand {

    public SetCommand() {
        super("set", "zmiana wartosci wybranej gildii", "/ga set <tag/nazwa> <leader|owner|lives|pvp|size> <wartosc>", "revoguild.admin.set", "ustawrozmiar");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 3) {
            return Util.sendMessage(p, Lang.parse(Lang.CMD_CORRECT_USAGE, this));
        }
        Guild g = GuildManager.getGuild(args[0]);
        User user;
        switch (args[1]) {
            case "leader":
                user = UserManager.getUser(Bukkit.getPlayer(args[2]));
                if (user == null)
                    return Util.sendMessage(p, Lang.ERROR_CANT_FIND_USER);
                assert g != null;
                if (!g.isMember(user.getUuid()))
                    return Util.sendMessage(p, Lang.ERROR_PLAYER_ISNT_MEMBER);
                g.setLeader(user.getUuid());
                break;
            case "owner":
                user = UserManager.getUser(Bukkit.getPlayer(args[2]));
                if (user == null)
                    return Util.sendMessage(p, Lang.ERROR_CANT_FIND_USER);
                assert g != null;
                if (!g.isMember(user.getUuid()))
                    return Util.sendMessage(p, Lang.ERROR_PLAYER_ISNT_MEMBER);
                g.setOwner(user.getUuid());
                break;
            case "lives":
                if (!Util.isInteger(args[2]))
                    return Util.sendMessage(p, Lang.ERROR_BAD_INTEGER);
                int lives = Integer.parseInt(args[2]);
                if (lives < 1 || lives > Config.UPTAKE_LIVES_MAX)
                    return Util.sendMessage(p, Lang.ERROR_BAD_INTEGER);
                assert g != null;
                g.setLives(lives);
                break;
            case "size":
                if (!Util.isInteger(args[1]))
                    return Util.sendMessage(p, Lang.ERROR_BAD_INTEGER);

                int v = Integer.parseInt(args[1]);

                if (v < Config.CUBOID_SIZE_START || v > Config.CUBOID_SIZE_MAX)
                    return Util.sendMessage(p, Lang.ERROR_BAD_INTEGER);

                assert g != null;
                g.getCuboid().setSize(v);
                g.update(false);
                break;
            case "pvp":
                boolean pvp = Util.getBoolean(args[2]);
                assert g != null;
                g.setPvp(pvp);
                g.update(false);
                break;
            default:
                return Util.sendMessage(p, Lang.parse(Lang.CMD_CORRECT_USAGE, this));
        }

        return Util.sendMessage(p, Lang.parse(Lang.INFO_CHANGED_GUILD, g).replace("{FIELD}", args[1].toLowerCase()));
    }
}
