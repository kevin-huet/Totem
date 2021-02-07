package com.github.ventuss.game.factionsUUID;

import com.github.ventuss.App;
import com.github.ventuss.config.GlobalConfiguration;
import com.github.ventuss.config.MessageType;
import com.github.ventuss.game.IGame;
import com.github.ventuss.game.Status;
import com.github.ventuss.manager.Manager;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UUIDTotem implements IGame {

    private HashMap<Block, FPlayer> history = new HashMap<Block, FPlayer>();
    private List<Block> blocks = new ArrayList<Block>();
    private Faction actualFaction;
    private Material blockMaterial, itemInteract;
    private String name;
    private int size, actualSize, ptsForWin;
    private float timer;
    private Location location;
    private Status status;
    private final GlobalConfiguration globalConfiguration = Manager.getInstance().configManager.globalConfiguration;

    public UUIDTotem(String name) {
        this.status = Status.IN_WAITING;
        this.timer = -1;
        this.size = 5;
        this.actualSize = size;
        this.ptsForWin = 20;
        this.itemInteract = Material.DIAMOND_SWORD;
        this.blockMaterial = Material.QUARTZ_BLOCK;
        this.name = name;
    }

    @Override
    public void spawn() {
        for (Block b : this.blocks) {
            b.setType(this.blockMaterial);
        }
        this.status = Status.STARTED;
        this.actualSize = size;
    }

    @Override
    public void stop() {
        for (Block b : this.blocks) {
            b.setType(Material.AIR);
        }
        this.status = Status.FINISHED;
    }

    @Override
    public void reset() {
        this.actualSize = this.size;
        this.actualFaction = null;
        for (Block b : this.blocks) {
            b.setType(this.blockMaterial);
        }
        this.history.clear();
        this.status = Status.STARTED;
    }

    @Override
    public void generateTotem() {
        Location tmpLocation = App.utilities.worldUtilities.copyLocation(location);
        double y = this.location.getY();

        this.blocks.clear();
        for (double tmp = 0; tmpLocation.getBlockY() < y + this.size - 1; tmp++) {
            tmpLocation.setY(y + tmp);
            this.addBlock(tmpLocation.getBlock());
        }
    }

    @Override
    public void playerBreakTotemBlock(Player tmp) {
        FPlayer player = FPlayers.getInstance().getByPlayer(tmp);
        if (player.getFaction() != this.actualFaction && this.actualFaction != null) {
            this.actualFaction = null;
            this.actualSize = this.size;
            App.getInstance().getServer().broadcastMessage(
                    ChatColor.GOLD+this.globalConfiguration.getMessageConfig(MessageType.TOTEM_BREAK_CANCEL, this, player.getPlayer())
            );
            generateTotem();
        } else if (player.getFaction() == this.actualFaction) {
            this.actualSize--;
            if (this.actualSize == 0) {
                victory(tmp);
                stop();
                return;
            }
            App.getInstance().getServer().broadcastMessage(
                    ChatColor.GOLD+this.globalConfiguration.getMessageConfig(MessageType.TOTEM_BREAK_BLOCK, this, player.getPlayer())
            );

        } else {
            this.actualFaction = player.getFaction();
            this.actualSize--;
            App.getInstance().getServer().broadcastMessage(
                    ChatColor.GOLD+this.globalConfiguration.getMessageConfig(MessageType.TOTEM_FIRST_BREAK_BLOCK, this, player.getPlayer())
            );
        }
    }


    @Override
    public void victory(Player player) {
        Faction faction = FPlayers.getInstance().getByPlayer(player).getFaction();
        App.getInstance().getServer().broadcastMessage(
                ChatColor.GOLD+this.globalConfiguration.getMessageConfig(MessageType.TOTEM_WIN, this, null)
        );
        this.location.getWorld().playEffect(this.location, Effect.FIREWORKS_SPARK, 1);
        Manager.getInstance().configManager.sqlConfig.addWinner(faction.getTag(), this.ptsForWin);
    }

    public HashMap<Block, FPlayer> getHistory() {
        return history;
    }

    public void setHistory(HashMap<Block, FPlayer> history) {
        this.history = history;
    }

    public void addBreakHistory(Block block, FPlayer player) {
        this.history.put(block, player);
    }

    public Faction getActualFaction() {
        return actualFaction;
    }

    public void setActualFaction(Faction actualFaction) {
        this.actualFaction = actualFaction;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public float getTimer() {
        return timer;
    }

    @Override
    public void setTimer(float timer) {
        this.timer = timer;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Material getBlockMaterial() {
        return this.blockMaterial;
    }


    @Override
    public void setBlockMaterial(Material blockMaterial) {
        this.blockMaterial = blockMaterial;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean timerUsed() {
        return timer != -1;
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    @Override
    public void addBlock(Block block) {
        this.blocks.add(block);
    }

    @Override
    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public int getPtsForWin() {
        return ptsForWin;
    }

    @Override
    public void setPtsForWin(int ptsForWin) {
        this.ptsForWin = ptsForWin;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getActualSize() {
        return actualSize;
    }

    @Override
    public void setActualSize(int actualSize) {
        this.actualSize = actualSize;
    }

    @Override
    public Material getItemInteract() {
        return itemInteract;
    }

    @Override
    public void setItemInteract(Material itemInteract) {
        this.itemInteract = itemInteract;
    }

    @Override
    public String getFactionName() {
        if (this.actualFaction == null)
            return null;
        return this.actualFaction.getTag();
    }
}
