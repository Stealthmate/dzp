package com.dzp.game.mechanics.towers;

import android.graphics.Bitmap;
import android.graphics.Point;

public class SlowTower extends Tower {

    public SlowTower(
            Point position,
            int range,
            int level,
            int cost,
            int maxCharge,
            int frequency,
            int damage,
            Bitmap skin) {
        super(position, range, level, maxCharge, cost, frequency, damage, skin);
    }

    @Override
    public void upgrade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
