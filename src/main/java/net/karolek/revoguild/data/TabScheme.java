package net.karolek.revoguild.data;

import net.karolek.revoguild.GuildPlugin;
import net.karolek.revoguild.utils.Logger;
import net.karolek.revoguild.utils.Util;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TabScheme {

    public static final Map<Integer, String> slots = new HashMap<>();
    private static final File file = new File(GuildPlugin.getPlugin().getDataFolder(), "tablist.yml");
    public static List<Integer> updateSlots = new ArrayList<>();
    private static FileConfiguration c = null;

    private static void loadTablist() {

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            InputStream is = GuildPlugin.getPlugin().getResource(file.getName());
            if (is != null)
                Util.copy(is, file);
        }

        c = YamlConfiguration.loadConfiguration(file);

        for (String s : c.getConfigurationSection("tablist.slots").getKeys(false))
            slots.put(Integer.parseInt(s), c.getString("tablist.slots." + s));
        updateSlots = c.getIntegerList("tablist.update-slots");

    }

    private static void saveTablist() {
        try {
            for (Entry<Integer, String> e : slots.entrySet())
                c.set("tablist.slots." + e.getKey(), e.getValue());
            c.set("tablist.update-slots", updateSlots);
            c.save(file);
        } catch (Exception e) {
            Logger.exception(e);
        }
    }

    public static void reloadTablist() {
        loadTablist();
        saveTablist();
    }

}
