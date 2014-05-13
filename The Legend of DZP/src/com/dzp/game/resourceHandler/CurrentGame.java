package com.dzp.game.resourceHandler;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class CurrentGame {
    
    private static CurrentGame game;
    
    private final GameEpoch epoch;
    private final GameLevel level;
    private final Map<String, Integer> towerUpgrades;
    
    public static void createGame(GameEpoch e, GameLevel l, File upgradeList) throws IOException {
        CurrentGame.game = new CurrentGame(e, l, upgradeList);
    }
    
    private CurrentGame(GameEpoch e, GameLevel l, File upgradeList) throws IOException {
        this.epoch = e;
        this.level = l;
        towerUpgrades=null;
    }
    
    
    
    public GameLevel getLevel() {
        return this.level;
    }    
    public GameEpoch getEpoch() {
        return this.epoch;
    }
    public static CurrentGame getCurrentGame() {
        return CurrentGame.game;
    }
    
}