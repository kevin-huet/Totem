package com.github.ventuss.Command.totem;

import com.github.ventuss.App;
import com.github.ventuss.config.MessageDefaultValue;
import com.github.ventuss.game.IGame;
import com.github.ventuss.game.factions.Totem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangeTotemMaterialCommand extends MessageDefaultValue implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player && strings.length < 2)
            return true;
        assert commandSender instanceof Player;
        if (!commandSender.hasPermission("totem.change.block.material"))
            return true;
        changeMaterial((Player) commandSender, strings[0], strings[1]);
        return false;
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
