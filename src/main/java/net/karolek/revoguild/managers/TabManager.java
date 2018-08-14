package net.karolek.revoguild.managers;

import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.data.TabScheme;
import net.karolek.revoguild.tablist.TabDataImpl;
import net.karolek.revoguild.tablist.update.RankList.Data;
import net.karolek.revoguild.tablist.update.TabThread;
import net.karolek.revoguild.utils.PacketUtil;
import net.karolek.revoguild.utils.TimeUtil;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public class TabManager {

    private static final WeakHashMap<Player, TabDataImpl> tablists = new WeakHashMap<>();

    public static void createTab(Player p) {
        if (hasTab(p)) {
            getTab(p);
            return;
        }
        TabDataImpl t = new TabDataImpl();
        sendTab(t, p);
        tablists.put(p, t);
    }

    public static boolean removeTab(Player p) {
        if (!hasTab(p)) {
            return false;
        }
        tablists.remove(p);
        return true;
    }

    public static void updateAll() {
        for (TabDataImpl t : TabManager.getTablists().values()) {
            for (Map.Entry<Integer, Map<Integer, String>> c : TabScheme.tablist.entrySet()) {
                for (Map.Entry<Integer, String> d : c.getValue().entrySet()) {
                    TabManager.updateSlot(t, d.getKey(), c.getKey(), d.getValue());
                }
            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                t.setHeaderAndFooter(parse(Config.TABLIST_HEADER, p), parse(Config.TABLIST_FOOTER, p));
            }
        }
    }

    public static void updateSlots() {
        for (TabDataImpl t : TabManager.getTablists().values()) {
            for (Map.Entry<Integer, Map<Integer, String>> c : TabScheme.tablist.entrySet()) {
                for (Map.Entry<Integer, String> d : c.getValue().entrySet()) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        TabManager.updateSlot(t, d.getKey(), c.getKey(), parse(d.getValue(), p));
                        t.setHeaderAndFooter(parse(Config.TABLIST_HEADER, p), parse(Config.TABLIST_FOOTER, p));
                    }
                }
            }
        }
    }

    private static TabDataImpl getTab(Player p) {
        return tablists.get(p);
    }

    private static boolean hasTab(Player p) {
        return tablists.containsKey(p);
    }

    private static void sendTab(TabDataImpl t, Player p) {
        t.setPlayer(p);
        t.setHeaderAndFooter(parse(Config.TABLIST_HEADER, p), parse(Config.TABLIST_FOOTER, p));
        for (Entry<Integer, Map<Integer, String>> c : TabScheme.tablist.entrySet()) {
            for (Entry<Integer, String> d : c.getValue().entrySet()){
                t.setSlot(d.getKey(), c.getKey(), parse(d.getValue(), t.getPlayer()));
            }
        }
        t.sendTab();
    }

    public static void updateSlot(TabDataImpl t, int slot, int column, String text) {
        t.updateSlot(slot, column, text);
    }

    private static String parse(String s, Player p) {
        User pU = UserManager.getUser(p);
        List<Data<User>> playerList = TabThread.getInstance().getRankList().getTopPlayers();
        for (int i = 0; i < 20; i++) {
            String format = Config.TABLIST_FORMAT_PTOP;
            if (i >= playerList.size()) {
                format = format.replace("{NAME}", Config.TABLIST_FORMAT_EMPTY);
            } else {
                Data<User> u = playerList.get(i);
                format = format.replace("{NAME}", u == null ? Config.TABLIST_FORMAT_EMPTY : u.getKey().getName());
            }
            s = s.replace("{PTOP-" + (i + 1) + "}", format);
        }
        List<Data<Guild>> guildList = TabThread.getInstance().getRankList().getTopGuilds();
        for (int i = 0; i < 20; i++) {
            String format = Config.TABLIST_FORMAT_GTOP;
            if (i >= guildList.size()) {
                format = Config.TABLIST_FORMAT_EMPTY;
            } else {
                Data<Guild> g = guildList.get(i);
                format = Messages.parse(format, g.getKey());
            }
            s = s.replace("{GTOP-" + (i + 1) + "}", format);
        }
        s = Messages.parse(s, pU);
        s = s.replace("{ONLINE}", Integer.toString(Bukkit.getOnlinePlayers().size()));
        s = s.replace("{TIME}", TimeUtil.formatTime(System.currentTimeMillis()));
        Guild g = GuildManager.getGuild(p);
        s = s.replace("{TAG}", g == null ? Config.TABLIST_FORMAT_EMPTY : g.getTag());
        s = s.replace("{PING}", Integer.toString(PacketUtil.getPing(p)));
        return Util.fixColor(s);
    }

    public static WeakHashMap<Player, TabDataImpl> getTablists() {
        return tablists;
    }

}
