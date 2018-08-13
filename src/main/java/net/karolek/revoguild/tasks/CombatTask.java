package net.karolek.revoguild.tasks;

import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.managers.CombatManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CombatTask extends BukkitRunnable {

    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p != null) {
                if (CombatManager.wasInFight(p) && CombatManager.isInFight(p)) {
                    Util.sendMessage(p, Lang.INFO_FIGHT_END);
                }
            }
        }
    }

}
