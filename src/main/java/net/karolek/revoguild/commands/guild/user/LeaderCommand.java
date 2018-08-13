package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.base.Guild;
import net.karolek.revoguild.base.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.managers.GuildManager;
import net.karolek.revoguild.managers.UserManager;
import net.karolek.revoguild.utils.ItemUtil;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class LeaderCommand extends SubCommand {

    public LeaderCommand() {
        super("lider", "zmienianie lidera gildii", "/g lider <gracz>", "revoguild.leader", "leader");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 1)
            return Util.sendMessage(p, Lang.parse(Lang.CMD_CORRECT_USAGE, this));

        Guild g = GuildManager.getGuild(p);

        if (g == null)
            return Util.sendMessage(p, Lang.ERROR_DONT_HAVE_GUILD);

        if (!g.isOwner(UserManager.getUser(p).getUuid()))
            return Util.sendMessage(p, Lang.ERROR_NOT_OWNER);

        @SuppressWarnings("deprecation")
        Player o = Bukkit.getPlayer(args[0]);
        User u = UserManager.getUser(o);

        if (!g.isMember(u.getUuid()))
            return Util.sendMessage(p, Lang.ERROR_PLAYER_ISNT_MEMBER);

        List<ItemStack> items = ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.COST_LEADER_VIP : Config.COST_LEADER_NORMAL, 1);

        if (!ItemUtil.checkAndRemove(items, p))
            return Util.sendMessage(p, Lang.ERROR_DONT_HAVE_ITEMS.replace("{ITEMS}", ItemUtil.getItems(items)));

        g.setLeader(u.getUuid());

        Util.sendMessage(p, Lang.INFO_LEADER_CHANGED);
        return Util.sendMessage(o, Lang.INFO_NOW_LEADER);
    }
}
