package com.github.ventuss.manager;

import com.github.ventuss.manager.command.CommandManager;
import com.github.ventuss.manager.config.ConfigManager;
import com.github.ventuss.manager.game.FactionManager;
import com.github.ventuss.manager.game.GameManager;
import com.github.ventuss.manager.game.TotemManager;
import com.github.ventuss.manager.listener.ListenerManager;

public class Manager {

    private static Manager instance;
    public GameManager gameManager;
    public TotemManager totemManager;
    public FactionManager factionManager;
    public ListenerManager listenerManager;
    public CommandManager commandManager;
    public ConfigManager configManager;

    public Manager() {
        instance = this;
        this.configManager = new ConfigManager();
        this.factionManager = new FactionManager();
        this.listenerManager = new ListenerManager();
        this.totemManager = new TotemManager();
        this.commandManager = new CommandManager();
        this.gameManager = new GameManager();
    }

    public static Manager getInstance() {
        return instance;
    }
}
