/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dzp.game.mechanics.mobs;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 *
 * @author madara1233
 */
public class FastMob extends Mob{

    public FastMob(Point position, int maxHP, int HP, int speed, Bitmap skin) {
        super(position, HP, maxHP, speed, skin);
    }
    
    @Override
    protected void act() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void drawAct() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
