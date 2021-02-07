package com.github.ventuss.game;

import com.github.ventuss.App;
import com.github.ventuss.config.MessageType;
import com.github.ventuss.manager.Manager;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public interface IGame {

    void spawn();

    void stop();

    void reset();

    void generateTotem();

    void playerBreakTotemBlock(Player player);

    void victory(Player player);

    List<Block> getBlocks();

    Location getLocation();

    Material getItemInteract();


    //HashMap<Block, MPlayer> getHistory();
    //oid setHistory(HashMap<Block, MPlayer> history);
    //void addBreakHistory(Block block, MPlayer player);
    //Faction getActualFaction();
    //void setActualFaction(Faction actualFaction);
    int getSize();
    void setSize(int size);
    float getTimer();
    void setTimer(float timer);
    void setLocation(Location location);
    Material getBlockMaterial();
    void setBlockMaterial(Material blockMaterial);
    Status getStatus();
    void setStatus(Status status);
    boolean timerUsed();
    void addBlock(Block block);
    void setBlocks(List<Block> blocks);
    int getPtsForWin();
    void setPtsForWin(int ptsForWin);
    String getName();
    void setName(String name);
    int getActualSize();
    void setActualSize(int actualSize);
    void setItemInteract(Material itemInteract);
    String getFactionName();
}
