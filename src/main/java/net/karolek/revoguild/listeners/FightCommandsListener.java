package net.karolek.revoguild.listeners;

import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.managers.CombatManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class FightCommandsListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();

        if (CombatManager.isInFight(p))
            return;

        if (!Config.ESCAPE_DISABLED$COMMANDS_ENABLED)
            return;

        for (String s : Config.ESCAPE_DISABLED$COMMANDS_COMMANDS) {
            if (!msg.contains("/" + s))
                continue;
            e.setCancelled(true);
            if (Config.ESCAPE_DISABLED$COMMANDS_NOTIFY_ENABLED)
                Util.sendMessage(p, Util.fixColor(Config.ESCAPE_DISABLED$COMMANDS_NOTIFY_MESSAGE));
            return;

        }
    }

}
