package com.dzp.game.resourceHandler;

import java.util.ArrayList;
import android.widget.ImageView;

public class GameEpoch {

    private final ArrayList<GameLevel> levels;
    private ImageView characterSkin;
    private final ImageView towerSkin;

    public GameEpoch(ImageView towerSkin, ArrayList<GameLevel> levels) {
        this.towerSkin = towerSkin;
        this.levels = levels;
    }

    public ImageView getTowerSkin() {
        return this.towerSkin;
    }

    public GameLevel getLevel(int n) {
        return levels.get(n);
    }
}