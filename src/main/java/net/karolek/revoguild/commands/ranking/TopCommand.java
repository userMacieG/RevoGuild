package net.karolek.revoguild.commands.ranking;

import net.karolek.revoguild.base.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.tablist.update.RankList.Data;
import net.karolek.revoguild.tablist.update.TabThread;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class TopCommand extends SubCommand {

    public TopCommand() {
        super("top", "top10 graczy", "/top", "revoguild.top", "topka", "top10");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Util.sendMessage(p, Lang.LIST_RANKING_HEADER);

        int i = 1;
        for (Data<User> u : TabThread.getInstance().getRankList().getTopPlayers()) {
            if (i > 10)
                break;

            Util.sendMessage(p, Lang.parse(Lang.LIST_RANKING_ELEMENT, u.getKey()).replace("{POS}", Integer.toString(i)));

            i++;
        }

        return Util.sendMessage(p, Lang.LIST_RANKING_FOOTER);

    }
}
