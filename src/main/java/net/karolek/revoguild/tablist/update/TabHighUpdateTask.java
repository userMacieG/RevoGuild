package net.karolek.revoguild.tablist.update;

import net.karolek.revoguild.managers.TabManager;
import org.bukkit.scheduler.BukkitRunnable;

class TabHighUpdateTask extends BukkitRunnable {

    @Override
    public void run() {
        TabManager.updateAll();
    }

}
