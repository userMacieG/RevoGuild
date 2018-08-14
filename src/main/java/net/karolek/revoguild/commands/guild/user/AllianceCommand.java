package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.AllianceManager;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.utils.ItemUtil;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class AllianceCommand extends SubCommand {

    public AllianceCommand() {
        super(Commands.GUILD_USER_ALLIANCE_NAME, Commands.GUILD_USER_ALLIANCE_DESCRIPTION, Commands.GUILD_USER_ALLIANCE_USAGE, Commands.GUILD_USER_ALLIANCE_PERMISSION, Commands.GUILD_USER_ALLIANCE_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 1)
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));

        Guild g = GuildManager.getGuild(p);

        if (g == null)
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);

        if (!g.isOwner(UserManager.getUser(p).getUuid()))
            return Util.sendMessage(p, Messages.ERROR_YOU$ARENT$LEADER);

        Guild o = GuildManager.getGuild(args[0]);

        if (o == null)
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_GUILD);

        if (AllianceManager.hasAlliance(g, o)) {
            AllianceManager.removeAlliance(g, o);
            return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_ALLIANCE_DELETED, g, o));
        }

        if (AllianceManager.getGuildAlliances(g).size() >= Config.ALLIANCES_MAX)
            return Util.sendMessage(p, Messages.ERROR_ALLIANCES$MAX);

        if (AllianceManager.getInvites().contains(o.getTag() + ":" + g.getTag())) {

            List<ItemStack> items = ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_ALLIANCE_VIP : Config.ITEMS_ALLIANCE_NORMAL, 1);

            if (!ItemUtil.checkAndRemove(items, p))
                return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_ITEMS.replace("{ITEMS}", ItemUtil.getItems(items)));

            AllianceManager.getInvites().remove(o.getTag() + ":" + g.getTag());
            AllianceManager.createAlliance(g, o);
            return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_ALLIANCE_CREATED, g, o));
        }

        OfflinePlayer owner = Bukkit.getOfflinePlayer(o.getOwner());

        if (!owner.isOnline())
            return Util.sendMessage(p, Messages.ERROR_OWNER$NOT$ONLINE);

        AllianceManager.getInvites().add(g.getTag() + ":" + o.getTag());

        Util.sendMessage(owner.getPlayer(), Messages.parse(Messages.INFO_ALLY_NEW, g));
        return Util.sendMessage(p, Messages.parse(Messages.INFO_ALLY_SEND, o));

    }
}
