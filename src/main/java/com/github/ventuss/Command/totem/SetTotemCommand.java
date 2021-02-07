package com.github.ventuss.Command.totem;

import com.github.ventuss.App;
import com.github.ventuss.config.MessageDefaultValue;
import com.github.ventuss.game.IGame;
import com.github.ventuss.game.factions.Totem;
import com.github.ventuss.manager.Manager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetTotemCommand extends MessageDefaultValue implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player) && strings.length < 1)
            return false;
        assert commandSender instanceof Player;
        if (!commandSender.hasPermission("totem.set"))
            return true;
        createAndSetTotem((Player) commandSender, strings[0]);
        return false;
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
