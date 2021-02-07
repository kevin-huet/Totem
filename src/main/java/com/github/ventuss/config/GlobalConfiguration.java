package com.github.ventuss.config;

import com.github.ventuss.App;
import com.github.ventuss.game.IGame;
import com.github.ventuss.game.factions.Totem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GlobalConfiguration extends MessageDefaultValue implements IConfiguration {

    public boolean factionsUUIDEnabled, factionsEnabled, legacyFactionsEnabled = false;
    private FileConfiguration config;

    public GlobalConfiguration() {
        if (Bukkit.getPluginManager().isPluginEnabled("LegacyFactions"))
            this.legacyFactionsEnabled = true;

        if (Bukkit.getPluginManager().isPluginEnabled("MassiveCore") && Bukkit.getPluginManager().isPluginEnabled("Factions"))
            this.factionsEnabled = true;
        else if (Bukkit.getPluginManager().isPluginEnabled("Factions"))
            this.factionsUUIDEnabled = true;
        config = App.getInstance().getConfig();
        defaultConfig();
    }

    @Override
    public void save() {
        App.getInstance().saveConfig();
    }

    @Override
    public void setMessageConfig(MessageType messageType, String message, IGame game) {

    }

    @Override
    public void setMessageConfig(MessageType messageType, String message) {

    }

    public void defaultConfig() {
        if (this.config == null) {
            this.config = App.getInstance().getConfig();
        }
        this.config.options().copyDefaults(true);
        save();
        if (this.config.get(".Totem.totems") == null)
            this.generateDefaultConfig();

    }

    private void generateDefaultConfig() {
        this.config.set(".Totem.totems.test.location", "80;80;80");
        this.config.set(".Totem.totems.test.size", "5");
        this.config.set(".Totem.totems.test.world", "world");
        this.config.set(".Totem.totems.test.material", "QUARTZ_BLOCK");
        this.config.set(".Totem.totems.test.winPtsReward", "20");
        this.config.set(".Totem.totems.test.itemInteract", "DIAMOND_SWORD");
        this.config.set(".Totem.messages.startMessage", TotemSpawn);
        this.config.set(".Totem.messages.stopMessage", "");
        this.config.set(".Totem.messages.winMessage", TotemEnd);
        this.config.set(".Totem.messages.firstBreakMessage", TotemFirstBreak);
        this.config.set(".Totem.messages.breakMessage", TotemBreak);
        this.config.set(".Totem.messages.resetBreakMessage", TotemBreakCancel);
        this.config.set(".Totem.messages.set", TotemSet);
        this.config.set(".Totem.messages.manualSpawn", TotemSpawn);
        this.config.set(".Totem.messages.changeSize", TotemChangeSize);
        this.config.set(".Totem.messages.reset", TotemManualReset);
        this.config.set(".Totem.sql.enable", false);
        this.config.set(".Totem.sql.host", "");
        this.config.set(".Totem.sql.port", "");
        this.config.set(".Totem.sql.database", "");
        this.config.set(".Totem.sql.username", "");
        this.config.set(".Totem.sql.password", "");
        App.getInstance().saveDefaultConfig();
    }

    public String getDefaultMessage(MessageType messageType) {
        switch (messageType) {
            case TOTEM_WIN:
                return TotemEnd;
            case TOTEM_TIMER:
                return TotemTimer;
            case TOTEM_STOP:
                return TotemManualEnd;
            case TOTEM_SPAWN:
                return TotemSpawn;
            case TOTEM_FIRST_BREAK_BLOCK:
                return TotemFirstBreak;
            case TOTEM_BREAK_CANCEL:
                return TotemBreakCancel;
            case TOTEM_BREAK_BLOCK:
                return TotemBreak;
            default:
                return "";
        }
    }

    public String checkMessage(MessageType messageType, IGame totem, String message, Player player) {
        message = message.replaceAll("%FACTION_NAME%", totem.getFactionName());
        message = message.replaceAll("%BLOCK_NUMBER%", ""+totem.getActualSize());
        message = message.replaceAll("%TOTEM_NAME%", totem.getName());
        message = message.replaceAll("%PLAYER_NAME%", (player != null ) ? player.getName() : "");

        return message;
    }

    @Override
    public String getMessageConfig(MessageType messageType, IGame totem, Player player) {
        switch (messageType) {
            case TOTEM_WIN:
                return (this.config.get(".Totem.messages.winMessage") != null) ?
                        checkMessage(messageType, totem, this.config.get(".Totem.messages.winMessage").toString(), player) :
                        getDefaultMessage(messageType);
            case TOTEM_TIMER:
                return (this.config.get(".Totem.messages.timerMessage") != null) ?
                        checkMessage(messageType, totem, this.config.get(".Totem.messages.timerMessage").toString(), player) :
                        getDefaultMessage(messageType);
            case TOTEM_STOP:
                return (this.config.get(".Totem.messages.stopMessage") != null) ?
                        checkMessage(messageType, totem, this.config.get(".Totem.messages.stopMessage").toString(), player) :
                        getDefaultMessage(messageType);
            case TOTEM_SPAWN:
                return (this.config.get(".Totem.messages.startMessage") != null) ?
                        checkMessage(messageType, totem, this.config.get(".Totem.messages.startMessage").toString(), player) :
                        getDefaultMessage(messageType);
            case TOTEM_FIRST_BREAK_BLOCK:
                return (this.config.get(".Totem.messages.firstBreakMessage") != null) ?
                        checkMessage(messageType, totem, this.config.get(".Totem.messages.firstBreakMessage").toString(), player) :
                        getDefaultMessage(messageType);
            case TOTEM_BREAK_CANCEL:
                return (this.config.get(".Totem.messages.resetBreakMessage") != null) ?
                        checkMessage(messageType, totem, this.config.get(".Totem.messages.resetBreakMessage").toString(), player) :
                        getDefaultMessage(messageType);
            case TOTEM_BREAK_BLOCK:
                return (this.config.get(".Totem.messages.breakMessage") != null) ?
                        checkMessage(messageType, totem, this.config.get(".Totem.messages.breakMessage").toString(), player) :
                        getDefaultMessage(messageType);
            default:
                return null;
        }
    }
}
