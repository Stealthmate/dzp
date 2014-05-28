package com.dzp.game.resourceHandler;

import android.graphics.Bitmap;
import android.widget.ImageView;
import java.util.ArrayList;

public class GameWorld {

    private final ArrayList<GameLevel> levels;
    private Bitmap characterSkin;
    private final Bitmap towerSkin;

    public GameWorld(Bitmap towerSkin, ArrayList<GameLevel> levels) {
        this.towerSkin = towerSkin;
        this.levels = levels;
    }

    public Bitmap getTowerSkin() {
        return this.towerSkin;
    }

    public GameLevel getLevel(int n) {
        return levels.get(n);
    }
}