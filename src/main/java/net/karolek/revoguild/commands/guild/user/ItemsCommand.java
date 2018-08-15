package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.utils.ItemUtil;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.*;
import org.bukkit.*;

import java.util.ArrayList;
import java.util.List;

public class ItemsCommand extends SubCommand {

    public ItemsCommand() {
        super(Commands.GUILD_USER_ITEMS_NAME, Commands.GUILD_USER_ITEMS_DESCRIPTION, Commands.GUILD_USER_ITEMS_USAGE, Commands.GUILD_USER_ITEMS_PERMISSION, Commands.GUILD_USER_ITEMS_ALIASES);
    }

    public boolean onCommand(final Player p, final String[] args) {
        List<String> msg = Messages.GUILD_USER$ITEMS$HELP;
        List<String> fin = new ArrayList<>();
        for (String s : msg) {
            s = s.replace("{CREATE}", ItemUtil.getItems(ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_CREATE_VIP : Config.ITEMS_CREATE_NORMAL, 1)));
            String algorithm = Config.ALGORITHM_JOIN;
            algorithm = algorithm.replace("{MEMBERS_NUM}", Integer.toString(1));
            int modifier = Util.calculate(algorithm);
            s = s.replace("{JOIN}", ItemUtil.getItems(ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_JOIN_VIP : Config.ITEMS_JOIN_NORMAL, modifier)));
            s = s.replace("{LEADER}", ItemUtil.getItems(ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_LEADER_VIP : Config.ITEMS_LEADER_NORMAL, 1)));
            s = s.replace("{OWNER}", ItemUtil.getItems(ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_OWNER_VIP : Config.ITEMS_OWNER_NORMAL, 1)));
            String algorithm1 = Config.ALGORITHM_ENLARGE;
            algorithm1 = algorithm1.replace("{CUBOID_SIZE}", Integer.toString(1));
            int modifier1 = Util.calculate(algorithm1);
            s = s.replace("{ENLARGE}", ItemUtil.getItems(ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_ENLARGE_VIP : Config.ITEMS_ENLARGE_NORMAL, modifier1)));
            s = s.replace("{PROLONG}", ItemUtil.getItems(ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_PROLONG_VIP : Config.ITEMS_PROLONG_NORMAL, 1)));
            s = s.replace("{EFFECT}", ItemUtil.getItems(ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_EFFECT_VIP : Config.ITEMS_EFFECT_NORMAL, 1)));
            s = s.replace("{ALLIANCE}", ItemUtil.getItems(ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_ALLIANCE_VIP : Config.ITEMS_ALLIANCE_NORMAL, 1)));
            fin.add(s);
        }
        return Util.sendMessage(p, fin);
    }
}
