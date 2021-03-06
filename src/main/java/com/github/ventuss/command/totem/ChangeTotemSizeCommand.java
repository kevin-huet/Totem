package com.github.ventuss.command.totem;

import com.github.ventuss.App;
import com.github.ventuss.game.IGame;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChangeTotemSizeCommand implements ICommand {

    @Override
    public void launch(Player player, String[] strings) {
        if (!player.hasPermission("totem.change.size")) {
            player.sendMessage(ChatColor.RED+"You don't have permission to do that");
            return;
        }
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
