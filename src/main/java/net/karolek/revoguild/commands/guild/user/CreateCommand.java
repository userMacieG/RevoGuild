package net.karolek.revoguild.commands.guild.user;

import net.karolek.revoguild.objects.guild.Guild;
import net.karolek.revoguild.commands.SubCommand;
import net.karolek.revoguild.data.Commands;
import net.karolek.revoguild.data.Config;
import net.karolek.revoguild.data.Messages;
import net.karolek.revoguild.managers.guild.GuildManager;
import net.karolek.revoguild.utils.ItemUtil;
import net.karolek.revoguild.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CreateCommand extends SubCommand {

    public CreateCommand() {
        super(Commands.GUILD_USER_CREATE_NAME, Commands.GUILD_USER_CREATE_DESCRIPTION, Commands.GUILD_USER_CREATE_USAGE, Commands.GUILD_USER_CREATE_PERMISSION, Commands.GUILD_USER_CREATE_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 2) {
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));
        }
        String tag = args[0];
        String name = args[1];
        if (GuildManager.getGuild(p) != null) {
            return Util.sendMessage(p, Messages.ERROR_HAVE$GUILD);
        }
        if (tag.length() < Config.TAG_LENGHT$MIN || tag.length() > Config.TAG_LENGHT$MAX) {
            return Util.sendMessage(p, Messages.ERROR_TAG$INCORRECT$LENGTH.replace("{TAGMIN}", Integer.toString(Config.TAG_LENGHT$MIN)).replace("{TAGMAX}", Integer.toString(Config.TAG_LENGHT$MAX)));
        }
        if (name.length() < Config.NAME_LENGHT$MIN || name.length() > Config.NAME_LENGHT$MAX) {
            return Util.sendMessage(p, Messages.ERROR_NAME$INCORRECT$LENGTH.replace("{NAMEMIN}", Integer.toString(Config.NAME_LENGHT$MIN)).replace("{NAMEMAX}", Integer.toString(Config.NAME_LENGHT$MAX)));
        }
        if (!tag.matches(Config.TAG_REGEX)) {
            return Util.sendMessage(p, Messages.ERROR_TAG$INCORRECT$CHARACTERS.replace("{REGEX}", Config.TAG_REGEX));
        }
        if (!name.matches(Config.NAME_REGEX)) {
            return Util.sendMessage(p, Messages.ERROR_NAME$INCORRECT$CHARACTERS.replace("{REGEX}", Config.NAME_REGEX));
        }
        if (GuildManager.getGuild(tag) != null || GuildManager.getGuild(name) != null) {
            return Util.sendMessage(p, Messages.ERROR_GUILD_ALREADY$EXISTS);
        }
        if (!GuildManager.canCreateGuild(p.getLocation())) {
            return Util.sendMessage(p, Messages.ERROR_GUILD_NEARBY);
        }
        List<ItemStack> items = ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_CREATE_VIP : Config.ITEMS_CREATE_NORMAL, 1);
        if (!ItemUtil.checkAndRemove(items, p)) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_ITEMS.replace("{ITEMS}", ItemUtil.getItems(items)));
        }
        Guild g = GuildManager.createGuild(tag, name, p);
        return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_CREATED, g));
    }

}
