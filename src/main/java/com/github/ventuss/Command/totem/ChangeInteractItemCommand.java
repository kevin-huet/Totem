package com.github.ventuss.Command.totem;

import com.github.ventuss.App;
import com.github.ventuss.game.IGame;
import com.github.ventuss.game.factions.Totem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangeInteractItemCommand implements ICommand {

    @Override
    public void launch(Player player, String[] strings) {
        if (strings.length < 3)
            return;
        changeInteractItem(player, strings[1], strings[2]);
    }

    private void changeInteractItem(Player player, String totemName, String materialName) {
        IGame totem = App.manager.totemManager.findByName(totemName);
        Material material = Material.getMaterial(materialName);

        if (material == null || totem == null)
            return;
        totem.setItemInteract(material);
        player.sendMessage(ChatColor.GREEN+"new material set");
    }
}
