package net.karolek.revoguild.commands;

import net.karolek.revoguild.utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public abstract class SubCommand extends Command {

    private final String name;
    private final String usage;
    private final String desc;
    private final String permission;

    protected SubCommand(String name, String desc, String usage, String permission, String... aliases) {
        super(name, desc, usage, Arrays.asList(aliases));
        this.name = name;
        this.usage = usage;
        this.desc = desc;
        this.permission = permission;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return Util.sendMessage(sender, "&cYou must be a player to run that command!");
        }
        Player p = (Player) sender;
        if (!p.hasPermission(this.permission)) {
            return Util.sendMessage(p, "&cYou don't have permissions to run that command! &7(" + this.permission + ")");
        }
        return onCommand(p, args);
    }

    public abstract boolean onCommand(Player p, String[] args);

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsage() {
        return usage;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String getPermission() {
        return permission;
    }

}
