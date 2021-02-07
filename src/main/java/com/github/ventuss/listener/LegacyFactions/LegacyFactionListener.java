package com.github.ventuss.listener.LegacyFactions;

import com.github.ventuss.game.IGame;
import com.github.ventuss.manager.Manager;
import net.redstoneore.legacyfactions.entity.Faction;
import net.redstoneore.legacyfactions.event.EventFactionsDisband;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LegacyFactionListener implements Listener {

    @EventHandler
    public void onFactionDisband(EventFactionsDisband event) {
        Faction faction = event.getFaction();
        IGame totem = Manager.getInstance().totemManager.findTotemByFaction(faction);
        if (totem == null) {
            event.getFPlayer().getPlayer().sendMessage("totem null");
            return;
        }
        totem.reset();
    }

}