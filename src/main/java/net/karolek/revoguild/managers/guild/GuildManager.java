package net.karolek.revoguild.managers.guild;

import net.karolek.revoguild.GuildPlugin;
import net.karolek.revoguild.managers.NameTagManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.tablist.update.RankList;
import net.karolek.revoguild.tablist.update.TabThread;
import net.karolek.revoguild.utils.Logger;
import net.karolek.revoguild.utils.SpaceUtil;
import net.karolek.revoguild.utils.UptakeUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GuildManager {

    private static final Map<String, Guild> guilds = new HashMap<>();

    public static Guild createGuild(String tag, String name, Player owner) {
        Guild g = new Guild(tag, name, owner);
        User u = UserManager.getUser(owner);
        g.addInvite(u.getUuid());
        g.addMember(u.getUuid());
        g.addTreasureUser(u.getUuid());
        guilds.put(g.getTag().toUpperCase(), g);
        NameTagManager.createGuild(g, owner);
        setGuildRoom(g);
        owner.teleport(g.getCuboid().getCenter());
        g.setHome(g.getCuboid().getCenter());
        TabThread.restart();
        UptakeUtil.respawnGuild(g);
        return g;
    }

    public static void removeGuild(Guild g) {
        g.delete();
        removeGuildRoom(g);
        NameTagManager.removeGuild(g);
        guilds.remove(g.getTag().toUpperCase());
        TabThread.restart();
        UptakeUtil.despawnGuild(g);
    }

    public static Guild getGuild(String tag) {
        for (Guild g : guilds.values())
            if ((g.getTag().equalsIgnoreCase(tag)) || (g.getName().equalsIgnoreCase(tag)))
                return g;
        return null;
    }

    public static Guild getGuild(Location loc) {
        for (Guild g : guilds.values())
            if (g.getCuboid().isInCuboid(loc))
                return g;
        return null;
    }

    public static Guild getGuild(Player p) {
        for (Guild g : guilds.values()) {
            if (g.isMember(p.getUniqueId())) {
                return g;
            }
        }
        return null;
    }

    public static boolean canCreateGuild(Location loc) {
        if (!loc.getWorld().getName().equals(Config.CUBOID_WORLD)) {
            return false;
        }
        int spawnX = loc.getWorld().getSpawnLocation().getBlockX();
        int spawnZ = loc.getWorld().getSpawnLocation().getBlockZ();
        if (Config.CUBOID_SPAWN_ENABLED) {
            if (Math.abs(loc.getBlockX() - spawnX) < Config.CUBOID_SPAWN_DISTANCE && Math.abs(loc.getBlockZ() - spawnZ) < Config.CUBOID_SPAWN_DISTANCE) {
                return false;
            }
        }
        int mindistance = Config.CUBOID_SIZE_MAX * 2 + Config.CUBOID_SIZE_BETWEEN;
        for (Guild g : guilds.values())
            if ((Math.abs(g.getCuboid().getCenterX() - loc.getBlockX()) <= mindistance) && (Math.abs(g.getCuboid().getCenterZ() - loc.getBlockZ()) <= mindistance))
                return false;
        return true;
    }

    public static int getPosition(Guild guild) {
        for (RankList.Data<Guild> guildData : TabThread.getInstance().getRankList().getTopGuilds()) {
            if (guildData.getKey().equals(guild)) {
                return TabThread.getInstance().getRankList().getTopGuilds().indexOf(guildData) + 1;
            }
        }
        return -1;
    }


    public static void setGuildRoom(Guild g) {
        Location c = g.getCuboid().getCenter();
        c.setY(59);
        for (Location loc : SpaceUtil.getSquare(c, 4, 3)) {
            loc.getBlock().setType(Material.AIR);
        }
        for (Location loc : SpaceUtil.getSquare(c, 4)) {
            loc.getBlock().setType(Material.OBSIDIAN);
        }
        for (Location loc : SpaceUtil.getCorners(c, 4, 3)) {
            loc.getBlock().setType(Material.OBSIDIAN);
        }
        c.add(0, 4, 0);
        for (Location loc : SpaceUtil.getWalls(c, 4)) {
            loc.getBlock().setType(Material.OBSIDIAN);
        }
    }

    public static void removeGuildRoom(Guild g) {
        Location c = g.getCuboid().getCenter();
        c.setY(59);
        for (Location loc : SpaceUtil.getSquare(c, 4, 4)) {
            loc.getBlock().setType(Material.AIR);
        }
    }

    public static void enable() {
        ResultSet rs = GuildPlugin.getStore().query("SELECT * FROM `{P}guilds`");
        try {
            while (rs.next()) {
                Guild g = new Guild(rs);
                guilds.put(g.getTag().toUpperCase(), g);
            }
            Logger.info("Loaded " + guilds.size() + " guilds!");
        } catch (SQLException e) {
            Logger.warning("An error occurred while loading guilds!", "Error: " + e.getMessage());
            Logger.exception(e);
        }
    }

    public static Map<String, Guild> getGuilds() {
        return guilds;
    }

}
