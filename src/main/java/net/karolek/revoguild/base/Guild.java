package net.karolek.revoguild.base;

import net.karolek.revoguild.GuildPlugin;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Lang;
import net.karolek.revoguild.managers.AllianceManager;
import net.karolek.revoguild.managers.UserManager;
import net.karolek.revoguild.store.Entry;
import net.karolek.revoguild.tablist.update.TabThread;
import net.karolek.revoguild.utils.TimeUtil;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Guild implements Entry {

    private final String tag;
    private final String name;
    private final Treasure treasure;
    private final long createTime;
    private final Set<UUID> members = new HashSet<>();
    private final Set<UUID> treasureUsers = new HashSet<>();
    private final Set<UUID> invites = new HashSet<>();
    private UUID owner;
    private UUID leader;
    private long expireTime;
    private long lastExplodeTime;
    private long lastTakenLifeTime;
    private int lives;
    private Cuboid cuboid;
    private Location home;
    private boolean pvp;
    private boolean preDeleted;
    private String banAdmin;
    private long banTime;
    private String banReason;

    public Guild(String tag, String name, Player owner) {
        this.tag = tag;
        this.name = name;
        this.owner = owner.getUniqueId();
        this.leader = owner.getUniqueId();
        this.cuboid = new Cuboid(owner.getWorld().getName(), owner.getLocation().getBlockX(), owner.getLocation().getBlockZ(), Config.CUBOID_SIZE_START);
        this.treasure = new Treasure(this);
        this.home = owner.getLocation();
        this.createTime = System.currentTimeMillis();
        this.expireTime = System.currentTimeMillis() + TimeUtil.DAY.getTime(Config.TIME_START);
        this.lastExplodeTime = System.currentTimeMillis() - TimeUtil.SECOND.getTime(Config.TNT_CANTBUILD_TIME);
        this.lastTakenLifeTime = 0L;
        this.lives = Config.UPTAKE_LIVES_START;
        this.pvp = false;
        this.preDeleted = false;
        this.banAdmin = "";
        this.banTime = -1L;
        this.banReason = "";
        this.insert();
    }

    public Guild(ResultSet rs) throws SQLException {
        this.tag = rs.getString("tag");
        this.name = rs.getString("name");
        this.owner = UUID.fromString(rs.getString("owner"));
        this.leader = UUID.fromString(rs.getString("leader"));
        this.cuboid = new Cuboid(rs.getString("cuboidWorld"), rs.getInt("cuboidX"), rs.getInt("cuboidZ"), rs.getInt("cuboidSize"));
        this.treasure = new Treasure(this, GuildPlugin.getStore().query("SELECT * FROM `{P}treasures` WHERE `tag` = '" + this.tag + "'"));
        this.home = new Location(Bukkit.getWorld(rs.getString("homeWorld")), rs.getInt("homeX"), rs.getInt("homeY"), rs.getInt("homeZ"));
        this.createTime = rs.getLong("createTime");
        this.expireTime = rs.getLong("expireTime");
        this.lastExplodeTime = System.currentTimeMillis() - TimeUtil.SECOND.getTime(Config.TNT_CANTBUILD_TIME);
        this.lastTakenLifeTime = rs.getLong("lastTakenLifeTime");
        this.lives = rs.getInt("lives");
        this.pvp = (rs.getInt("pvp") == 1);
        this.preDeleted = false;
        this.banAdmin = rs.getString("banAdmin");
        this.banReason = rs.getString("banReason");
        this.banTime = rs.getLong("banTime");
        ResultSet r = GuildPlugin.getStore().query("SELECT * FROM `{P}members` WHERE `tag` = '" + this.tag + "'");
        while (r.next()) {
            this.members.add(UUID.fromString(r.getString("uuid")));
        }
        ResultSet r1 = GuildPlugin.getStore().query("SELECT * FROM `{P}treasure_users` WHERE `tag` = '" + this.tag + "'");
        while (r1.next()) {
            this.treasureUsers.add(UUID.fromString(r1.getString("uuid")));
        }
    }

    public void openTreasure(Player p) {
        Treasure bp = this.treasure;
        Inventory inv = bp.getInv();
        inv.clear();
        inv.setContents(bp.getItems());
        p.openInventory(inv);
        p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1.0F, 1.0F);
    }

    public void closeTreasure(Player p, Inventory i) {
        Treasure bp = this.treasure;
        bp.setItems(i.getContents());
        bp.update(false);
        p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1.0F, 1.0F);
    }

    public Set<Player> getOnlineMembers() {
        Set<Player> online = new HashSet<>();
        for (UUID uuid : this.members) {
            User u = UserManager.getUser(uuid);
            OfflinePlayer op = u.getOfflinePlayer();
            if (op.isOnline()) {
                online.add(op.getPlayer());
            }
        }
        return online;
    }

    public int getPoints() {
        String algorithm = Config.ALGORITHM_GUILD_POINTS;
        int points = 0;
        for (UUID uuid : this.members) {
            User u = UserManager.getUser(uuid);
            points += u.getPoints();
        }
        algorithm = algorithm.replace("{MEMBERS_POINTS}", Integer.toString(points));
        algorithm = algorithm.replace("{MEMBERS_NUM}", Integer.toString(this.members.size()));
        return Util.calculate(algorithm);
    }

    public int getKills() {
        int kills = 0;
        for (UUID uuid : this.members) {
            User u = UserManager.getUser(uuid);
            kills += u.getKills();
        }
        return kills;
    }

    public int getDeaths() {
        int deaths = 0;
        for (UUID uuid : this.members) {
            User u = UserManager.getUser(uuid);
            deaths += u.getDeaths();
        }
        return deaths;
    }

    public boolean isOwner(UUID u) {
        return this.owner.equals(u);
    }

    public boolean isLeader(UUID u) {
        return this.leader.equals(u);
    }

    public boolean isMember(UUID u) {
        return this.members.contains(u);
    }

    public boolean isTreasureUser(UUID u) {
        return this.treasureUsers.contains(u);
    }

    public void addTreasureUser(UUID u) {
        GuildPlugin.getStore().update(false, "INSERT INTO `{P}treasure_users` (`id`, `uuid`, `tag`) VALUES(NULL, '" + u.toString() + "' , '" + this.tag + "')");
        this.treasureUsers.add(u);
    }

    public void removeTreasureUser(UUID u) {
        GuildPlugin.getStore().update(false, "DELETE FROM `{P}treasure_users` WHERE `uuid` = '" + u.toString() + "' AND `tag` = '" + this.tag + "'");
        this.treasureUsers.remove(u);
    }

    private boolean hasInvite(UUID u) {
        return this.invites.contains(u);
    }

    public boolean addInvite(UUID u) {
        if (hasInvite(u)) {
            return false;
        }
        return this.invites.add(u);
    }

    public void removeInvite(UUID u) {
        this.invites.remove(u);
    }

    public boolean addMember(UUID u) {
        if (!hasInvite(u)) {
            return false;
        }
        if (isMember(u)) {
            return false;
        }
        removeInvite(u);
        this.members.add(u);
        GuildPlugin.getStore().update(false, "INSERT INTO `{P}members` (`id`,`uuid`,`tag`) VALUES(NULL, '" + u.toString() + "', '" + this.tag + "')");
        TabThread.restart();
        return true;
    }

    public boolean addSize() {
        if (!this.cuboid.addSize()) {
            return false;
        }
        GuildPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `cuboidSize` = '" + this.cuboid.getSize() + "' WHERE `tag` = '" + this.tag + "'");
        return true;
    }

    public boolean removeMember(UUID u) {
        if (!isMember(u)) {
            return false;
        }
        this.members.remove(u);
        removeTreasureUser(u);
        GuildPlugin.getStore().update(false, "DELETE FROM `{P}members` WHERE `uuid` = '" + u.toString() + "' AND `tag` = '" + this.tag + "'");
        TabThread.restart();
        return true;
    }

    public boolean isBanned() {
        return this.banTime > System.currentTimeMillis();
    }

    public boolean ban(String admin, String reason, long time) {
        if (isBanned()) {
            return false;
        }
        this.banAdmin = admin;
        this.banReason = reason;
        this.banTime = time;
        GuildPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `banAdmin` = '" + admin + "', `banTime`='" + time + "',`banReason`='" + reason + "' WHERE `tag` = '" + this.tag + "'");
        for (Player p : getOnlineMembers()) {
            p.kickPlayer(Lang.parse(Lang.BAN_KICKED, this));
        }
        return true;
    }

    public boolean unban() {
        if (!isBanned()) {
            return false;
        }
        this.banAdmin = "";
        this.banReason = "";
        this.banTime = -1L;
        GuildPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `banAdmin` = '" + this.banAdmin + "', `banTime`='" + this.banTime + "',`banReason`='" + this.banReason + "' WHERE `tag` = '" + this.tag + "'");
        return true;
    }

    @Override
    public void insert() {
        String u = "INSERT INTO `{P}guilds` (`id`, `tag`, `name`, `owner`, `leader`, `cuboidWorld`, `cuboidX`, `cuboidZ`, `cuboidSize`, `homeWorld`, `homeX`, `homeY`, `homeZ`, `lives`, `createTime`, `expireTime`, `lastTakenLifeTime`, `pvp`, `banAdmin`, `banReason`, `banTime`) VALUES (NULL, '" + this.tag + "', '" + this.name + "', '" + this.owner + "', '" + this.leader + "', '" + this.cuboid.getWorld().getName() + "', '" + this.cuboid.getCenterX() + "', '" + this.cuboid.getCenterZ() + "', '" + this.cuboid.getSize() + "', '" + this.home.getWorld().getName() + "', '" + this.home.getBlockX() + "', '" + this.home.getBlockY() + "', '" + this.home.getBlockZ() + "', '" + this.lives + "', '" + this.createTime + "', '" + this.expireTime + "', '" + this.lastTakenLifeTime + "', '" + (this.pvp ? 1 : 0) + "', '" + this.banAdmin + "', '" + this.banReason + "', '" + this.banTime + "')";
        GuildPlugin.getStore().update(false, u);
    }

    @Override
    public void update(boolean now) {
        String update = "UPDATE `{P}guilds` SET `owner`='" + this.owner + "', `leader`='" + this.leader + "', `cuboidWorld`='" + this.cuboid.getWorld().getName() + "', `cuboidX`='" + this.cuboid.getCenterX() + "', `cuboidZ`='" + this.cuboid.getCenterZ() + "', `cuboidSize`='" + this.cuboid.getSize() + "', `homeWorld`='" + this.home.getWorld().getName() + "', `homeX`='" + this.home.getBlockX() + "', `homeY`='" + this.home.getBlockY() + "', `homeZ`='" + this.home.getBlockZ() + "', `createTime`='" + this.createTime + "', `expireTime`='" + this.expireTime + "', `lastTakenLifeTime` = '" + this.lastTakenLifeTime + "', `lives` = '" + this.lives + "', `pvp`='" + (this.pvp ? 1 : 0) + "', `banAdmin` = '" + this.banAdmin + "', `banTime`='" + this.banTime + "', `banReason`='" + this.banReason + "' WHERE `tag`='" + this.tag + "'";
        GuildPlugin.getStore().update(now, update);

    }

    @Override
    public void delete() {
        GuildPlugin.getStore().update(true, "DELETE FROM `{P}guilds` WHERE `tag` = '" + this.tag + "'");
        GuildPlugin.getStore().update(true, "DELETE FROM `{P}members` WHERE `tag` = '" + this.tag + "'");
        GuildPlugin.getStore().update(true, "DELETE FROM `{P}treasure_users` WHERE `tag` = '" + this.tag + "'");
        this.treasure.delete();
        for (Alliance a : AllianceManager.getGuildAlliances(this)) {
            a.delete();
        }
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public UUID getLeader() {
        return leader;
    }

    public void setLeader(UUID leader) {
        this.leader = leader;
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public long getLastExplodeTime() {
        return lastExplodeTime;
    }

    public void setLastExplodeTime(long lastExplodeTime) {
        this.lastExplodeTime = lastExplodeTime;
    }

    public long getLastTakenLifeTime() {
        return lastTakenLifeTime;
    }

    public void setLastTakenLifeTime(long lastTakenLifeTime) {
        this.lastTakenLifeTime = lastTakenLifeTime;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Set<UUID> getMembers() {
        return members;
    }

    public Set<UUID> getTreasureUsers() {
        return treasureUsers;
    }

    public Set<UUID> getInvites() {
        return invites;
    }

    public Cuboid getCuboid() {
        return cuboid;
    }

    public void setCuboid(Cuboid cuboid) {
        this.cuboid = cuboid;
    }

    public Location getHome() {
        return home;
    }

    public void setHome(Location home) {
        this.home = home;
    }

    public boolean isPvp() {
        return pvp;
    }

    public void setPvp(boolean pvp) {
        this.pvp = pvp;
    }

    public boolean isPreDeleted() {
        return preDeleted;
    }

    public void setPreDeleted(boolean preDeleted) {
        this.preDeleted = preDeleted;
    }

    public String getBanAdmin() {
        return banAdmin;
    }

    public void setBanAdmin(String banAdmin) {
        this.banAdmin = banAdmin;
    }

    public long getBanTime() {
        return banTime;
    }

    public void setBanTime(long banTime) {
        this.banTime = banTime;
    }

    public String getBanReason() {
        return banReason;
    }

    public void setBanReason(String banReason) {
        this.banReason = banReason;
    }

    public void addLives(int lives) {
        this.lives += lives;
        update(false);
    }

    public void removeLives(int lives) {
        this.lives -= lives;
        update(false);
    }

    public void addExpireTime(long expireTime) {
        this.expireTime += expireTime;
        update(false);
    }

    public void removeExpireTime(int expireTime) {
        this.expireTime -= expireTime;
        update(false);
    }

}
