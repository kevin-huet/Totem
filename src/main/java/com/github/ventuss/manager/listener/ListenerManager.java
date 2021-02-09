package com.github.ventuss.manager.listener;

import com.github.ventuss.App;
import com.github.ventuss.listener.factions.FactionListener;
import com.github.ventuss.listener.factions.GameListener;
import com.github.ventuss.listener.legacyFactions.LegacyFactionListener;
import com.github.ventuss.listener.legacyFactions.LegacyGameListener;
import com.github.ventuss.listener.factionsUUID.FactionsUUIDListener;
import com.github.ventuss.listener.factionsUUID.GameUUIDListener;
import com.github.ventuss.listener.towny.TownGameListener;
import com.github.ventuss.listener.towny.TownListener;
import com.github.ventuss.manager.IManager;
import com.github.ventuss.manager.Manager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import java.util.List;

public class ListenerManager implements IManager {

    private List<Listener> listeners;

    public ListenerManager() {
        PluginManager pluginManager = App.getInstance().getServer().getPluginManager();

        if (Manager.getInstance().configManager.globalConfiguration.factionsEnabled)
            registerFactionsEvent(pluginManager);
        if (Manager.getInstance().configManager.globalConfiguration.legacyFactionsEnabled)
            registerLegacyFactionsEvent(pluginManager);
        if (Manager.getInstance().configManager.globalConfiguration.factionsUUIDEnabled)
            registerFactionsUUIDEvent(pluginManager);
        if (Manager.getInstance().configManager.globalConfiguration.townyEnabled)
            registerTownyEvent(pluginManager);
    }

    private void registerLegacyFactionsEvent(PluginManager pluginManager) {
        pluginManager.registerEvents(new LegacyGameListener(), App.getInstance());
        pluginManager.registerEvents(new LegacyFactionListener(), App.getInstance());
        App.getInstance().getLogger().info("Register LegacyFactions events...");
    }


    private void registerFactionsEvent(PluginManager pluginManager) {
        pluginManager.registerEvents(new GameListener(), App.getInstance());
        pluginManager.registerEvents(new FactionListener(), App.getInstance());
        App.getInstance().getLogger().info("Register Factions events...");
    }

    private void registerFactionsUUIDEvent(PluginManager pluginManager) {
        pluginManager.registerEvents(new FactionsUUIDListener(), App.getInstance());
        pluginManager.registerEvents(new GameUUIDListener(), App.getInstance());
        App.getInstance().getLogger().info("Register FactionsUUID events...");
    }

    private void registerTownyEvent(PluginManager pluginManager) {
        pluginManager.registerEvents(new TownGameListener(), App.getInstance());
        pluginManager.registerEvents(new TownListener(), App.getInstance());
        App.getInstance().getLogger().info("Register Towny events...");
    }
}
