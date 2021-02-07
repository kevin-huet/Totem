package com.github.ventuss.listener.Factions;

import com.github.ventuss.game.IGame;
import com.github.ventuss.game.factions.Totem;
import com.github.ventuss.manager.Manager;
import com.massivecraft.factions.entity.MPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class GameListener implements Listener {

    @EventHandler
    public void onFactionBreakTotemEvent(BlockBreakEvent event) {
        IGame totem = Manager.getInstance().totemManager.findTotemByBlock(event.getBlock());
        MPlayer player = MPlayer.get(event.getPlayer());
        if (totem == null)
            return;
        if (!player.hasFaction()) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED+"You have to be in a faction to break the totem");
            return;
        }
        breakBlockTotem(event.getPlayer(), event.getBlock(), totem);
    }

    public void breakBlockTotem(Player player, Block block, IGame totem) {
        if (totem == null || !isGoodItem(player.getPlayer(), totem.getItemInteract()))
            return;
        totem.getLocation().getWorld().playEffect(block.getLocation(), Effect.EXPLOSION, 1);
        totem.playerBreakTotemBlock(player);
    }

    private boolean isGoodItem(Player player, Material material) {
        return player.getItemInHand().getType() == material;
    }
}
