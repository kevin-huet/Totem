package com.github.ventuss.Command.totem;

import com.github.ventuss.App;
import com.github.ventuss.config.MessageDefaultValue;
import com.github.ventuss.game.IGame;
import com.github.ventuss.game.factions.Totem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnTotemCommand extends MessageDefaultValue implements ICommand {

    @Override
    public void launch(Player player, String[] strings) {
        if (strings.length < 2)
            return;
        spawnTotem(player, strings[1]);
    }

    private void spawnTotem(Player player, String name) {
        IGame totem = App.manager.totemManager.findByName(name);
        if (totem == null) {
            player.sendMessage(ChatColor.RED+"Totem name not found");
            return;
        }
        player.sendMessage(ChatColor.GREEN+TotemSpawn);
        totem.spawn();
    }
}
