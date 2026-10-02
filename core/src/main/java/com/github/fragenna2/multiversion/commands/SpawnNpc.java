package com.github.fragenna2.multiversion.commands;

import com.github.fragenna2.multiversion.api.NMSHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnNpc implements CommandExecutor {

    private static final String OLD_VERSION = "1.8.8";

    private final NMSHandler nmsHandler;

    public SpawnNpc(NMSHandler nmsHandler) {
        this.nmsHandler = nmsHandler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        final String serverVersion = Bukkit.getVersion();

        if (!serverVersion.contains(OLD_VERSION)) {
            sender.sendMessage(ChatColor.RED + "You can't execute this command because the server's version is not supported");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You can't execute this command");
            return true;
        }

        final Player player = (Player) sender;
        nmsHandler.spawnNpc(player);
        nmsHandler.showNpc(player);
        return true;
    }
}
