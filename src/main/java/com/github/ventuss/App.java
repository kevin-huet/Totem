package com.github.ventuss;

import com.github.ventuss.game.IGame;
import com.github.ventuss.game.factions.Totem;
import com.github.ventuss.game.factionsUUID.UUIDTotem;
import com.github.ventuss.game.legacyFactions.LegacyTotem;
import com.github.ventuss.manager.Manager;
import com.github.ventuss.utils.Utilities;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    private static App instance;
    public static Manager manager;
    public static Utilities utilities;

    @Override
    public void onEnable() {
        instance = this;
        utilities = new Utilities();
        manager = new Manager();
        getLogger().info("enable");
    }

    @Override
    public void onDisable() {

    }

    public IGame newGame(String name) {
        if (Manager.getInstance().configManager.globalConfiguration.factionsEnabled)
            return new Totem(name);
        if (Manager.getInstance().configManager.globalConfiguration.legacyFactionsEnabled)
            return new LegacyTotem(name);
        if (Manager.getInstance().configManager.globalConfiguration.factionsUUIDEnabled)
            return new UUIDTotem(name);
        return null;
    }

    public static App getInstance() {
        return instance;
    }
}
