package com.github.ventuss.command.totem;

import com.github.ventuss.App;
import com.github.ventuss.config.MessageDefaultValue;
import com.github.ventuss.game.IGame;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SpawnTotemCommand extends MessageDefaultValue implements ICommand {

    @Override
    public void launch(Player player, String[] strings) {
        if (!player.hasPermission("totem.spawn")) {
            player.sendMessage(ChatColor.RED+"You don't have permission to do that");
            return;
        }
        if (strings.length < 2) {
            player.sendMessage(ChatColor.RED+"usage : /totem spawn [name]");
            return;
        }
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
