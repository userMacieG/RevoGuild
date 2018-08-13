package net.karolek.revoguild.commands.guild.admin;

import net.karolek.revoguild.base.Guild;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.managers.GuildManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UnBanCommand extends SubCommand {

    public UnBanCommand() {
        super("unban", "odbanowanie wybranej gildii", "/ga unban <tag/nazwa>", "revoguild.admin.unban", "odbanuj");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 1)
            return Util.sendMessage(p, Lang.parse(Lang.CMD_CORRECT_USAGE, this));

        Guild g = GuildManager.getGuild(args[0]);

        if (g == null)
            return Util.sendMessage(p, Lang.ERROR_CANT_FIND_GUILD);

        if (!g.unban())
            return Util.sendMessage(p, Lang.ERROR_GUILD_DONT_HAVE_BAN);

        return Util.sendMessage(Bukkit.getOnlinePlayers(), Lang.parse(Lang.ADMIN_BC_GUILD_UNBANNED, g, p));
    }
}
