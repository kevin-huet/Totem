package com.github.ventuss.Command.totem;

import com.github.ventuss.App;
import com.github.ventuss.game.IGame;
import com.github.ventuss.game.factions.Totem;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class changeTotemSizeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player && strings.length < 2)
            return true;
        assert commandSender instanceof Player;
        if (!commandSender.hasPermission("totem.change.size"))
            return true;
        changeTotemSize((Player) commandSender, strings[0], strings[1]);
        return false;
    }

    private void changeTotemSize(Player player, String totemName, String size) {
        IGame totem = App.manager.totemManager.findByName(totemName);

        if (!StringUtils.isNumeric(size) || totem == null)
            return;
        totem.setSize(Integer.parseInt(size));
        totem.setActualSize(Integer.parseInt(size));
        totem.generateTotem();
        player.sendMessage(ChatColor.GREEN+"new size set");
    }
}
