package net.karolek.revoguild.utils;

import net.karolek.revoguild.utils.Reflection.FieldAccessor;
import net.karolek.revoguild.utils.Reflection.MethodInvoker;
import org.bukkit.entity.Player;

public class PacketUtil {

    private static final MethodInvoker handleMethod;
    private static final MethodInvoker sendPacket;
    private static final FieldAccessor<Object> playerConnection;
    private static final FieldAccessor<Integer> lastPing;

    static {
        handleMethod = Reflection.getMethod(Reflection.getCraftBukkitClass("entity.CraftEntity"), "getHandle");
        sendPacket = Reflection.getMethod(Reflection.getMinecraftClass("PlayerConnection"), "sendPacket", Reflection.getMinecraftClass("Packet"));
        playerConnection = Reflection.getSimpleField(Reflection.getMinecraftClass("EntityPlayer"), "playerConnection");
        lastPing = Reflection.getField(Reflection.getMinecraftClass("EntityPlayer"), "ping", int.class);
    }

    public static void sendPacket(Player player, Object... objects) {
        if (handleMethod == null) {
            throw new RuntimeException("HandleMethod can not be null!");
        }
        Object handle = handleMethod.invoke(player);
        for (Object o : objects) {
            sendPacket.invoke(playerConnection.get(handle), o);
        }
    }

    public static int getPing(Player p) {
        return lastPing.get(handleMethod.invoke(p));
    }

}
