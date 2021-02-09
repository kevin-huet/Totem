package com.github.ventuss.command.totem;

import com.github.ventuss.App;
import com.github.ventuss.game.IGame;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ChangeInteractItemCommand implements ICommand {

    @Override
    public void launch(Player player, String[] strings) {
        if (!player.hasPermission("totem.change.interact.item")) {
            player.sendMessage(ChatColor.RED+"You don't have permission to do that");
            return;
        }
        if (strings.length < 3) {
            player.sendMessage(ChatColor.RED+"usage : /totem item [name] [material]");
            return;
        }
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
