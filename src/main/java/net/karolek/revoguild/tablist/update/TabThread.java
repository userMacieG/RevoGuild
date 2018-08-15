package net.karolek.revoguild.tablist.update;

import net.karolek.revoguild.GuildPlugin;
import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.tablist.RankList;
import net.karolek.revoguild.tablist.RankList.Data;
import net.karolek.revoguild.utils.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class TabThread extends Thread {

    private static TabThread instance;

    private static Comparator<User> usersComparator = (o1, o2) -> o2.getPoints() - o1.getPoints();

    private static Comparator<Guild> guildsComparator = (o1, o2) -> o2.getPoints() - o1.getPoints();

    private static AtomicInteger ai = new AtomicInteger();
    private RankList rankList;
    private Executor executor;
    private Object locker;

    public TabThread() {
        instance = this;
        rankList = new RankList();
        locker = new Object();
        this.setName("TabThread");
        this.start();
    }

    public static void restart() {
        if (instance == null) {
            Logger.warning("TabThread instance cannot be null!");
            return;
        }
        synchronized (instance.locker) {
            instance.locker.notify();
        }
    }

    public static TabThread getInstance() {
        return instance;
    }

    public static void setInstance(TabThread instance) {
        TabThread.instance = instance;
    }

    private static Comparator<User> getUsersComparator() {
        return usersComparator;
    }

    public static void setUsersComparator(Comparator<User> usersComparator) {
        TabThread.usersComparator = usersComparator;
    }

    private static Comparator<Guild> getGuildsComparator() {
        return guildsComparator;
    }

    public static void setGuildsComparator(Comparator<Guild> guildsComparator) {
        TabThread.guildsComparator = guildsComparator;
    }

    public static AtomicInteger getAi() {
        return ai;
    }

    public static void setAi(AtomicInteger ai) {
        TabThread.ai = ai;
    }

    @Override
    public void run() {
        try {
            while (true) {
                List<User> stats = new ArrayList<>(UserManager.getUsers().values());
                stats.sort(getUsersComparator());
                List<Data<User>> toAddPlayers = new LinkedList<>();
                for (User u : stats) {
                    toAddPlayers.add(new Data<>(u, u.getPoints()));
                }
                rankList.setTopPlayers(toAddPlayers);
                List<Guild> guilds = new ArrayList<>(GuildManager.getGuilds().values());
                guilds.sort(getGuildsComparator());
                List<Data<Guild>> toAddGuilds = new LinkedList<>();
                for (Guild g : guilds) {
                    toAddGuilds.add(new Data<>(g, g.getPoints()));
                }
                rankList.setTopGuilds(toAddGuilds);
                new TabHighUpdateTask().runTaskLaterAsynchronously(GuildPlugin.getPlugin(), 1L);
                synchronized (locker) {
                    locker.wait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RankList getRankList() {
        return rankList;
    }

    public void setRankList(RankList rankList) {
        this.rankList = rankList;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public Object getLocker() {
        return locker;
    }

    public void setLocker(Object locker) {
        this.locker = locker;
    }

}
