package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.base.Guild;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.managers.GuildManager;
import net.karolek.revoguild.utils.ItemUtil;
import net.karolek.revoguild.utils.TimeUtil;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ProlongCommand extends SubCommand {

    public ProlongCommand() {
        super("przedluz", "przedluzanie waznosci gildii", "/g przedluz", "revoguild.prolong", "prolong", "addtime");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Guild g = GuildManager.getGuild(p);

        if (g == null)
            return Util.sendMessage(p, Lang.ERROR_DONT_HAVE_GUILD);

        long t = g.getExpireTime();

        if ((t - System.currentTimeMillis()) >= TimeUtil.DAY.getTime(Config.TIME_MAX))
            return Util.sendMessage(p, Lang.ERROR_CANT_ADD_TIME);

        List<ItemStack> items = ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.COST_PROLONG_VIP : Config.COST_PROLONG_NORMAL, 1);

        if (!ItemUtil.checkAndRemove(items, p))
            return Util.sendMessage(p, Lang.ERROR_DONT_HAVE_ITEMS.replace("{ITEMS}", ItemUtil.getItems(items)));

        g.addExpireTime(TimeUtil.DAY.getTime(Config.TIME_ADD));

        return Util.sendMessage(p, Lang.INFO_PROLONGED_VALIDITY);
    }
}
