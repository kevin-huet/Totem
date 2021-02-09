package com.github.ventuss.listener.towny;

import com.github.ventuss.App;
import com.github.ventuss.game.IGame;
import com.github.ventuss.manager.Manager;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.event.DeleteTownEvent;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TownListener implements Listener {

    @EventHandler
    public void onTownDeleted(DeleteTownEvent event) {
        try {
            manageEvent(event);
        } catch (NotRegisteredException e) {
            App.getInstance().getLogger().info(e.getMessage());
        }
    }

    private void manageEvent(DeleteTownEvent event) throws NotRegisteredException {

        Town town = TownyAPI.getInstance().getDataSource().getTown(event.getTownUUID());
        IGame totem = null;

        if (town == null)
            return;
        totem = Manager.getInstance().totemManager.findTotemByTown(town);
        if (totem == null) {
            return;
        }
        totem.reset();
    }
}
