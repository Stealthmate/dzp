/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dzp.game.mechanics.towers;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 *
 * @author madara1233
 */
public class BasicTower extends Tower{
    
    public BasicTower(
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
    public void upgrade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
