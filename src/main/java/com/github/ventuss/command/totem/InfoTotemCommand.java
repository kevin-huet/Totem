package com.github.ventuss.command.totem;

import com.github.ventuss.App;
import com.github.ventuss.game.IGame;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InfoTotemCommand implements ICommand {

    @Override
    public void launch(Player player, String[] strings) {
        if (!player.hasPermission("totem.info")) {
            player.sendMessage(ChatColor.RED+"You don't have permission to do that");
            return;
        }
        if (strings.length < 2) {
            player.sendMessage(ChatColor.RED+"usage : /totem info [name]");
            return;
        }
        printTotemInfo(player, strings[1]);
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
