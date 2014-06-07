package com.dzp.game.mechanics;

import android.graphics.Point;
import java.util.ArrayList;

public class Map {

    private static final int width = 21;
    private static final int height = 12;
    private final ArrayList<ArrayList<Tile>> tiles;

    public Map(String[] mapConfig) throws Exception {
        this.tiles = new ArrayList();
        
        System.out.println(mapConfig.length);
        String line = mapConfig[0];
        int x = Integer.parseInt(line.substring(0, line.indexOf(' ')));
        int y = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
        
        for (int i = 0; i <= x - 1; i++) {
            this.tiles.add(new ArrayList<Tile>());
            line = mapConfig[i+1];
            int begin = 0;
            for (int j = 0; j <= y - 1; j++) {
                int state = 0;
                try {
                    state = Integer.parseInt(line.substring(begin, begin + 1));
                } catch (NumberFormatException ex) {
                    System.out.println(begin);
                }
                this.tiles.get(i).add(new Tile(Occupator.forNumber(state), new Point(i, j)));
                begin += 2;
            }
        }
    }
    
    public Tile getTile(int x, int y) {
        return this.tiles.get(x).get(y);
    }
}