package net.karolek.revoguild.tablist;

import org.bukkit.entity.Player;

public interface TabData {

    Player getPlayer();

    void setPlayer(Player player);

    void sendTab();

    void setSlot(int row, int column, String text);

    void updateSlot(int row, int column, String text);

    void setHeaderAndFooter(String header, String footer);

}
