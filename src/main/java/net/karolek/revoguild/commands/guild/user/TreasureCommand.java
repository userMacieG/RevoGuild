package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class TreasureCommand extends SubCommand {

    public TreasureCommand() {
        super(Commands.GUILD_USER_TREASURE_NAME, Commands.GUILD_USER_TREASURE_DESCRIPTION, Commands.GUILD_USER_TREASURE_USAGE, Commands.GUILD_USER_TREASURE_PERMISSION, Commands.GUILD_USER_TREASURE_ALIASES);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(Player p, String[] args) {

        Guild g = GuildManager.getGuild(p);

        if (g == null)
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);

        User pU = UserManager.getUser(p);

        if (args.length == 0) {
            if (g.isTreasureUser(pU.getUuid())) {
                if (Config.TREASURE_OPEN$ONLY$ON$GUILD) {
                    Guild o = GuildManager.getGuild(p.getLocation());
                    if (!g.equals(o))
                        return Util.sendMessage(p, Messages.ERROR_CANT_OPEN$TREASURE$OUTSIDE$CUBOID);
                }
                g.openTreasure(p);
                return Util.sendMessage(p, Messages.INFO_TREASURE_OPENED);
            }
            return Util.sendMessage(p, Messages.ERROR_CANT_OPEN$TREASURE);
        } else {
            if (!g.isOwner(pU.getUuid()))
                return Util.sendMessage(p, Messages.ERROR_YOU$ARENT$OWNER);
            OfflinePlayer op;
            User u;
            switch (args[0]) {
                case "dodaj":
                    if (args.length != 2)
                        return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));

                    op = Bukkit.getOfflinePlayer(args[1]);
                    u = UserManager.getUser(op);

                    if (!g.isMember(u.getUuid()))
                        return Util.sendMessage(p, Messages.ERROR_PLAYER$ISNT_MEMBER);

                    if (g.isTreasureUser(u.getUuid()))
                        return Util.sendMessage(p, Messages.ERROR_PLAYER$IS_TREASURE$USER);

                    g.addTreasureUser(u.getUuid());
                    if (op.isOnline())
                        Util.sendMessage(op.getPlayer(), Messages.parse(Messages.INFO_TREASURE_USER_ADD$INFO, p));
                    return Util.sendMessage(p, Messages.parse(Messages.INFO_TREASURE_USER_ADD, op));
                case "usun":
                    if (args.length != 2)
                        return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));

                    op = Bukkit.getOfflinePlayer(args[1]);
                    u = UserManager.getUser(op);

                    if (!g.isMember(u.getUuid()))
                        return Util.sendMessage(p, Messages.ERROR_PLAYER$ISNT_MEMBER);

                    if (!g.isTreasureUser(u.getUuid()))
                        return Util.sendMessage(p, Messages.ERROR_PLAYER$ISNT_TREASURE$USER);

                    g.removeTreasureUser(u.getUuid());
                    if (op.isOnline())
                        Util.sendMessage(op.getPlayer(), Messages.parse(Messages.INFO_TREASURE_USER_REMOVE$INFO, p));
                    return Util.sendMessage(p, Messages.parse(Messages.INFO_TREASURE_USER_REMOVE, op));
                case "lista":
                    return Util.sendMessage(p, Messages.INFO_TREASURE_USERS.replace("{USERS}", Messages.getTreasureUsers(g)));
                default:
                    return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));
            }
        }

    }
}
