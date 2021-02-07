package com.github.ventuss.game.towny;

import com.github.ventuss.game.IGame;
import com.github.ventuss.game.Status;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

public class TownyTotem implements IGame {

    private Town actualTown;
    private Nation actualNation;

    public TownyTotem(String name) {
    }

    @Override
    public void spawn() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void generateTotem() {

    }

    @Override
    public void playerBreakTotemBlock(Player player) {

    }

    @Override
    public void victory(Player player) {

    }

    @Override
    public List<Block> getBlocks() {
        return null;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public Material getItemInteract() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void setSize(int size) {

    }

    @Override
    public float getTimer() {
        return 0;
    }

    @Override
    public void setTimer(float timer) {

    }

    @Override
    public void setLocation(Location location) {

    }

    @Override
    public Material getBlockMaterial() {
        return null;
    }

    @Override
    public void setBlockMaterial(Material blockMaterial) {

    }

    @Override
    public Status getStatus() {
        return null;
    }

    @Override
    public void setStatus(Status status) {

    }

    @Override
    public boolean timerUsed() {
        return false;
    }

    @Override
    public void addBlock(Block block) {

    }

    @Override
    public void setBlocks(List<Block> blocks) {

    }

    @Override
    public int getPtsForWin() {
        return 0;
    }

    @Override
    public void setPtsForWin(int ptsForWin) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public int getActualSize() {
        return 0;
    }

    @Override
    public void setActualSize(int actualSize) {

    }

    @Override
    public void setItemInteract(Material itemInteract) {

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
