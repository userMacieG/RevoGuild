package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.NameTagManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.utils.ItemUtil;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class JoinCommand extends SubCommand {

    public JoinCommand() {
        super(Commands.GUILD_USER_JOIN_NAME, Commands.GUILD_USER_JOIN_DESCRIPTION, Commands.GUILD_USER_JOIN_USAGE, Commands.GUILD_USER_JOIN_PERMISSION, Commands.GUILD_USER_JOIN_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 1) {
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));
        }
        if (GuildManager.getGuild(p) != null) {
            return Util.sendMessage(p, Messages.ERROR_HAVE$GUILD);
        }
        Guild g = GuildManager.getGuild(args[0]);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_GUILD);
        }
        String algorithm = Config.ALGORITHM_JOIN;
        algorithm = algorithm.replace("{MEMBERS_NUM}", Integer.toString(g.getMembers().size()));
        int modifier = Util.calculate(algorithm);
        List<ItemStack> items = ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_JOIN_VIP : Config.ITEMS_JOIN_NORMAL, modifier);
        if (!ItemUtil.checkItems(items, p)) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_ITEMS.replace("{ITEMS}", ItemUtil.getItems(items)));
        }
        if (!g.addMember(UserManager.getUser(p).getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_INVITE);
        }
        ItemUtil.removeItems(items, p);
        Util.sendMessage(p, Messages.parse(Messages.INFO_JOINED, g));
        NameTagManager.joinToGuild(g, p);
        return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_JOINED, g, p));
    }

}
