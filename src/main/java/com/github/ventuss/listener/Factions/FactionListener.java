package com.github.ventuss.listener.Factions;

import com.github.ventuss.game.IGame;
import com.github.ventuss.game.factions.Totem;
import com.github.ventuss.manager.Manager;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.event.EventFactionsDisband;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FactionListener implements Listener {

    @EventHandler
    public void onFactionDisband(EventFactionsDisband event) {
        Faction faction = event.getFaction();
        IGame totem = Manager.getInstance().totemManager.findTotemByFaction(faction);
        if (totem == null)
            return;
        totem.reset();
    }
}
