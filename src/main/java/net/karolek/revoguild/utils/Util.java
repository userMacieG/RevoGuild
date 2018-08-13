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

    private static boolean sendMessage(CommandSender sender, String message, String permission) {
        if (permission != null) {
            if (!sender.hasPermission(permission)) {
                sender.sendMessage(fixColor(message));
            }
        }
        sender.sendMessage(fixColor(message));
        return true;
    }

    public static boolean sendMessage(CommandSender sender, String message) {
        return sendMessage(sender, message, null);
    }

    public static boolean sendMessage(Collection<? extends CommandSender> senders, String message, String permission) {
        sendMessage(Bukkit.getConsoleSender(), message, permission);
        for (CommandSender sender : senders) {
            sendMessage(sender, message, permission);
        }
        return true;
    }

    public static boolean sendMessage(Collection<? extends CommandSender> senders, String message) {
        for (CommandSender sender : senders) {
            sendMessage(sender, message, null);
        }
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

    public static String[] fixColor(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = fixColor(strings[i]);
        }
        return strings;
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

    public static String secondsToString(int seconds) {
        if (seconds == 0) {
            return "nigdy";
        }
        LinkedHashMap<Integer, String> values = new LinkedHashMap<>(6);
        values.put(60 * 60 * 24 * 30 * 12, "y");
        values.put(60 * 60 * 24 * 30, "msc");
        values.put(60 * 60 * 24, "d");
        values.put(60 * 60, "h");
        values.put(60, "min");
        values.put(1, "s");
        String[] v = new String[6];
        int i = 0;
        for (Entry<Integer, String> e : values.entrySet()) {
            int iDiv = seconds / e.getKey();
            if (iDiv >= 1) {
                int x = (int) Math.floor(iDiv);
                v[i] = x + e.getValue();
                seconds -= x * e.getKey();
            }
            ++i;
        }
        return StringUtils.join(v, " ");
    }

    public static String getDate(long time) {
        return new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(new Date(time));
    }

    public static String getTime(long time) {
        return new SimpleDateFormat("HH:mm:ss").format(new Date(time));
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

    public static long parseDateDiff(String time, boolean future) {
        try {
            Pattern timePattern = Pattern.compile("(?:([0-9]+)\\s*y[a-z]*[,\\s]*)?(?:([0-9]+)\\s*mo[a-z]*[,\\s]*)?(?:([0-9]+)\\s*w[a-z]*[,\\s]*)?(?:([0-9]+)\\s*d[a-z]*[,\\s]*)?(?:([0-9]+)\\s*h[a-z]*[,\\s]*)?(?:([0-9]+)\\s*m[a-z]*[,\\s]*)?(?:([0-9]+)\\s*(?:s[a-z]*)?)?", Pattern.CASE_INSENSITIVE);
            Matcher m = timePattern.matcher(time);
            int years = 0;
            int months = 0;
            int weeks = 0;
            int days = 0;
            int hours = 0;
            int minutes = 0;
            int seconds = 0;
            boolean found = false;
            while (m.find()) {
                if ((m.group() != null) && (!m.group().isEmpty())) {
                    for (int i = 0; i < m.groupCount(); i++) {
                        if ((m.group(i) != null) && (!m.group(i).isEmpty())) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        if ((m.group(1) != null) && (!m.group(1).isEmpty())) {
                            years = Integer.parseInt(m.group(1));
                        }
                        if ((m.group(2) != null) && (!m.group(2).isEmpty())) {
                            months = Integer.parseInt(m.group(2));
                        }
                        if ((m.group(3) != null) && (!m.group(3).isEmpty())) {
                            weeks = Integer.parseInt(m.group(3));
                        }
                        if ((m.group(4) != null) && (!m.group(4).isEmpty())) {
                            days = Integer.parseInt(m.group(4));
                        }
                        if ((m.group(5) != null) && (!m.group(5).isEmpty())) {
                            hours = Integer.parseInt(m.group(5));
                        }
                        if ((m.group(6) != null) && (!m.group(6).isEmpty())) {
                            minutes = Integer.parseInt(m.group(6));
                        }
                        if ((m.group(7) == null) || (m.group(7).isEmpty())) {
                            break;
                        }
                        seconds = Integer.parseInt(m.group(7));
                        break;
                    }
                }
            }
            if (!found) {
                return -1L;
            }
            Calendar c = new GregorianCalendar();
            if (years > 0) {
                c.add(Calendar.YEAR, years * (future ? 1 : -1));
            }
            if (months > 0) {
                c.add(Calendar.MONTH, months * (future ? 1 : -1));
            }
            if (weeks > 0) {
                c.add(Calendar.WEEK_OF_YEAR, weeks * (future ? 1 : -1));
            }
            if (days > 0) {
                c.add(Calendar.DATE, days * (future ? 1 : -1));
            }
            if (hours > 0) {
                c.add(Calendar.HOUR_OF_DAY, hours * (future ? 1 : -1));
            }
            if (minutes > 0) {
                c.add(Calendar.MINUTE, minutes * (future ? 1 : -1));
            }
            if (seconds > 0) {
                c.add(Calendar.SECOND, seconds * (future ? 1 : -1));
            }
            Calendar max = new GregorianCalendar();
            max.add(Calendar.YEAR, 10);
            if (c.after(max)) {
                return max.getTimeInMillis();
            }
            return c.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1L;
    }

}
