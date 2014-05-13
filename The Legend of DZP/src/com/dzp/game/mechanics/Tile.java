package com.dzp.game.mechanics;

import com.dzp.game.Dimension;
import android.graphics.Point;

public class Tile {

    public static final Dimension size = new Dimension(16, 16);
    private Occupator occupator;
    private final Point mapCoordinates;

    public Tile(Occupator o, Point coord) {
        setOccupator(o);
        this.mapCoordinates = coord;
    }

    public Occupator getOccupator() {
        return this.occupator;
    }

    ///Set parameters
    private void setOccupator(Occupator o) {
        this.occupator = o;
    }
}