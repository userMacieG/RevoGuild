package net.karolek.revoguild.tablist.packets;

import net.karolek.revoguild.tablist.Profile;
import org.bukkit.entity.Player;

public interface PacketManager {

    void sendPackets(Player player, Object... objects);

    void sendTablistHeaderPacket(Player player, String header, String footer);

    void sendPlayerListPacket(Player player, Profile profile, String displayName, PlayerInfoAction action);

}
