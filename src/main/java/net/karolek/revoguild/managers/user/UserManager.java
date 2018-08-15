package net.karolek.revoguild.managers.user;

import net.karolek.revoguild.GuildPlugin;
import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.tablist.RankList;
import net.karolek.revoguild.tablist.update.TabThread;
import net.karolek.revoguild.utils.Logger;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {

    private static final Map<UUID, User> users = new HashMap<>();

    public static User getUser(OfflinePlayer p) {
        for (User u : users.values()) {
            if (p.getUniqueId().equals(u.getUuid())) {
                return u;
            }
        }
        return null;
    }

    public static int getPosition(User user) {
        for (RankList.Data<User> userData : TabThread.getInstance().getRankList().getTopPlayers()) {
            if (userData.getKey().equals(user)) {
                return TabThread.getInstance().getRankList().getTopPlayers().indexOf(userData) + 1;
            }
        }
        return -1;
    }

    private static User getOfflineUser(OfflinePlayer p) {
        return getUser(p);
    }

    public static User getUser(Player p) {
        if (p.hasMetadata("user")) {
            User user = null;
            for (MetadataValue mv : p.getMetadata("user")) {
                if (!mv.getOwningPlugin().getName().equalsIgnoreCase("RevoGuild"))
                    continue;
                Object v = mv.value();
                if (v instanceof User) {
                    user = (User) v;
                }
            }
            return (user == null) ? getOfflineUser(p) : user;
        }
        return getOfflineUser(p);
    }

    public static User getUser(final UUID uuid) {
        return UserManager.users.get(uuid);
    }

    public static User getUserByName(String s) {
        for (User u : users.values()) {
            if (u.getName().equalsIgnoreCase(s)) {
                return u;
            }
        }
        return null;
    }

    public static User createUser(Player p) {
        final User u = new User(p);
        UserManager.users.put(u.getUuid(), u);
        return u;
    }

    public static void joinToGame(final Player p) {
        final User u = getUser(p);
        if (u == null) {
            createUser(p);
            return;
        }
        u.setName(p.getName());
    }

    public static void leaveFromGame(final Player p) {
        final User u = getUser(p);
        if (u == null) {
            Logger.warning("Data user '" + p.getName() + "' has been lost!");
            return;
        }
        u.setName(p.getName());
        u.update(false);
    }

    public static void initUser(Player p) {
        p.setMetadata("user", new FixedMetadataValue(GuildPlugin.getPlugin(), getUser(p)));
    }

    public static void enable() {
        ResultSet rs = GuildPlugin.getStore().query("SELECT * FROM `{P}users`");
        try {
            while (rs.next()) {
                User u = new User(rs);
                users.put(u.getUuid(), u);
            }
            Logger.info("Loaded " + users.size() + " users!");
        } catch (SQLException e) {
            Logger.warning("An error occurred while loading users!", "Error: " + e.getMessage());
            Logger.exception(e);
        }
    }

    public static Map<UUID, User> getUsers() {
        return users;
    }

}
