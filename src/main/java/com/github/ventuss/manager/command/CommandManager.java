package com.github.ventuss.manager.command;

import com.github.ventuss.App;
import com.github.ventuss.Command.totem.*;
import com.github.ventuss.manager.IManager;

public class CommandManager implements IManager {

    public CommandManager() {
        App.getInstance().getCommand("totem-size").setExecutor(new changeTotemSizeCommand());
        App.getInstance().getCommand("totem-reset").setExecutor(new ResetTotemCommand());
        App.getInstance().getCommand("totem-set").setExecutor(new SetTotemCommand());
        App.getInstance().getCommand("totem-spawn").setExecutor(new SpawnTotemCommand());
        App.getInstance().getCommand("totem-stop").setExecutor(new StopTotemCommand());
        App.getInstance().getCommand("totem-list").setExecutor(new ListTotemCommand());
        App.getInstance().getCommand("totem-info").setExecutor(new InfoTotemCommand());
        App.getInstance().getCommand("totem-unset").setExecutor(new UnsetCommand());
        App.getInstance().getCommand("totem-block-material").setExecutor(new ChangeTotemMaterialCommand());
        App.getInstance().getCommand("totem-item-material").setExecutor(new ChangeInteractItemCommand());
    }
}
