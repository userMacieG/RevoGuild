package net.karolek.revoguild.commands.ranking;

import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.tablist.update.RankList.Data;
import net.karolek.revoguild.tablist.update.TabThread;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class TopCommand extends SubCommand {

    public TopCommand() {
        super(Commands.TOP_NAME, Commands.TOP_DESCRIPTION, Commands.TOP_USAGE, Commands.TOP_PERMISSION, Commands.TOP_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Util.sendMessage(p, Messages.LIST_RANKING_HEADER);
        int i = 1;
        for (Data<User> u : TabThread.getInstance().getRankList().getTopPlayers()) {
            if (i > 10) {
                break;
            }
            Util.sendMessage(p, Messages.parse(Messages.LIST_RANKING_ELEMENT, u.getKey()).replace("{POS}", Integer.toString(i)));
            i++;
        }
        return Util.sendMessage(p, Messages.LIST_RANKING_FOOTER);

    }
}
