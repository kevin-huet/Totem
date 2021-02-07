package com.github.ventuss.listener.factionsUUID;

import com.github.ventuss.game.IGame;
import com.github.ventuss.manager.Manager;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.FactionDisbandEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FactionsUUIDListener implements Listener {

    @EventHandler
    public void onFactionDisband(FactionDisbandEvent event) {
        Faction faction = event.getFaction();
        IGame totem = Manager.getInstance().totemManager.findTotemByFaction(faction);
        if (totem == null) {
            event.getFPlayer().getPlayer().sendMessage("totem null");
            return;
        }
        totem.reset();
    }
}
