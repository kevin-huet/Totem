package com.github.ventuss.command.totem;

import com.github.ventuss.App;
import com.github.ventuss.config.MessageDefaultValue;
import com.github.ventuss.game.IGame;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ChangeTotemMaterialCommand extends MessageDefaultValue implements ICommand {

    @Override
    public void launch(Player player, String[] strings) {
        if (!player.hasPermission("totem.change.block.material")) {
            player.sendMessage(ChatColor.RED+"You don't have permission to do that");
            return;
        }
        if (strings.length < 3) {
            player.sendMessage(ChatColor.RED+"usage : /totem block [name] [material]");
            return;
        }
        changeMaterial(player, strings[1], strings[2]);
    }

    private void changeMaterial(Player player, String totemName, String materialName) {
        IGame totem = App.manager.totemManager.findByName(totemName);
        Material material = Material.getMaterial(materialName.toUpperCase());

        if (totem == null || material == null)
            return;
        totem.setBlockMaterial(material);
        totem.generateTotem();
        player.sendMessage(ChatColor.GREEN+ChangeMaterial
                +totem.getName()+" : "+material.toString());
    }

}
