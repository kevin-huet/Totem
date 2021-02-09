package com.github.ventuss.game.towny;

import com.github.ventuss.App;
import com.github.ventuss.config.GlobalConfiguration;
import com.github.ventuss.config.MessageType;
import com.github.ventuss.game.IGame;
import com.github.ventuss.game.Status;
import com.github.ventuss.manager.Manager;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.EconomyException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class TownyTotem implements IGame {

    private Town actualTown;
    private Nation actualNation;

    private List<Block> blocks = new ArrayList<Block>();
    private Material blockMaterial, itemInteract;
    private String name;
    private int size, actualSize, ptsForWin;
    private float timer;
    private Location location;
    private Status status;
    private final GlobalConfiguration globalConfiguration = Manager.getInstance().configManager.globalConfiguration;

    public TownyTotem(String name) {
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
        this.actualTown = null;
        for (Block b : this.blocks) {
            b.setType(this.blockMaterial);
        }
        //this.history.clear();
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
        try {
            playerBreakTotemBlockTowny(tmp);
        } catch (NotRegisteredException e) {
            App.getInstance().getLogger().info(e.getMessage());
        }
    }

    private void playerBreakTotemBlockTowny(Player tmp) throws NotRegisteredException {
        Resident player = TownyUniverse.getInstance().getResident(tmp.getName());
        Town town = null;
        if (player == null)
            return;
        if (player.hasTown())
            town = player.getTown();
        if (town != null && town != this.actualTown && this.actualTown != null) {
            this.actualTown = null;
            this.actualSize = this.size;
            App.getInstance().getServer().broadcastMessage(ChatColor.GOLD+this.globalConfiguration.getMessageConfig(MessageType.TOTEM_BREAK_CANCEL, this, player.getPlayer()));
            generateTotem();
        } else if (town == this.actualTown) {
            this.actualSize--;
            if (this.actualSize == 0) {
                victory(tmp);
                stop();
                return;
            }
            App.getInstance().getServer().broadcastMessage(ChatColor.GOLD+this.globalConfiguration.getMessageConfig(MessageType.TOTEM_BREAK_BLOCK, this, player.getPlayer()));
        } else {
            this.actualTown = town;
            this.actualSize--;
            App.getInstance().getServer().broadcastMessage(ChatColor.GOLD+this.globalConfiguration.getMessageConfig(MessageType.TOTEM_FIRST_BREAK_BLOCK, this, player.getPlayer())
            );
        }
    }

    @Override
    public void victory(Player tmp) {
        try {
            victoryTowny(tmp);
        } catch (TownyException | EconomyException e) {
            App.getInstance().getLogger().info(e.getMessage());
        }
    }

    private void victoryTowny(Player tmp) throws TownyException, EconomyException {
        Resident player = TownyUniverse.getInstance().getResident(tmp.getName());
        Town town = null;
        if (player == null)
            return;
        if (player.hasTown())
            town = player.getTown();
        if (town == null)
            return;
        App.getInstance().getServer().broadcastMessage(
                ChatColor.GOLD+this.globalConfiguration.getMessageConfig(MessageType.TOTEM_WIN, this, null)
        );
        town.depositToBank(player, 50);
        this.location.getWorld().playEffect(this.location, Effect.FIREWORKS_SPARK, 1);
        Manager.getInstance().configManager.sqlConfig.addWinner(town.getTag(), this.ptsForWin);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public float getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Material getBlockMaterial() {
        return this.blockMaterial;
    }

    public void setBlockMaterial(Material blockMaterial) {
        this.blockMaterial = blockMaterial;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean timerUsed() {
        return timer != -1;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void addBlock(Block block) {
        this.blocks.add(block);
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public int getPtsForWin() {
        return ptsForWin;
    }

    public void setPtsForWin(int ptsForWin) {
        this.ptsForWin = ptsForWin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActualSize() {
        return actualSize;
    }

    public void setActualSize(int actualSize) {
        this.actualSize = actualSize;
    }

    public Material getItemInteract() {
        return itemInteract;
    }

    public void setItemInteract(Material itemInteract) {
        this.itemInteract = itemInteract;
    }

    @Override
    public String getFactionName() {
        return null;
    }

    public Town getActualTown() {
        return actualTown;
    }

    public void setActualTown(Town actualTown) {
        this.actualTown = actualTown;
    }

    public Nation getActualNation() {
        return this.actualNation;
    }

    public void setActualNation(Nation actualNation) {
        this.actualNation = actualNation;
    }
}
