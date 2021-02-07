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

public class SpawnTotemCommand extends MessageDefaultValue implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player) && strings.length < 1)
            return false;
        assert commandSender instanceof Player;
        if (!commandSender.hasPermission("totem.spawn"))
            return true;
        spawnTotem((Player) commandSender, strings[0]);
        return false;
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
