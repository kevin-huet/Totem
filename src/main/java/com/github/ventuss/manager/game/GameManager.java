package com.github.ventuss.manager.game;

import com.github.ventuss.App;
import com.github.ventuss.game.IGame;
import com.github.ventuss.game.factions.Totem;
import com.github.ventuss.game.legacyFactions.LegacyTotem;
import com.github.ventuss.manager.IManager;
import com.github.ventuss.manager.Manager;

import java.util.ArrayList;
import java.util.List;

public class GameManager implements IManager {

    private List<IGame> games = new ArrayList<IGame>();

    public GameManager() {
        initGameFromConfigFile();
    }

    public void initGameFromConfigFile() {
       List <IGame> totems = Manager.getInstance().configManager.totemConfiguration.getAllTotem();
       if (totems == null)
           return;
       games.addAll(totems);
    }

    public IGame startNewTotem() {
        return null;
    }

    public List<IGame> getGames() {
        return games;
    }

    public void addGame(IGame game) {
        this.games.add(game);
        App.getInstance().getLogger().info("game added : "+game.getName());
    }

    public void setGames(List<IGame> games) {
        this.games = games;
    }
}
