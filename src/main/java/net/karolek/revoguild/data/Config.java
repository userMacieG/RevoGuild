package net.karolek.revoguild.data;

import net.karolek.revoguild.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Config extends Configuration {

    public static boolean ENABLED = false;
    public static boolean UPDATER = true;

    public static String STORE_TYPE = "sqlite";
    public static String STORE_TABLE$PREFIX = "ks_";
    public static String STORE_MYSQL_HOST = "localhost";
    public static int STORE_MYSQL_PORT = 3306;
    public static String STORE_MYSQL_USERNAME = "root";
    public static String STORE_MYSQL_PASSWORD = "";
    public static String STORE_MYSQL_BASE$NAME = "minecraft";
    public static String STORE_SQLITE_BASE$NAME = "minecraft.db";

    public static String NAME$TAG_FORMAT = "&8[{COLOR}{TAG}&8] {COLOR}";
    public static String NAME$TAG_COLOR_NO$GUILD = "&7";
    public static String NAME$TAG_COLOR_FRIEND = "&a";
    public static String NAME$TAG_COLOR_FRIEND$PVP = "&9";
    public static String NAME$TAG_COLOR_ENEMY = "&c";
    public static String NAME$TAG_COLOR_ALLIANCE = "&6";

    public static int NAME_LENGHT$MIN = 2;
    public static int NAME_LENGHT$MAX = 22;
    public static String NAME_REGEX = "[a-zA-Z]+";

    public static int TAG_LENGHT$MIN = 2;
    public static int TAG_LENGHT$MAX = 5;
    public static String TAG_REGEX = "[a-zA-Z]+";

    public static String CHAT_FORMAT_TAG = "&8[&2{TAG}&8]&r ";
    public static String CHAT_FORMAT_RANK = "&8[&2{RANK}&8]&r ";

    public static boolean CHAT_LOCAL_ENABLED = false;
    public static String CHAT_LOCAL_CHAR = "!";
    public static String CHAT_LOCAL_FORMAT_GUILD = "&2{PLAYER} -> Gildia: &a{MESSAGE}";
    public static String CHAT_LOCAL_FORMAT_ALLIANCE = "&6{TAG} {PLAYER} -> Sojusz: &e{MESSAGE}";

    public static int RANKING_START$POINTS = 1000;
    public static String RANKING_DEATH_TAG = "&7[&2{TAG}&7]&r ";
    public static String RANKING_DEATH_MESSAGE = "&2Gracz {PGUILD}&7{PLAYER} ({LOSEPOINTS}) &2zostal zabity przez {KGUILD}&7{KILLER} ({WINPOINTS})&2!";

    public static boolean EFFECTS_ENABLED = false;
    public static double EFFECTS_CHANCE = 50.00;
    public static int EFFECTS_LEVEL_MIN = 0;
    public static int EFFECTS_LEVEL_MAX = 3;
    public static int EFFECTS_TIME_MIN = 10;
    public static int EFFECTS_TIME_MAX = 3600;
    public static int EFFECTS_TIME_INTERVAL = 5;
    public static List<String> EFFECTS_TYPES = Arrays.asList("FAST_DIGGING", "REGENERATION", "INCREASE_DAMAGE");

    public static boolean ESCAPE_ENABLED = true;
    public static int ESCAPE_TIME = 10;
    public static int ESCAPE_LOSE$POINTS = 50;
    public static String ESCAPE_BROADCAST = "&2Gracz {PGUILD}&7{PLAYER} &7(-50) &2wylogowal sie podczas walki!";

    public static boolean ESCAPE_DISABLED$COMMANDS_ENABLED = false;
    public static List<String> ESCAPE_DISABLED$COMMANDS_COMMANDS = Arrays.asList("g dom", "dom", "spawn");
    public static boolean ESCAPE_DISABLED$COMMANDS_NOTIFY_ENABLED = false;
    public static String ESCAPE_DISABLED$COMMANDS_NOTIFY_MESSAGE = "&4Blad: &cNie mozesz uzywac tej komendy podczas walki!";

    public static String ALGORITHM_RANKING_WIN = "(300 + (({KILLER_POINTS} - {PLAYER_POINTS}) * (-0.2)))";
    public static String ALGORITHM_RANKING_LOSE = "Math.abs({WIN_POINTS} / 2)";
    public static String ALGORITHM_GUILD$POINTS = "{MEMBERS_POINTS} - ({MEMBERS_NUM} * 1000)";
    public static String ALGORITHM_ENLARGE = "({CUBOID_SIZE} - 24) / 5 + 1";
    public static String ALGORITHM_JOIN = "Math.max(1, {MEMBERS_NUM} / 2)";

    public static boolean MOVEMENT_NOTIFY_ENABLED = true;
    public static boolean MOVEMENT_NOTIFY_INTRUDER_ENABLED = true;

    public static int ALLIANCES_MAX = 3;

    public static boolean ACTIONS_BLOCK_BREAK = false;
    public static boolean ACTIONS_BLOCK_PLACE = false;
    public static boolean ACTIONS_BUCKET_EMPTY = false;
    public static boolean ACTIONS_BUCKET_FILL = false;
    public static List<Integer> ACTIONS_PROTECTED$ID = Collections.singletonList(54);

    public static boolean UPTAKE_ENABLED = false;
    public static int UPTAKE_LIVES_START = 3;
    public static int UPTAKE_LIVES_MAX = 6;
    public static int UPTAKE_LIVES_TIME = 24;

    public static boolean TREASURE_ENABLED = false;
    public static boolean TREASURE_OPEN$ONLY$ON$GUILD = true;
    public static String TREASURE_TITLE = "Skarbiec gildii:";
    public static int TREASURE_ROWS = 6;

    public static boolean TNT_OFF_ENABLED = false;
    public static List<Integer> TNT_OFF_HOURS = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);

    public static boolean TNT_PROTECTION_ENABLED = false;
    public static int TNT_PROTECTION_TIME = 24;

    public static boolean TNT_CANT$BUILD_ENABLED = false;
    public static int TNT_CANT$BUILD_TIME = 90;

    public static boolean TNT_DURABILITY_ENABLED = false;
    public static List<String> TNT_DURABILITY_BLOCKS = Arrays.asList("OBSIDIAN 73.6", "WATER 10.0");

    public static String ITEMS_CREATE_NORMAL = "1:0-10;";
    public static String ITEMS_JOIN_NORMAL = "1:0-10;";
    public static String ITEMS_LEADER_NORMAL = "1:0-10;";
    public static String ITEMS_OWNER_NORMAL = "1:0-10;";
    public static String ITEMS_ENLARGE_NORMAL = "1:0-10;";
    public static String ITEMS_PROLONG_NORMAL = "1:0-10;";
    public static String ITEMS_EFFECT_NORMAL = "1:0-10;";
    public static String ITEMS_ALLIANCE_NORMAL = "1:0-10;";

    public static String ITEMS_CREATE_VIP = "1:0-5;";
    public static String ITEMS_JOIN_VIP = "1:0-5;";
    public static String ITEMS_LEADER_VIP = "1:0-5;";
    public static String ITEMS_OWNER_VIP = "1:0-5;";
    public static String ITEMS_ENLARGE_VIP = "1:0-5;";
    public static String ITEMS_PROLONG_VIP = "1:0-5;";
    public static String ITEMS_EFFECT_VIP = "1:0-10;";
    public static String ITEMS_ALLIANCE_VIP = "1:0-10;";

    public static String CUBOID_WORLD = "world";

    public static int CUBOID_SIZE_START = 24;
    public static int CUBOID_SIZE_MAX = 74;
    public static int CUBOID_SIZE_ADD = 1;
    public static int CUBOID_SIZE_BETWEEN = 50;

    public static boolean CUBOID_SPAWN_ENABLED = true;
    public static int CUBOID_SPAWN_DISTANCE = 400;

    public static boolean CUBOID_DISABLED$COMMANDS_ENABLED = true;
    public static List<String> CUBOID_DISABLED$COMMANDS_COMMANDS = Arrays.asList("g dom", "g efekt", "g skarbiec", "g ustawdom");
    public static boolean CUBOID_DISABLED$COMMANDS_NOTIFY_ENABLED = false;
    public static String CUBOID_DISABLED$COMMANDS_NOTIFY_MESSAGE = "&4Blad: &cNie mozesz uzywac tej komendy na terenie innej gildii!";

    public static int TIME_START = 3;
    public static int TIME_MAX = 14;
    public static int TIME_ADD = 7;
    public static int TIME_CHECK = 3;
    public static int TIME_TELEPORT = 10;

    public static boolean TABLIST_ENABLED = false;
    public static int TABLIST_REFRESH_INTERVAL = 10;

    public static String TABLIST_FORMAT_GTOP = "{TAG} &7[&8{POINTS}&7]";
    public static String TABLIST_FORMAT_PTOP = "{NAME}";
    public static String TABLIST_FORMAT_EMPTY = "brak";

    public static String TABLIST_HEADER = "&6&lRevo&7&lGUILD";
    public static String TABLIST_FOOTER = "&7Online: &6{ONLINE}&7/&6300";

    public Config(JavaPlugin plugin) {
        super(plugin, "config.yml", "config.");
    }

}
