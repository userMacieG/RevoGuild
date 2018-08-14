package net.karolek.revoguild.tablist.update;

import net.karolek.revoguild.managers.TabManager;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map.Entry;

public class TabLowUpdateTask extends BukkitRunnable {

    @Override
    public void run() {
        TabManager.updateSlots();
    }

}
