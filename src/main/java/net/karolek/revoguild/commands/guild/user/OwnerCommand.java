package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.utils.ItemUtil;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class OwnerCommand extends SubCommand {

    public OwnerCommand() {
        super(Commands.GUILD_USER_OWNER_NAME, Commands.GUILD_USER_OWNER_DESCRIPTION, Commands.GUILD_USER_OWNER_USAGE, Commands.GUILD_USER_OWNER_PERMISSION, Commands.GUILD_USER_OWNER_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 1) {
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));
        }
        Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);
        }
        if (!g.isOwner(UserManager.getUser(p).getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_YOU$ARENT$OWNER);
        }
        Player o = Bukkit.getPlayer(args[0]);
        User u = UserManager.getUser(o);
        if (!g.isMember(u.getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_PLAYER$ISNT_MEMBER);
        }
        List<ItemStack> items = ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_OWNER_VIP : Config.ITEMS_OWNER_NORMAL, 1);
        if (!ItemUtil.checkAndRemove(items, p)) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_ITEMS.replace("{ITEMS}", ItemUtil.getItems(items)));
        }
        g.setOwner(u.getUuid());
        g.update(false);
        Util.sendMessage(p, Messages.INFO_CHANGED_OWNER);
        return Util.sendMessage(o, Messages.INFO_IS$NOW_OWNER);
    }
}
