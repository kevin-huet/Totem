package com.github.ventuss.manager.game;

import com.github.ventuss.App;
import com.github.ventuss.game.IGame;
import com.github.ventuss.game.Status;
import com.github.ventuss.game.factions.Totem;
import com.github.ventuss.game.factionsUUID.UUIDTotem;
import com.github.ventuss.game.legacyFactions.LegacyTotem;
import com.github.ventuss.game.towny.TownyTotem;
import com.github.ventuss.manager.IManager;
import com.github.ventuss.manager.Manager;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.List;

public class TotemManager implements IManager {

    public TotemManager() {

    }

    public IGame findByName(String name) {
        List<IGame> games = Manager.getInstance().gameManager.getGames();

        for (IGame game : games) {
            App.getInstance().getServer().broadcastMessage(game.getName());
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

    public IGame findTotemByStatus(Status status) {
        List<IGame> games = Manager.getInstance().gameManager.getGames();

        for (IGame game : games) {
            if (game.getStatus() == status) {
                return game;
            }
        }
        return null;
    }

    public IGame findTotemBySize(int size) {
        List<IGame> games = Manager.getInstance().gameManager.getGames();

        for (IGame game : games) {
            if (game.getSize() == size) {
                return game;
            }
        }
        return null;
    }

    public IGame findTotemByTown(Town town) {
        List<IGame> games = Manager.getInstance().gameManager.getGames();

        for (IGame game : games) {
            if (((TownyTotem) game).getActualTown() == town) {
                return game;
            }
        }
        return null;
    }

    public IGame findTotemByNation(Nation nation) {
        List<IGame> games = Manager.getInstance().gameManager.getGames();

        for (IGame game : games) {
            if (((TownyTotem) game).getActualNation() == nation) {
                return game;
            }
        }
        return null;
    }
}
