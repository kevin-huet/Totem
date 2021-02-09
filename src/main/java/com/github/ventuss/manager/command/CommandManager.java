package com.github.ventuss.manager.command;

import com.github.ventuss.App;
import com.github.ventuss.command.global.TotemCommand;
import com.github.ventuss.command.totem.*;
import com.github.ventuss.manager.IManager;

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
    public HelpCommand helpCommand;

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
        helpCommand = new HelpCommand();
        App.getInstance().getCommand("totem").setExecutor(new TotemCommand());
    }
}
