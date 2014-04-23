package com.dzp.game.resourceHandler;

import java.net.URL;
import java.util.Map;
import android.widget.ImageView;

public class GameLevel {

    public static Map<GameLevel, String> levels;
    private final com.dzp.game.mechanics.Map mapConfig;

    public GameLevel(URL mapImage, URL mapConfig) throws Exception {
        
        this.mapConfig = new com.dzp.game.mechanics.Map(mapConfig);
        
        //ImageView m = new ImageView(mapImage)
    }

    public com.dzp.game.mechanics.Map getMap () {
        return this.mapConfig;
    }
    
    public ImageView getMapImage() {
        return null;
    }
}