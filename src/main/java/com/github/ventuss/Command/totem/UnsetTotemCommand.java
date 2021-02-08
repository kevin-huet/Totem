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

public class UnsetTotemCommand implements ICommand {

    @Override
    public void launch(Player player, String[] strings) {
        if (strings.length < 2) {
            player.sendMessage(ChatColor.RED+"usage : /totem size [name]");
            return;
        }
        unsetTotem(player, strings[1]);
    }

    public void unsetTotem(Player player, String name) {
        IGame totem = App.manager.totemManager.findByName(name);
        Manager.getInstance().gameManager.getGames().remove(totem);
        if (totem == null)
            return;
        player.sendMessage(ChatColor.GREEN+totem.getName()+" unset");
        App.manager.gameManager.getGames().remove(totem);
    }
}
