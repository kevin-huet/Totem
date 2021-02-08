package com.github.ventuss.Command.totem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopTotemCommand implements ICommand {


    @Override
    public void launch(Player player, String[] strings) {
        if (!player.hasPermission("totem.stop")) {
            player.sendMessage(ChatColor.RED+"You don't have permission to do that");
            return;
        }
        if (strings.length < 2) {
            player.sendMessage(ChatColor.RED+"usage : /totem stop [name]");
            return;
        }
    }
}
