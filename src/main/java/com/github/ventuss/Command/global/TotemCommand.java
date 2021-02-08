package com.github.ventuss.Command.global;

import com.github.ventuss.manager.Manager;
import com.github.ventuss.manager.command.CommandManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TotemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        CommandManager manager = Manager.getInstance().commandManager;
        if (strings.length < 2)
            return true;
        if (!commandSender.hasPermission("totem"))
            return true;
        switch (strings[0]) {
            case "set": manager.setTotemCommand.launch((Player) commandSender, strings);
                break;
            case "size": manager.changeTotemSizeCommand.launch((Player) commandSender, strings);
                break;
            case "spawn": manager.spawnTotemCommand.launch((Player) commandSender, strings);
                break;
            case "reset": manager.resetTotemCommand.launch((Player) commandSender, strings);
                break;
            case "stop": manager.stopTotemCommand.launch((Player) commandSender, strings);
                break;
            case "list": manager.listTotemCommand.launch((Player) commandSender, strings);
                break;
            case "info": manager.infoTotemCommand.launch((Player) commandSender, strings);
                break;
            case "unset": manager.unsetTotemCommand.launch((Player) commandSender, strings);
                break;
            case "block": manager.changeTotemMaterialCommand.launch((Player) commandSender, strings);
                break;
            case "item": manager.changeInteractItemCommand.launch((Player) commandSender, strings);
                break;
            case "help": manager.helpCommand.launch((Player) commandSender, strings);
                break;
            default:
                break;
        }
        return false;
    }
}
