package com.github.ventuss.listener.LegacyFactions;

import com.github.ventuss.game.IGame;
import com.github.ventuss.manager.Manager;
import net.redstoneore.legacyfactions.entity.FPlayer;
import net.redstoneore.legacyfactions.entity.FPlayerColl;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class LegacyGameListener implements Listener {

    @EventHandler
    public void onFactionBreakTotemEvent(BlockBreakEvent event) {
        IGame totem = Manager.getInstance().totemManager.findTotemByBlock(event.getBlock());
        FPlayer player = FPlayerColl.get(event.getPlayer());
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