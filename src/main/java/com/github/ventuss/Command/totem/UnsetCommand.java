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

public class UnsetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player) && strings.length < 1)
            return true;
        if (!commandSender.hasPermission("totem.unset"))
            return true;
        assert commandSender instanceof Player;
        unsetTotem((Player) commandSender, strings[0]);
        return false;
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
