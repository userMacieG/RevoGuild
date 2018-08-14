package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.utils.ItemUtil;
import net.karolek.revoguild.utils.enums.Time;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ProlongCommand extends SubCommand {

    public ProlongCommand() {
        super(Commands.GUILD_USER_PROLONG_NAME, Commands.GUILD_USER_PROLONG_DESCRIPTION, Commands.GUILD_USER_PROLONG_USAGE, Commands.GUILD_USER_PROLONG_PERMISSION, Commands.GUILD_USER_PROLONG_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Guild g = GuildManager.getGuild(p);

        if (g == null)
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);

        long t = g.getExpireTime();

        if ((t - System.currentTimeMillis()) >= Time.DAY.getTime(Config.TIME_MAX))
            return Util.sendMessage(p, Messages.ERROR_CANT_PROLONG);

        List<ItemStack> items = ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_PROLONG_VIP : Config.ITEMS_PROLONG_NORMAL, 1);

        if (!ItemUtil.checkAndRemove(items, p))
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_ITEMS.replace("{ITEMS}", ItemUtil.getItems(items)));

        g.addExpireTime(Time.DAY.getTime(Config.TIME_ADD));

        return Util.sendMessage(p, Messages.INFO_PROLONGED$VALIDITY);
    }
}
