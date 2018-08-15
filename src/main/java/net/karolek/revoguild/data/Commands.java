package net.karolek.revoguild.data;

import net.karolek.revoguild.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class Commands extends Configuration {

    // Gildie: Gracz
    public static boolean GUILD_USER_MAIN_ENABLED = true;
    public static String GUILD_USER_MAIN_NAME = "gildia";
    public static String GUILD_USER_MAIN_DESCRIPTION = "glowna komenda systemu gildii";
    public static String GUILD_USER_MAIN_USAGE = "/gildia <subkomenda>";
    public static String GUILD_USER_MAIN_PERMISSION = "revoguild.commands.guild";
    public static List<String> GUILD_USER_MAIN_ALIASES = Arrays.asList("g", "gildie", "guild");

    public static boolean GUILD_USER_ALLIANCE_ENABLED = true;
    public static String GUILD_USER_ALLIANCE_NAME = "usun";
    public static String GUILD_USER_ALLIANCE_DESCRIPTION = "usuwa gildie";
    public static String GUILD_USER_ALLIANCE_USAGE = "/g usun";
    public static String GUILD_USER_ALLIANCE_PERMISSION = "revoguild.commands.guild.delete";
    public static List<String> GUILD_USER_ALLIANCE_ALIASES = Arrays.asList("delete", "skasuj");

    public static boolean GUILD_USER_CREATE_ENABLED = true;
    public static String GUILD_USER_CREATE_NAME = "zaloz";
    public static String GUILD_USER_CREATE_DESCRIPTION = "tworzy nowa gildie";
    public static String GUILD_USER_CREATE_USAGE = "/g zaloz <tag> <nazwa>";
    public static String GUILD_USER_CREATE_PERMISSION = "revoguild.commands.guild.create";
    public static List<String> GUILD_USER_CREATE_ALIASES = Arrays.asList("create", "utworz", "nowa");

    public static boolean GUILD_USER_DELETE_ENABLED = true;
    public static String GUILD_USER_DELETE_NAME = "usun";
    public static String GUILD_USER_DELETE_DESCRIPTION = "usuwa gildie";
    public static String GUILD_USER_DELETE_USAGE = "/g usun";
    public static String GUILD_USER_DELETE_PERMISSION = "revoguild.commands.guild.delete";
    public static List<String> GUILD_USER_DELETE_ALIASES = Arrays.asList("delete", "skasuj");

    public static boolean GUILD_USER_EFFECT_ENABLED = true;
    public static String GUILD_USER_EFFECT_NAME = "efekt";
    public static String GUILD_USER_EFFECT_DESCRIPTION = "losowanie efektu dla gildii";
    public static String GUILD_USER_EFFECT_USAGE = "/g efekt";
    public static String GUILD_USER_EFFECT_PERMISSION = "revoguild.commands.guild.effect";
    public static List<String> GUILD_USER_EFFECT_ALIASES = Arrays.asList("effect", "buff");

    public static boolean GUILD_USER_ENLARGE_ENABLED = true;
    public static String GUILD_USER_ENLARGE_NAME = "powieksz";
    public static String GUILD_USER_ENLARGE_DESCRIPTION = "powiekszanie terenu gildii";
    public static String GUILD_USER_ENLARGE_USAGE = "/g powieksz";
    public static String GUILD_USER_ENLARGE_PERMISSION = "revoguild.commands.guild.enlarge";
    public static List<String> GUILD_USER_ENLARGE_ALIASES = Arrays.asList("enlarge", "resize");

    public static boolean GUILD_USER_HOME_ENABLED = true;
    public static String GUILD_USER_HOME_NAME = "dom";
    public static String GUILD_USER_HOME_DESCRIPTION = "teleportacja do domu gildii";
    public static String GUILD_USER_HOME_USAGE = "/g dom";
    public static String GUILD_USER_HOME_PERMISSION = "revoguild.commands.guild.home";
    public static List<String> GUILD_USER_HOME_ALIASES = Arrays.asList("home", "baza");

    public static boolean GUILD_USER_INFO_ENABLED = true;
    public static String GUILD_USER_INFO_NAME = "info";
    public static String GUILD_USER_INFO_DESCRIPTION = "informacje o gildii";
    public static String GUILD_USER_INFO_USAGE = "/g info <tag/nazwa>";
    public static String GUILD_USER_INFO_PERMISSION = "revoguild.commands.guild.info";
    public static List<String> GUILD_USER_INFO_ALIASES = Arrays.asList("informacje");

    public static boolean GUILD_USER_INVITE_ENABLED = true;
    public static String GUILD_USER_INVITE_NAME = "zapros";
    public static String GUILD_USER_INVITE_DESCRIPTION = "zapraszanie do gildii";
    public static String GUILD_USER_INVITE_USAGE = "/g zapros <gracz>";
    public static String GUILD_USER_INVITE_PERMISSION = "revoguild.commands.guild.invite";
    public static List<String> GUILD_USER_INVITE_ALIASES = Arrays.asList("invite", "dodaj");

    public static boolean GUILD_USER_ITEMS_ENABLED = true;
    public static String GUILD_USER_ITEMS_NAME = "itemy";
    public static String GUILD_USER_ITEMS_DESCRIPTION = "informacje o itemach na gildiie";
    public static String GUILD_USER_ITEMS_USAGE = "/g itemy";
    public static String GUILD_USER_ITEMS_PERMISSION = "revoguild.commands.guild.items";
    public static List<String> GUILD_USER_ITEMS_ALIASES = Arrays.asList("items", "przedmioty");

    public static boolean GUILD_USER_JOIN_ENABLED = true;
    public static String GUILD_USER_JOIN_NAME = "dolacz";
    public static String GUILD_USER_JOIN_DESCRIPTION = "dolaczanie do gildii";
    public static String GUILD_USER_JOIN_USAGE = "/g usun";
    public static String GUILD_USER_JOIN_PERMISSION = "revoguild.commands.guild.delete";
    public static List<String> GUILD_USER_JOIN_ALIASES = Arrays.asList("delete", "skasuj");

    public static boolean GUILD_USER_KICK_ENABLED = true;
    public static String GUILD_USER_KICK_NAME = "wyrzuc";
    public static String GUILD_USER_KICK_DESCRIPTION = "wyrzucanie czlonka gildii";
    public static String GUILD_USER_KICK_USAGE = "/g wyrzuc <gracz>";
    public static String GUILD_USER_KICK_PERMISSION = "revoguild.commands.guild.kick";
    public static List<String> GUILD_USER_KICK_ALIASES = Arrays.asList("kick");

    public static boolean GUILD_USER_LEADER_ENABLED = true;
    public static String GUILD_USER_LEADER_NAME = "lider";
    public static String GUILD_USER_LEADER_DESCRIPTION = "zmienianie lidera gildii";
    public static String GUILD_USER_LEADER_USAGE = "/g lider <gracz>";
    public static String GUILD_USER_LEADER_PERMISSION = "revoguild.commands.guild.leader";
    public static List<String> GUILD_USER_LEADER_ALIASES = Arrays.asList("leader");

    public static boolean GUILD_USER_LEAVE_ENABLED = true;
    public static String GUILD_USER_LEAVE_NAME = "opusc";
    public static String GUILD_USER_LEAVE_DESCRIPTION = "opuszczanie gildii";
    public static String GUILD_USER_LEAVE_USAGE = "/g opusc";
    public static String GUILD_USER_LEAVE_PERMISSION = "revoguild.commands.guild.leave";
    public static List<String> GUILD_USER_LEAVE_ALIASES = Arrays.asList("leave");

    public static boolean GUILD_USER_TOP_ENABLED = true;
    public static String GUILD_USER_TOP_NAME = "top";
    public static String GUILD_USER_TOP_DESCRIPTION = "top 10 wszystkich gildii";
    public static String GUILD_USER_TOP_USAGE = "/g top";
    public static String GUILD_USER_TOP_PERMISSION = "revoguild.commands.guild.top";
    public static List<String> GUILD_USER_TOP_ALIASES = Arrays.asList("list", "lista", "top10", "topka");

    public static boolean GUILD_USER_OWNER_ENABLED = true;
    public static String GUILD_USER_OWNER_NAME = "zalozyciel";
    public static String GUILD_USER_OWNER_DESCRIPTION = "zmienianie zalozyciela gildii";
    public static String GUILD_USER_OWNER_USAGE = "/g zalozyciel <gracz>";
    public static String GUILD_USER_OWNER_PERMISSION = "revoguild.commands.guild.owner";
    public static List<String> GUILD_USER_OWNER_ALIASES = Arrays.asList("owner");

    public static boolean GUILD_USER_PROLONG_ENABLED = true;
    public static String GUILD_USER_PROLONG_NAME = "przedluz";
    public static String GUILD_USER_PROLONG_DESCRIPTION = "przedluzanie waznosci gildii";
    public static String GUILD_USER_PROLONG_USAGE = "/g przedluz";
    public static String GUILD_USER_PROLONG_PERMISSION = "revoguild.commands.guild.prolong";
    public static List<String> GUILD_USER_PROLONG_ALIASES = Arrays.asList("prolong", "addtime");

    public static boolean GUILD_USER_PVP_ENABLED = true;
    public static String GUILD_USER_PVP_NAME = "pvp";
    public static String GUILD_USER_PVP_DESCRIPTION = "zmiana statusu pvp w gildii";
    public static String GUILD_USER_PVP_USAGE = "/g pvp";
    public static String GUILD_USER_PVP_PERMISSION = "revoguild.commands.guild.pvp";
    public static List<String> GUILD_USER_PVP_ALIASES = Arrays.asList("friendlyfire", "ff");

    public static boolean GUILD_USER_SET$HOME_ENABLED = true;
    public static String GUILD_USER_SET$HOME_NAME = "ustawdom";
    public static String GUILD_USER_SET$HOME_DESCRIPTION = "ustawianie domu gildii";
    public static String GUILD_USER_SET$HOME_USAGE = "/g ustawdom";
    public static String GUILD_USER_SET$HOME_PERMISSION = "revoguild.commands.guild.sethome";
    public static List<String> GUILD_USER_SET$HOME_ALIASES = Arrays.asList("sethome", "ustawbaze");

    public static boolean GUILD_USER_TREASURE_ENABLED = true;
    public static String GUILD_USER_TREASURE_NAME = "skarbiec";
    public static String GUILD_USER_TREASURE_DESCRIPTION = "zarzadzanie skarbcem gildii";
    public static String GUILD_USER_TREASURE_USAGE = "/g skarbiec [dodaj <gracz>/usun <gracz>/lista]";
    public static String GUILD_USER_TREASURE_PERMISSION = "revoguild.commands.guild.treasure";
    public static List<String> GUILD_USER_TREASURE_ALIASES = Arrays.asList("treasure", "skrzynia");

    // Gildie: Admin
    public static boolean GUILD_ADMIN_MAIN_ENABLED = true;
    public static String GUILD_ADMIN_MAIN_NAME = "ga";
    public static String GUILD_ADMIN_MAIN_DESCRIPTION = "glowna komenda administratora systemu gildii";
    public static String GUILD_ADMIN_MAIN_USAGE = "/ga <subkomenda>";
    public static String GUILD_ADMIN_MAIN_PERMISSION = "revoguild.commands.admin.guild";
    public static List<String> GUILD_ADMIN_MAIN_ALIASES = Arrays.asList("gildieadmin", "guildadmin", "gildiaadmin");

    public static boolean GUILD_ADMIN_BAN_ENABLED = true;
    public static String GUILD_ADMIN_BAN_NAME = "ban";
    public static String GUILD_ADMIN_BAN_DESCRIPTION = "banowanie wybranej gildii";
    public static String GUILD_ADMIN_BAN_USAGE = "/ga ban <tag/nazwa> <czas> <powod>";
    public static String GUILD_ADMIN_BAN_PERMISSION = "revoguild.commands.admin.guild.ban";
    public static List<String> GUILD_ADMIN_BAN_ALIASES = Arrays.asList("zbanuj");

    public static boolean GUILD_ADMIN_DELETE_ENABLED = true;
    public static String GUILD_ADMIN_DELETE_NAME = "usun";
    public static String GUILD_ADMIN_DELETE_DESCRIPTION = "usuwanie wybranej gildii";
    public static String GUILD_ADMIN_DELETE_USAGE = "/ga usun <tag/nazwa>";
    public static String GUILD_ADMIN_DELETE_PERMISSION = "revoguild.commands.admin.guild.delete";
    public static List<String> GUILD_ADMIN_DELETE_ALIASES = Arrays.asList("delete");

    public static boolean GUILD_ADMIN_KICK_ENABLED = true;
    public static String GUILD_ADMIN_KICK_NAME = "wyrzuc";
    public static String GUILD_ADMIN_KICK_DESCRIPTION = "wyrzuca czlonka wybranej gildii";
    public static String GUILD_ADMIN_KICK_USAGE = "/ga wyrzuc <tag/nazwa> <gracz>";
    public static String GUILD_ADMIN_KICK_PERMISSION = "revoguild.commands.admin.guild.kick";
    public static List<String> GUILD_ADMIN_KICK_ALIASES = Arrays.asList("kick");

    public static boolean GUILD_ADMIN_RELOAD_ENABLED = true;
    public static String GUILD_ADMIN_RELOAD_NAME = "reload";
    public static String GUILD_ADMIN_RELOAD_DESCRIPTION = "przeladowanie plikow konfiguracyjnych";
    public static String GUILD_ADMIN_RELOAD_USAGE = "/ga reload";
    public static String GUILD_ADMIN_RELOAD_PERMISSION = "revoguild.commands.admin.guild.reload";
    public static List<String> GUILD_ADMIN_RELOAD_ALIASES = Arrays.asList("przeladuj");

    public static boolean GUILD_ADMIN_SET_ENABLED = true;
    public static String GUILD_ADMIN_SET_NAME = "set";
    public static String GUILD_ADMIN_SET_DESCRIPTION = "zmiana wartosci wybranej gildii";
    public static String GUILD_ADMIN_SET_USAGE = "/ga set <tag/nazwa> <leader|owner|lives|pvp|size> <wartosc>";
    public static String GUILD_ADMIN_SET_PERMISSION = "revoguild.commands.admin.guild.set";
    public static List<String> GUILD_ADMIN_SET_ALIASES = Arrays.asList("ustawrozmiar");

    public static boolean GUILD_ADMIN_SET$CUBOID_ENABLED = true;
    public static String GUILD_ADMIN_SET$CUBOID_NAME = "setcuboid";
    public static String GUILD_ADMIN_SET$CUBOID_DESCRIPTION = "zmiana lokacji cuboida wybranej gildii";
    public static String GUILD_ADMIN_SET$CUBOID_USAGE = "/ga setcuboid <tag/nazwa>";
    public static String GUILD_ADMIN_SET$CUBOID_PERMISSION = "revoguild.commands.admin.guild.setcuboid";
    public static List<String> GUILD_ADMIN_SET$CUBOID_ALIASES = Arrays.asList("setcub", "ustawteren");

    public static boolean GUILD_ADMIN_TELEPORT_ENABLED = true;
    public static String GUILD_ADMIN_TELEPORT_NAME = "teleport";
    public static String GUILD_ADMIN_TELEPORT_DESCRIPTION = "teleport do centrum wybranej gildii";
    public static String GUILD_ADMIN_TELEPORT_USAGE = "/ga teleport <tag/nazwa>";
    public static String GUILD_ADMIN_TELEPORT_PERMISSION = "revoguild.commands.admin.guild.teleport";
    public static List<String> GUILD_ADMIN_TELEPORT_ALIASES = Arrays.asList("tp");

    public static boolean GUILD_ADMIN_UNBAN_ENABLED = true;
    public static String GUILD_ADMIN_UNBAN_NAME = "unban";
    public static String GUILD_ADMIN_UNBAN_DESCRIPTION = "odbanowanie wybranej gildii";
    public static String GUILD_ADMIN_UNBAN_USAGE = "/ga unban <tag/nazwa>";
    public static String GUILD_ADMIN_UNBAN_PERMISSION = "revoguild.commands.admin.guild.unban";
    public static List<String> GUILD_ADMIN_UNBAN_ALIASES = Arrays.asList("odbanuj");

    // Ranking: Gracz
    public static boolean RANKING_USER_ENABLED = true;
    public static String RANKING_USER_NAME = "ranking";
    public static String RANKING_USER_DESCRIPTION = "informacje o rankingu wybranego gracza";
    public static String RANKING_USER_USAGE = "/ranking [gracz]";
    public static String RANKING_USER_PERMISSION = "revoguild.commands.ranking";
    public static List<String> RANKING_USER_ALIASES = Arrays.asList("elo", "gracz", "points");

    // Ranking: Admin
    public static boolean RANKING_ADMIN_MAIN_ENABLED = true;
    public static String RANKING_ADMIN_MAIN_NAME = "ra";
    public static String RANKING_ADMIN_MAIN_DESCRIPTION = "glowna komenda administratora systemu rankingu";
    public static String RANKING_ADMIN_MAIN_USAGE = "/ra <subkomenda>";
    public static String RANKING_ADMIN_MAIN_PERMISSION = "revoguild.commands.admin.ranking";
    public static List<String> RANKING_ADMIN_MAIN_ALIASES = Arrays.asList("rankingadmin");

    public static boolean RANKING_ADMIN_SET_ENABLED = true;
    public static String RANKING_ADMIN_SET_NAME = "set";
    public static String RANKING_ADMIN_SET_DESCRIPTION = "ustawianie wartosci wybranego gracza";
    public static String RANKING_ADMIN_SET_USAGE = "/ra set <gracz> <kills|deaths|points> <wartosc>";
    public static String RANKING_ADMIN_SET_PERMISSION = "revoguild.commands.admin.ranking.set";
    public static List<String> RANKING_ADMIN_SET_ALIASES = Arrays.asList("ustaw");

    public static boolean RANKING_ADMIN_RESET_ENABLED = true;
    public static String RANKING_ADMIN_RESET_NAME = "reset";
    public static String RANKING_ADMIN_RESET_DESCRIPTION = "reset rankingu wybranego gracza";
    public static String RANKING_ADMIN_RESET_USAGE = "/ra reset <gracz>";
    public static String RANKING_ADMIN_RESET_PERMISSION = "revoguild.commands.admin.ranking.reset";
    public static List<String> RANKING_ADMIN_RESET_ALIASES = Arrays.asList("resetuj");

    // Inne
    public static boolean COMBAT_ENABLED = true;
    public static String COMBAT_NAME = "combat";
    public static String COMBAT_DESCRIPTION = "sprawdzanie czasu walki";
    public static String COMBAT_USAGE = "/combat";
    public static String COMBAT_PERMISSION = "revoguild.commands.combat";
    public static List<String> COMBAT_ALIASES = Arrays.asList("ct", "tag", "walka", "pvp", "logout");

    public static boolean TOP_ENABLED = true;
    public static String TOP_NAME = "top";
    public static String TOP_DESCRIPTION = "top 10 wszystkich graczy";
    public static String TOP_USAGE = "/top";
    public static String TOP_PERMISSION = "revoguild.commands.top";
    public static List<String> TOP_ALIASES = Arrays.asList("topka", "top10");

    public Commands(JavaPlugin plugin) {
        super(plugin, "commands.yml", "commands.");
    }
}
