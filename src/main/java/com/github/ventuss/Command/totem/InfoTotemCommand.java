package com.github.ventuss.Command.totem;

import com.github.ventuss.App;
import com.github.ventuss.game.IGame;
import com.github.ventuss.game.factions.Totem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoTotemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length < 1)
            return true;
        if (!commandSender.hasPermission("totem.info"))
            return true;
        printTotemInfo((Player) commandSender, strings[0]);
        return false;
    }

    private void printTotemInfo(Player player, String name) {
        IGame totem = App.manager.totemManager.findByName(name);
        player.sendMessage(ChatColor.YELLOW+"---- [Totem "+totem.getName()+"] ----\n");
        player.sendMessage(ChatColor.GREEN+" - Status : "+
                App.utilities.conversionUtilities.statusToString(totem.getStatus()));
        player.sendMessage(ChatColor.YELLOW+" - World : "+totem.getLocation().getWorld().toString());
        player.sendMessage(ChatColor.YELLOW+" - Location : "+
                totem.getLocation().getX()+" "+totem.getLocation().getY()+" "+totem.getLocation().getZ());
        player.sendMessage(ChatColor.YELLOW+" - Size : "+totem.getSize());
        player.sendMessage(ChatColor.YELLOW+" - Actual size : "+totem.getActualSize());
        player.sendMessage(ChatColor.YELLOW+" - Block Material : "+totem.getBlockMaterial().toString());
        player.sendMessage(ChatColor.YELLOW+" - Interact item : "+totem.getItemInteract().toString());

    }
}
