package com.github.ventuss.manager.listener;

import com.github.ventuss.App;
import com.github.ventuss.listener.Factions.FactionListener;
import com.github.ventuss.listener.Factions.GameListener;
import com.github.ventuss.listener.LegacyFactions.LegacyFactionListener;
import com.github.ventuss.listener.LegacyFactions.LegacyGameListener;
import com.github.ventuss.listener.factionsUUID.FactionsUUIDListener;
import com.github.ventuss.listener.factionsUUID.GameUUIDListener;
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
}
