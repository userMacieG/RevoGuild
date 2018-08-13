package net.karolek.revoguild.tablist.update;

import net.karolek.revoguild.base.Guild;
import net.karolek.revoguild.base.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RankList {

    private List<Data<User>> topPlayers = Collections.synchronizedList(new ArrayList<>());
    private List<Data<Guild>> topGuilds = Collections.synchronizedList(new ArrayList<>());

    public void setTopPlayers(Collection<Data<User>> data) {
        topPlayers.clear();
        topPlayers.addAll(data);
    }

    public void setTopGuilds(Collection<Data<Guild>> data) {
        topGuilds.clear();
        topGuilds.addAll(data);
    }

    public List<Data<User>> getTopPlayers() {
        return topPlayers;
    }

    public void setTopPlayers(List<Data<User>> topPlayers) {
        this.topPlayers = topPlayers;
    }

    public List<Data<Guild>> getTopGuilds() {
        return topGuilds;
    }

    public void setTopGuilds(List<Data<Guild>> topGuilds) {
        this.topGuilds = topGuilds;
    }

    public static class Data<T> {
        private final T key;
        private final int points;

        public Data(final T key, final int points) {
            this.key = key;
            this.points = points;
        }

        public T getKey() {
            return this.key;
        }

        public int getPoints() {
            return this.points;
        }
    }
}
