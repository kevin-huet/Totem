package com.github.ventuss.command.totem;

import com.github.ventuss.App;
import com.github.ventuss.config.MessageDefaultValue;
import com.github.ventuss.game.IGame;
import com.github.ventuss.manager.Manager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SetTotemCommand extends MessageDefaultValue implements ICommand {

    @Override
    public void launch(Player player, String[] strings) {
        if (!player.hasPermission("totem.set")) {
            player.sendMessage(ChatColor.RED+"You don't have permission to do that");
            return;
        }
        if (strings.length < 2) {
            player.sendMessage(ChatColor.RED+"usage : /totem set [name]");
            return;
        }
        createAndSetTotem(player, strings[1]);
    }

    private void createAndSetTotem(Player player, String name) {
        IGame totem = Manager.getInstance().totemManager.findByName(name);
        if (totem == null) {
            totem = App.getInstance().newGame(name);
            Manager.getInstance().gameManager.addGame(totem);
        }
        totem.setLocation(player.getLocation());
        App.manager.configManager.totemConfiguration.setLocationConfig(totem);
        App.manager.configManager.totemConfiguration.setItemInteractionConfig(totem);
        totem.generateTotem();
        player.sendMessage(TotemSet);
    }
}
