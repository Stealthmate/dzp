package com.dzp.game.resourceHandler;

import android.graphics.Bitmap;
import android.widget.ImageView;
import java.util.ArrayList;

public class GameLevel {

    private final com.dzp.game.mechanics.Map mapConfig;

    public GameLevel(Bitmap mapImage, String[] mapConfig) throws Exception {
        
        this.mapConfig = new com.dzp.game.mechanics.Map(mapConfig);
        
    }

    public com.dzp.game.mechanics.Map getMap () {
        return this.mapConfig;
    }
    
    public ImageView getMapImage() {
        return null;
    }
}