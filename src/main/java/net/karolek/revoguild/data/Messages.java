package net.karolek.revoguild.data;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.objects.user.User;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.configuration.Configuration;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.managers.user.UserManager;
import net.karolek.revoguild.utils.DateUtil;
import net.karolek.revoguild.utils.Util;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Messages extends Configuration {

    public static List<String> GUILD_USER$HELP = Arrays.asList("&7&m----------&r &6RevoGUILD: komendy uzytkownika &7&m----------&r", "  &6/g sojusz <tag/nazwa> &7- zarzadzanie sojuszami gildii", "  &6/g efekt &7- losowanie efektu dla gildii", "  &6/g zaloz <tag> <nazwa> &7- tworzenie gildii", "  &6/g usun &7- usuwanie gildii", "  &6/g powieksz &7- powiekszanie gildii", "  &6/g dom &7- teleport do domu gildii", "  &6/g info <tag/nazwa> &7- podstawowe informacje o gildii", "  &6/g zapros <gracz> &7- zapraszanie graczy do gildii", "  &6/g dolacz <tag/nazwa> &7- dolaczanie do gildii", "  &6/g wyrzuc <gracz> &7- wyrzucanie graczy z gildii", "  &6/g lider <gracz> &7- zmiana lidera gildii", "  &6/g opusc &7- opuszczanie gildii", "  &6/g lista &7- lista wszystkich gildii", "  &6/g zalozyciel <gracz> &7- zmiana zalozyciela gildii", "  &6/g przedluz &7- przedluzanie waznosci gildii", "  &6/g pvp &7- zmiana statusu pvp w gildii", "  &6/g ustawdom &7- ustawianie domu gildii", "  &6/g skarbiec [dodaj <gracz> | usun <gracz> | lista] &7- zarzadzanie skarbcem gildii", "&7&m----------------------------------------------------");
    public static List<String> GUILD_ADMIN$HELP = Arrays.asList("&7&m-------&r &6RevoGUILD: komendy administratora gildii &7&m-------&r", "  &6/ga tp <tag/nazwa> &7- teleport do gildii", "  &6/ga usun <tag/nazwa> &7- usuwanie gildii", "  &6/ga ban <tag/nazwa> <czas> <powod> &7- banowanie gildii", "  &6/ga unban <tag/nazwa> &7- odbanowywanie gildii", "  &6/ga set <tag/nazwa> <leader|owner|lives|pvp|size> <wartosc> &7- zmiana wartosci pola wybranej gildii", "  &6/ga setcuboid <tag/nazwa> &7- zmiana cuboida gildii", "  &6/ga wyrzuc <tag/nazwa> <gracz> &7- wyrzucanie gracza z gildii", "  &6/ga reload &7- przeladowanie plikow konfiguracyjnych", "&7&m----------------------------------------------------");

    public static List<String> GUILD_USER$ITEMS$HELP = Arrays.asList("&6Przedmioty do akcji w gildii:", " &8&m-- &6zalozenie gildii: ", "&7{CREATE}", " &8&m-- &6dolaczenie do gildii: ", "&7{JOIN}", " &8&m-- &6zmiana lidera fildii: ", "&7{LEADER}", " &8&m-- &6zmiana zalozyciela gildii: ", "&7{OWNER}", " &8&m-- &6powiekszenie terenu gildii: ", "&7{ENLARGE}", " &8&m-- &6przedluzanie waznosci gildii: ", "&7{PROLONG}", " &8&m-- &6efekt dla gildii: ", "&7{EFFECT}", " &8&m-- &6sojusz gildii: ", "&7{ALLIANCE}");

    public static List<String> RANKING$ADMIN$HELP = Arrays.asList("&7&m-------&r &6RevoGUILD: komendy administratora rankingu &7&m-------&r", "  &6/ra reset <gracz> &7- reset rankingu gracza", "  &6/ra set <gracz> <kills|deaths|points> <warotsc> &7- ustawianie wartosci gracza", "&7&m----------------------------------------------------");

    public static String COMBAT$INFO = "&6Mozesz sie wylogowac za &7{TIME}s&6!";

    public static String COMMANDS_NO$ENOUGH$ARGS = "&6Prawidlowe uzycie: &7{USAGE}&6!";
    public static String COMMANDS_NO$PERMISSIONS = "&cNie posiadasz dostepu do tej komendy! ({PERM})";

    public static String ERROR_TAG$INCORRECT$LENGTH = "&4Blad: &cTag musi miec od {TAGMIN} do {TAGMAX} znakow!";
    public static String ERROR_TAG$INCORRECT$CHARACTERS = "&4Blad: &cTag musi byc alfanumerczny ({REGEX})";
    public static String ERROR_NAME$INCORRECT$LENGTH = "&4Blad: &cNazwa musi miec od {NAMEMIN} do {NAMEMAX} znakow!";
    public static String ERROR_NAME$INCORRECT$CHARACTERS = "&4Blad: &cNazwa musi byc alfanumerczna ({REGEX})";
    public static String ERROR_HAVE$GUILD = "&4Blad: &cMasz juz gildie!";
    public static String ERROR_BAD$INTEGER = "&4Blad: &cPodana wartosc nie jest liczba!";
    public static String ERROR_GUILD_ALREADY$EXISTS = "&4Blad: &cIstnieje juz taka gildia!";
    public static String ERROR_GUILD_NEARBY = "&4Blad: &cW poblizu znajduje sie gildia lub spawn!";
    public static String ERROR_DONT$HAVE_ITEMS = "&4Blad: &cNie posiadasz wystarczajacej ilosci przedmiotow!";
    public static String ERROR_DONT$HAVE_GUILD = "&4Blad: &cNie posiadasz gildii!";
    public static String ERROR_DONT$HAVE_INVITE = "&4Blad: &cnie posiadasz zaproszenia do gildii!";
    public static String ERROR_DONT$HAVE_LUCKY$TO$EFFECT = "&4Blad: &cEfekt nie zostal wylosowany! Nie masz szczescia i straciles itemy! :(";
    public static String ERROR_YOU$ARENT$OWNER = "&4Blad: &cNie jestes zalozycielem!";
    public static String ERROR_YOU$ARENT$LEADER = "&4Blad: &cNie jestes liderem!";
    public static String ERROR_PLAYER$IS_MEMBER = "&4Blad: &cGracz jest czlonkiem Twojej gildii!";
    public static String ERROR_PLAYER$ISNT_MEMBER = "&4Blad: &cGracz nie jest czlonkiem Twojej gildii!";
    public static String ERROR_OWNER$CANT$LEAVE$GUILD = "&4Blad: &cZalozyciel nie moze opuscic gildii!";
    public static String ERROR_MAX$SIZE = "&4Blad: &cTwoja gildia ma juz maksymalny rozmiar!";
    public static String ERROR_NOT$YOUR$GUILD = "&4Blad: &cTo nie Twoja gildia!";
    public static String ERROR_EXPLODE$TNT = "&4Blad: &cPrzed chwila wybuchlo TNT! Nie mozesz budowac przez 60 sekund!";
    public static String ERROR_PLAYER$IS_TREASURE$USER = "&4Blad: &cGracz jest juz uzytkownikiem skarbca gildii!";
    public static String ERROR_PLAYER$ISNT_TREASURE$USER = "&4Blad: Gracz nie jest uzytkownikiem skarbca gildii!";
    public static String ERROR_OWNER$NOT$ONLINE = "&4Blad: &cZalozyciel gildii nie jest online!";
    public static String ERROR_CANT_KICK$LEADER$OR$OWNER = "&4Blad: &cNie mozesz wyrzucic lidera i zalozyciela gildii!";
    public static String ERROR_CANT_SET$HOME$OUTSIDE$CUBOID = "&4Blad: &cDom gildii musi byc na jej terenie!";
    public static String ERROR_CANT_TAKE$LIFE = "&4Blad: &cNie mozna teraz zabrac zycia gildii! Musi uplynac minimum 24h od ostatniej takiej akcji!";
    public static String ERROR_CANT_OPEN$TREASURE = "&4Blad: &cNie jestes uprawniony do otwierania skarbca gildii!";
    public static String ERROR_CANT_ATTACK$PLAYER = "&4Blad: &cNie mozesz atakowac tego gracza!";
    public static String ERROR_CANT_PROLONG = "&4Blad: &cWaznosc gildii nie moze byc wieksza niz 14 dni!";
    public static String ERROR_CANT_USE = "&4Blad: &cNie mozesz tego uzywac!";
    public static String ERROR_CANT_OPEN$TREASURE$OUTSIDE$CUBOID = "&4Blad: &cSkarbiec gildii mozna otwierac tylko na terenie gildii!";
    public static String ERROR_CANT_SET$CUBOID = "&4Blad: &cNie mozna przeniesc cuboida gildii poniewaz w poblizu znajduje sie inna gildia/spawn!";
    public static String ERROR_CANT$FIND_USER = "&4Blad: Uzytkownik nie istnieje w bazie danych!";
    public static String ERROR_CANT$FIND_PLAYER = "&4Blad: &cGracz jest offline!";
    public static String ERROR_CANT$FIND_GUILD = "&4Blad: &cTaka gildia nie istnieje!";
    public static String ERROR_MUST$WAIT = "&4Blad: Musisz poczekac 5 minut przed kolejnym losowaniem!";
    public static String ERROR_ALLIANCES$MAX = "&4Blad: &cTwoja gildia ma juz maksymalna ilosc sojuszy!";
    public static String ERROR_GUILD_HAVE$BAN = "&4Blad: &cGildia ma juz bana!";
    public static String ERROR_GUILD_DONT$HAVE$BAN = "&4Blad: &cGildia nie ma bana!";
    public static String ERROR_NO$EFFECTS$TO$ROLL = "&4Blad: &cW puli nie ma efektow do wylosowania!";
    public static String ERROR_GUILD_IS$FULL = "&4Blad: &cTwoja gildia ma juz maksymalna ilosc czlonkow! (30 osob)";

    public static String INFO_CONFIRM$DELETE = "&6Potwierdz usuniecie gildii: &7/g usun&6!";
    public static String INFO_INVITE_SEND = "&6Zaproszenie zostalo wyslane!";
    public static String INFO_INVITE_BACK = "&6Zaproszenie zostalo cofniete!";
    public static String INFO_INVITE_CANCEL = "&6Zaproszenie do gildii &7[{TAG}] {NAME}&6 zostalo cofniete!";
    public static String INFO_INVITE_NEW = "&6Zotales zaproszony do gildii &7[{TAG}] {NAME}&6! Zaakceptuj zaproszenie: &7/g dolacz {TAG}&6!";
    public static String INFO_JOINED = "&6Dolaczyles do gildii!";
    public static String INFO_CHANGED_LEADER = "&6Lider zostal zmieniony!";
    public static String INFO_CHANGED_OWNER = "&6Zalozyciel zostal zmieniony!";
    public static String INFO_IS$NOW_LEADER = "&6Awansowales na lidera gildii!";
    public static String INFO_IS$NOW_OWNER = "&6Awansowales na zalozyciela gildii!";
    public static String INFO_RESIZED = "&6Powiekszono!";
    public static String INFO_PVP_ON = "&6PVP w gildii zostalo wlaczone!";
    public static String INFO_PVP_OFF = "&6PVP w gildii zostalo wylaczone!";
    public static String INFO_SET_HOME = "&6Dom zostal ustawiony!";

    public static String INFO_GUILD_MOVE_IN = "&aWkroczyles na teren swojej gildii &7[{TAG}] {NAME}&a!";
    public static String INFO_GUILD_MOVE_OUT = "&aOpusciles teren swojej gildii &7[{TAG}] {NAME}&a!";
    public static String INFO_GUILD$ALLIANCE_MOVE_IN = "&6Wkroczyles na teren sojuszniczej gildii &7[{TAG}] {NAME}&6!";
    public static String INFO_GUILD$ALLIANCE_MOVE_OUT = "&6Opusciles teren sojuszniczej gildii &7[{TAG}] {NAME}&6!";
    public static String INFO_GUILD$ENEMY_MOVE_IN = "&cWkroczyles na teren wrogiej gildii &7[{TAG}] {NAME}&c!";
    public static String INFO_GUILD$ENEMY_MOVE_OUT = "&cOpusciles teren wrogiej gildii &7[{TAG}] {NAME}&c!";

    public static String INFO_MOVE_INTRUDER = "&4Intruz na terenie Twojej gildii!";
    public static List<String> INFO_GUILD = Arrays.asList("&7&m---------&r &7Gildia &6[{TAG}] {NAME} &7&m---------&r", "  &6Zalozyciel: &7{OWNER}", "  &6Lider: &7{LEADER}", "  &6Utworzona: &7{CREATETIME}", "  &6Wygasa: &7{EXPIRETIME}", "  &6Ostatni atak: &7{LASTTAKENLIFETIME}", "  &6Zgony: &7{DEATHS}", "  &6Zabicia: &7{KILLS}", "  &6Punkty: &7{POINTS}", "  &6Pvp: &7{PVP}", "  &6Zycia: &7{LIVES}", "  &6Rozmiar: &7{SIZE}x{SIZE}", "  &6Czlonkow: &7{MEMBERSNUM}, online: {ONLINENUM}", "  &6Czlonkowie: &7{MEMBERS}", "");
    public static List<String> INFO_RANKING = Arrays.asList("&7&m---------&r &7Gracz &6{NAME} &7&m---------&r", "  &6Punkty: &7{POINTS}", "  &6Zabicia: &7{KILLS}", "  &6Zgony: &7{DEATHS}", "  &6Pozycja: &7{POSITION}");
    public static String INFO_TREASURE_OPENED = "&6Otwarto skarbiec gildii!";
    public static String INFO_TREASURE_USER_ADD = "&6Gracz &7{PLAYER} &6jest od teraz uzytkownikiem skarbca gildii!";
    public static String INFO_TREASURE_USER_ADD$INFO = "&6Gracz &7{PLAYER} &6nadal Ci uprawnienia do skarbca gildii!";
    public static String INFO_TREASURE_USER_REMOVE = "&6Gracz &7{PLAYER} &6nie jest od teraz uzytkownikiem skarbca gildii!";
    public static String INFO_TREASURE_USER_REMOVE$INFO = "&6Gracz &7{PLAYER} &6odebral Ci uprawnienia do skarbca gildii!";
    public static String INFO_TREASURE_USERS = "&6Lista uzytkownikow skarbca: {USERS}";
    public static String INFO_PROLONGED$VALIDITY = "&6Przedluzo waznosc gildii!";
    public static String INFO_ALLY_NEW = "&6Twoja gildia zostala zaproszona do sojuszu przez gildie  &7[{TAG}] {NAME}&6! Zaakceptuj uzywajac &7/g sojusz {TAG}&6!";
    public static String INFO_ALLY_SEND = "&6Zaprosiles gildie &7[{TAG}] {NAME}&6 do sojuszu!";
    public static String INFO_RELOADED = "&6Przeladowano plik config.yml oraz lang.yml!";
    public static String INFO_SET_CUBOID = "&6Zmieniono teren gildii!";
    public static String INFO_RESETED = "&6Zresetowano ranking gracza!";
    public static String INFO_CHANGED_GUILD = "&6Zmieniono wartosc pola {FIELD} dla gildii {TAG}!";
    public static String INFO_SETTED = "&6Zmieniono wartosci gracza!";
    public static String INFO_USER$KICKED = "&6Wyrzucono gracza z gildii!";
    public static String INFO_FIGHT_START = "&cJestes w trakcie walki! Nie mozesz sie wylogowac przez 20 sekund!";
    public static String INFO_FIGHT_END = "&aSkonczyles walczyc! Mozesz sie spokojnie wylogowac! ;)";

    public static String TELEPORT_START = "&6Teleport nastapi za &7{TIME} &6sekund! Prosze sie nie ruszac!";
    public static String TELEPORT_END = "&6Przeteleportowano!";
    public static String TELEPORT_CANCELLED = "&6Teleport przerwany!";

    public static String LIST_RANKING_HEADER = "&7&m--------&r &6Lista wszystkich gildii &7&m--------&r \n  &7&otag - nazwa - zalozyciel";
    public static String LIST_RANKING_ELEMENT = "  &7{TAG} -  {NAME} -  {OWNER}";
    public static String LIST_RANKING_FOOTER = "&7&m----------------------------------";

    public static String LIST_GUILD_HEADER = "&7&m--------&r &6Ranking graczy (TOP 10)&7&m--------&r ";
    public static String LIST_GUILD_ELEMENT = "  &7{POS}. {NAME} - {POINTS}; {KILLS}:{DEATHS}";
    public static String LIST_GUILD_FOOTER = "&7&m----------------------------------";

    public static String BROADCAST_GUILD_ADMIN_DELETED = "&4Gildia &7[{TAG}] {NAME}&4 zostala usunieta przez administratora &7{PLAYER}&4!";
    public static String BROADCAST_GUILD_ADMIN_BANNED = "&4Gildia &7[{TAG}] {NAME}&4 zostala zbanowana przez administratora &7{PLAYER}&4 z powodu: &7{BANREASON}!";
    public static String BROADCAST_GUILD_ADMIN_UNBANNED = "&4Gildia &7[{TAG}] {NAME}&4 zostala odbanowana przez administratora &7{PLAYER}&4!";

    public static String BROADCAST_GUILD_CREATED = "&6Gildia &7[{TAG}] {NAME}&6 zostala utworzona przez &7{OWNER}&6!";
    public static String BROADCAST_GUILD_DELETED = "&6Gildia &7[{TAG}] {NAME}&6 zostala usunieta przez &7{OWNER}!";
    public static String BROADCAST_GUILD_JOINED = "&6Gracz &7{PLAYER} &6dolaczyl do gildii &7[{TAG}] {NAME}&6!";
    public static String BROADCAST_GUILD_KICKED = "&6Gracz &7{PLAYER} &6zostal wyrzucony z gildii &7[{TAG}] {NAME}&6!";
    public static String BROADCAST_GUILD_LEAVED = "&6Gracz &7{PLAYER} &6opuscil gildie &7[{TAG}] {NAME}&6!";
    public static String BROADCAST_GUILD_LIFE$TAKEN = "&6Gracz &7[{TAG2}] &6{PLAYER} zaatakowal gildie &7[{TAG}] {NAME}&6!";
    public static String BROADCAST_GUILD_TAKEN = "&6Gracz &7[{TAG2}] &6{PLAYER} zniszczyl gildie &7[{TAG}] {NAME}&6!";
    public static String BROADCAST_GUILD_EXPIRED = "&6Gildia &7[{TAG}] {NAME}&6 stracila swoja waznosc! Jej stare koorydynaty: &7[x: {X}; z: {Z}]&6!";
    public static String BROADCAST_GUILD_ALLIANCE_CREATED = "&6Gildia &7[{TAG}] {NAME}&6 zawarla sojusz z gildia &7[{TAG2}] {NAME2}&6!";
    public static String BROADCAST_GUILD_ALLIANCE_DELETED = "&6Gildia &7[{TAG}] {NAME}&6 zerwala sojusz z gildia &7[{TAG2}] {NAME2}&6!";
    public static String BROADCAST_GUILD_EFFECT = "&6Gildia &7[{TAG}] {NAME}&6 wylosowala efekt &7{EFFECT} ({LEVEL}) &6na okres &7{TIME}&6!";

    public static String BAN$REASON = "&cTwoja gildia zostala zbanowana przez {BANADMIN}!\nPowod: {BANREASON}\nWygasa: {BANTIME}";

    public Messages(JavaPlugin plugin) {
        super(plugin, "messages.yml", "messages.");
    }

    public static String parse(String msg, SubCommand sc) {
        msg = msg.replace("{NAME}", sc.getName());
        msg = msg.replace("{USAGE}", sc.getUsage());
        msg = msg.replace("{DESC}", sc.getDesc());
        msg = msg.replace("{PERM}", sc.getPermission());
        return Util.fixColor(msg);
    }

    public static String parse(String msg, User u) {
        msg = msg.replace("{NAME}", u.getName());
        msg = msg.replace("{POINTS}", Integer.toString(u.getPoints()));
        msg = msg.replace("{KILLS}", Integer.toString(u.getKills()));
        msg = msg.replace("{DEATHS}", Integer.toString(u.getDeaths()));
        msg = msg.replace("{POSITION}", Integer.toString(UserManager.getPosition(u)));
        return Util.fixColor(msg);
    }

    public static List<String> parse(List<String> list, User u) {
        List<String> fin = new ArrayList<>();
        for (String msg : list) {
            msg = parse(msg, u);
            fin.add(msg);
        }
        return Util.fixColor(fin);
    }

    public static String parse(String msg, Guild g) {
        msg = msg.replace("{TAG}", g.getTag());
        msg = msg.replace("{NAME}", g.getName());
        msg = msg.replace("{OWNER}", Bukkit.getOfflinePlayer(g.getOwner()).getName());
        msg = msg.replace("{LEADER}", Bukkit.getOfflinePlayer(g.getLeader()).getName());
        msg = msg.replace("{CREATETIME}", DateUtil.formatDate(g.getCreateTime()));
        msg = msg.replace("{EXPIRETIME}", DateUtil.formatDate(g.getExpireTime()));
        msg = msg.replace("{LASTTAKENLIFETIME}", DateUtil.formatDate(g.getLastTakenLifeTime()));
        msg = msg.replace("{SIZE}", Integer.toString(g.getCuboid().getSize() * 2 + 1));
        msg = msg.replace("{PVP}", (g.isPvp() ? "tak" : "nie"));
        msg = msg.replace("{MEMBERS}", getMembers(g));
        msg = msg.replace("{ONLINENUM}", Integer.toString(g.getOnlineMembers().size()));
        msg = msg.replace("{MEMBERSNUM}", Integer.toString(g.getMembers().size()));
        msg = msg.replace("{POINTS}", Integer.toString(g.getPoints()));
        msg = msg.replace("{KILLS}", Integer.toString(g.getKills()));
        msg = msg.replace("{DEATHS}", Integer.toString(g.getDeaths()));
        msg = msg.replace("{LIVES}", Integer.toString(g.getLives()));
        msg = msg.replace("{BANTIME}", DateUtil.formatDate(g.getBanTime()));
        msg = msg.replace("{BANADMIN}", g.getBanAdmin());
        msg = msg.replace("{BANREASON}", g.getBanReason());
        msg = msg.replace("{POSITION}", Integer.toString(GuildManager.getPosition(g)));
        return Util.fixColor(msg);
    }

    public static List<String> parse(List<String> list, Guild g) {
        List<String> fin = new ArrayList<>();
        for (String msg : list) {
            msg = parse(msg, g);
            fin.add(msg);
        }
        return Util.fixColor(fin);
    }

    public static String parse(String msg, Guild g, Guild g1, OfflinePlayer p) {
        msg = parse(msg, g, g1);
        msg = parse(msg, p);
        return Util.fixColor(msg);
    }

    public static String parse(String msg, Guild g, Guild g1) {
        msg = parse(msg, g);
        msg = msg.replace("{TAG2}", g1.getTag());
        msg = msg.replace("{NAME2}", g1.getName());
        msg = msg.replace("{OWNER2}", Bukkit.getOfflinePlayer(g1.getOwner()).getName());
        msg = msg.replace("{LEADER2}", Bukkit.getOfflinePlayer(g1.getLeader()).getName());
        msg = msg.replace("{CREATETIME2}", DateUtil.formatDate(g1.getCreateTime()));
        msg = msg.replace("{EXPIRETIME2}", DateUtil.formatDate(g1.getExpireTime()));
        msg = msg.replace("{LASTTAKENLIFETIME2}", DateUtil.formatDate(g1.getLastTakenLifeTime()));
        msg = msg.replace("{SIZE2}", Integer.toString(g1.getCuboid().getSize() * 2 + 1));
        msg = msg.replace("{PVP2}", (g1.isPvp() ? "tak" : "nie"));
        msg = msg.replace("{MEMBERS2}", getMembers(g1));
        msg = msg.replace("{ONLINENUM2}", Integer.toString(g1.getOnlineMembers().size()));
        msg = msg.replace("{MEMBERSNUM2}", Integer.toString(g1.getMembers().size()));
        msg = msg.replace("{POINTS2}", Integer.toString(g1.getPoints()));
        msg = msg.replace("{KILLS2}", Integer.toString(g1.getKills()));
        msg = msg.replace("{DEATHS2}", Integer.toString(g1.getDeaths()));
        msg = msg.replace("{LIVES2}", Integer.toString(g1.getLives()));
        msg = msg.replace("{POSITION2}", Integer.toString(GuildManager.getPosition(g1)));

        return Util.fixColor(msg);
    }

    public static String parse(String msg, OfflinePlayer p) {
        msg = msg.replace("{PLAYER}", p.getName());
        return Util.fixColor(msg);
    }

    public static String parse(String msg, Guild g, OfflinePlayer p) {
        msg = parse(msg, g);
        msg = parse(msg, p);
        return Util.fixColor(msg);
    }

    private static String getMembers(Guild g) {
        String[] members = new String[g.getMembers().size()];
        int i = 0;
        for (UUID uuid : g.getMembers()) {
            OfflinePlayer op = Bukkit.getOfflinePlayer(uuid);
            if (op.isOnline()) {
                members[i] = ChatColor.GREEN + op.getName();
            } else {
                members[i] = ChatColor.RED + op.getName();
            }
            i++;
        }
        return StringUtils.join(members, ChatColor.GRAY + ", " + ChatColor.RESET);
    }

    public static String getTreasureUsers(Guild g) {
        String[] members = new String[g.getMembers().size()];
        int i = 0;
        for (UUID uuid : g.getTreasureUsers()) {
            OfflinePlayer op = Bukkit.getOfflinePlayer(uuid);
            if (op.isOnline()) {
                members[i] = ChatColor.GREEN + op.getName();
            } else {
                members[i] = ChatColor.RED + op.getName();
            }
            i++;
        }
        return StringUtils.join(members, ChatColor.GRAY + ", " + ChatColor.RESET);
    }

}
