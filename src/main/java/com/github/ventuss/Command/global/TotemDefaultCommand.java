package com.github.ventuss.Command.global;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TotemDefaultCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player))
            return true;
        if (!commandSender.hasPermission("totem.default.command"))
            return true;
        printUsageCommand((Player) commandSender);
        return false;
    }

    private void printUsageCommand(Player commandSender) {
        commandSender.sendMessage(ChatColor.GOLD+  "--- [Totem Plugin] ---");
        commandSender.sendMessage(ChatColor.YELLOW+"                      ");
        commandSender.sendMessage(ChatColor.YELLOW+"developed by Ventuss");
        commandSender.sendMessage(ChatColor.YELLOW+"Contact : "+ChatColor.BLUE+"ventuss.pro@gmail.com");
        commandSender.sendMessage(ChatColor.YELLOW+"");

    }
}
