package com.dzp.game.mechanics;

import android.graphics.Point;
import com.dzp.game.Dimension;
import com.dzp.game.Rectangle;
import com.dzp.game.mechanics.towers.Tower;

public class Tile {

    public static final Dimension size = new Dimension(16, 16);
    private Occupator occupator;
    private final Point mapCoordinates;
    private Tower occupatorTower;

    public Tile(Occupator o, Point coord, Tower occupatorTower) {
        setOccupator(o, occupatorTower);
        this.mapCoordinates = coord;
    }

    public Occupator getOccupator() {
        return this.occupator;
    }
    public Tower getOccupatorTower() {
        return this.occupatorTower;
    }

    ///Set parameters
    private void setOccupator(Occupator o, Tower occupatorTower) {
        this.occupator = o;
        if(occupatorTower!=Tower.noTower) this.occupatorTower = occupatorTower;
    }
    
}