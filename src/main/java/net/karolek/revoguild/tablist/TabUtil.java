package net.karolek.revoguild.tablist;

import net.karolek.revoguild.utils.Reflection;
import net.karolek.revoguild.utils.Reflection.ConstructorInvoker;
import net.karolek.revoguild.utils.Reflection.FieldAccessor;

import java.util.Arrays;
import java.util.Collection;

class TabUtil {

    private static final Class<?> packetClass = Reflection.getMinecraftClass("PacketPlayOutScoreboardTeam");
    private static final ConstructorInvoker con = Reflection.getConstructor(packetClass);
    private static final FieldAccessor<String> a = Reflection.getField(packetClass, "a", String.class);
    private static final FieldAccessor<String> b = Reflection.getField(packetClass, "b", String.class);
    private static final FieldAccessor<String> c = Reflection.getField(packetClass, "c", String.class);
    private static final FieldAccessor<String> d = Reflection.getField(packetClass, "d", String.class);
    @SuppressWarnings("rawtypes")
    private static final FieldAccessor<Collection> e = Reflection.getField(packetClass, "e", Collection.class);
    private static final FieldAccessor<Integer> f = Reflection.getField(packetClass, "f", int.class);
    private static final FieldAccessor<Integer> g = Reflection.getField(packetClass, "g", int.class);
    private static final Class<?> packetClassPlayer = Reflection.getMinecraftClass("PacketPlayOutPlayerInfo");
    private static final ConstructorInvoker conPlayer = Reflection.getConstructor(packetClassPlayer, String.class, boolean.class, int.class);

    public static Object createTeamPacket(String name, String display, String prefix, String suffix, int flag, String... members) {
        Object packet = con.invoke();
        a.set(packet, name.length() > 16 ? name.substring(0, 16) : name);
        f.set(packet, flag);
        if (flag == 0 || flag == 2) {
            if (display == null) {
                b.set(packet, "");
            } else if (display.length() > 16) {
                b.set(packet, display.substring(0, 16));
            } else {
                b.set(packet, display);
            }
            if (prefix == null) {
                c.set(packet, "");
            } else if (prefix.length() > 16) {
                c.set(packet, prefix.substring(0, 16));
            } else {
                c.set(packet, prefix);
            }
            if (suffix == null) {
                d.set(packet, "");
            } else if (suffix.length() > 16) {
                d.set(packet, suffix.substring(0, 16));
            } else {
                d.set(packet, suffix);
            }
            g.set(packet, 0);
            if (flag == 0) {
                e.set(packet, Arrays.asList(members));
            }
        }
        return packet;
    }

    public static Object createPlayerPacket(String name, boolean visible, int ping) {
        return conPlayer.invoke(name, visible, ping);
    }

}
