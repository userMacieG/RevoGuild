package net.karolek.revoguild.tablist;

import net.karolek.revoguild.utils.PacketUtil;
import net.karolek.revoguild.utils.Util;

public class TabCell {

    private final Tab tab;
    private final String name;
    boolean sent;
    boolean teamExists;
    boolean toRemove;
    private String prefix;
    private String suffix;
    private int ping;

    public TabCell(Tab tab, String prefix, String name, String suffix, int ping) {
        this.tab = tab;
        this.prefix = Util.fixColor(prefix.substring(0, Math.min(prefix.length(), 16)));
        this.name = Util.fixColor(name.substring(0, Math.min(name.length(), 16)));
        this.suffix = Util.fixColor(suffix.substring(0, Math.min(suffix.length(), 16)));
        this.ping = ping;
        this.teamExists = true;
        this.sent = false;
    }

    public TabCell(Tab tab, String name) {
        this.tab = tab;
        this.name = Util.fixColor(name.substring(0, Math.min(name.length(), 16)));
        this.teamExists = false;
        this.sent = false;
    }

    public void createPrefixAndSuffix(String prefix, String suffix) {
        if (toRemove || teamExists) {
            return;
        }
        this.teamExists = true;
        this.prefix = Util.fixColor(prefix.substring(0, Math.min(prefix.length(), 16)));
        this.suffix = Util.fixColor(suffix.substring(0, Math.min(suffix.length(), 16)));
        PacketUtil.sendPacket(this.tab.getPlayer(), TabUtil.createTeamPacket(name, name, this.prefix, this.suffix, 0, name));
    }

    public void updatePrefixAndSuffix(String prefix, String suffix) {
        if (toRemove || !teamExists) {
            return;
        }
        this.prefix = Util.fixColor(prefix.substring(0, Math.min(prefix.length(), 16)));
        this.suffix = Util.fixColor(suffix.substring(0, Math.min(suffix.length(), 16)));
        PacketUtil.sendPacket(this.tab.getPlayer(), TabUtil.createTeamPacket(name, name, this.prefix, this.suffix, 2, name));
    }

    public void removePrefixAndSuffix() {
        if (toRemove || !teamExists) {
            return;
        }
        this.teamExists = false;
        PacketUtil.sendPacket(this.tab.getPlayer(), TabUtil.createTeamPacket(name, name, null, null, 1, name));
    }

    public Tab getTab() {
        return tab;
    }

    public String getName() {
        return name;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public boolean isTeamExists() {
        return teamExists;
    }

    public void setTeamExists(boolean teamExists) {
        this.teamExists = teamExists;
    }

    public boolean isToRemove() {
        return toRemove;
    }

    public void setToRemove(boolean toRemove) {
        this.toRemove = toRemove;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getPing() {
        return ping;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }

}
