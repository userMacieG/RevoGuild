package net.karolek.revoguild.utils;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static boolean sendMessage(CommandSender sender, Collection<String> messages) {
        for (String s : messages) {
            sendMessage(sender, s);
        }
        return true;
    }

    public static boolean sendMessage(Collection<? extends CommandSender> col, Collection<String> messages) {
        for (String s : messages) {
            sendMessage(col, s);
        }
        return true;
    }

    public static boolean sendMessage(CommandSender sender, String message, String permission) {
        if (!sender.hasPermission(permission)) {
            return false;
        }
        return sendMessage(sender, message);
    }

    public static boolean sendMessage(Collection<? extends CommandSender> col, String message, String permission) {
        for (CommandSender cs : col) {
            if (!cs.hasPermission(permission)) {
                continue;
            }
            sendMessage(cs, message);
        }
        return true;
    }

    public static boolean sendMessage(Collection<? extends CommandSender> col, Collection<String> messages, String permission) {
        for (CommandSender cs : col) {
            if (!cs.hasPermission(permission)) {
                continue;
            }
            sendMessage(cs, messages);
        }
        return true;
    }

    public static boolean sendMessage(final Collection<? extends CommandSender> col, final String message) {
        for (final CommandSender cs : col) {
            sendMessage(cs, message);
        }
        return true;
    }

    public static boolean sendMessage(final CommandSender sender, final String message) {
        sender.sendMessage(fixColor(message));
        return true;
    }

    public static String fixColor(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static List<String> fixColor(List<String> strings) {
        List<String> colors = new ArrayList<>();
        for (String s : strings) {
            colors.add(fixColor(s));
        }
        return colors;
    }

    public static String[] fixColor(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = fixColor(strings[i]);
        }
        return strings;
    }

    public static int calculate(String s) {
        int i;
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Number n = 1;
        try {
            n = (Number) engine.eval(s);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        i = n.intValue();
        return i;
    }

    public static Player getDamager(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (damager instanceof Player) {
            return ((Player) damager);
        } else if (damager instanceof Projectile) {
            Projectile p = (Projectile) damager;
            if (p instanceof Player) {
                return (Player) p.getShooter();
            }
        }
        return null;
    }

    public static void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean containsIgnoreCase(String[] array, String element) {
        for (String s : array) {
            if (s.equalsIgnoreCase(element)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAlphaNumeric(String s) {
        return s.matches("^[a-zA-Z0-9_]*$");
    }

    public static boolean isFloat(String string) {
        return Pattern.matches("([0-9]*)\\.([0-9]*)", string);
    }

    public static boolean isInteger(String string) {
        return (Pattern.matches("-?[0-9]+", string.subSequence(0, string.length())));
    }

    public static boolean getBoolean(String s) {
        if ("true".equalsIgnoreCase(s) || "tak".equalsIgnoreCase(s) || "t".equalsIgnoreCase(s) || "1".equalsIgnoreCase(s) || "yes".equalsIgnoreCase(s) || "y".equalsIgnoreCase(s)) {
            return true;
        }
        if ("false".equalsIgnoreCase(s) || "nie".equalsIgnoreCase(s) || "n".equalsIgnoreCase(s) || "0".equalsIgnoreCase(s) || "no".equalsIgnoreCase(s)) {
            return false;
        }
        return false;
    }

    public static Location getLocation(String world, int x, int y, int z) {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

    public static Location getLocation(String world, double x, double y, double z, float yaw, float pitch) {
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

}
