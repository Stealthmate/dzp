package com.dzp.game.mechanics.towers;


import android.graphics.Bitmap;
import android.graphics.Point;
import java.util.ArrayList;


public class NullTower extends Tower {
    
    public static final ArrayList<String> upgrades = new ArrayList();
    private static final int SpecialMaxHP = 100;
    
    public NullTower(
            Point position,
            int range,
            int level,
            int maxCharge,
            int cost,
            int frequency,
            int damage,
            Bitmap skin) {
        super(position, range, level, maxCharge, maxCharge, cost, frequency, damage, skin);
    }
    
    ///Set parameters
    
    ///Get parameters

    @Override
    public void upgrade() {
        
    }

    @Override
    public void fire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}