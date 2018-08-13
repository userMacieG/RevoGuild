package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.base.Guild;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.managers.GuildManager;
import net.karolek.revoguild.managers.NameTagManager;
import net.karolek.revoguild.managers.UserManager;
import net.karolek.revoguild.utils.ItemUtil;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class JoinCommand extends SubCommand {

    public JoinCommand() {
        super("dolacz", "dolaczanie do gildii", "/g dolacz <tag/nazwa>", "revoguild.join", "join");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {

        if (args.length != 1)
            return Util.sendMessage(p, Lang.parse(Lang.CMD_CORRECT_USAGE, this));

        if (GuildManager.getGuild(p) != null)
            return Util.sendMessage(p, Lang.ERROR_HAVE_GUILD);

        Guild g = GuildManager.getGuild(args[0]);

        if (g == null)
            return Util.sendMessage(p, Lang.ERROR_CANT_FIND_GUILD);

        String algorithm = Config.ALGORITHM_JOIN;
        algorithm = algorithm.replace("{MEMBERS_NUM}", Integer.toString(g.getMembers().size()));

        int modifier = Util.calculate(algorithm);

        List<ItemStack> items = ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.COST_JOIN_VIP : Config.COST_JOIN_NORMAL, modifier);


        if (!ItemUtil.checkItems(items, p))
            return Util.sendMessage(p, Lang.ERROR_DONT_HAVE_ITEMS.replace("{ITEMS}", ItemUtil.getItems(items)));

        if (!g.addMember(UserManager.getUser(p).getUuid()))
            return Util.sendMessage(p, Lang.ERROR_DONT_HAVE_INVITE);

        ItemUtil.removeItems(items, p);

        Util.sendMessage(p, Lang.parse(Lang.INFO_JOINED, g));

        NameTagManager.joinToGuild(g, p);


        return Util.sendMessage(Bukkit.getOnlinePlayers(), Lang.parse(Lang.BC_GUILD_JOINED, g, p));


    }

}
