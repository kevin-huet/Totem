package com.github.ventuss.Command.totem;

import com.github.ventuss.App;
import com.github.ventuss.game.IGame;
import com.github.ventuss.game.factions.Totem;
import com.github.ventuss.manager.Manager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListTotemCommand implements ICommand {

    @Override
    public void launch(Player player, String[] strings) {
        if (!player.hasPermission("totem.list")) {
            player.sendMessage(ChatColor.RED+"You don't have permission to do that");
            return;
        }
        if (strings.length < 1) {
            player.sendMessage(ChatColor.RED+"usage : /totem list");
            return;
        }
        printTotemList(player);
    }

    public void printTotemList(Player player) {
        player.sendMessage(ChatColor.YELLOW+"---- [Totem List] ----\n");
        for (IGame game : Manager.getInstance().gameManager.getGames()) {
                String status = App.utilities.conversionUtilities.statusToString(game.getStatus());
                player.sendMessage(ChatColor.GREEN+" - "+game.getName()+" : "+status);
        }
    }

}
