package com.github.ventuss.config;

import com.github.ventuss.game.IGame;
import org.bukkit.entity.Player;

public interface IConfiguration {

    void save();
    void setMessageConfig(MessageType messageType, String message, IGame game);
    void setMessageConfig(MessageType messageType, String message);
    String getMessageConfig(MessageType messageType, IGame game, Player player);

}
