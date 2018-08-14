package net.karolek.revoguild.objects.user;

import net.karolek.revoguild.GuildPlugin;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.store.Entry;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class User implements Entry {

    private final UUID uuid;
    private String name;
    private int kills;
    private int deaths;
    private int points;

    public User(Player p) {
        this.uuid = p.getUniqueId();
        this.name = p.getName();
        this.kills = 0;
        this.deaths = 0;
        this.points = Config.RANKING_START$POINTS;
        this.insert();
    }

    public User(ResultSet rs) throws SQLException {
        this.uuid = UUID.fromString(rs.getString("uuid"));
        this.name = rs.getString("name");
        this.kills = rs.getInt("kills");
        this.deaths = rs.getInt("deaths");
        this.points = rs.getInt("points");
    }

    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(this.uuid);
    }

    @Override
    public void insert() {
        GuildPlugin.getStore().update(false, "INSERT INTO `{P}users` (`id`, `uuid`, `name`, `points` ,`kills`, `deaths`) VALUES(NULL, '" + this.uuid + "','" + this.name + "','" + this.points + "','" + this.kills + "','" + this.deaths + "')");
    }

    @Override
    public void update(boolean now) {
        GuildPlugin.getStore().update(now, "UPDATE `{P}users` SET `name` = '" + this.name + "',`points` = '" + this.points + "',`kills` = '" + this.kills + "',`deaths` = '" + this.deaths + "' WHERE `uuid` = '" + this.uuid + "'");
    }

    @Override
    public void delete() {
        GuildPlugin.getStore().update(true, "DELETE FROM `{P}users` WHERE `uuid` = '" + this.uuid + "'");
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addKills(int kills) {
        this.kills += kills;
        update(true);
    }

    public void removeKills(int kills) {
        this.kills -= kills;
        update(false);
    }

    public void addDeaths(int points) {
        this.points += points;
        update(false);
    }

    public void removeDeaths(int deaths) {
        this.deaths -= deaths;
        update(false);
    }

    public void addPoints(int points) {
        this.points += points;
        update(false);
    }

    public void removePoints(int points) {
        this.points -= points;
        update(false);
    }

    public void setName(String name) {
        this.name = name;
    }

}
