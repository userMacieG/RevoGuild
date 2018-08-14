package net.karolek.revoguild.commands;

import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.CombatManager;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class CombatCommand extends SubCommand {

    public CombatCommand() {
        super(Commands.COMBAT_NAME, Commands.COMBAT_DESCRIPTION, Commands.COMBAT_USAGE, Commands.COMBAT_PERMISSION, Commands.COMBAT_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        return Util.sendMessage(p, Messages.COMBAT$INFO.replace("{TIME}", CombatManager.getTime(p)));
    }
}