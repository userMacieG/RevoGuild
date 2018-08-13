package net.karolek.revoguild.managers;

import net.karolek.revoguild.base.Guild;
import net.karolek.revoguild.base.User;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.data.TabScheme;
import net.karolek.revoguild.tablist.Tab;
import net.karolek.revoguild.tablist.TabCell;
import net.karolek.revoguild.tablist.update.RankList.Data;
import net.karolek.revoguild.tablist.update.TabThread;
import net.karolek.revoguild.utils.PacketUtil;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public class TabManager {

    private static final WeakHashMap<Player, Tab> tablists = new WeakHashMap<>();

    public static void createTab(Player p) {
        if (hasTab(p)) {
            getTab(p);
            return;
        }
        Tab t = new Tab(p);
        sendTab(t);
        tablists.put(p, t);
    }

    public static boolean removeTab(Player p) {
        if (!hasTab(p)) {
            return false;
        }
        Tab t = getTab(p);
        t.clear();
        tablists.remove(p);
        return true;
    }

    public static void updateAll() {
        for (Tab t : tablists.values()) {
            t.update();
        }
    }

    private static Tab getTab(Player p) {
        return tablists.get(p);
    }

    private static boolean hasTab(Player p) {
        return tablists.containsKey(p);
    }

    private static void sendTab(Tab t) {
        for (Entry<Integer, String> e : TabScheme.slots.entrySet()) {
            String[] pns = e.getValue().split("\\|");
            String prefix = pns.length > 0 ? pns[0] : "";
            String name = pns.length > 1 ? pns[1] : "";
            String suffix = pns.length > 2 ? pns[2] : "";
            t.setSlot(e.getKey(), parse(prefix, t.getPlayer()), name, parse(suffix, t.getPlayer()));
        }
        t.send();
    }

    public static void updateSlot(Tab t, int slot) {
        TabCell tc = t.getSlot(slot);
        if (tc == null) {
            return;
        }
        String[] pns = TabScheme.slots.get(slot).split("\\|");
        String prefix = pns.length > 0 ? pns[0] : "";
        String suffix = pns.length > 2 ? pns[2] : "";
        tc.updatePrefixAndSuffix(parse(prefix, t.getPlayer()), parse(suffix, t.getPlayer()));
    }

    private static String parse(String s, Player p) {
        User pU = UserManager.getUser(p);
        List<Data<User>> playerList = TabThread.getInstance().getRankList().getTopPlayers();
        for (int i = 0; i < 20; i++) {
            String format = Config.TABLIST_FORMAT_PTOP;
            if (i >= playerList.size()) {
                format = format.replace("{NAME}", "brak");
            } else {
                Data<User> u = playerList.get(i);
                format = format.replace("{NAME}", u == null ? "brak" : u.getKey().getName());
            }
            s = s.replace("{PTOP-" + (i + 1) + "}", format);
        }
        List<Data<Guild>> guildList = TabThread.getInstance().getRankList().getTopGuilds();
        for (int i = 0; i < 20; i++) {
            String format = Config.TABLIST_FORMAT_GTOP;
            if (i >= guildList.size()) {
                format = "brak";
            } else {
                Data<Guild> g = guildList.get(i);
                format = Lang.parse(format, g.getKey());
            }
            s = s.replace("{GTOP-" + (i + 1) + "}", format);
        }
        s = Lang.parse(s, pU);
        s = s.replace("{ONLINE}", Integer.toString(Bukkit.getOnlinePlayers().size()));
        s = s.replace("{TIME}", Util.getTime(System.currentTimeMillis()));
        Guild g = GuildManager.getGuild(p);
        s = s.replace("{TAG}", g == null ? "brak" : g.getTag());
        s = s.replace("{PING}", Integer.toString(PacketUtil.getPing(p)));
        return Util.fixColor(s);
    }

    public static WeakHashMap<Player, Tab> getTablists() {
        return tablists;
    }

}
