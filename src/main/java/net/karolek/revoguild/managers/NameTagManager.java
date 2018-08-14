package net.karolek.revoguild.managers;

import net.karolek.revoguild.managers.guild.AllianceManager;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class NameTagManager {

    private static String parse(String color, Guild g) {
        if (g == null) {
            return Util.fixColor(color);
        }
        String format = Config.NAME$TAG_FORMAT;
        format = format.replace("{COLOR}", color);
        format = Messages.parse(format, g);
        return format;
    }

    public static void initPlayer(Player p) {
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Guild g = GuildManager.getGuild(p);
        Team t;
        for (Guild o : GuildManager.getGuilds().values()) {
            t = sb.getTeam(o.getTag());
            if (t == null) {
                t = sb.registerNewTeam(o.getTag());
            }
            if (g == null) {
                t.setPrefix(parse(Config.NAME$TAG_COLOR_ENEMY, o));
            } else if (Objects.equals(g.getTag(), o.getTag())) {
                if (g.isPvp()) {
                    t.setPrefix(parse(Config.NAME$TAG_COLOR_FRIEND$PVP, o));
                } else {
                    t.setPrefix(parse(Config.NAME$TAG_COLOR_FRIEND, o));
                }
            } else if (AllianceManager.hasAlliance(o, g)) {
                t.setPrefix(parse(Config.NAME$TAG_COLOR_ALLIANCE, o));
            } else {
                t.setPrefix(parse(Config.NAME$TAG_COLOR_ENEMY, o));
            }
        }
        Team noguild = sb.getTeam("noguild");
        if (noguild == null) {
            noguild = sb.registerNewTeam("noguild");
            noguild.setAllowFriendlyFire(true);
            noguild.setCanSeeFriendlyInvisibles(false);
            noguild.setPrefix(parse(Config.NAME$TAG_COLOR_NO$GUILD, null));
        }
        p.setScoreboard(sb);
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.getScoreboard().getTeam(g != null ? g.getTag() : "noguild").addPlayer(p);
            Guild onlineguild = GuildManager.getGuild(online);
            p.getScoreboard().getTeam(onlineguild != null ? onlineguild.getTag() : "noguild").addPlayer(online);
        }
    }

    public static void createGuild(Guild g, Player p) {
        for (Player o : Bukkit.getOnlinePlayers()) {
            Scoreboard sb = o.getScoreboard();
            Team t = sb.registerNewTeam(g.getTag());
            if (o == p) {
                t.setPrefix(parse(Config.NAME$TAG_COLOR_FRIEND, g));
            } else {
                t.setPrefix(parse(Config.NAME$TAG_COLOR_ENEMY, g));
            }
            t.addPlayer(p);
        }
    }

    public static void removeGuild(Guild g) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Scoreboard sb = p.getScoreboard();
            sb.getTeam(g.getTag()).unregister();
            Team noguild = sb.getTeam("noguild");
            for (Player guildplayer : g.getOnlineMembers()) {
                noguild.addPlayer(guildplayer);
            }
        }
    }

    public static void setPvp(Guild g) {
        for (Player p : g.getOnlineMembers()) {
            Team t = p.getScoreboard().getTeam(g.getTag());
            if (g.isPvp()) {
                t.setPrefix(parse(Config.NAME$TAG_COLOR_FRIEND$PVP, g));
            } else {
                t.setPrefix(parse(Config.NAME$TAG_COLOR_FRIEND, g));
            }
        }
    }

    public static void joinToGuild(Guild g, Player p) {
        for (Player o : Bukkit.getOnlinePlayers()) {
            o.getScoreboard().getTeam(g.getTag()).addPlayer(p);
        }
        if (g.isPvp()) {
            p.getScoreboard().getTeam(g.getTag()).setPrefix(parse(Config.NAME$TAG_COLOR_FRIEND$PVP, g));
        } else {
            p.getScoreboard().getTeam(g.getTag()).setPrefix(parse(Config.NAME$TAG_COLOR_FRIEND, g));
        }
    }

    public static void leaveFromGuild(Guild g, OfflinePlayer p) {
        for (Player o : Bukkit.getOnlinePlayers()) {
            o.getScoreboard().getTeam("noguild").addPlayer(p);
        }
        if (p.isOnline()) {
            p.getPlayer().getScoreboard().getTeam(g.getTag()).setPrefix(parse(Config.NAME$TAG_COLOR_ENEMY, g));
        }
    }

    public static void createAlliance(Guild g, Guild o) {
        for (Player p : g.getOnlineMembers()) {
            Team t = p.getScoreboard().getTeam(o.getTag());
            if (t != null) {
                t.setPrefix(parse(Config.NAME$TAG_COLOR_ALLIANCE, o));
            }
        }
        for (Player p : o.getOnlineMembers()) {
            Team t = p.getScoreboard().getTeam(g.getTag());
            if (t != null) {
                t.setPrefix(parse(Config.NAME$TAG_COLOR_ALLIANCE, g));
            }
        }
    }

    public static void removeAlliance(Guild g, Guild o) {
        for (Player p : g.getOnlineMembers()) {
            Team t = p.getScoreboard().getTeam(o.getTag());
            if (t != null) {
                t.setPrefix(parse(Config.NAME$TAG_COLOR_ENEMY, o));
            }
        }
        for (Player p : o.getOnlineMembers()) {
            Team t = p.getScoreboard().getTeam(g.getTag());
            if (t != null) {
                t.setPrefix(parse(Config.NAME$TAG_COLOR_ENEMY, g));
            }
        }
    }

}
