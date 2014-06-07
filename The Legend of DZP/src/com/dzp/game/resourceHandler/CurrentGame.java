package com.dzp.game.resourceHandler;

import com.dzp.game.mechanics.EntityManagerThread;
import com.dzp.game.mechanics.Nexus;
import java.io.IOException;
import java.util.Map;

public class CurrentGame {

    private static CurrentGame game;
    private final Nexus nexus;
    private final GameWorld epoch;
    private final GameLevel level;
    private final com.dzp.game.mechanics.Map gameMap;
    private final Map<String, Integer> towerUpgrades;
    public final EntityManagerThread manager;

    public static void createGame(GameWorld e, GameLevel l) throws IOException {
        CurrentGame.game = new CurrentGame(e, l);
    }

    private CurrentGame(GameWorld e, GameLevel l) throws IOException {
        this.epoch = e;
        this.level = l;
        this.nexus = new Nexus();
        this.gameMap = this.level.getMap();
        towerUpgrades = null;
        manager = new EntityManagerThread();
    }

    public Nexus getNexus() {
        return this.nexus;
    }

    public com.dzp.game.mechanics.Map getMap() {
        return this.gameMap;
    }
    
    public GameLevel getLevel() {
        return this.level;
    }

    public GameWorld getEpoch() {
        return this.epoch;
    }

    public static CurrentGame getCurrentGame() {
        return CurrentGame.game;
    }

}
