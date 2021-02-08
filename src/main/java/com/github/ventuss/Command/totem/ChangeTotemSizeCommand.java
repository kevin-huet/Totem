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

public class ChangeTotemSizeCommand implements ICommand {

    @Override
    public void launch(Player player, String[] strings) {
        if (strings.length < 3) {
            player.sendMessage(ChatColor.RED+"usage : /totem size [name] [size]");
            return;
        }
        changeTotemSize(player, strings[1], strings[2]);
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
