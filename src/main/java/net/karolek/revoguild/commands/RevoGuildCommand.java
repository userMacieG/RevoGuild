package net.karolek.revoguild.commands;

import net.karolek.revoguild.GuildPlugin;
import net.karolek.revoguild.utils.Util;
import org.bukkit.entity.Player;

public class RevoGuildCommand extends SubCommand {

    public RevoGuildCommand() {
        super("revoguild", "main RevoGuild command", "/revoguild", "revoguild.main");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Util.sendMessage(p, "&7&m--------&r &cRevoGUILD &7&m--------&r");
        Util.sendMessage(p, " &8» &7Wersja: &c" + GuildPlugin.getPlugin().getDescription().getVersion());
        Util.sendMessage(p, " &8» &7Strona pluginu: &chttps://github.com/userMacieG/RevoGuild");
        Util.sendMessage(p, " &8» &7Autor: &cKarolek");
        Util.sendMessage(p, " &8» &7Reaktywator: &cuserMacieG");
        Util.sendMessage(p, "&7Nie usuwaj tej notki, szanuj czyjas prace, to bardzo mile! ;)");
        return true;
    }
}
