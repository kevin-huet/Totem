package com.github.ventuss.manager.command;

import com.github.ventuss.App;
import com.github.ventuss.Command.global.TotemCommand;
import com.github.ventuss.Command.totem.*;
import com.github.ventuss.manager.IManager;
import com.github.ventuss.manager.game.TotemManager;

public class CommandManager implements IManager {

    public ChangeInteractItemCommand changeInteractItemCommand;
    public ChangeTotemMaterialCommand changeTotemMaterialCommand;
    public ChangeTotemSizeCommand changeTotemSizeCommand;
    public InfoTotemCommand infoTotemCommand;
    public ListTotemCommand listTotemCommand;
    public ResetTotemCommand resetTotemCommand;
    public SetTotemCommand setTotemCommand;
    public SpawnTotemCommand spawnTotemCommand;
    public StopTotemCommand stopTotemCommand;
    public UnsetTotemCommand unsetTotemCommand;

    public CommandManager() {
        changeInteractItemCommand = new ChangeInteractItemCommand();
        changeTotemMaterialCommand = new ChangeTotemMaterialCommand();
        changeTotemSizeCommand = new ChangeTotemSizeCommand();
        infoTotemCommand = new InfoTotemCommand();
        listTotemCommand = new ListTotemCommand();
        resetTotemCommand = new ResetTotemCommand();
        setTotemCommand = new SetTotemCommand();
        spawnTotemCommand = new SpawnTotemCommand();
        stopTotemCommand = new StopTotemCommand();
        unsetTotemCommand = new UnsetTotemCommand();
        App.getInstance().getCommand("totem").setExecutor(new TotemCommand());
    }
}
