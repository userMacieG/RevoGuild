package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.tablist.update.RankList.Data;
import net.karolek.revoguild.tablist.update.TabThread;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class ListCommand extends SubCommand {

    public ListCommand() {
        super(Commands.GUILD_USER_LIST_NAME, Commands.GUILD_USER_LIST_DESCRIPTION, Commands.GUILD_USER_LIST_USAGE, Commands.GUILD_USER_LIST_PERMISSION, Commands.GUILD_USER_LIST_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Util.sendMessage(p, Messages.LIST_GUILD_HEADER);
        int i = 1;
        for (Data<User> u : TabThread.getInstance().getRankList().getTopPlayers()) {
            if (i > 10) {
                break;
            }
            Util.sendMessage(p, Messages.parse(Messages.LIST_GUILD_ELEMENT, u.getKey()).replace("{POS}", Integer.toString(i)));
            i++;
        }
        return Util.sendMessage(p, Messages.LIST_GUILD_FOOTER);
    }
}
