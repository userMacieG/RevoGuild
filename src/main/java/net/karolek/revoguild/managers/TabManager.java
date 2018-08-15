package net.karolek.revoguild.managers;

import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.data.TabScheme;
import net.karolek.revoguild.tablist.TabData;
import net.karolek.revoguild.tablist.RankList.Data;
import net.karolek.revoguild.tablist.update.TabThread;
import net.karolek.revoguild.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public class TabManager {

    private static final WeakHashMap<Player, TabData> tablists = new WeakHashMap<>();

    public static void createTab(Player p) {
        if (hasTab(p)) {
            getTab(p);
            return;
        }
        TabData t = new TabData();
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
        for (TabData t : TabManager.getTablists().values()) {
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

    public static void updateSlots() {
        for (TabData t : TabManager.getTablists().values()) {
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

    private static TabData getTab(Player p) {
        return tablists.get(p);
    }

    private static boolean hasTab(Player p) {
        return tablists.containsKey(p);
    }

    private static void sendTab(TabData t, Player p) {
        t.setPlayer(p);
        t.setHeaderAndFooter(parse(Config.TABLIST_HEADER, p), parse(Config.TABLIST_FOOTER, p));
        for (Entry<Integer, Map<Integer, String>> c : TabScheme.tablist.entrySet()) {
            for (Entry<Integer, String> d : c.getValue().entrySet()){
                t.setSlot(d.getKey(), c.getKey(), parse(d.getValue(), t.getPlayer()));
            }
        }
        t.sendTab();
    }

    public static void updateSlot(TabData t, int slot, int column, String text) {
        t.updateSlot(slot, column, text);
    }

    private static String parse(String s, Player p) {
        User pU = UserManager.getUser(p);
        List<Data<User>> playerList = TabThread.getInstance().getRankList().getTopPlayers();
        for (int i = 0; i < Config.TABLIST_TOPS$MAX; i++) {
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
        for (int i = 0; i < Config.TABLIST_TOPS$MAX; i++) {
            String format = Config.TABLIST_FORMAT_GTOP;
            if (i >= guildList.size()) {
                format = Config.TABLIST_FORMAT_EMPTY;
            } else {
                Data<Guild> g = guildList.get(i);
                format = Messages.parse(format, g.getKey());
            }
            s = s.replace("{GTOP-" + (i + 1) + "}", format);
        }
        // Zmienne: Gracz
        s = Messages.parse(s, pU);
        s = s.replace("{POSITION}", Integer.toString(UserManager.getPosition(pU)));
        // Zmienne: Gildia
        Guild g = GuildManager.getGuild(p);
        s = s.replace("{TAG}", g == null ? Config.TABLIST_FORMAT_EMPTY : g.getTag());
        s = s.replace("{GNAME}", g == null ? Config.TABLIST_FORMAT_EMPTY : g.getName());
        s = s.replace("{OWNER}", g == null ? Config.TABLIST_FORMAT_EMPTY : Bukkit.getOfflinePlayer(g.getOwner()).getName());
        s = s.replace("{LEADER}", g == null ? Config.TABLIST_FORMAT_EMPTY : Bukkit.getOfflinePlayer(g.getLeader()).getName());
        s = s.replace("{EXPIRETIME}", g == null ? Config.TABLIST_FORMAT_EMPTY : DateUtil.formatDate(g.getExpireTime()));
        s = s.replace("{LASTTAKENLIFETIME}", g == null ? Config.TABLIST_FORMAT_EMPTY : DateUtil.formatDate(g.getLastTakenLifeTime()));
        s = s.replace("{SIZE}", g == null ? Config.TABLIST_FORMAT_EMPTY : Integer.toString(g.getCuboid().getSize() * 2 + 1));
        s = s.replace("{PVP}", g == null ? Config.TABLIST_FORMAT_EMPTY : (g.isPvp() ? "tak" : "nie"));
        s = s.replace("{ONLINENUM}", g == null ? Config.TABLIST_FORMAT_EMPTY : Integer.toString(g.getOnlineMembers().size()));
        s = s.replace("{MEMBERSNUM}", g == null ? Config.TABLIST_FORMAT_EMPTY : Integer.toString(g.getMembers().size()));
        s = s.replace("{GPOINTS}", g == null ? Config.TABLIST_FORMAT_EMPTY : Integer.toString(g.getPoints()));
        s = s.replace("{GKILLS}", g == null ? Config.TABLIST_FORMAT_EMPTY : Integer.toString(g.getKills()));
        s = s.replace("{GDEATHS}", g == null ? Config.TABLIST_FORMAT_EMPTY : Integer.toString(g.getDeaths()));
        s = s.replace("{LIVES}", g == null ? Config.TABLIST_FORMAT_EMPTY : Integer.toString(g.getLives()));
        s = s.replace("{GPOSITION}", g == null ? Config.TABLIST_FORMAT_EMPTY : Integer.toString(GuildManager.getPosition(g)));
        // Zmienne: Inne
        s = s.replace("{ONLINE}", Integer.toString(Bukkit.getOnlinePlayers().size()));
        s = s.replace("{TIME}", TimeUtil.formatTime(System.currentTimeMillis()));
        s = s.replace("{PING}", Integer.toString(PacketUtil.getPing(p)));
        s = s.replace("{TPS}", Ticking.getTPS());
        return Util.fixColor(s);
    }

    public static WeakHashMap<Player, TabData> getTablists() {
        return tablists;
    }

}
