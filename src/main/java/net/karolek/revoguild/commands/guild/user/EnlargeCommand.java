package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.utils.ItemUtil;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EnlargeCommand extends SubCommand {

    public EnlargeCommand() {
        super(Commands.GUILD_USER_ENLARGE_NAME, Commands.GUILD_USER_ENLARGE_DESCRIPTION, Commands.GUILD_USER_ENLARGE_USAGE, Commands.GUILD_USER_ENLARGE_PERMISSION, Commands.GUILD_USER_ENLARGE_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);
        }
        if (!g.isOwner(UserManager.getUser(p).getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_YOU$ARENT$OWNER);
        }
        String algorithm = Config.ALGORITHM_ENLARGE;
        algorithm = algorithm.replace("{CUBOID_SIZE}", Integer.toString(g.getCuboid().getSize()));
        int modifier = Util.calculate(algorithm);
        List<ItemStack> items = ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_ENLARGE_VIP : Config.ITEMS_ENLARGE_NORMAL, modifier);
        if (!ItemUtil.checkItems(items, p)) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_ITEMS.replace("{ITEMS}", ItemUtil.getItems(items)));
        }
        if (!g.addSize()) {
            return Util.sendMessage(p, Messages.ERROR_MAX$SIZE);
        }
        ItemUtil.removeItems(items, p);
        return Util.sendMessage(p, Messages.INFO_RESIZED);
    }
}
