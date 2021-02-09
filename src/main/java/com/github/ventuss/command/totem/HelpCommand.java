package com.github.ventuss.command.totem;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HelpCommand  implements ICommand {

    @Override
    public void launch(Player player, String[] strings) {
        if (!player.hasPermission("totem.help")) {
            player.sendMessage(ChatColor.RED+"You don't have permission to do that");
            return;
        }
        if (strings.length < 1) {
            player.sendMessage(ChatColor.RED+"usage: /totem help [optional : page]");
            return;
        }
        player.sendMessage(new String[] {
                ChatColor.GREEN+"----- [Totem Help] -----\n",
                ChatColor.GOLD+"/totem help [page] "+ChatColor.WHITE+"- help",
                ChatColor.GOLD+"/totem set [name] "+ChatColor.WHITE+"- create and set totem",
                ChatColor.GOLD+"/totem unset [name] "+ChatColor.WHITE+"- delete and unset totem",
                ChatColor.GOLD+"/totem spawn [name] "+ChatColor.WHITE+"- spawn totem",
                ChatColor.GOLD+"/totem stop [name] "+ChatColor.WHITE+"- stop totem",
                ChatColor.GOLD+"/totem item [name] [material] "+ChatColor.WHITE+"- set item material",
                ChatColor.GOLD+"/totem block [name] [material] "+ChatColor.WHITE+"- set block material",
                ChatColor.GOLD+"/totem reset [name] "+ChatColor.WHITE+"- reset totem",
                ChatColor.GOLD+"/totem info [name] "+ChatColor.WHITE+"- show totem info",
                ChatColor.GOLD+"/totem list "+ChatColor.WHITE+"- show list of totems",
                ChatColor.GOLD+"/totem size [name] [size] "+ChatColor.WHITE+"- set totem size",
        });
    }
}
