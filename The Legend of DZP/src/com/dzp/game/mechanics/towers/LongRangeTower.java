package com.dzp.game.mechanics.towers;

import android.graphics.Bitmap;
import android.graphics.Point;

public class LongRangeTower extends Tower {

    public LongRangeTower(
            Point position,
            int range,
            int level,
            int maxCharge,
            int cost,
            int frequency,
            int damage,
            Bitmap skin) {
        super(position, range, level, maxCharge, cost, frequency, damage, skin);
    }
    
    @Override
    public void fire() {
        if(hasInRange());
    }
    
    @Override
    public void upgrade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}