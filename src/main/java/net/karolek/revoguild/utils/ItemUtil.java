package net.karolek.revoguild.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemUtil {

    public static List<ItemStack> getItems(String string, int modifier) {
        List<ItemStack> items = new ArrayList<>();
        for (String s : string.split(";")) {
            String[] split = s.split("-");
            int id = Integer.parseInt(split[0].split(":")[0]);
            int data = Integer.parseInt(split[0].split(":")[1]);
            int amount = Integer.parseInt(split[1]) * modifier;
            items.add(new ItemStack(Material.getMaterial(id), amount, (short) data));
        }
        return items;
    }

    public static void giveItems(Player p, ItemStack... items) {
        Inventory i = p.getInventory();
        HashMap<Integer, ItemStack> notStored = i.addItem(items);
        for (Map.Entry<Integer, ItemStack> e : notStored.entrySet()) {
            p.getWorld().dropItemNaturally(p.getLocation(), e.getValue());
        }
    }

    public static boolean checkItems(List<ItemStack> items, Player p) {
        for (ItemStack item : items) {
            if (!p.getInventory().containsAtLeast(item, item.getAmount())) {
                return false;
            }
        }
        return true;
    }

    public static String getItems(List<ItemStack> items) {
        StringBuilder sb = new StringBuilder();
        for (ItemStack item : items) {
            sb.append(item.getType().name().toLowerCase().replace("_", " "));
            sb.append(" ");
            if (item.getData().getData() != 0) {
                sb.append("(").append(sb.append(item.getTypeId())).append("/").append(item.getData().getData()).append(")");
            } else {
                sb.append("(").append(item.getTypeId()).append(")");
            }
            sb.append(", ").append(item.getAmount()).append(" szt.").append("\n");
        }
        return sb.toString();
    }

    public static void removeItems(List<ItemStack> items, Player player) {
        Inventory inv = player.getInventory();
        List<ItemStack> removes = new ArrayList<>();
        for (ItemStack item : items) {
            if (inv.containsAtLeast(item, item.getAmount())) {
                removes.add(item);
            }
        }
        if (removes.size() == items.size()) {
            for (ItemStack item : items) {
                for (ItemStack remove : removes) {
                    if (item.getType().equals(remove.getType()) && item.getData().equals(remove.getData())) {
                        inv.removeItem(item);
                    }
                }
            }
        }
        removes.clear();
    }

    public static boolean checkAndRemove(List<ItemStack> items, Player player) {
        boolean has = checkItems(items, player);
        if (has) {
            removeItems(items, player);
        }
        return has;
    }

}
