package com.github.ventuss.manager.game;

import com.github.ventuss.App;
import com.github.ventuss.game.IGame;
import com.github.ventuss.game.Status;
import com.github.ventuss.game.factions.Totem;
import com.github.ventuss.game.factionsUUID.UUIDTotem;
import com.github.ventuss.game.legacyFactions.LegacyTotem;
import com.github.ventuss.manager.IManager;
import com.github.ventuss.manager.Manager;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.List;

public class TotemManager implements IManager {

    public TotemManager() {

    }

    public IGame findByName(String name) {
        List<IGame> games = Manager.getInstance().gameManager.getGames();

        for (IGame game : games) {
            if (game.getName().equals(name)) {
                return game;
            }
        }
        return null;
    }

    public Totem findTotemByLocation(Location location) {
        List<IGame> games = Manager.getInstance().gameManager.getGames();

        for (IGame game : games) {
            if (App.utilities.worldUtilities.locationEqual(location, (game.getLocation()))) {
                return (Totem) game;
            }
        }
        return null;
    }

    public IGame findTotemByBlock(Block block) {
        List<IGame> games = Manager.getInstance().gameManager.getGames();

        for (IGame game : games) {
            if (game.getBlocks().contains(block)) {
                return game;
            }
        }
        return null;
    }

    public Totem findTotemByMPlayer(MPlayer player) {
        List<IGame> games = Manager.getInstance().gameManager.getGames();

        for (IGame game : games) {
            if (game instanceof Totem && ((Totem) game).getActualFaction().getMPlayers().contains(player)) {
                return (Totem) game;
            }
        }
        return null;
    }

    public IGame findTotemByFaction(Faction faction) {
        List<IGame> games = Manager.getInstance().gameManager.getGames();

        for (IGame game : games) {
            if (((Totem) game).getActualFaction() == faction) {
                return game;
            }
        }
        return null;
    }

    public IGame findTotemByFaction(com.massivecraft.factions.Faction faction) {
        List<IGame> games = Manager.getInstance().gameManager.getGames();

        for (IGame game : games) {
            if (((UUIDTotem) game).getActualFaction() == faction) {
                return game;
            }
        }
        return null;
    }

    public IGame findTotemByFaction(net.redstoneore.legacyfactions.entity.Faction faction) {
        List<IGame> games = Manager.getInstance().gameManager.getGames();

        for (IGame game : games) {
            if (((LegacyTotem) game).getActualFaction() == faction) {
                return game;
            }
        }
        return null;
    }

    public Totem findTotemByStatus(Status status) {
        List<IGame> games = Manager.getInstance().gameManager.getGames();

        for (IGame game : games) {
            if (game.getStatus() == status) {
                return (Totem) game;
            }
        }
        return null;
    }

    public Totem findTotemBySize(int size) {
        List<IGame> games = Manager.getInstance().gameManager.getGames();

        for (IGame game : games) {
            if (game.getSize() == size) {
                return (Totem) game;
            }
        }
        return null;
    }

}
